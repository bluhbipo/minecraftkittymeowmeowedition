package themod.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.StepSound;

public class ModBlockBuilder extends ModBlockList
{
	boolean hasGravity = false;
	int textureIndex = 0;
	float hardness = 1.5f;
	StepSound stepSound = Block.soundStoneFootstep;
	float resistance = 10f;
	String name = "you forgot to name it";
	Material material = Material.cloth;
	ISimpleBlockRenderingHandler renderer;
	public ModBlockBuilder setTextureIndex(int i)
	{
		textureIndex=i;
		return this;
	}
	public ModBlockBuilder setHardness(int h)
	{
		hardness=h;
		return this;
	}
	public  ModBlockBuilder setMaterial(Material m)
	{
		material=m;
		return this;
	}
	public ModBlockBuilder setStepSound(StepSound s)
	{
		stepSound=s;
		return this;
	}
	public ModBlockBuilder setResistance(float r)
	{
		resistance=r;
		return this;
	}
	public ModBlockBuilder setName(String n)
	{
		name=n;
		return this;
	}
	public ModBlockBuilder hasGravity()
	{
		hasGravity=true;
		return this;
	}
	public ModBlockBuilder setRenderer(ISimpleBlockRenderingHandler r)
	{
		renderer = r;
		return this;
	}
	public ModifiedBlock buildAndRegister()
	{
		ModifiedBlock block;
		if(hasGravity)
		{
			block = new ModBlockGravity(this);
		}else{
			block = new ModBlock(this);
		}
		ModBlockList.allBlocks.add(block);
		return block;
	}
}
