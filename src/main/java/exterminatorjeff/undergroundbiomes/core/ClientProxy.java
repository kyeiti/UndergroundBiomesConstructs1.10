package exterminatorjeff.undergroundbiomes.core;

import exterminatorjeff.undergroundbiomes.api.API;
import exterminatorjeff.undergroundbiomes.client.UBCreativeTab;
import exterminatorjeff.undergroundbiomes.client.UBOreModelLoader;
import exterminatorjeff.undergroundbiomes.client.UBStateMappers;
import exterminatorjeff.undergroundbiomes.common.block.UBOre;
import exterminatorjeff.undergroundbiomes.config.UBConfig;
import exterminatorjeff.undergroundbiomes.intermod.OresRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.Loader;

/**
 * @author CurtisA, LouisDB
 */
public final class ClientProxy extends CommonProxy {

  @Override
  public void preInit(FMLPreInitializationEvent e) {
    super.preInit(e);

    MinecraftForge.EVENT_BUS.register(OresRegistry.INSTANCE);
    ModelLoaderRegistry.registerLoader(new UBOreModelLoader());
  }

  @Override
  public void init(FMLInitializationEvent e) {
    super.init(e);

    colorizeOres();
    UBCreativeTab.UB_BLOCKS_TAB.setTabIconItem(API.IGNEOUS_STONE.getItemBlock());
    UBCreativeTab.UB_ITEMS_TAB.setTabIconItem(API.LIGNITE_COAL.getItem());
    UBCreativeTab.UB_ORES_TAB.setTabIconItem(OresRegistry.INSTANCE.getUBOresTabIcon());
  }

  @Override
  public void postInit(FMLPostInitializationEvent e) {
    super.postInit(e);

  }

  public void registerModels(ModelRegistryEvent event) {
    registerBlocksModels();
    registerItemsModels();
    OresRegistry.INSTANCE.registerOreModels();
  };

  private final void registerBlocksModels() {
    // Stones
    API.IGNEOUS_STONE.registerModel();
    API.IGNEOUS_MONSTER_STONE.registerModel();
    API.IGNEOUS_COBBLE.registerModel();
    API.IGNEOUS_BRICK.registerModel();
    API.IGNEOUS_MOSSY_COBBLE.registerModel();
    API.IGNEOUS_OVERGROWN.registerModel();
    API.IGNEOUS_OVERGROWN_SNOWED.registerModel();
    API.METAMORPHIC_STONE.registerModel();
    API.METAMORPHIC_MONSTER_STONE.registerModel();
    API.METAMORPHIC_COBBLE.registerModel();
    API.METAMORPHIC_BRICK.registerModel();
    API.METAMORPHIC_OVERGROWN.registerModel();
    API.METAMORPHIC_OVERGROWN_SNOWED.registerModel();
    API.METAMORPHIC_MOSSY_COBBLE.registerModel();
    API.IGNEOUS_MOSSY_COBBLE.registerModel();
    API.SEDIMENTARY_STONE.registerModel();
    API.SEDIMENTARY_MONSTER_STONE.registerModel();
    API.SEDIMENTARY_OVERGROWN.registerModel();
    API.SEDIMENTARY_OVERGROWN_SNOWED.registerModel();
    API.SEDIMENTARY_MOSSY_COBBLE.registerModel();

    API.IGNEOUS_GRAVEL.registerModel();
    API.METAMORPHIC_GRAVEL.registerModel();
    API.SEDIMENTARY_GRAVEL.registerModel();

    API.IGNEOUS_SAND.registerModel();
    API.METAMORPHIC_SAND.registerModel();
    API.SEDIMENTARY_SAND.registerModel();

    API.IGNEOUS_SANDSTONE.registerModel();
    API.METAMORPHIC_SANDSTONE.registerModel();
    API.SEDIMENTARY_SANDSTONE.registerModel();

    API.IGNEOUS_SMOOTH_SANDSTONE.registerModel();
    API.METAMORPHIC_SMOOTH_SANDSTONE.registerModel();
    API.SEDIMENTARY_SMOOTH_SANDSTONE.registerModel();

    API.IGNEOUS_CHISELED_SANDSTONE.registerModel();
    API.METAMORPHIC_CHISELED_SANDSTONE.registerModel();
    API.SEDIMENTARY_CHISELED_SANDSTONE.registerModel();

    API.IGNEOUS_CLAY.registerModel();
    API.METAMORPHIC_CLAY.registerModel();
    API.SEDIMENTARY_CLAY.registerModel();

    if (Loader.isModLoaded("quark")) {
      API.IGNEOUS_SPELEOTHEM.registerModel();
      API.METAMORPHIC_SPELEOTHEM.registerModel();
      API.SEDIMENTARY_SPELEOTHEM.registerModel();
    }
    // Slabs
    if (!UBConfig.SPECIFIC.alternativeSlabTextures()) {
      API.IGNEOUS_STONE_SLAB.registerModel(UBStateMappers.UBSLAB_STATE_MAPPER);
      API.METAMORPHIC_STONE_SLAB.registerModel(UBStateMappers.UBSLAB_STATE_MAPPER);
    } else {
      API.IGNEOUS_STONE_SLAB.registerModel();
      API.METAMORPHIC_STONE_SLAB.registerModel();
    }
    API.SEDIMENTARY_STONE_SLAB.registerModel();

    API.IGNEOUS_COBBLE_SLAB.registerModel();
    API.METAMORPHIC_COBBLE_SLAB.registerModel();
    API.IGNEOUS_BRICK_SLAB.registerModel();
    API.METAMORPHIC_BRICK_SLAB.registerModel();
    // Buttons
    API.IGNEOUS_STONE_BUTTON.registerModel(UBStateMappers.UBBUTTON_STATE_MAPPER);
    API.IGNEOUS_COBBLE_BUTTON.registerModel(UBStateMappers.UBBUTTON_STATE_MAPPER);
    API.IGNEOUS_BRICK_BUTTON.registerModel(UBStateMappers.UBBUTTON_STATE_MAPPER);
    API.METAMORPHIC_STONE_BUTTON.registerModel(UBStateMappers.UBBUTTON_STATE_MAPPER);
    API.METAMORPHIC_COBBLE_BUTTON.registerModel(UBStateMappers.UBBUTTON_STATE_MAPPER);
    API.METAMORPHIC_BRICK_BUTTON.registerModel(UBStateMappers.UBBUTTON_STATE_MAPPER);
    API.SEDIMENTARY_STONE_BUTTON.registerModel(UBStateMappers.UBBUTTON_STATE_MAPPER);
    // Walls
    API.IGNEOUS_STONE_WALL.registerModel();
    API.IGNEOUS_COBBLE_WALL.registerModel();
    API.IGNEOUS_BRICK_WALL.registerModel();
    API.METAMORPHIC_STONE_WALL.registerModel();
    API.METAMORPHIC_COBBLE_WALL.registerModel();
    API.METAMORPHIC_BRICK_WALL.registerModel();
    API.SEDIMENTARY_STONE_WALL.registerModel();
    // Stairs
    API.IGNEOUS_STONE_STAIRS.registerModel(UBStateMappers.UBSTAIRS_STATE_MAPPER);
    API.IGNEOUS_COBBLE_STAIRS.registerModel(UBStateMappers.UBSTAIRS_STATE_MAPPER);
    API.IGNEOUS_BRICK_STAIRS.registerModel(UBStateMappers.UBSTAIRS_STATE_MAPPER);
    API.METAMORPHIC_STONE_STAIRS.registerModel(UBStateMappers.UBSTAIRS_STATE_MAPPER);
    API.METAMORPHIC_COBBLE_STAIRS.registerModel(UBStateMappers.UBSTAIRS_STATE_MAPPER);
    API.METAMORPHIC_BRICK_STAIRS.registerModel(UBStateMappers.UBSTAIRS_STATE_MAPPER);
    API.SEDIMENTARY_STONE_STAIRS.registerModel(UBStateMappers.UBSTAIRS_STATE_MAPPER);
  }

  private final void registerItemsModels() {
    API.LIGNITE_COAL.registerModel();
    API.FOSSIL_PIECE.registerModel();
  }

  public void colorizeOres() {
    BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
    ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
    for (UBOre ore : API.REGISTERED_ORES) {
      if (ore.config.getColor() != null) {
        int color = Integer.decode(ore.config.getColor());
        blockColors.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> color, ore);
        itemColors.registerItemColorHandler((stack, tintIndex) -> color, Item.getItemFromBlock(ore));
      }
    }
    blockColors.registerBlockColorHandler(new IBlockColor() {
      @Override
      public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
        return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos)
            : ColorizerGrass.getGrassColor(0.5D, 1.0D);
      }
    }, API.IGNEOUS_OVERGROWN.getBlock());
    blockColors.registerBlockColorHandler(new IBlockColor() {
      @Override
      public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
        return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos)
            : ColorizerGrass.getGrassColor(0.5D, 1.0D);
      }
    }, API.METAMORPHIC_OVERGROWN.getBlock());
    blockColors.registerBlockColorHandler(new IBlockColor() {
      @Override
      public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
        return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos)
            : ColorizerGrass.getGrassColor(0.5D, 1.0D);
      }
    }, API.SEDIMENTARY_OVERGROWN.getBlock());
  }
}
