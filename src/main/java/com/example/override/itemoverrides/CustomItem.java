package com.example.override.itemoverrides;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

import java.util.List;

public class CustomItem extends Item
{
	List preamble;
	public CustomItem(Item source, List preamble)
	{
		super(source.shiftedIndex-256);
		this.preamble = preamble;
		this.setItemName(source.getItemName());
	}

	public CustomItem(Item source)
	{
		this(source, null);
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, List par2List)
	{
		if(preamble != null)
		{
			if(!preamble.isEmpty())
			{
				par2List.addAll(preamble);
			}
		}
		par2List.add("§b§oSOULBOUND");
	}
}