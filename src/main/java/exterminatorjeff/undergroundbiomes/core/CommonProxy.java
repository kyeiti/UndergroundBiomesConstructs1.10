package exterminatorjeff.undergroundbiomes.core;

import java.io.File;
import java.util.ArrayList;

import com.ferreusveritas.dynamictrees.api.TreeRegistry;

import org.apache.logging.log4j.Level;

import exterminatorjeff.undergroundbiomes.api.API;
import exterminatorjeff.undergroundbiomes.api.common.UBLogger;
import exterminatorjeff.undergroundbiomes.api.enums.IgneousVariant;
import exterminatorjeff.undergroundbiomes.api.enums.MetamorphicVariant;
import exterminatorjeff.undergroundbiomes.api.enums.SedimentaryVariant;
import exterminatorjeff.undergroundbiomes.common.ButtonRecipe;
import exterminatorjeff.undergroundbiomes.common.RegularStoneRecipe;
import exterminatorjeff.undergroundbiomes.common.UBFuelHandler;
import exterminatorjeff.undergroundbiomes.common.block.IgneousBrick;
import exterminatorjeff.undergroundbiomes.common.block.IgneousClay;
import exterminatorjeff.undergroundbiomes.common.block.IgneousCobble;
import exterminatorjeff.undergroundbiomes.common.block.IgneousGravel;
import exterminatorjeff.undergroundbiomes.common.block.IgneousMonsterStone;
import exterminatorjeff.undergroundbiomes.common.block.IgneousMossyCobble;
import exterminatorjeff.undergroundbiomes.common.block.IgneousOvergrown;
import exterminatorjeff.undergroundbiomes.common.block.IgneousOvergrownSnowed;
import exterminatorjeff.undergroundbiomes.common.block.IgneousSand;
import exterminatorjeff.undergroundbiomes.common.block.IgneousSandstone;
import exterminatorjeff.undergroundbiomes.common.block.IgneousSandstoneChiseled;
import exterminatorjeff.undergroundbiomes.common.block.IgneousSandstoneSmooth;
import exterminatorjeff.undergroundbiomes.common.block.IgneousSpeleothem;
import exterminatorjeff.undergroundbiomes.common.block.IgneousStone;
import exterminatorjeff.undergroundbiomes.common.block.MetamorphicBrick;
import exterminatorjeff.undergroundbiomes.common.block.MetamorphicClay;
import exterminatorjeff.undergroundbiomes.common.block.MetamorphicCobble;
import exterminatorjeff.undergroundbiomes.common.block.MetamorphicGravel;
import exterminatorjeff.undergroundbiomes.common.block.MetamorphicMonsterStone;
import exterminatorjeff.undergroundbiomes.common.block.MetamorphicMossyCobble;
import exterminatorjeff.undergroundbiomes.common.block.MetamorphicOvergrown;
import exterminatorjeff.undergroundbiomes.common.block.MetamorphicOvergrownSnowed;
import exterminatorjeff.undergroundbiomes.common.block.MetamorphicSand;
import exterminatorjeff.undergroundbiomes.common.block.MetamorphicSandstone;
import exterminatorjeff.undergroundbiomes.common.block.MetamorphicSandstoneChiseled;
import exterminatorjeff.undergroundbiomes.common.block.MetamorphicSandstoneSmooth;
import exterminatorjeff.undergroundbiomes.common.block.MetamorphicSpeleothem;
import exterminatorjeff.undergroundbiomes.common.block.MetamorphicStone;
import exterminatorjeff.undergroundbiomes.common.block.SedimentaryClay;
import exterminatorjeff.undergroundbiomes.common.block.SedimentaryGravel;
import exterminatorjeff.undergroundbiomes.common.block.SedimentaryMonsterStone;
import exterminatorjeff.undergroundbiomes.common.block.SedimentaryMossyCobble;
import exterminatorjeff.undergroundbiomes.common.block.SedimentaryOvergrown;
import exterminatorjeff.undergroundbiomes.common.block.SedimentaryOvergrownSnowed;
import exterminatorjeff.undergroundbiomes.common.block.SedimentarySand;
import exterminatorjeff.undergroundbiomes.common.block.SedimentarySandstone;
import exterminatorjeff.undergroundbiomes.common.block.SedimentarySandstoneChiseled;
import exterminatorjeff.undergroundbiomes.common.block.SedimentarySandstoneSmooth;
import exterminatorjeff.undergroundbiomes.common.block.SedimentarySpeleothem;
import exterminatorjeff.undergroundbiomes.common.block.SedimentaryStone;
import exterminatorjeff.undergroundbiomes.common.block.button.UBButtonIgneous;
import exterminatorjeff.undergroundbiomes.common.block.button.UBButtonIgneousBrick;
import exterminatorjeff.undergroundbiomes.common.block.button.UBButtonIgneousCobble;
import exterminatorjeff.undergroundbiomes.common.block.button.UBButtonMetamorphic;
import exterminatorjeff.undergroundbiomes.common.block.button.UBButtonMetamorphicBrick;
import exterminatorjeff.undergroundbiomes.common.block.button.UBButtonMetamorphicCobble;
import exterminatorjeff.undergroundbiomes.common.block.button.UBButtonSedimentary;
import exterminatorjeff.undergroundbiomes.common.block.slab.UBIgneousBrickSlabDouble;
import exterminatorjeff.undergroundbiomes.common.block.slab.UBIgneousBrickSlabHalf;
import exterminatorjeff.undergroundbiomes.common.block.slab.UBIgneousCobbleSlabDouble;
import exterminatorjeff.undergroundbiomes.common.block.slab.UBIgneousCobbleSlabHalf;
import exterminatorjeff.undergroundbiomes.common.block.slab.UBIgneousStoneSlabDouble;
import exterminatorjeff.undergroundbiomes.common.block.slab.UBIgneousStoneSlabHalf;
import exterminatorjeff.undergroundbiomes.common.block.slab.UBMetamorphicBrickSlabDouble;
import exterminatorjeff.undergroundbiomes.common.block.slab.UBMetamorphicBrickSlabHalf;
import exterminatorjeff.undergroundbiomes.common.block.slab.UBMetamorphicCobbleSlabDouble;
import exterminatorjeff.undergroundbiomes.common.block.slab.UBMetamorphicCobbleSlabHalf;
import exterminatorjeff.undergroundbiomes.common.block.slab.UBMetamorphicStoneSlabDouble;
import exterminatorjeff.undergroundbiomes.common.block.slab.UBMetamorphicStoneSlabHalf;
import exterminatorjeff.undergroundbiomes.common.block.slab.UBSedimentaryStoneSlabDouble;
import exterminatorjeff.undergroundbiomes.common.block.slab.UBSedimentaryStoneSlabHalf;
import exterminatorjeff.undergroundbiomes.common.block.stairs.UBStairsIgneous;
import exterminatorjeff.undergroundbiomes.common.block.stairs.UBStairsIgneousBrick;
import exterminatorjeff.undergroundbiomes.common.block.stairs.UBStairsIgneousCobble;
import exterminatorjeff.undergroundbiomes.common.block.stairs.UBStairsMetamorphic;
import exterminatorjeff.undergroundbiomes.common.block.stairs.UBStairsMetamorphicBrick;
import exterminatorjeff.undergroundbiomes.common.block.stairs.UBStairsMetamorphicCobble;
import exterminatorjeff.undergroundbiomes.common.block.stairs.UBStairsSedimentary;
import exterminatorjeff.undergroundbiomes.common.block.wall.UBWallIgneous;
import exterminatorjeff.undergroundbiomes.common.block.wall.UBWallIgneousBrick;
import exterminatorjeff.undergroundbiomes.common.block.wall.UBWallIgneousCobble;
import exterminatorjeff.undergroundbiomes.common.block.wall.UBWallMetamorphic;
import exterminatorjeff.undergroundbiomes.common.block.wall.UBWallMetamorphicBrick;
import exterminatorjeff.undergroundbiomes.common.block.wall.UBWallMetamorphicCobble;
import exterminatorjeff.undergroundbiomes.common.block.wall.UBWallSedimentary;
import exterminatorjeff.undergroundbiomes.common.item.ItemFossilPiece;
import exterminatorjeff.undergroundbiomes.common.item.ItemLigniteCoal;
import exterminatorjeff.undergroundbiomes.common.itemblock.ButtonItemBlock;
import exterminatorjeff.undergroundbiomes.common.itemblock.SlabItemBlock;
import exterminatorjeff.undergroundbiomes.common.itemblock.StairsItemBlock;
import exterminatorjeff.undergroundbiomes.config.ConfigManager;
import exterminatorjeff.undergroundbiomes.config.UBConfig;
import exterminatorjeff.undergroundbiomes.intermod.DropsRegistry;
import exterminatorjeff.undergroundbiomes.intermod.ModOreRegistrar;
import exterminatorjeff.undergroundbiomes.intermod.OresRegistry;
import exterminatorjeff.undergroundbiomes.intermod.StonesRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.server.dedicated.PropertyManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

/**
 * @author CurtisA, LouisDB
 */
public class CommonProxy {
  private ConfigManager configManager;
  private DimensionManager dimensionManager;
  protected static final UBLogger LOGGER = new UBLogger(CommonProxy.class, Level.INFO);

  public void preInit(FMLPreInitializationEvent event) {
    LOGGER.debug("Start preInit");
    configManager = new ConfigManager(event);
    dimensionManager = new DimensionManager(configManager);

    API.STONES_REGISTRY = StonesRegistry.INSTANCE;
    API.ORES_REGISTRY = OresRegistry.INSTANCE;
    API.DROPS_REGISTRY = DropsRegistry.INSTANCE;
    API.SETTINGS = UBConfig.SPECIFIC;
    API.MOD_ORE_REGISTRAR = new ModOreRegistrar(event);

    API.STRATA_COLUMN_PROVIDER = dimensionManager;
  }

  @SuppressWarnings("deprecation")
  public void init(FMLInitializationEvent e) {
    if (Loader.isModLoaded("dynamictrees")) {
      // Needs improvements, but works !
      for (Object resou : TreeRegistry.getSpeciesDirectory().toArray()) {
        TreeRegistry.findSpecies(((ResourceLocation) resou).toString())
            .addAcceptableSoil(API.IGNEOUS_OVERGROWN.getBlock());
        TreeRegistry.findSpecies(((ResourceLocation) resou).toString())
            .addAcceptableSoil(API.METAMORPHIC_OVERGROWN.getBlock());
        TreeRegistry.findSpecies(((ResourceLocation) resou).toString())
            .addAcceptableSoil(API.SEDIMENTARY_OVERGROWN.getBlock());
      }
    }

    DropsRegistry.INSTANCE.init();

    addOreDicts();
    GameRegistry.registerFuelHandler(UBFuelHandler.INSTANCE);
  }

  public void postInit(FMLPostInitializationEvent e) {
    MinecraftForge.EVENT_BUS.register(dimensionManager);
    API.ORES_REGISTRY.registerRecipes(null);
  }

  @SuppressWarnings("unused")
  public void serverLoad(FMLServerAboutToStartEvent event) {
    // logger.info("server starting");
    File worldSaveDirectory = null;
    String worldName = event.getServer().getFolderName();
    if (event.getServer().isSinglePlayer()) {
      File saveDirectory = event.getServer().getFile("saves");
      worldSaveDirectory = new File(saveDirectory, worldName);
    } else {
      PropertyManager settings = new PropertyManager(event.getServer().getFile("server.properties"));
      worldName = settings.getStringProperty("level-name", worldName);
      worldSaveDirectory = event.getServer().getFile(worldName);
    }
    try {
      WorldServer server = event.getServer().getWorld(0);
      File worldLocation = server.getChunkSaveLocation();
      // UndergroundBiomes.logger.info(world.toString() + " "
      // +worldLocation.getAbsolutePath());
      configManager.setWorldFile(worldLocation);
    } catch (NullPointerException e) {
      throw e;
    }
    dimensionManager.refreshManagers();
  }

  public void onServerStopped(FMLServerStoppedEvent event) {
    // for some reason onWorldLoad is running before any of the ServerStartxxx
    // events
    // so I'm having to use a clunky workaround.
    dimensionManager.clearWorldManagers();
    OresRegistry.INSTANCE.recheckPile();
    for (Runnable action : serverCloseActions) {
      action.run();
    }
    for (Runnable action : oneShotServerCloseActions) {
      action.run();
    }
    oneShotServerCloseActions.clear();

  }

  public void runOnServerClose(Runnable action) {
    serverCloseActions.add(action);
  }

  public void runOnNextServerCloseOnly(Runnable action) {
    serverCloseActions.add(action);
  }

  private ArrayList<Runnable> oneShotServerCloseActions = new ArrayList<Runnable>();
  private ArrayList<Runnable> serverCloseActions = new ArrayList<Runnable>();

  public void registerModels(ModelRegistryEvent event) {
    // Only used in Client, overwritten there
  }

  public void registerBlocks(RegistryEvent.Register<Block> event) {
    /*
     * Blocks
     */
    // new ActuallyAdditionsRegistrar().register(event);
    // new ForestryRegistrar().register(event);
    // new IC2Registrar().register(event);
    // new ImmersiveEngineeringRegistrar().register(event);
    // new ThermalFoundationRegistrar().register(event);
    API.MOD_ORE_REGISTRAR.requestOreSetups(event);

    LOGGER.debug("Start registering blocks");

    API.IGNEOUS_STONE.registerBlock(event, new IgneousStone());
    API.IGNEOUS_MONSTER_STONE.registerBlock(event, new IgneousMonsterStone());
    API.IGNEOUS_COBBLE.registerBlock(event, new IgneousCobble());
    API.IGNEOUS_BRICK.registerBlock(event, new IgneousBrick());
    API.IGNEOUS_OVERGROWN.registerBlock(event, new IgneousOvergrown());
    API.IGNEOUS_OVERGROWN_SNOWED.registerBlock(event, new IgneousOvergrownSnowed());
    API.IGNEOUS_MOSSY_COBBLE.registerBlock(event, new IgneousMossyCobble());
    API.METAMORPHIC_STONE.registerBlock(event, new MetamorphicStone());
    API.METAMORPHIC_MONSTER_STONE.registerBlock(event, new MetamorphicMonsterStone());
    API.METAMORPHIC_COBBLE.registerBlock(event, new MetamorphicCobble());
    API.METAMORPHIC_BRICK.registerBlock(event, new MetamorphicBrick());
    API.METAMORPHIC_OVERGROWN.registerBlock(event, new MetamorphicOvergrown());
    API.METAMORPHIC_OVERGROWN_SNOWED.registerBlock(event, new MetamorphicOvergrownSnowed());
    API.METAMORPHIC_MOSSY_COBBLE.registerBlock(event, new MetamorphicMossyCobble());
    API.SEDIMENTARY_STONE.registerBlock(event, new SedimentaryStone());
    API.SEDIMENTARY_MONSTER_STONE.registerBlock(event, new SedimentaryMonsterStone());
    API.SEDIMENTARY_OVERGROWN.registerBlock(event, new SedimentaryOvergrown());
    API.SEDIMENTARY_OVERGROWN_SNOWED.registerBlock(event, new SedimentaryOvergrownSnowed());
    API.SEDIMENTARY_MOSSY_COBBLE.registerBlock(event, new SedimentaryMossyCobble());

    API.IGNEOUS_GRAVEL.registerBlock(event, new IgneousGravel());
    API.METAMORPHIC_GRAVEL.registerBlock(event, new MetamorphicGravel());
    API.SEDIMENTARY_GRAVEL.registerBlock(event, new SedimentaryGravel());

    API.IGNEOUS_SAND.registerBlock(event, new IgneousSand());
    API.METAMORPHIC_SAND.registerBlock(event, new MetamorphicSand());
    API.SEDIMENTARY_SAND.registerBlock(event, new SedimentarySand());

    API.IGNEOUS_SANDSTONE.registerBlock(event, new IgneousSandstone());
    API.METAMORPHIC_SANDSTONE.registerBlock(event, new MetamorphicSandstone());
    API.SEDIMENTARY_SANDSTONE.registerBlock(event, new SedimentarySandstone());

    API.IGNEOUS_SMOOTH_SANDSTONE.registerBlock(event, new IgneousSandstoneSmooth());
    API.METAMORPHIC_SMOOTH_SANDSTONE.registerBlock(event, new MetamorphicSandstoneSmooth());
    API.SEDIMENTARY_SMOOTH_SANDSTONE.registerBlock(event, new SedimentarySandstoneSmooth());

    API.IGNEOUS_CHISELED_SANDSTONE.registerBlock(event, new IgneousSandstoneChiseled());
    API.METAMORPHIC_CHISELED_SANDSTONE.registerBlock(event, new MetamorphicSandstoneChiseled());
    API.SEDIMENTARY_CHISELED_SANDSTONE.registerBlock(event, new SedimentarySandstoneChiseled());

    API.IGNEOUS_CLAY.registerBlock(event, new IgneousClay());
    API.METAMORPHIC_CLAY.registerBlock(event, new MetamorphicClay());
    API.SEDIMENTARY_CLAY.registerBlock(event, new SedimentaryClay());

    if (Loader.isModLoaded("quark")) {
      API.IGNEOUS_SPELEOTHEM.registerBlock(event, new IgneousSpeleothem());
      API.METAMORPHIC_SPELEOTHEM.registerBlock(event, new MetamorphicSpeleothem());
      API.SEDIMENTARY_SPELEOTHEM.registerBlock(event, new SedimentarySpeleothem());
    }
    /*
     * Slabs
     */

    API.IGNEOUS_BRICK_SLAB.registerBlock(event,
        new SlabItemBlock(new UBIgneousBrickSlabHalf(), new UBIgneousBrickSlabDouble()));
    API.METAMORPHIC_BRICK_SLAB.registerBlock(event,
        new SlabItemBlock(new UBMetamorphicBrickSlabHalf(), new UBMetamorphicBrickSlabDouble()));
    API.IGNEOUS_STONE_SLAB.registerBlock(event,
        new SlabItemBlock(new UBIgneousStoneSlabHalf(), new UBIgneousStoneSlabDouble()));
    API.METAMORPHIC_STONE_SLAB.registerBlock(event,
        new SlabItemBlock(new UBMetamorphicStoneSlabHalf(), new UBMetamorphicStoneSlabDouble()));
    API.IGNEOUS_COBBLE_SLAB.registerBlock(event,
        new SlabItemBlock(new UBIgneousCobbleSlabHalf(), new UBIgneousCobbleSlabDouble()));
    API.METAMORPHIC_COBBLE_SLAB.registerBlock(event,
        new SlabItemBlock(new UBMetamorphicCobbleSlabHalf(), new UBMetamorphicCobbleSlabDouble()));
    API.SEDIMENTARY_STONE_SLAB.registerBlock(event,
        new SlabItemBlock(new UBSedimentaryStoneSlabHalf(), new UBSedimentaryStoneSlabDouble()));

    /*
     * Buttons
     */

    if (UBConfig.SPECIFIC.buttonsOn()) {
      if (UBConfig.SPECIFIC.igneousButtonsOn()) {
        if (UBConfig.SPECIFIC.stoneButtonsOn()) {
          API.IGNEOUS_STONE_BUTTON.registerBlock(event, new ButtonItemBlock(API.IGNEOUS_STONE, UBButtonIgneous.class));
        }
        if (UBConfig.SPECIFIC.cobbleButtonsOn()) {
          API.IGNEOUS_COBBLE_BUTTON.registerBlock(event,
              new ButtonItemBlock(API.IGNEOUS_COBBLE, UBButtonIgneousCobble.class));
        }
        if (UBConfig.SPECIFIC.brickButtonsOn())
          API.IGNEOUS_BRICK_BUTTON.registerBlock(event,
              new ButtonItemBlock(API.IGNEOUS_BRICK, UBButtonIgneousBrick.class));
      }
      if (UBConfig.SPECIFIC.metamorphicButtonsOn()) {
        if (UBConfig.SPECIFIC.stoneButtonsOn())
          API.METAMORPHIC_STONE_BUTTON.registerBlock(event,
              new ButtonItemBlock(API.METAMORPHIC_STONE, UBButtonMetamorphic.class));
        if (UBConfig.SPECIFIC.cobbleButtonsOn())
          API.METAMORPHIC_COBBLE_BUTTON.registerBlock(event,
              new ButtonItemBlock(API.METAMORPHIC_COBBLE, UBButtonMetamorphicCobble.class));
        if (UBConfig.SPECIFIC.brickButtonsOn())
          API.METAMORPHIC_BRICK_BUTTON.registerBlock(event,
              new ButtonItemBlock(API.METAMORPHIC_BRICK, UBButtonMetamorphicBrick.class));
      }
      if (UBConfig.SPECIFIC.sedimentaryButtonsOn())
        API.SEDIMENTARY_STONE_BUTTON.registerBlock(event,
            new ButtonItemBlock(API.SEDIMENTARY_STONE, UBButtonSedimentary.class));
    }

    /*
     * Walls
     */

    if (UBConfig.SPECIFIC.wallsOn()) {
      if (UBConfig.SPECIFIC.igneousWallsOn()) {
        if (UBConfig.SPECIFIC.stoneWallsOn())
          API.IGNEOUS_STONE_WALL.registerBlock(event, new UBWallIgneous(API.IGNEOUS_STONE));
        if (UBConfig.SPECIFIC.cobbleWallsOn())
          API.IGNEOUS_COBBLE_WALL.registerBlock(event, new UBWallIgneousCobble(API.IGNEOUS_COBBLE));
        if (UBConfig.SPECIFIC.brickWallsOn())
          API.IGNEOUS_BRICK_WALL.registerBlock(event, new UBWallIgneousBrick(API.IGNEOUS_BRICK));
      }
      if (UBConfig.SPECIFIC.metamorphicWallsOn()) {
        if (UBConfig.SPECIFIC.stoneWallsOn())
          API.METAMORPHIC_STONE_WALL.registerBlock(event, new UBWallMetamorphic(API.METAMORPHIC_STONE));
        if (UBConfig.SPECIFIC.cobbleWallsOn())
          API.METAMORPHIC_COBBLE_WALL.registerBlock(event, new UBWallMetamorphicCobble(API.METAMORPHIC_COBBLE));
        if (UBConfig.SPECIFIC.brickWallsOn())
          API.METAMORPHIC_BRICK_WALL.registerBlock(event, new UBWallMetamorphicBrick(API.METAMORPHIC_BRICK));
      }
      if (UBConfig.SPECIFIC.sedimentaryWallsOn())
        API.SEDIMENTARY_STONE_WALL.registerBlock(event, new UBWallSedimentary(API.SEDIMENTARY_STONE));
    }

    /*
     * Stairs
     */

    if (UBConfig.SPECIFIC.stairsOn()) {
      if (UBConfig.SPECIFIC.igneousStairsOn()) {
        if (UBConfig.SPECIFIC.stoneStairsOn())
          API.IGNEOUS_STONE_STAIRS.registerBlock(event, new StairsItemBlock(API.IGNEOUS_STONE, UBStairsIgneous.class));
        if (UBConfig.SPECIFIC.cobbleStairsOn())
          API.IGNEOUS_COBBLE_STAIRS.registerBlock(event,
              new StairsItemBlock(API.IGNEOUS_COBBLE, UBStairsIgneousCobble.class));
        if (UBConfig.SPECIFIC.brickStairsOn())
          API.IGNEOUS_BRICK_STAIRS.registerBlock(event,
              new StairsItemBlock(API.IGNEOUS_BRICK, UBStairsIgneousBrick.class));
      }
      if (UBConfig.SPECIFIC.metamorphicStairsOn()) {
        if (UBConfig.SPECIFIC.stoneStairsOn())
          API.METAMORPHIC_STONE_STAIRS.registerBlock(event,
              new StairsItemBlock(API.METAMORPHIC_STONE, UBStairsMetamorphic.class));
        if (UBConfig.SPECIFIC.cobbleStairsOn())
          API.METAMORPHIC_COBBLE_STAIRS.registerBlock(event,
              new StairsItemBlock(API.METAMORPHIC_COBBLE, UBStairsMetamorphicCobble.class));
        if (UBConfig.SPECIFIC.brickStairsOn())
          API.METAMORPHIC_BRICK_STAIRS.registerBlock(event,
              new StairsItemBlock(API.METAMORPHIC_BRICK, UBStairsMetamorphicBrick.class));
      }
      if (UBConfig.SPECIFIC.sedimentaryStairsOn())
        API.SEDIMENTARY_STONE_STAIRS.registerBlock(event,
            new StairsItemBlock(API.SEDIMENTARY_STONE, UBStairsSedimentary.class));
    }
    OresRegistry.INSTANCE.registerBlocks(event);
  }

  @SubscribeEvent
  public void registerItems(RegistryEvent.Register<Item> event) {

    LOGGER.debug("Start registering items");
    API.LIGNITE_COAL.registerItem(event, new ItemLigniteCoal());
    API.FOSSIL_PIECE.registerItem(event, new ItemFossilPiece());

    /* Blocks */

    IForgeRegistry<Item> registry = event.getRegistry();
    registry.register(API.IGNEOUS_STONE.getItemBlock());
    registry.register(API.METAMORPHIC_STONE.getItemBlock());
    registry.register(API.SEDIMENTARY_STONE.getItemBlock());

    registry.register(API.IGNEOUS_MONSTER_STONE.getItemBlock());
    registry.register(API.METAMORPHIC_MONSTER_STONE.getItemBlock());
    registry.register(API.SEDIMENTARY_MONSTER_STONE.getItemBlock());

    registry.register(API.IGNEOUS_COBBLE.getItemBlock());
    registry.register(API.METAMORPHIC_COBBLE.getItemBlock());

    registry.register(API.IGNEOUS_BRICK.getItemBlock());
    registry.register(API.METAMORPHIC_BRICK.getItemBlock());

    registry.register(API.IGNEOUS_GRAVEL.getItemBlock());
    registry.register(API.METAMORPHIC_GRAVEL.getItemBlock());
    registry.register(API.SEDIMENTARY_GRAVEL.getItemBlock());

    registry.register(API.IGNEOUS_SAND.getItemBlock());
    registry.register(API.METAMORPHIC_SAND.getItemBlock());
    registry.register(API.SEDIMENTARY_SAND.getItemBlock());

    registry.register(API.IGNEOUS_SANDSTONE.getItemBlock());
    registry.register(API.METAMORPHIC_SANDSTONE.getItemBlock());
    registry.register(API.SEDIMENTARY_SANDSTONE.getItemBlock());

    registry.register(API.IGNEOUS_SMOOTH_SANDSTONE.getItemBlock());
    registry.register(API.METAMORPHIC_SMOOTH_SANDSTONE.getItemBlock());
    registry.register(API.SEDIMENTARY_SMOOTH_SANDSTONE.getItemBlock());

    registry.register(API.IGNEOUS_CHISELED_SANDSTONE.getItemBlock());
    registry.register(API.METAMORPHIC_CHISELED_SANDSTONE.getItemBlock());
    registry.register(API.SEDIMENTARY_CHISELED_SANDSTONE.getItemBlock());

    registry.register(API.IGNEOUS_CLAY.getItemBlock());
    registry.register(API.METAMORPHIC_CLAY.getItemBlock());
    registry.register(API.SEDIMENTARY_CLAY.getItemBlock());

    registry.register(API.IGNEOUS_OVERGROWN.getItemBlock());
    registry.register(API.IGNEOUS_OVERGROWN_SNOWED.getItemBlock());
    registry.register(API.METAMORPHIC_OVERGROWN.getItemBlock());
    registry.register(API.METAMORPHIC_OVERGROWN_SNOWED.getItemBlock());
    registry.register(API.SEDIMENTARY_OVERGROWN.getItemBlock());
    registry.register(API.SEDIMENTARY_OVERGROWN_SNOWED.getItemBlock());

    registry.register(API.METAMORPHIC_MOSSY_COBBLE.getItemBlock());
    registry.register(API.IGNEOUS_MOSSY_COBBLE.getItemBlock());
    registry.register(API.SEDIMENTARY_MOSSY_COBBLE.getItemBlock());

    if (Loader.isModLoaded("quark")) {
      registry.register(API.IGNEOUS_SPELEOTHEM.getItemBlock());
      registry.register(API.METAMORPHIC_SPELEOTHEM.getItemBlock());
      registry.register(API.SEDIMENTARY_SPELEOTHEM.getItemBlock());
    }
    /* Slabs */

    API.IGNEOUS_STONE_SLAB.registerItem(event, null);
    API.METAMORPHIC_STONE_SLAB.registerItem(event, null);
    API.IGNEOUS_COBBLE_SLAB.registerItem(event, null);
    API.METAMORPHIC_COBBLE_SLAB.registerItem(event, null);
    API.SEDIMENTARY_STONE_SLAB.registerItem(event, null);
    API.IGNEOUS_BRICK_SLAB.registerItem(event, null);
    API.METAMORPHIC_BRICK_SLAB.registerItem(event, null);

    /*
     * Buttons
     */

    if (UBConfig.SPECIFIC.buttonsOn()) {
      if (UBConfig.SPECIFIC.igneousButtonsOn()) {
        if (UBConfig.SPECIFIC.stoneButtonsOn()) {
          registry.register(API.IGNEOUS_STONE_BUTTON.getItemBlock());
        }
        if (UBConfig.SPECIFIC.cobbleButtonsOn()) {
          registry.register(API.IGNEOUS_COBBLE_BUTTON.getItemBlock());
        }
        if (UBConfig.SPECIFIC.brickButtonsOn())
          registry.register(API.IGNEOUS_BRICK_BUTTON.getItemBlock());
      }
      if (UBConfig.SPECIFIC.metamorphicButtonsOn()) {
        if (UBConfig.SPECIFIC.stoneButtonsOn())
          registry.register(API.METAMORPHIC_STONE_BUTTON.getItemBlock());
        if (UBConfig.SPECIFIC.cobbleButtonsOn())
          registry.register(API.METAMORPHIC_COBBLE_BUTTON.getItemBlock());
        if (UBConfig.SPECIFIC.brickButtonsOn())
          registry.register(API.METAMORPHIC_BRICK_BUTTON.getItemBlock());
      }
      if (UBConfig.SPECIFIC.sedimentaryButtonsOn())
        registry.register(API.SEDIMENTARY_STONE_BUTTON.getItemBlock());
    }

    /*
     * Walls
     */

    if (UBConfig.SPECIFIC.wallsOn()) {
      if (UBConfig.SPECIFIC.igneousWallsOn()) {
        if (UBConfig.SPECIFIC.stoneWallsOn())
          API.IGNEOUS_STONE_WALL.registerItem(event, new UBWallIgneous(API.IGNEOUS_STONE));
        if (UBConfig.SPECIFIC.cobbleWallsOn())
          API.IGNEOUS_COBBLE_WALL.registerItem(event, new UBWallIgneousCobble(API.IGNEOUS_COBBLE));
        if (UBConfig.SPECIFIC.brickWallsOn())
          API.IGNEOUS_BRICK_WALL.registerItem(event, new UBWallIgneousBrick(API.IGNEOUS_BRICK));
      }
      if (UBConfig.SPECIFIC.metamorphicWallsOn()) {
        if (UBConfig.SPECIFIC.stoneWallsOn())
          API.METAMORPHIC_STONE_WALL.registerItem(event, new UBWallMetamorphic(API.METAMORPHIC_STONE));
        if (UBConfig.SPECIFIC.cobbleWallsOn())
          API.METAMORPHIC_COBBLE_WALL.registerItem(event, new UBWallMetamorphicCobble(API.METAMORPHIC_COBBLE));
        if (UBConfig.SPECIFIC.brickWallsOn())
          API.METAMORPHIC_BRICK_WALL.registerItem(event, new UBWallMetamorphicBrick(API.METAMORPHIC_BRICK));
      }
      if (UBConfig.SPECIFIC.sedimentaryWallsOn())
        API.SEDIMENTARY_STONE_WALL.registerItem(event, new UBWallSedimentary(API.SEDIMENTARY_STONE));
    }

    /*
     * Stairs
     */

    if (UBConfig.SPECIFIC.stairsOn()) {
      if (UBConfig.SPECIFIC.igneousStairsOn()) {
        if (UBConfig.SPECIFIC.stoneStairsOn())
          API.IGNEOUS_STONE_STAIRS.registerItem(event, new StairsItemBlock(API.IGNEOUS_STONE, UBStairsIgneous.class));
        if (UBConfig.SPECIFIC.cobbleStairsOn())
          API.IGNEOUS_COBBLE_STAIRS.registerItem(event,
              new StairsItemBlock(API.IGNEOUS_COBBLE, UBStairsIgneousCobble.class));
        if (UBConfig.SPECIFIC.brickStairsOn())
          API.IGNEOUS_BRICK_STAIRS.registerItem(event,
              new StairsItemBlock(API.IGNEOUS_BRICK, UBStairsIgneousBrick.class));
      }
      if (UBConfig.SPECIFIC.metamorphicStairsOn()) {
        if (UBConfig.SPECIFIC.stoneStairsOn())
          API.METAMORPHIC_STONE_STAIRS.registerItem(event,
              new StairsItemBlock(API.METAMORPHIC_STONE, UBStairsMetamorphic.class));
        if (UBConfig.SPECIFIC.cobbleStairsOn())
          API.METAMORPHIC_COBBLE_STAIRS.registerItem(event,
              new StairsItemBlock(API.METAMORPHIC_COBBLE, UBStairsMetamorphicCobble.class));
        if (UBConfig.SPECIFIC.brickStairsOn())
          API.METAMORPHIC_BRICK_STAIRS.registerItem(event,
              new StairsItemBlock(API.METAMORPHIC_BRICK, UBStairsMetamorphicBrick.class));
      }
      if (UBConfig.SPECIFIC.sedimentaryStairsOn())
        API.SEDIMENTARY_STONE_STAIRS.registerItem(event,
            new StairsItemBlock(API.SEDIMENTARY_STONE, UBStairsSedimentary.class));
    }
    OresRegistry.INSTANCE.registerItems(event);
  }

  public void addOreDicts() {
    // wildcarding is not working
    for (int i = 0; i < 8; i++) {
      OreDictionary.registerOre("stone", new ItemStack(API.IGNEOUS_STONE.getItemBlock(), 1, i));
      OreDictionary.registerOre("stone", new ItemStack(API.METAMORPHIC_STONE.getItemBlock(), 1, i));
      OreDictionary.registerOre("stone", new ItemStack(API.SEDIMENTARY_STONE.getItemBlock(), 1, i));
      OreDictionary.registerOre("stoneIgneous", new ItemStack(API.IGNEOUS_STONE.getItemBlock(), 1, i));
      OreDictionary.registerOre("stoneMetamorphic", new ItemStack(API.METAMORPHIC_STONE.getItemBlock(), 1, i));
      OreDictionary.registerOre("stoneSedimentary", new ItemStack(API.SEDIMENTARY_STONE.getItemBlock(), 1, i));
      OreDictionary.registerOre("gravel", new ItemStack(API.IGNEOUS_GRAVEL.getItemBlock(), 1, i));
      OreDictionary.registerOre("gravel", new ItemStack(API.METAMORPHIC_GRAVEL.getItemBlock(), 1, i));
      OreDictionary.registerOre("gravel", new ItemStack(API.SEDIMENTARY_GRAVEL.getItemBlock(), 1, i));
      OreDictionary.registerOre("sand", new ItemStack(API.IGNEOUS_SAND.getItemBlock(), 1, i));
      OreDictionary.registerOre("sand", new ItemStack(API.METAMORPHIC_SAND.getItemBlock(), 1, i));
      OreDictionary.registerOre("sand", new ItemStack(API.SEDIMENTARY_SAND.getItemBlock(), 1, i));
      OreDictionary.registerOre("sandstone", new ItemStack(API.IGNEOUS_SANDSTONE.getItemBlock(), 1, i));
      OreDictionary.registerOre("sandstone", new ItemStack(API.METAMORPHIC_SANDSTONE.getItemBlock(), 1, i));
      OreDictionary.registerOre("sandstone", new ItemStack(API.SEDIMENTARY_SANDSTONE.getItemBlock(), 1, i));
      OreDictionary.registerOre("blockClay", new ItemStack(API.IGNEOUS_CLAY.getItemBlock(), 1, i));
      OreDictionary.registerOre("blockClay", new ItemStack(API.METAMORPHIC_CLAY.getItemBlock(), 1, i));
      OreDictionary.registerOre("blockClay", new ItemStack(API.SEDIMENTARY_CLAY.getItemBlock(), 1, i));
      OreDictionary.registerOre("cobblestone", new ItemStack(API.IGNEOUS_COBBLE.getItemBlock(), 1, i));
      OreDictionary.registerOre("cobblestone", new ItemStack(API.METAMORPHIC_COBBLE.getItemBlock(), 1, i));
      OreDictionary.registerOre("bricksStone", new ItemStack(API.IGNEOUS_BRICK.getItemBlock(), 1, i));
      OreDictionary.registerOre("bricksStone", new ItemStack(API.METAMORPHIC_BRICK.getItemBlock(), 1, i));
      OreDictionary.registerOre("fossilPiece", new ItemStack(API.METAMORPHIC_BRICK.getItemBlock(), 1, i));
      OreDictionary.registerOre("blockMossy", new ItemStack(API.METAMORPHIC_MOSSY_COBBLE.getItemBlock(), 1, i));
      OreDictionary.registerOre("blockMossy", new ItemStack(API.SEDIMENTARY_MOSSY_COBBLE.getItemBlock(), 1, i));
      OreDictionary.registerOre("blockMossy", new ItemStack(API.IGNEOUS_MOSSY_COBBLE.getItemBlock(), 1, i));
    }
    OresRegistry.INSTANCE.copyOreDictionaries();
    OreDictionary.registerOre("stoneMarble", new ItemStack(API.METAMORPHIC_STONE.getItemBlock(), 1, 2));
    OreDictionary.registerOre("stoneLimestone", new ItemStack(API.SEDIMENTARY_STONE.getItemBlock(), 1, 0));
    OreDictionary.registerOre("stoneBasalt", new ItemStack(API.IGNEOUS_STONE.getItemBlock(), 1, 5));
  }

  public void createRecipes(RegistryEvent.Register<IRecipe> event) {
    IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) event.getRegistry();

    for (int i = 0; i < IgneousVariant.NB_VARIANTS; ++i) {
      GameRegistry.addSmelting(new ItemStack(API.IGNEOUS_COBBLE.getItemBlock(), 1, i),
          new ItemStack(API.IGNEOUS_STONE.getItemBlock(), 1, i), 0.1f);
    }
    for (int i = 0; i < MetamorphicVariant.NB_VARIANTS; ++i) {
      GameRegistry.addSmelting(new ItemStack(API.METAMORPHIC_COBBLE.getItemBlock(), 1, i),
          new ItemStack(API.METAMORPHIC_STONE.getItemBlock(), 1, i), 0.1f);
    }

    for (int i = 0; i < IgneousVariant.NB_VARIANTS; ++i) {
      GameRegistry.addSmelting(new ItemStack(API.IGNEOUS_SAND.getItemBlock(), 1, i), new ItemStack(Blocks.GLASS, 1),
          0.1f);
    }
    for (int i = 0; i < MetamorphicVariant.NB_VARIANTS; ++i) {
      GameRegistry.addSmelting(new ItemStack(API.METAMORPHIC_SAND.getItemBlock(), 1, i), new ItemStack(Blocks.GLASS, 1),
          0.1f);
    }
    for (int i = 0; i < SedimentaryVariant.NB_VARIANTS; ++i) {
      GameRegistry.addSmelting(new ItemStack(API.SEDIMENTARY_SAND.getItemBlock(), 1, i), new ItemStack(Blocks.GLASS, 1),
          0.1f);
    }

    if (UBConfig.SPECIFIC.buttonsOn()) {
      modRegistry.remove(new ResourceLocation("minecraft:stone_button"));
    }
    if (API.SETTINGS.replaceMossyCobblestone()) {
      modRegistry.remove(new ResourceLocation("minecraft:mossy_cobblestone"));
    }
    if (API.SETTINGS.replaceSandstone()) {
      modRegistry.remove(new ResourceLocation("minecraft:sandstone"));
    }

    RegularStoneRecipe regularCobblestoneRecipe = new RegularStoneRecipe();
    modRegistry.register(regularCobblestoneRecipe);
    ((UBConfig) (UBConfig.SPECIFIC)).regularStoneCrafting.addTrackerAndUpdate(regularCobblestoneRecipe);
    ((UBConfig) (UBConfig.SPECIFIC)).changeButtonRecipe.addTrackerAndUpdate(new ButtonRecipe());
    // This will probably be needed here in 1.13
    // API.ORES_REGISTRY.registerRecipes(event);
  }

}
