package com.example.item;

import com.example.ItemOrBlock;
import com.example.item.creation.Material;
import com.example.item.creation.ModItemBuilder;
import com.example.item.creation.ToolAndArmourFactory;
import com.example.item.handler.ModItem;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ModItemList
{

	protected static Set<ModItem> allItems = new HashSet<>();
	public static Set<ModItem> get()
	{
		if(allItems.isEmpty()) generate();
		return allItems;
	}
	public static ModifiedItem mutton;
	public static ModifiedItem cookedMutton;
	public static void generate()
	{


		mutton = new ModItemBuilder()
			.setTextureCoords(0, 0)
			.setName("Raw Mutton")
			.isEdible()
			.setHungerPoints(3)
			.setSaturation(0.3f)
			.buildAndRegister();
		cookedMutton = new ModItemBuilder()
			.setTextureCoords(0, 1)
			.setName("Cooked Mutton")
			.isEdible()
			.setHungerPoints(8)
			.setSaturation(0.8f)
			.cookedFrom((ItemOrBlock) mutton)
			.buildAndRegister();
		ModItemBuilder[] netheriteGear = ToolAndArmourFactory.getSet(Material.NETHERITE);
		for (ModItemBuilder mm : netheriteGear)
		{
			mm.buildAndRegister();
		}
		ModItemBuilder[] enderiteGear = ToolAndArmourFactory.getSet(Material.ENDERITE);
		for (ModItemBuilder mm : enderiteGear)
		{
			if(mm.name.equals("Enderite Ingot"))
			{
				mm.name = "Enderite";
			}
			mm.buildAndRegister();
		}
	}
}
