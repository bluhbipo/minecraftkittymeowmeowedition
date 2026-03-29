package themod.item;

import cpw.mods.fml.common.Mod;
import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;

public class ModItemFood extends ItemFood implements ModifiedItem
{
	String name = "";
	public ModItemFood(ModItemBuilder struct)
	{
		super(ModItemDefaults.id, struct.hungerPoints, struct.saturationModifier, struct.meatness);

		ModItemDefaults.init(this, struct);

		name=struct.name;

		ModItem.getItemByID.put(256+ModItemDefaults.id, this);
		ModItemDefaults.id++;
	}
	@Override
	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		return name;
	}
}
