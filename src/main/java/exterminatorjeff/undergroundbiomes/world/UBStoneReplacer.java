/*
 */

package exterminatorjeff.undergroundbiomes.world;

import exterminatorjeff.undergroundbiomes.api.*;
import exterminatorjeff.undergroundbiomes.api.enums.UBStoneStyle;
import exterminatorjeff.undergroundbiomes.common.block.IgneousStone;
import exterminatorjeff.undergroundbiomes.common.block.MetamorphicStone;
import exterminatorjeff.undergroundbiomes.common.block.UBStone;
import exterminatorjeff.undergroundbiomes.config.UBConfig;
import exterminatorjeff.undergroundbiomes.intermod.OresRegistry;
import exterminatorjeff.undergroundbiomes.intermod.StonesRegistry;
import exterminatorjeff.undergroundbiomes.world.noise.NoiseGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.block.properties.PropertyBool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class UBStoneReplacer implements UBStrataColumnProvider {

  final UBBiome[] biomeList;
  final NoiseGenerator noiseGenerator;

  public UBStoneReplacer(UBBiome[] biomeList, NoiseGenerator noiseGenerator) {
    this.biomeList = biomeList;
    this.noiseGenerator = noiseGenerator;
    if (biomeList == null) throw new RuntimeException();
    if (noiseGenerator == null) throw new RuntimeException();
  }

  public abstract int[] getBiomeValues(Chunk chunk);

  public void replaceStoneInChunk(Chunk chunk) {
    int[] biomeValues = getBiomeValues(chunk);
    int xPos = chunk.getPos().x * 16;
    int zPos = chunk.getPos().z * 16;
    //TimeTracker.manager.start("overall");

    // For each storage array
    for (ExtendedBlockStorage storage : chunk.getBlockStorageArray()) {
      if (storage != null && !storage.isEmpty()) {
        int yPos = storage.getYLocation();
        if (yPos >= UBConfig.SPECIFIC.generationHeight())
          return;
        //
        for (int x = 0; x < 16; ++x) {
          for (int z = 0; z < 16; ++z) {
            // Get the underground biome for the position
            UBBiome currentBiome = biomeList[biomeValues[x * 16 + z]];
            if (currentBiome == null) throw new RuntimeException(
              "" + biomeValues[x * 16 + z]);
            //
            // Perlin noise for strata layers height variation
            int variation = (int) (noiseGenerator.noise((xPos + x) / 55.533, (zPos + z) / 55.533, 3, 1, 0.5) * 10 - 5);
            for (int y = 0; y < 16; ++y) {
              IBlockState currentBlockState = storage.get(x, y, z);
              Block currentBlock = currentBlockState.getBlock();
              /*
               * Skip air, water and UBStone
               */
              if (Block.isEqualTo(Blocks.AIR, currentBlock))
                continue;
              if (Block.isEqualTo(Blocks.WATER, currentBlock))
                continue;
              // TODO Test without
              if (currentBlock instanceof UBStone)
                continue;
              /*
               * Stone
               */
              if (currentBlock == Blocks.STONE) {
                // Replace with UBified version
                storage.set(x, y, z, currentBiome.getStrataBlockAtLayer(yPos + y + variation));
              } else if (currentBlock == Blocks.COBBLESTONE && API.SETTINGS.replaceCobblestone())  {
                // Replace with UBified version
                IBlockState strata = currentBiome.getStrataBlockAtLayer(yPos + y + variation);
                if(strata.getBlock() instanceof UBStone) {
                  UBStone block = (UBStone) strata.getBlock();
                  storage.set(x, y, z, (StonesRegistry.INSTANCE.stoneFor(block.getStoneType(), UBStoneStyle.COBBLE).getBlock()).getStateFromMeta(block.getMetaFromState(strata)));
                }
                continue;
              } else if (currentBlock.getRegistryName().toString().equals("biomesoplenty:grass") && currentBlockState.getProperties().toString().contains("=overgrown_stone") && API.SETTINGS.replaceOvergrown())  {
                // Replace with UBified version. Not the best way to test the block... But at least does not require an API 
                boolean snowy = currentBlockState.getValue(PropertyBool.create("snowy"));
                IBlockState strata = currentBiome.getStrataBlockAtLayer(yPos + y + variation);
                if(strata.getBlock() instanceof UBStone) {
                  UBStone block = (UBStone) strata.getBlock();
                  if(!snowy)
                  storage.set(x, y, z, (StonesRegistry.INSTANCE.stoneFor(block.getStoneType(), UBStoneStyle.OVERGROWN).getBlock()).getStateFromMeta(block.getMetaFromState(strata)));
                  else
                  storage.set(x, y, z, (StonesRegistry.INSTANCE.stoneFor(block.getStoneType(), UBStoneStyle.OVERGROWN_SNOWED).getBlock()).getStateFromMeta(block.getMetaFromState(strata)));
                }
                continue;
              } else {
                /*
                 * Ore
                 */
                //TimeTracker.manager.start("ore");
                IBlockState strata = currentBiome.getStrataBlockAtLayer(yPos + y + variation);
                Block strataBlock = strata.getBlock();
                if (!(strataBlock instanceof UBStone)) {
                  strata = currentBiome.filler;
                  strataBlock = strata.getBlock();
                }
                if (OresRegistry.INSTANCE.isUBified(strataBlock, currentBlockState)) {
                  if (strataBlock instanceof UBStone) {
                    UBStone stone = ((UBStone) strataBlock);
                    IBlockState ore = OresRegistry.INSTANCE.getUBifiedOre(stone, stone.getMetaFromState(strata), currentBlockState);
                    storage.set(x, y, z, ore);
                  }
                }
                //TimeTracker.manager.stop("ore");
              }
            }
          }
        }
      }
    }
    //TimeTracker.manager.stop("overall");
  }

  abstract public UBBiome UBBiomeAt(int x, int z);

  public void redoOres(World world) {
    HashMap<ChunkPos, ArrayList<BlockPos>> toRedo = OresRegistry.INSTANCE.forRedo(world);
    for (ChunkPos chunkID : toRedo.keySet()) {
      ArrayList<BlockPos> locations = toRedo.get(chunkID);
      Chunk chunk = world.getChunkFromChunkCoords(chunkID.x, chunkID.z);
      int[] biomeValues = getBiomeValues(chunk);
      for (BlockPos location : locations) {
        IBlockState currentBlockState = chunk.getBlockState(location);
        UBBiome currentBiome = biomeList[biomeValues[(location.getX() & 15) * 16 + location.getZ() & 15]];
        int variation = (int) (noiseGenerator.noise((location.getX()) / 55.533, (location.getZ()) / 55.533, 3, 1, 0.5) * 10 - 5);
        IBlockState strata = currentBiome.getStrataBlockAtLayer(location.getY() + variation);
        Block strataBlock = strata.getBlock();
        if (!(strataBlock instanceof UBStone)) {
          strata = currentBiome.filler;
          strataBlock = strata.getBlock();
        }
        if (OresRegistry.INSTANCE.isUBified(strataBlock, currentBlockState)) {
          if (strataBlock instanceof UBStone) {
            UBStone stone = (UBStone) strataBlock;
            IBlockState ore = OresRegistry.INSTANCE.getUBifiedOre(stone, stone.getMetaFromState(strata), currentBlockState);
            chunk.setBlockState(location, ore);
          }
        }
      }
    }
  }

  private UBStrataColumn strataColumn(
    final StrataLayer[] strata,
    final IBlockState fillerBlockCodes,
    final int variation) {
    return new UBStrataColumn() {


      public IBlockState stone(int y) {
        if (y >= UBConfig.SPECIFIC.generationHeight()) return Blocks.STONE.getDefaultState();
        for (int i = 0; i < strata.length; i++) {
          if (strata[i].heightInLayer(y + variation) == true) {
            return strata[i].filler;
          }
        }
        return fillerBlockCodes;
      }

      public IBlockState cobblestone(int height) {
        if (height >= UBConfig.SPECIFIC.generationHeight()) return Blocks.COBBLESTONE.getDefaultState();
        IBlockState stone = stone(height);
        if (stone.getBlock() == API.IGNEOUS_STONE.getBlock()) {
          return API.IGNEOUS_COBBLE.getBlock().getStateFromMeta(stone.getBlock().getMetaFromState(stone));
        }
        if (stone.getBlock() == API.METAMORPHIC_STONE.getBlock()) {
          return API.METAMORPHIC_COBBLE.getBlock().getStateFromMeta(stone.getBlock().getMetaFromState(stone));
        }
        return stone;
      }

      public IBlockState cobblestone() {
        IBlockState stone = stone();
        if (stone.getBlock() == API.IGNEOUS_STONE.getBlock()) {
          return API.IGNEOUS_COBBLE.getBlock().getStateFromMeta(stone.getBlock().getMetaFromState(stone));
        }
        if (stone.getBlock() == API.METAMORPHIC_STONE.getBlock()) {
          return API.METAMORPHIC_COBBLE.getBlock().getStateFromMeta(stone.getBlock().getMetaFromState(stone));
        }
        return stone;
      }

      public IBlockState stone() {
        return fillerBlockCodes;
      }
    };
  }

  public UBStrataColumn strataColumn(int x, int z) {
    // make sure we have the right chunk
    UBBiome biome = UBBiomeAt(x, z);
    int variation = (int) (noiseGenerator.noise((x) / 55.533, (z) / 55.533, 3, 1, 0.5) * 10 - 5);
    return strataColumn(biome.strata, biome.filler, variation);
  }
}
