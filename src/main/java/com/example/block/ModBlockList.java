package com.example.block;

import net.minecraft.src.Block;
import com.example.block.model.AnvilModel;

import java.util.HashSet;
import java.util.Set;

public class ModBlockList
{
	protected static Set<ModifiedBlock> allBlocks = new HashSet<>();
	public static Set<ModifiedBlock> get()
	{
		if(allBlocks.isEmpty()) generate();
		return allBlocks;
	}
	public static ModifiedBlock netheriteOre;
	private static void generate()
	{
		new ModBlockBuilder()
			.setName("Ash Block")
			.setTextureCoords(0, 0)
			.setStepSound(Block.soundSandFootstep)
			.hasGravity()
			.buildAndRegister();
		new ModBlockBuilder()
			.setName("Anvil")
			.setTextureCoords(7, 0)
			.setStepSound(Block.soundMetalFootstep)
			.hasGravity()
			.setRenderer(new AnvilModel())
			.buildAndRegister();
		netheriteOre = new ModBlockBuilder()
			.setName("Netherite Ore")
			.setTextureCoords(0, 15)
			.setStepSound(Block.soundStoneFootstep)
			.buildAndRegister();
	}
}
