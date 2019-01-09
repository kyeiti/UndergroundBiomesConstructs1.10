package exterminatorjeff.undergroundbiomes.common.block;

import com.google.common.base.Predicate;
import exterminatorjeff.undergroundbiomes.api.enums.UBStoneStyle;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.BlockRenderLayer;
import static exterminatorjeff.undergroundbiomes.api.enums.IgneousVariant.*;

/**
 * @author CurtisA, LouisDB
 */
public class IgneousOvergrown extends IgneousStone {
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
  public boolean isReplaceableOreGen(IBlockState state, IBlockAccess world, BlockPos pos, Predicate<IBlockState> target) {
    return false;
  }

  @Override
  public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
}
