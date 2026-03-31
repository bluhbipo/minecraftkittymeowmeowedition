package com.example.override.itemoverrides;

import net.minecraft.src.Block;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

import java.util.List;

public class CustomBlockItem extends ItemBlock
{
	List preamble;
	public CustomBlockItem(Block source, List preamble)
	{
		super(source.blockID-256);
		this.preamble = preamble;
	}

	public CustomBlockItem(Block source)
	{
		super(source.blockID-256);
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