package com.example.item;

import com.example.item.creation.ModItemBuilder;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class ModItemDefaults
{
	public static int id = 10000;
	public static void init(Item i, ModItemBuilder ii)
	{
		System.out.println("registered item with id: "+(256+id));

		i.setItemName(ii.name);
		i.setIconCoord(ii.tx, ii.ty);
		i.setTextureFile("/mods/themod/textures/items.png");
		i.setIconIndex(ii.tx+16*ii.ty);

		if(ii.cookedFrom != null)
		{
			FurnaceRecipes.smelting().addSmelting(
				((Item)ii.cookedFrom).shiftedIndex,
				new ItemStack(i),
				0.35F
			);
		}
	}
}
