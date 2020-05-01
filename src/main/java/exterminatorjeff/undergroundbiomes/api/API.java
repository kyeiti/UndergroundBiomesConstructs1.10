package exterminatorjeff.undergroundbiomes.api;

import exterminatorjeff.undergroundbiomes.api.common.*;
import exterminatorjeff.undergroundbiomes.api.names.*;
import exterminatorjeff.undergroundbiomes.common.block.UBOre;
import exterminatorjeff.undergroundbiomes.intermod.ModOreRegistrar;

import java.util.ArrayList;

/**
 * Static access to Underground Biomes API.
 *
 * @author CurtisA, LouisDB
 */
public final class API {

  public static String VERSION = "2.0.0";

  public static UBStonesRegistry STONES_REGISTRY;
  public static UBOresRegistry ORES_REGISTRY;
  public static UBDropsRegistry DROPS_REGISTRY;
  public static UBDimensionalStrataColumnProvider STRATA_COLUMN_PROVIDER;
  public static UBSettings SETTINGS;
  public static ModOreRegistrar MOD_ORE_REGISTRAR;
  public static ArrayList<UBOre> REGISTERED_ORES = new ArrayList<>();

  /*
   * Stones
   */

  public static final StoneEntry IGNEOUS_STONE = new StoneEntry("igneous_stone");
  public static final StoneEntry IGNEOUS_MONSTER_STONE = new StoneEntry("igneous_monster_stone");
  public static final StoneEntry IGNEOUS_COBBLE = new StoneEntry("igneous_cobble");
  public static final StoneEntry IGNEOUS_BRICK = new StoneEntry("igneous_brick");
  public static final StoneEntry IGNEOUS_OVERGROWN = new StoneEntry("igneous_overgrown");
  public static final StoneEntry IGNEOUS_OVERGROWN_SNOWED = new StoneEntry("igneous_overgrown_snowed");
  public static final StoneEntry IGNEOUS_MOSSY_COBBLE = new StoneEntry("igneous_cobble_mossy");
  public static final StoneEntry METAMORPHIC_STONE = new StoneEntry("metamorphic_stone");
  public static final StoneEntry METAMORPHIC_MONSTER_STONE = new StoneEntry("metamorphic_monster_stone");
  public static final StoneEntry METAMORPHIC_COBBLE = new StoneEntry("metamorphic_cobble");
  public static final StoneEntry METAMORPHIC_OVERGROWN = new StoneEntry("metamorphic_overgrown");
  public static final StoneEntry METAMORPHIC_OVERGROWN_SNOWED = new StoneEntry("metamorphic_overgrown_snowed");
  public static final StoneEntry METAMORPHIC_BRICK = new StoneEntry("metamorphic_brick");
  public static final StoneEntry METAMORPHIC_MOSSY_COBBLE = new StoneEntry("metamorphic_cobble_mossy");
  public static final StoneEntry SEDIMENTARY_STONE = new StoneEntry("sedimentary_stone");
  public static final StoneEntry SEDIMENTARY_MONSTER_STONE = new StoneEntry("sedimentary_monster_stone");
  public static final StoneEntry SEDIMENTARY_OVERGROWN = new StoneEntry("sedimentary_overgrown");
  public static final StoneEntry SEDIMENTARY_OVERGROWN_SNOWED = new StoneEntry("sedimentary_overgrown_snowed");
  public static final StoneEntry SEDIMENTARY_MOSSY_COBBLE = new StoneEntry("sedimentary_stone_mossy");

  /*
   * Gravels
   */

  public static final StoneEntry IGNEOUS_GRAVEL = new StoneEntry("igneous_gravel");
  public static final StoneEntry METAMORPHIC_GRAVEL = new StoneEntry("metamorphic_gravel");
  public static final StoneEntry SEDIMENTARY_GRAVEL = new StoneEntry("sedimentary_gravel");

  /*
   * Sands
   */

  public static final StoneEntry IGNEOUS_SAND = new StoneEntry("igneous_sand");
  public static final StoneEntry METAMORPHIC_SAND = new StoneEntry("metamorphic_sand");
  public static final StoneEntry SEDIMENTARY_SAND = new StoneEntry("sedimentary_sand");

  /*
   * Sandstones
   */

  public static final StoneEntry IGNEOUS_SANDSTONE = new StoneEntry("igneous_sandstone");
  public static final StoneEntry METAMORPHIC_SANDSTONE = new StoneEntry("metamorphic_sandstone");
  public static final StoneEntry SEDIMENTARY_SANDSTONE = new StoneEntry("sedimentary_sandstone");

  /*
   * Sandstones
   */

  public static final StoneEntry IGNEOUS_SMOOTH_SANDSTONE = new StoneEntry("igneous_sandstone_smooth");
  public static final StoneEntry METAMORPHIC_SMOOTH_SANDSTONE = new StoneEntry("metamorphic_sandstone_smooth");
  public static final StoneEntry SEDIMENTARY_SMOOTH_SANDSTONE = new StoneEntry("sedimentary_sandstone_smooth");

  /*
   * Sandstones
   */

  public static final StoneEntry IGNEOUS_CHISELED_SANDSTONE = new StoneEntry("igneous_sandstone_chiseled");
  public static final StoneEntry METAMORPHIC_CHISELED_SANDSTONE = new StoneEntry("metamorphic_sandstone_chiseled");
  public static final StoneEntry SEDIMENTARY_CHISELED_SANDSTONE = new StoneEntry("sedimentary_sandstone_chiseled");

  /*
   * Clays
   */

  public static final StoneEntry IGNEOUS_CLAY = new StoneEntry("igneous_clay");
  public static final StoneEntry METAMORPHIC_CLAY = new StoneEntry("metamorphic_clay");
  public static final StoneEntry SEDIMENTARY_CLAY = new StoneEntry("sedimentary_clay");


  /*
   * Speleothems
   */
  public static final StoneEntry IGNEOUS_SPELEOTHEM = new StoneEntry("igneous_speleothem");
  public static final StoneEntry METAMORPHIC_SPELEOTHEM = new StoneEntry("metamorphic_speleothem");
  public static final StoneEntry SEDIMENTARY_SPELEOTHEM = new StoneEntry("sedimentary_speleothem");

  /*
   * Slabs
   */

  public static final SlabEntry IGNEOUS_STONE_SLAB = new SlabEntry(IGNEOUS_STONE);
  public static final SlabEntry IGNEOUS_COBBLE_SLAB = new SlabEntry(IGNEOUS_COBBLE);
  public static final SlabEntry IGNEOUS_BRICK_SLAB = new SlabEntry(IGNEOUS_BRICK);
  public static final SlabEntry METAMORPHIC_STONE_SLAB = new SlabEntry(METAMORPHIC_STONE);
  public static final SlabEntry METAMORPHIC_COBBLE_SLAB = new SlabEntry(METAMORPHIC_COBBLE);
  public static final SlabEntry METAMORPHIC_BRICK_SLAB = new SlabEntry(METAMORPHIC_BRICK);
  public static final SlabEntry SEDIMENTARY_STONE_SLAB = new SlabEntry(SEDIMENTARY_STONE);

  /*
   * Buttons
   */

  public static final ButtonEntry IGNEOUS_STONE_BUTTON = new ButtonEntry(IGNEOUS_STONE);
  public static final ButtonEntry IGNEOUS_COBBLE_BUTTON = new ButtonEntry(IGNEOUS_COBBLE);
  public static final ButtonEntry IGNEOUS_BRICK_BUTTON = new ButtonEntry(IGNEOUS_BRICK);
  public static final ButtonEntry METAMORPHIC_STONE_BUTTON = new ButtonEntry(METAMORPHIC_STONE);
  public static final ButtonEntry METAMORPHIC_COBBLE_BUTTON = new ButtonEntry(METAMORPHIC_COBBLE);
  public static final ButtonEntry METAMORPHIC_BRICK_BUTTON = new ButtonEntry(METAMORPHIC_BRICK);
  public static final ButtonEntry SEDIMENTARY_STONE_BUTTON = new ButtonEntry(SEDIMENTARY_STONE);

  /*
   * Walls
   */

  public static final WallEntry IGNEOUS_STONE_WALL = new WallEntry(IGNEOUS_STONE);
  public static final WallEntry IGNEOUS_COBBLE_WALL = new WallEntry(IGNEOUS_COBBLE);
  public static final WallEntry IGNEOUS_BRICK_WALL = new WallEntry(IGNEOUS_BRICK);
  public static final WallEntry METAMORPHIC_STONE_WALL = new WallEntry(METAMORPHIC_STONE);
  public static final WallEntry METAMORPHIC_COBBLE_WALL = new WallEntry(METAMORPHIC_COBBLE);
  public static final WallEntry METAMORPHIC_BRICK_WALL = new WallEntry(METAMORPHIC_BRICK);
  public static final WallEntry SEDIMENTARY_STONE_WALL = new WallEntry(SEDIMENTARY_STONE);

  /*
   * Stairs
   */

  public static final StairsEntry IGNEOUS_STONE_STAIRS = new StairsEntry(IGNEOUS_STONE);
  public static final StairsEntry IGNEOUS_COBBLE_STAIRS = new StairsEntry(IGNEOUS_COBBLE);
  public static final StairsEntry IGNEOUS_BRICK_STAIRS = new StairsEntry(IGNEOUS_BRICK);
  public static final StairsEntry METAMORPHIC_STONE_STAIRS = new StairsEntry(METAMORPHIC_STONE);
  public static final StairsEntry METAMORPHIC_COBBLE_STAIRS = new StairsEntry(METAMORPHIC_COBBLE);
  public static final StairsEntry METAMORPHIC_BRICK_STAIRS = new StairsEntry(METAMORPHIC_BRICK);
  public static final StairsEntry SEDIMENTARY_STONE_STAIRS = new StairsEntry(SEDIMENTARY_STONE);

  /*
   * Items
   */

  public static final ItemEntry LIGNITE_COAL = new ItemEntry("lignite_coal");
  public static final ItemEntry FOSSIL_PIECE = new ItemEntry("fossil_piece");

  /*
   *
   */

  private API() {
  }

}
