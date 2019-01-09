package exterminatorjeff.undergroundbiomes.common.block;

import com.google.common.base.Predicate;
import exterminatorjeff.undergroundbiomes.api.enums.UBStoneStyle;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.BlockRenderLayer;

/**
 * @author CurtisA, LouisDB
 */
public class SedimentaryOvergrown extends SedimentaryStone {
  public static final String internal_name = "sedimentary_overgrown";

  @Override
  public String getInternalName() {
    return internal_name;
  }

  @Override
  public UBStoneStyle getStoneStyle() {
    return UBStoneStyle.OVERGROWN;
  }

  @Override
  public boolean isReplaceableOreGen(IBlockState state, IBlockAccess world, BlockPos pos, Predicate<IBlockState> target) {
    return false;
  }

  @Override
  public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
}
