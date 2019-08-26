package exterminatorjeff.undergroundbiomes.common.block;

import com.google.common.base.Predicate;

import exterminatorjeff.undergroundbiomes.api.enums.UBStoneStyle;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * @author CurtisA, LouisDB
 */
public class SedimentarySandstone extends SedimentaryStone {
  public static final String internal_name = "sedimentary_sandstone";

  public SedimentarySandstone() {
    super();
  }

  @Override
  public String getInternalName() {
    return internal_name;
  }

  @Override
  public UBStoneStyle getStoneStyle() {
    return UBStoneStyle.SANDSTONE;
  }

  @Override
  public boolean isReplaceableOreGen(IBlockState state, IBlockAccess world, BlockPos pos, Predicate<IBlockState> target) {
    return false;
  }
}
