package exterminatorjeff.undergroundbiomes.common.block;

import com.google.common.base.Predicate;
import exterminatorjeff.undergroundbiomes.api.enums.UBStoneStyle;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;

/**
 * @author CurtisA, LouisDB
 */
public class MetamorphicOvergrownSnowed extends MetamorphicStone {
  public static final String internal_name = "metamorphic_overgrown_snowed";

  @Override
  public String getInternalName() {
    return internal_name;
  }

  @Override
  public UBStoneStyle getStoneStyle() {
    return UBStoneStyle.OVERGROWN_SNOWED;
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
    EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));
    if (plantable == Blocks.MELON_STEM || plantable == Blocks.PUMPKIN_STEM || plantType == EnumPlantType.Desert
        || plantType == EnumPlantType.Plains || plantType == EnumPlantType.Cave) {
      return true;
    } else
      return false;
  }
}
