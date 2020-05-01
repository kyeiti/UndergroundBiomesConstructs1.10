package exterminatorjeff.undergroundbiomes.common.block;

import com.google.common.base.Predicate;
import exterminatorjeff.undergroundbiomes.api.enums.UBStoneStyle;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import static exterminatorjeff.undergroundbiomes.api.enums.IgneousVariant.*;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

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
    // Note: EnumPlantType will be changed at runtime by other mods using a Forge
    // functionality.
    // switch() does NOT work with enums in that case, but will crash when
    // encountering
    // a value not known beforehand.

    // support desert, plains and cave plants
    if (plantable.getPlantType(world, pos) == EnumPlantType.Desert || plantable.getPlantType(world, pos) == EnumPlantType.Plains || plantable.getPlantType(world, pos) == EnumPlantType.Cave) {
      return true;
    }
    // support beach plants if there's water alongside
    if (plantable.getPlantType(world, pos) == EnumPlantType.Beach) {
      return (world.getBlockState(pos.east()).getMaterial() == Material.WATER
          || world.getBlockState(pos.west()).getMaterial() == Material.WATER
          || world.getBlockState(pos.north()).getMaterial() == Material.WATER
          || world.getBlockState(pos.south()).getMaterial() == Material.WATER);
    }
    // don't support nether plants, water plants, or crops (require farmland), or
    // anything else by default
    return false;
  }
}
