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
import exterminatorjeff.undergroundbiomes.intermod.DropsRegistry;
import net.minecraft.item.ItemStack;
import java.util.List;
import java.util.ArrayList;
import net.minecraft.util.NonNullList;
import exterminatorjeff.undergroundbiomes.api.API;

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
  public boolean isReplaceableOreGen(IBlockState state, IBlockAccess world, BlockPos pos,
      Predicate<IBlockState> target) {
    return false;
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return itemBlock;
  }

  @Override
  public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
    Item mossyCobbleBlock = API.IGNEOUS_MOSSY_COBBLE.getItemBlock();
    int meta = state.getBlock().getMetaFromState(state);
    ItemStack itemStack = new ItemStack(mossyCobbleBlock, 1, meta);
    List<ItemStack> result = new ArrayList<ItemStack>();
    result.add(itemStack);
    DropsRegistry.INSTANCE.addDrops(result, this, world, pos, state, fortune);
    return result;
  }

  @Override
  public void getDrops(NonNullList<ItemStack> stacks, IBlockAccess world, BlockPos pos, IBlockState state,
      int fortune) {
    Item mossyCobbleBlock = API.IGNEOUS_MOSSY_COBBLE.getItemBlock();
    int meta = state.getBlock().getMetaFromState(state);
    ItemStack itemStack = new ItemStack(mossyCobbleBlock, 1, meta);
    stacks.add(itemStack);
    DropsRegistry.INSTANCE.addDrops(stacks, this, world, pos, state, fortune);
  }

  @Override
  public BlockRenderLayer getRenderLayer() {
    return BlockRenderLayer.CUTOUT_MIPPED;
  }
}
