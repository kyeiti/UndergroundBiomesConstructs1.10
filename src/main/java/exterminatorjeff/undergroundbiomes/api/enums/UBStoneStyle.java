package exterminatorjeff.undergroundbiomes.api.enums;

/**
 * @author LouisDB
 */
public enum UBStoneStyle {
  STONE, COBBLE, BRICK, OVERGROWN, OVERGROWN_SNOWED, MOSSY_COBBLE, MONSTER_STONE;

  @Override
  public String toString() {
    return name().toLowerCase();
  }

}
