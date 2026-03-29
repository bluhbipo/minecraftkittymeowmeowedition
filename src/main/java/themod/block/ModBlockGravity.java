package themod.block;


import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.src.BlockGravel;
import net.minecraft.src.BlockSand;
import net.minecraft.src.Material;

public class ModBlockGravity extends BlockSand implements ModifiedBlock
{
	public ISimpleBlockRenderingHandler renderer;
	public ModBlockGravity(ModBlockBuilder struct)
	{
		super(ModBlockDefaults.id, struct.textureIndex);
		if(struct.renderer!=null)
		{
			renderer = struct.renderer;

		}
		ModBlockDefaults.id++;
		ModBlockDefaults.init(this, struct);
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
