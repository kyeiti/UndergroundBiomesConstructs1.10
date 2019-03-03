package exterminatorjeff.undergroundbiomes.common.block;

import com.google.common.base.Predicate;
import exterminatorjeff.undergroundbiomes.api.enums.UBStoneStyle;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import java.util.Random;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import vazkii.arl.block.BlockMetaVariants.EnumBase;
import static exterminatorjeff.undergroundbiomes.api.enums.SedimentaryVariant.*;
import java.util.List;
import java.util.ArrayList;
import net.minecraft.util.NonNullList;

/**
 * @author CurtisA, LouisDB
 */
public class SedimentarySpeleothem extends SedimentaryStone {
  public static final String internal_name = "sedimentary_speleothem";
  public static PropertyEnum<EnumSize> SIZE = PropertyEnum.create("size", EnumSize.class);

  public SedimentarySpeleothem() {
    super();
    setDefaultState(blockState.getBaseState().withProperty(SIZE, EnumSize.small)
        .withProperty(SEDIMENTARY_VARIANT_PROPERTY, LIMESTONE));
  }

  @Override
  public BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, SIZE, SEDIMENTARY_VARIANT_PROPERTY);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(SEDIMENTARY_VARIANT_PROPERTY).getMetadata();
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(SEDIMENTARY_VARIANT_PROPERTY, SEDIMENTARY_VARIANTS[meta & 7]);
  }

  @Override
  public IBlockState getActualState(final IBlockState state, final IBlockAccess worldIn, final BlockPos pos) {
    EnumSize size = EnumSize.values()[Math.max(0, getBearing(worldIn, pos) - 1)];
    if (isCenter(worldIn, pos))
      size = EnumSize.medium;
    return state.withProperty(SIZE, size);
  }

  @Override
  public String getInternalName() {
    return internal_name;
  }

  @Override
  public UBStoneStyle getStoneStyle() {
    return UBStoneStyle.SPELEOTHEM;
  }

  @Override
  public Block setHardness(float hardness) {
    return super.setHardness(hardness);
  }

  @Override
  public boolean isReplaceableOreGen(IBlockState state, IBlockAccess world, BlockPos pos,
      Predicate<IBlockState> target) {
    return false;
  }

  @Override
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    return getBearing(worldIn, pos) > 0;
  }

  @Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
      ItemStack stack) {
    EnumSize size = EnumSize.values()[Math.max(0, getBearing(worldIn, pos) - 1)];
    worldIn.setBlockState(pos, state.withProperty(SIZE, size));
  }

  @Override
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
    int size = state.getValue(SIZE).strength;
    if (getBearing(worldIn, pos) < size + 1) {
      worldIn.playEvent(2001, pos, Block.getStateId(worldIn.getBlockState(pos)));
      dropBlockAsItem(worldIn, pos, state, 0);
      worldIn.setBlockToAir(pos);
    }
  }

  @Override
  public int quantityDropped(Random random) {
    return 0;
  }

  @Override
  public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
    List<ItemStack> result = new ArrayList<ItemStack>();
    return result;
  }

  @Override
  public void getDrops(NonNullList<ItemStack> stacks, IBlockAccess world, BlockPos pos, IBlockState state,
      int fortune) {
  }

  @Override
  public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
    return true;
  }

  @Override
  public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
    return false;
  }

  private int getBearing(IBlockAccess world, BlockPos pos) {
    return Math.max(getStrength(world, pos.down()), getStrength(world, pos.up()));
  }

  private boolean isCenter(IBlockAccess world, BlockPos pos) {
    return isThis(world, pos.down()) && isThis(world, pos.up());
  }

  private boolean isThis(IBlockAccess world, BlockPos pos) {
    return world.getBlockState(pos).getBlock() instanceof SedimentarySpeleothem;
  }

  private int getStrength(IBlockAccess world, BlockPos pos) {
    IBlockState state = world.getBlockState(pos);
    if (state.getBlock().isFullBlock(state))
      return 3;

    if (state.getPropertyKeys().contains(SIZE))
      return state.getValue(SIZE).strength;

    return 0;
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return state.getValue(SIZE).aabb;
  }

  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
    return getBoundingBox(blockState, worldIn, pos);
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isFullBlock(IBlockState state) {
    return false;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_,
      EnumFacing p_193383_4_) {
    return BlockFaceShape.UNDEFINED;
  }

  @Override
  public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
    return true;
  }

  public static enum EnumSize implements EnumBase {

    small(0, 2), medium(1, 4), big(2, 8);

    private EnumSize(int strength, int width) {
      this.strength = strength;

      float pad = ((float) ((16 - width) / 2) / 16F);
      aabb = new AxisAlignedBB(pad, 0F, pad, 1F - pad, 1F, 1F - pad);
    }

    @Override
    public String getName() {
      return this.name();
    }

    public final int strength;
    public final AxisAlignedBB aabb;
  }
}
