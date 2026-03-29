package themod.block;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.src.Block;

public class ModBlockDefaults
{
	public static int id = 200;
	public static void init(Block b, ModBlockBuilder bb)
	{
		b.blockIndexInTexture = bb.textureIndex;
		b.setResistance(bb.resistance);
		b.setHardness(bb.hardness);
		b.setStepSound(bb.stepSound);
		b.setBlockName(bb.name);
		b.setTextureFile(Paths.block1);

		if(bb.renderer!= null)
		{
			RenderingRegistry.registerBlockHandler(b.getRenderType(), bb.renderer);
		}
	}
}
