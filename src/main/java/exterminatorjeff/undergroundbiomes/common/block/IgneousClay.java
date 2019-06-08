package exterminatorjeff.undergroundbiomes.common.block;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;
import exterminatorjeff.undergroundbiomes.api.enums.UBStoneStyle;
import exterminatorjeff.undergroundbiomes.intermod.DropsRegistry;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
/**
 * @author Aang23
 */
public class IgneousClay extends IgneousStone {
  public static final String internal_name = "igneous_clay";

  public IgneousClay() {
    super();
    setSoundType(SoundType.GROUND);
  }

  @Override
  public String getInternalName() {
    return internal_name;
  }

  @Override
  public UBStoneStyle getStoneStyle() {
    return UBStoneStyle.CLAY;
  }

  @Override
  public Material getMaterial(IBlockState state) {
    return Material.CLAY;
  }

  @Override
  public boolean isReplaceableOreGen(IBlockState state, IBlockAccess world, BlockPos pos,
      Predicate<IBlockState> target) {
    return false;
  }

  @Override
  public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
    ItemStack itemStack = new ItemStack(Items.CLAY_BALL, 4);
    List<ItemStack> result = new ArrayList<ItemStack>();
    result.add(itemStack);
    DropsRegistry.INSTANCE.addDrops(result, this, world, pos, state, fortune);
    return result;
  }

  @Override
  public void getDrops(NonNullList<ItemStack> stacks, IBlockAccess world, BlockPos pos, IBlockState state,
      int fortune) {
    ItemStack itemStack = new ItemStack(Items.CLAY_BALL, 4);
    stacks.add(itemStack);
    DropsRegistry.INSTANCE.addDrops(stacks, this, world, pos, state, fortune);
  }

  @Override
  public String getHarvestTool(IBlockState state) {
    return "shovel";
  }

  @Override
  public float getBlockHardness(IBlockState state, World worldIn, BlockPos pos) {
    return Blocks.CLAY.getBlockHardness(state, worldIn, pos);
  }
}
