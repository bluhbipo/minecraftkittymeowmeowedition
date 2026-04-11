package com.example.item;

import com.example.item.creation.ModItemBuilder;
import net.minecraft.src.Block;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class ModItemDefaults
{
	public static int id = 10000;
	public static void init(ModifiedItem i, ModItemBuilder ii)
	{
		System.out.println("registered item with id: "+id);


		Item item = (Item)i;
		item.setItemName(ii.name);
		item.setIconCoord(ii.tx, ii.ty);
		item.setTextureFile("/mods/themod/textures/items.png");
		item.setIconIndex(ii.tx+16*ii.ty);

		if(ii.cookedFrom != null)
		{
			if(ii.cookedFrom instanceof ModifiedItem)
			{
				FurnaceRecipes.smelting().addSmelting(
					((Item)ii.cookedFrom).shiftedIndex,
					new ItemStack(item),
					0.35F
				);
			}else{
				FurnaceRecipes.smelting().addSmelting(
					((Block)ii.cookedFrom).blockID,
					new ItemStack(item),
					0.35F
				);
			}

		}
	}
}
