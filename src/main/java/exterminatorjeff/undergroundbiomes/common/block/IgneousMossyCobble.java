package exterminatorjeff.undergroundbiomes.common.block;

import com.google.common.base.Predicate;
import exterminatorjeff.undergroundbiomes.api.enums.UBStoneStyle;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.item.Item;
import java.util.Random;
import net.minecraft.util.BlockRenderLayer;

/**
 * @author CurtisA, LouisDB
 */
public class IgneousMossyCobble extends IgneousStone {
  public static final String internal_name = "igneous_cobble_mossy";

  @Override
  public String getInternalName() {
    return internal_name;
  }

  @Override
  public UBStoneStyle getStoneStyle() {
    return UBStoneStyle.MOSSY_COBBLE;
  }

  @Override
  public Block setHardness(float hardness) {
    return super.setHardness(hardness * COBBLE_HARDNESS_MODIFIER);
  }

  @Override
  public boolean isReplaceableOreGen(IBlockState state, IBlockAccess world, BlockPos pos, Predicate<IBlockState> target) {
    return false;
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return super.getItemDropped(state, rand, fortune);
  }

  @Override
  public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
}
