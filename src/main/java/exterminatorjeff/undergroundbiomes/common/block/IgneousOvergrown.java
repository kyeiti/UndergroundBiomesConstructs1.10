package exterminatorjeff.undergroundbiomes.common.block;

import com.google.common.base.Predicate;
import exterminatorjeff.undergroundbiomes.api.enums.UBStoneStyle;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;

import static exterminatorjeff.undergroundbiomes.api.enums.IgneousVariant.*;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

/**
 * @author CurtisA, LouisDB
 */
public class IgneousOvergrown extends IgneousStone implements IGrowable {
  public static final String internal_name = "igneous_overgrown";

  public IgneousOvergrown() {
    super();
    setDefaultState(blockState.getBaseState().withProperty(IGNEOUS_VARIANT_PROPERTY, RED_GRANITE));
  }

  @Override
  public BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, IGNEOUS_VARIANT_PROPERTY);
  }

  @Override
  public String getInternalName() {
    return internal_name;
  }

  @Override
  public UBStoneStyle getStoneStyle() {
    return UBStoneStyle.OVERGROWN;
  }

  @Override
  public boolean isReplaceableOreGen(IBlockState state, IBlockAccess world, BlockPos pos,
      Predicate<IBlockState> target) {
    return false;
  }

  @Override
  public BlockRenderLayer getRenderLayer() {
    return BlockRenderLayer.CUTOUT_MIPPED;
  }

  @Override
  public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction,
      IPlantable plantable) {
    return true;
  }

  @Override
  public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
    return true;
  }

  @Override
  public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
    return true;
  }

  @Override // Taken from Vanilla's BlockGrass
  public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
    BlockPos blockpos = pos.up();

    for (int i = 0; i < 128; ++i) {
      BlockPos blockpos1 = blockpos;
      int j = 0;

      while (true) {
        if (j >= i / 16) {
          if (worldIn.getBlockState(blockpos1).getMaterial() == Material.AIR) {
            if (rand.nextInt(8) == 0) {
              BlockFlower.EnumFlowerType blockflower$enumflowertype = worldIn.getBiome(blockpos1).pickRandomFlower(rand,
                  blockpos1);
              BlockFlower blockflower = blockflower$enumflowertype.getBlockType().getBlock();
              IBlockState iblockstate = blockflower.getDefaultState().withProperty(blockflower.getTypeProperty(),
                  blockflower$enumflowertype);
              if (blockflower.canBlockStay(worldIn, blockpos1, iblockstate)) {
                worldIn.setBlockState(blockpos1, iblockstate, 3);
              }
            } else {
              IBlockState iblockstate1 = Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE,
                  BlockTallGrass.EnumType.GRASS);
              if (Blocks.TALLGRASS.canBlockStay(worldIn, blockpos1, iblockstate1)) {
                worldIn.setBlockState(blockpos1, iblockstate1, 3);
              }
            }
          }
          break;
        }

        blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2,
            rand.nextInt(3) - 1);
        if (worldIn.getBlockState(blockpos1.down()).getBlock() != Blocks.GRASS
            || worldIn.getBlockState(blockpos1).isNormalCube()) {
          break;
        }

        ++j;
      }
    }
  }
}
