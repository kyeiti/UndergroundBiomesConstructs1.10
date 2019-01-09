package exterminatorjeff.undergroundbiomes.common.item;

import exterminatorjeff.undergroundbiomes.api.common.UBItem;
import exterminatorjeff.undergroundbiomes.api.common.Variable;
import exterminatorjeff.undergroundbiomes.api.enums.FossilVariant;
import exterminatorjeff.undergroundbiomes.client.UBCreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * @author CurtisA, LouisDB
 */
public class ItemFossilPiece extends Item implements UBItem, Variable {

  public ItemFossilPiece() {
    super();
    setMaxDamage(0);
    setHasSubtypes(true);
    setCreativeTab(UBCreativeTab.UB_ITEMS_TAB);
  }

  @Override
  public int getMetadata(int damage) {
    return damage;
  }

  @Override
  public void getSubItems(CreativeTabs tabs, NonNullList<ItemStack> list) {
    if (this.isInCreativeTab(tabs)) {
      for (int i = 0; i < FossilVariant.NB_VARIANTS; ++i)
        list.add(new ItemStack(this, 1, i));
    }
  }

  @Override
  public String getUnlocalizedNameInefficiently(ItemStack stack) {
    return getTranslationKey() + "." + getVariantName(stack.getMetadata()).replaceAll("2", "");
  }

  @Override
  public Item toItem() {
    return this;
  }

  @Override
  public int getNbVariants() {
    return FossilVariant.NB_VARIANTS;
  }

  @Override
  public String getVariantName(int meta) {
    return FossilVariant.values()[meta].toString();
  }

}
