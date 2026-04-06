package com.example.block;

import com.example.gui.anvil.GuiAnvil;
import net.minecraft.src.BiomeEndDecorator;
import net.minecraft.src.Block;
import com.example.block.model.AnvilModel;
import net.minecraft.src.Material;

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
	public static ModifiedBlock netheriteBlock;
	public static ModifiedBlock enderiteBlock;
	public static ModifiedBlock enderiteOre;
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
			.setGUI(GuiAnvil.class)
			.buildAndRegister();
		netheriteOre = new ModBlockBuilder()
			.setName("Netherite Ore")
			.setTextureCoords(0, 15)
			.setStepSound(Block.soundStoneFootstep)
			.buildAndRegister();
		netheriteBlock = new ModBlockBuilder()
			.setName("Netherite Block")
			.setTextureCoords(0, 14)
			.setStepSound(Block.soundMetalFootstep)
			.buildAndRegister();
		enderiteBlock = new ModBlockBuilder()
			.setName("Enderite Block")
			.setTextureCoords(1, 14)
			.setStepSound(Block.soundMetalFootstep)
			.buildAndRegister();
		enderiteOre = new ModBlockBuilder()
			.setName("Enderite Ore")
			.setTextureCoords(1, 15)
			.setStepSound(Block.soundStoneFootstep)
			.buildAndRegister();
		new ModBlockBuilder()
			.setName("Endstone Tile")
			.setTextureCoords(5, 0)
			.setStepSound(Block.soundStoneFootstep)
			.setMaterial(Material.rock)
			.buildAndRegister();
	}
}
