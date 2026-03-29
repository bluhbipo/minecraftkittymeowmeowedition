package themod.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.StepSound;

import java.util.HashSet;
import java.util.Set;

public class ModBlock extends Block implements ModifiedBlock
{
	public ISimpleBlockRenderingHandler renderer;
	public ModBlock(ModBlockBuilder struct) {
		super(ModBlockDefaults.id, struct.material);
		ModBlockDefaults.id++;
		if(struct.renderer!=null)
		{
			renderer = struct.renderer;
		}
		ModBlockDefaults.init(this, struct);




	}

	@SideOnly(Side.CLIENT)
	public String getTextureFile()
	{
		return "/mods/themod/textures/blocks.png";
	}
	public int getRenderType()
	{
		if(renderer == null) return 0;
		return renderer.getRenderId();
	}
	@Override
	public boolean renderAsNormalBlock()
	{
		return renderer==null;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return renderer==null;
	}
}
