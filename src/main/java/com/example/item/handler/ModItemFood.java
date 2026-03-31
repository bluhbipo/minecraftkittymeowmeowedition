package com.example.item.handler;

import com.example.item.ModItemDefaults;
import com.example.item.ModifiedItem;
import com.example.item.creation.ModItemBuilder;
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
