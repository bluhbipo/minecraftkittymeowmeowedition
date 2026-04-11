package com.example.item.handler;

import com.example.ItemOrBlock;
import com.example.item.ModItemDefaults;
import com.example.item.ModifiedItem;
import com.example.item.creation.ModItemBuilder;
import com.example.override.itemoverrides.ToolTipRules;
import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;

import java.util.List;

public class ModItemFood extends ItemFood implements ModifiedItem, ItemOrBlock
{
	String name = "";
	public ModItemFood(ModItemBuilder struct)
	{
		super(ModItemDefaults.id-256, struct.hungerPoints, struct.saturationModifier, struct.meatness);

		ModItemDefaults.init(this, struct);

		name=struct.name;

		ModItem.getItemByID.put(ModItemDefaults.id, this);
		ModItemDefaults.id++;
	}
	@Override
	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		return name;
	}

	@Override
	public void addInformation(ItemStack itemStack, List list)
	{
		ToolTipRules.getTooltip(this, itemStack, list);
	}
}
