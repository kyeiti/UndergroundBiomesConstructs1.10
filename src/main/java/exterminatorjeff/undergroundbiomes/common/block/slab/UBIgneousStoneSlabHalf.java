package exterminatorjeff.undergroundbiomes.common.block.slab;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import exterminatorjeff.undergroundbiomes.api.API;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * @author CurtisA, LouisDB
 */
public class UBIgneousStoneSlabHalf extends UBIgneousStoneSlab {

  @Override
  public boolean isDouble() {
    return false;
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return API.IGNEOUS_STONE_SLAB.getItem();
  }

  @Override
  public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
    int meta = state.getBlock().getMetaFromState(state);
    ItemStack itemStack = new ItemStack(API.IGNEOUS_STONE_SLAB.getItem(), 1, meta >= 8 ? meta - 8 : meta);
    List<ItemStack> result = new ArrayList<ItemStack>();
    result.add(itemStack);
    return result;
  }

  @Override
  public void getDrops(NonNullList<ItemStack> stacks, IBlockAccess world, BlockPos pos, IBlockState state,
      int fortune) {
    int meta = state.getBlock().getMetaFromState(state);
    ItemStack itemStack = new ItemStack(API.IGNEOUS_STONE_SLAB.getItem(), 1, meta >= 8 ? meta - 8 : meta);
    stacks.add(itemStack);
  }
}
