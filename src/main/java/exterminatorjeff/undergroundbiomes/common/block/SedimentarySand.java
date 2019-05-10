package exterminatorjeff.undergroundbiomes.common.block;

import com.google.common.base.Predicate;
import exterminatorjeff.undergroundbiomes.api.enums.UBStoneStyle;
import exterminatorjeff.undergroundbiomes.intermod.DropsRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author CurtisA, LouisDB
 */
public class SedimentarySand extends SedimentaryStone {
  public static final String internal_name = "sedimentary_sand";

  public SedimentarySand() {
    super();
    setSoundType(SoundType.SAND);
  }

  @Override
  public String getInternalName() {
    return internal_name;
  }

  @Override
  public UBStoneStyle getStoneStyle() {
    return UBStoneStyle.SAND;
  }

  @Override
  public Material getMaterial(IBlockState state) {
    return Material.SAND;
  }

  @Override
  public boolean isReplaceableOreGen(IBlockState state, IBlockAccess world, BlockPos pos,
      Predicate<IBlockState> target) {
    return false;
  }

  // Taken from Vanilla's BlockFalling
  public static boolean fallInstantly;

  @Override
  public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
    worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
  }

  @Override
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos p_189540_5_) {
    worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
  }

  @Override
  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    if (!worldIn.isRemote) {
      this.checkFallable(worldIn, pos);
    }
  }

  private void checkFallable(World worldIn, BlockPos pos) {
    if (canFallThrough(worldIn.getBlockState(pos.down())) && pos.getY() >= 0) {
      int i = 32;
      if (!fallInstantly && worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32))) {
        if (!worldIn.isRemote) {
          EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, (double) pos.getX() + 0.5D,
              (double) pos.getY(), (double) pos.getZ() + 0.5D, worldIn.getBlockState(pos));
          this.onStartFalling(entityfallingblock);
          worldIn.spawnEntity(entityfallingblock);
        }
      } else {
        worldIn.setBlockToAir(pos);

        BlockPos blockpos;
        for (blockpos = pos.down(); canFallThrough(worldIn.getBlockState(blockpos))
            && blockpos.getY() > 0; blockpos = blockpos.down()) {
          ;
        }

        if (blockpos.getY() > 0) {
          worldIn.setBlockState(blockpos.up(), this.getDefaultState());
        }
      }
    }
  }

  protected void onStartFalling(EntityFallingBlock fallingEntity) {
  }

  @Override
  public int tickRate(World worldIn) {
    return 2;
  }

  public static boolean canFallThrough(IBlockState state) {
    Block block = state.getBlock();
    Material material = state.getMaterial();
    return block == Blocks.FIRE || material == Material.AIR || material == Material.WATER || material == Material.LAVA;
  }

  public void onEndFalling(World worldIn, BlockPos pos, IBlockState p_176502_3_, IBlockState p_176502_4_) {
  }

  @Override
  public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    if (rand.nextInt(16) == 0) {
      BlockPos blockpos = pos.down();
      if (canFallThrough(worldIn.getBlockState(blockpos))) {
        double d0 = (double) ((float) pos.getX() + rand.nextFloat());
        double d1 = (double) pos.getY() - 0.05D;
        double d2 = (double) ((float) pos.getZ() + rand.nextFloat());
        worldIn.spawnParticle(EnumParticleTypes.FALLING_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D, Block.getStateId(stateIn));
      }
    }
  }

  public int getDustColor(IBlockState p_189876_1_) {
    return -16777216;
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    if (fortune > 3) {
      fortune = 3;
    }
    return rand.nextInt(10 - fortune * 3) == 0 ? Items.FLINT : itemBlock;
  }

  @Override
  public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
    Item gravelBlock = this.getItemDropped(state, new Random(), fortune);
    ItemStack itemStack = null;
    if (gravelBlock == itemBlock) {
      int meta = state.getBlock().getMetaFromState(state);
      itemStack = new ItemStack(gravelBlock, 1, meta);
    } else {
      itemStack = new ItemStack(gravelBlock, 1);
    }
    List<ItemStack> result = new ArrayList<ItemStack>();
    result.add(itemStack);
    DropsRegistry.INSTANCE.addDrops(result, this, world, pos, state, fortune);
    return result;
  }

  @Override
  public void getDrops(NonNullList<ItemStack> stacks, IBlockAccess world, BlockPos pos, IBlockState state,
      int fortune) {
    Item gravelBlock = this.getItemDropped(state, new Random(), fortune);
    ItemStack itemStack = null;
    if (gravelBlock == itemBlock) {
      int meta = state.getBlock().getMetaFromState(state);
      itemStack = new ItemStack(gravelBlock, 1, meta);
    } else {
      itemStack = new ItemStack(gravelBlock, 1);
    }
    List<ItemStack> result = new ArrayList<ItemStack>();
    result.add(itemStack);
    DropsRegistry.INSTANCE.addDrops(result, this, world, pos, state, fortune);
  }

  @Override
  public String getHarvestTool(IBlockState state) {
    return "shovel";
  }

  @Override
  public float getBlockHardness(IBlockState state, World worldIn, BlockPos pos) {
    return Blocks.GRAVEL.getBlockHardness(state, worldIn, pos);
  }

  @Override
  public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction,
      IPlantable plantable) {
    EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));
    if (plantType == EnumPlantType.Desert || plantType == EnumPlantType.Beach) {
      return true;
    } else
      return false;
  }
}
