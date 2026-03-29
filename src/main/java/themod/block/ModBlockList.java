package themod.block;

import net.minecraft.src.Block;
import themod.block.model.AnvilModel;

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
	private static void generate()
	{
		new ModBlockBuilder()
			.setName("Ash Block")
			.setTextureIndex(0)
			.setStepSound(Block.soundSandFootstep)
			.hasGravity()
			.buildAndRegister();
		new ModBlockBuilder()
			.setName("Anvil")
			.setTextureIndex(7)
			.setStepSound(Block.soundMetalFootstep)
			.hasGravity()
			.setRenderer(new AnvilModel())
			.buildAndRegister();
	}
}
