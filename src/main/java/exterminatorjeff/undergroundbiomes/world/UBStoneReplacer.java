/*
 */

package exterminatorjeff.undergroundbiomes.world;

import exterminatorjeff.undergroundbiomes.api.*;
import exterminatorjeff.undergroundbiomes.api.enums.UBStoneStyle;
import exterminatorjeff.undergroundbiomes.common.block.UBStone;
import exterminatorjeff.undergroundbiomes.config.UBConfig;
import exterminatorjeff.undergroundbiomes.intermod.OresRegistry;
import exterminatorjeff.undergroundbiomes.intermod.StonesRegistry;
import exterminatorjeff.undergroundbiomes.world.noise.NoiseGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.block.properties.PropertyBool;
import vazkii.quark.world.block.BlockSpeleothem;
import java.util.ArrayList;
import java.util.HashMap;
import exterminatorjeff.undergroundbiomes.common.block.*;
import exterminatorjeff.undergroundbiomes.api.enums.UBStoneType;
import exterminatorjeff.undergroundbiomes.api.API;
import net.minecraftforge.fml.common.Loader;

public abstract class UBStoneReplacer implements UBStrataColumnProvider {

  final UBBiome[] biomeList;
  final NoiseGenerator noiseGenerator;

  public UBStoneReplacer(UBBiome[] biomeList, NoiseGenerator noiseGenerator) {
    this.biomeList = biomeList;
    this.noiseGenerator = noiseGenerator;
    if (biomeList == null)
      throw new RuntimeException();
    if (noiseGenerator == null)
      throw new RuntimeException();
  }

  public abstract int[] getBiomeValues(Chunk chunk);

  @SuppressWarnings("deprecation")
  public void replaceStoneInChunk(Chunk chunk) {
    boolean quarkpresent = Loader.isModLoaded("quark");
    int[] biomeValues = getBiomeValues(chunk);
    int xPos = chunk.getPos().x * 16;
    int zPos = chunk.getPos().z * 16;
    // TimeTracker.manager.start("overall");

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
            if (currentBiome == null)
              throw new RuntimeException("" + biomeValues[x * 16 + z]);
            //
            // Perlin noise for strata layers height variation
            int variation = (int) (noiseGenerator.noise((xPos + x) / 55.533, (zPos + z) / 55.533, 3, 1, 0.5) * 10 - 5);
            for (int y = 0; y < 16; ++y) {
              IBlockState currentBlockState = storage.get(x, y, z);
              Block currentBlock = currentBlockState.getBlock();
              BlockPos currentBlockPos = new BlockPos(x, y, z);
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
              } else if (currentBlock == Blocks.MONSTER_EGG && API.SETTINGS.replaceMonsterStone()) {
                // Replace with UBified version
                IBlockState strata = currentBiome.getStrataBlockAtLayer(yPos + y + variation);
                if (strata.getBlock() instanceof UBStone) {
                  UBStone block = (UBStone) strata.getBlock();
                  storage.set(x, y, z,
                      (StonesRegistry.INSTANCE.stoneFor(block.getStoneType(), UBStoneStyle.MONSTER_STONE).getBlock())
                          .getStateFromMeta(block.getMetaFromState(strata)));
                }
                continue;
              } else if (currentBlock == Blocks.COBBLESTONE && API.SETTINGS.replaceCobblestone()) {
                // Replace with UBified version
                IBlockState strata = currentBiome.getStrataBlockAtLayer(yPos + y + variation);
                if (strata.getBlock() instanceof UBStone) {
                  UBStone block = (UBStone) strata.getBlock();
                  storage.set(x, y, z,
                      (StonesRegistry.INSTANCE.stoneFor(block.getStoneType(), UBStoneStyle.COBBLE).getBlock())
                          .getStateFromMeta(block.getMetaFromState(strata)));
                }
                continue;
              } else if (currentBlock.getRegistryName().toString().equals("biomesoplenty:grass")
                  && currentBlockState.getProperties().toString().contains("=overgrown_stone")
                  && API.SETTINGS.replaceOvergrown()) {
                // Replace with UBified version. Not the best way to test the block... But at
                // least does not require an API. Needs to be re-written
                boolean snowy = currentBlockState.getValue(PropertyBool.create("snowy"));
                IBlockState strata = currentBiome.getStrataBlockAtLayer(yPos + y + variation);
                if (strata.getBlock() instanceof UBStone) {
                  UBStone block = (UBStone) strata.getBlock();
                  if (!snowy)
                    storage.set(x, y, z,
                        (StonesRegistry.INSTANCE.stoneFor(block.getStoneType(), UBStoneStyle.OVERGROWN).getBlock())
                            .getStateFromMeta(block.getMetaFromState(strata)));
                  else
                    storage.set(x, y, z,
                        (StonesRegistry.INSTANCE.stoneFor(block.getStoneType(), UBStoneStyle.OVERGROWN_SNOWED)
                            .getBlock()).getStateFromMeta(block.getMetaFromState(strata)));
                }
                continue;
              } else if (currentBlock == Blocks.MOSSY_COBBLESTONE && API.SETTINGS.replaceMossyCobblestone()) {
                // Replace with UBified version
                IBlockState strata = currentBiome.getStrataBlockAtLayer(yPos + y + variation);
                if (strata.getBlock() instanceof UBStone) {
                  UBStone block = (UBStone) strata.getBlock();
                  storage.set(x, y, z,
                      (StonesRegistry.INSTANCE.stoneFor(block.getStoneType(), UBStoneStyle.MOSSY_COBBLE).getBlock())
                          .getStateFromMeta(block.getMetaFromState(strata)));
                }
                continue;
              } else if (currentBlock == Blocks.GRAVEL && API.SETTINGS.replaceGravel()) {
                if (!API.SETTINGS.replaceGravelExcludedBiomes().contains(chunk
                    .getBiome(currentBlockPos, chunk.getWorld().getBiomeProvider()).getRegistryName().toString())) {
                  // Replace with UBified version
                  IBlockState strata = currentBiome.getStrataBlockAtLayer(yPos + y + variation);
                  if (strata.getBlock() instanceof UBStone) {
                    UBStone block = (UBStone) strata.getBlock();
                    storage.set(x, y, z,
                        (StonesRegistry.INSTANCE.stoneFor(block.getStoneType(), UBStoneStyle.GRAVEL).getBlock())
                            .getStateFromMeta(block.getMetaFromState(strata)));
                  }
                }
                continue;
              } else if (currentBlock == Blocks.SAND && API.SETTINGS.replaceSand()
                  && currentBlockState.getProperties().get(BlockSand.VARIANT) != BlockSand.EnumType.RED_SAND) {
                if (!API.SETTINGS.replaceSandExcludedBiomes().contains(chunk
                    .getBiome(currentBlockPos, chunk.getWorld().getBiomeProvider()).getRegistryName().toString())) {
                  // Replace with UBified version
                  IBlockState strata = currentBiome.getStrataBlockAtLayer(yPos + y + variation);
                  if (strata.getBlock() instanceof UBStone) {
                    UBStone block = (UBStone) strata.getBlock();
                    storage.set(x, y, z,
                        (StonesRegistry.INSTANCE.stoneFor(block.getStoneType(), UBStoneStyle.SAND).getBlock())
                            .getStateFromMeta(block.getMetaFromState(strata)));
                  }
                }
                continue;
              } else if (currentBlock == Blocks.CLAY && API.SETTINGS.replaceClay()) {
                // Replace with UBified version
                IBlockState strata = currentBiome.getStrataBlockAtLayer(yPos + y + variation);
                if (strata.getBlock() instanceof UBStone) {
                  UBStone block = (UBStone) strata.getBlock();
                  storage.set(x, y, z,
                      (StonesRegistry.INSTANCE.stoneFor(block.getStoneType(), UBStoneStyle.CLAY).getBlock())
                          .getStateFromMeta(block.getMetaFromState(strata)));
                }
                continue;
              } else if (currentBlock == Blocks.SANDSTONE && API.SETTINGS.replaceSandstone() && currentBlockState.getProperties().get(BlockSandStone.TYPE) == BlockSandStone.EnumType.DEFAULT) {
                // Replace with UBified version
                IBlockState strata = currentBiome.getStrataBlockAtLayer(yPos + y + variation);
                if (strata.getBlock() instanceof UBStone) {
                  UBStone block = (UBStone) strata.getBlock();
                  storage.set(x, y, z,
                      (StonesRegistry.INSTANCE.stoneFor(block.getStoneType(), UBStoneStyle.SANDSTONE).getBlock())
                          .getStateFromMeta(block.getMetaFromState(strata)));
                }
                continue;
              } else if (currentBlock == Blocks.SANDSTONE && API.SETTINGS.replaceSandstone() && currentBlockState.getProperties().get(BlockSandStone.TYPE) == BlockSandStone.EnumType.SMOOTH) {
                // Replace with UBified version
                IBlockState strata = currentBiome.getStrataBlockAtLayer(yPos + y + variation);
                if (strata.getBlock() instanceof UBStone) {
                  UBStone block = (UBStone) strata.getBlock();
                  storage.set(x, y, z,
                      (StonesRegistry.INSTANCE.stoneFor(block.getStoneType(), UBStoneStyle.SMOOTH_SANDSTONE).getBlock())
                          .getStateFromMeta(block.getMetaFromState(strata)));
                }
                continue;
              } else if (quarkpresent && API.SETTINGS.replaceSpeleothems() && currentBlock instanceof BlockSpeleothem) {
                // Replace with UBified version
                IBlockState strata = currentBiome.getStrataBlockAtLayer(yPos + y + variation);
                if (strata.getBlock() instanceof UBStone) {
                  UBStone block = (UBStone) strata.getBlock();
                  if (block.getStoneType() == UBStoneType.IGNEOUS)
                    storage.set(x, y, z,
                        (StonesRegistry.INSTANCE.stoneFor(block.getStoneType(), UBStoneStyle.SPELEOTHEM).getBlock())
                            .getStateFromMeta(block.getMetaFromState(strata))
                            .withProperty(IgneousSpeleothem.SIZE, IgneousSpeleothem.EnumSize.values()[Math.max(0,
                                currentBlockState.getValue(BlockSpeleothem.SIZE).ordinal())]));
                  if (block.getStoneType() == UBStoneType.METAMORPHIC)
                    storage.set(x, y, z,
                        (StonesRegistry.INSTANCE.stoneFor(block.getStoneType(), UBStoneStyle.SPELEOTHEM).getBlock())
                            .getStateFromMeta(block.getMetaFromState(strata))
                            .withProperty(MetamorphicSpeleothem.SIZE, MetamorphicSpeleothem.EnumSize.values()[Math
                                .max(0, currentBlockState.getValue(BlockSpeleothem.SIZE).ordinal())]));
                  if (block.getStoneType() == UBStoneType.SEDIMENTARY)
                    storage.set(x, y, z,
                        (StonesRegistry.INSTANCE.stoneFor(block.getStoneType(), UBStoneStyle.SPELEOTHEM).getBlock())
                            .getStateFromMeta(block.getMetaFromState(strata))
                            .withProperty(SedimentarySpeleothem.SIZE, SedimentarySpeleothem.EnumSize.values()[Math
                                .max(0, currentBlockState.getValue(BlockSpeleothem.SIZE).ordinal())]));
                }
                continue;
              } else {
                /*
                 * Ore
                 */
                // TimeTracker.manager.start("ore");
                IBlockState strata = currentBiome.getStrataBlockAtLayer(yPos + y + variation);
                Block strataBlock = strata.getBlock();
                if (!(strataBlock instanceof UBStone)) {
                  strata = currentBiome.filler;
                  strataBlock = strata.getBlock();
                }
                if (OresRegistry.INSTANCE.isUBified(strataBlock, currentBlockState)) {
                  if (strataBlock instanceof UBStone) {
                    UBStone stone = ((UBStone) strataBlock);
                    IBlockState ore = OresRegistry.INSTANCE.getUBifiedOre(stone, stone.getMetaFromState(strata),
                        currentBlockState);
                    storage.set(x, y, z, ore);
                  }
                }
                // TimeTracker.manager.stop("ore");
              }
            }
          }
        }
      }
    }
    // TimeTracker.manager.stop("overall");
  }

  abstract public UBBiome UBBiomeAt(int x, int z);

  public void redoOres(World world) {
    HashMap<ChunkPos, ArrayList<BlockPos>> toRedo = OresRegistry.INSTANCE.forRedo(world);
    for (ChunkPos chunkID : toRedo.keySet()) {
      ArrayList<BlockPos> locations = toRedo.get(chunkID);
      Chunk chunk = world.getChunk(chunkID.x, chunkID.z);
      int[] biomeValues = getBiomeValues(chunk);
      for (BlockPos location : locations) {
        IBlockState currentBlockState = chunk.getBlockState(location);
        UBBiome currentBiome = biomeList[biomeValues[(location.getX() & 15) * 16 + location.getZ() & 15]];
        int variation = (int) (noiseGenerator.noise((location.getX()) / 55.533, (location.getZ()) / 55.533, 3, 1, 0.5)
            * 10 - 5);
        IBlockState strata = currentBiome.getStrataBlockAtLayer(location.getY() + variation);
        Block strataBlock = strata.getBlock();
        if (!(strataBlock instanceof UBStone)) {
          strata = currentBiome.filler;
          strataBlock = strata.getBlock();
        }
        if (OresRegistry.INSTANCE.isUBified(strataBlock, currentBlockState)) {
          if (strataBlock instanceof UBStone) {
            UBStone stone = (UBStone) strataBlock;
            IBlockState ore = OresRegistry.INSTANCE.getUBifiedOre(stone, stone.getMetaFromState(strata),
                currentBlockState);
            chunk.setBlockState(location, ore);
          }
        }
      }
    }
  }

  @SuppressWarnings("deprecation")
  private UBStrataColumn strataColumn(final StrataLayer[] strata, final IBlockState fillerBlockCodes,
      final int variation) {
    return new UBStrataColumn() {

      public IBlockState stone(int y) {
        if (y >= UBConfig.SPECIFIC.generationHeight())
          return Blocks.STONE.getDefaultState();
        for (int i = 0; i < strata.length; i++) {
          if (strata[i].heightInLayer(y + variation) == true) {
            return strata[i].filler;
          }
        }
        return fillerBlockCodes;
      }

      public IBlockState cobblestone(int height) {
        if (height >= UBConfig.SPECIFIC.generationHeight())
          return Blocks.COBBLESTONE.getDefaultState();
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
