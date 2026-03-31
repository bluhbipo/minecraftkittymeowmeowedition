package com.example.override;

import com.example.override.itemoverrides.CustomBlockItem;
import com.example.override.itemoverrides.CustomItem;
import net.minecraft.src.Block;
import net.minecraft.src.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemOverride
{
	public static void inject()
	{
		override(Block.oreDiamond);
		override(Block.blockDiamond);
		override(Block.enchantmentTable);
	}
	private static void override(Block source)
	{
		Item.itemsList[source.blockID] = null;
		new CustomBlockItem(source);
	}
	public static List preamble = Collections.singletonList("all items made of diamonds are not dropped on death!");


	private static void override(Item source)
	{
		Item.itemsList[source.shiftedIndex] = null;
		if(source.shiftedIndex == Item.diamond.shiftedIndex)
		{
			new CustomItem(source, preamble);
			System.out.println(Item.itemsList[source.shiftedIndex]);
			return;
		}
		new CustomItem(source);
		System.out.println(Item.itemsList[source.shiftedIndex]);
	}
}
