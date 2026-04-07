package com.example.block;

import com.example.ArrayAppend;
import com.example.ItemOrBlock;
import com.example.gui.GuiRegister;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.src.Block;
import net.minecraft.src.ItemPickaxe;
import net.minecraft.src.Material;
import net.minecraft.src.StepSound;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

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
	public ModBlockBuilder setTextureCoords(int x, int y)
	{
		textureIndex=16*y + x;
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
	public GuiRegister gui;
	public ModBlockBuilder setGUI(GuiRegister g)
	{
		gui=g;
		return this;
	}
	public ItemOrBlock overriddenDrop;
	public ModBlockBuilder overrideDrop(ItemOrBlock t)
	{
		overriddenDrop = t;
		return this;
	}
	public ModifiedBlock buildAndRegister()
	{
		ModifiedBlock block;

		block = new ModBlock(this);

		ModBlockList.allBlocks.add(block);

		Material mat = ((Block)block).blockMaterial;
		Field blockList = null;
		if(mat == Material.rock || mat == Material.iron || mat == Material.glass){
			for(Field f : ItemPickaxe.class.getFields())
			{
				if(f.getGenericType() == Block[].class)
				{
					blockList = f;
					break;
				}
			}
			if(blockList!=null)
			{
				blockList.setAccessible(true);
				try
				{
					Field modifiersField = Field.class.getDeclaredField("modifiers");
					modifiersField.setAccessible(true);
					modifiersField.setInt(blockList, blockList.getModifiers() & ~Modifier.FINAL);
					blockList.set(null, new ArrayAppend<Block>().append(ItemPickaxe.blocksEffectiveAgainst, (Block)block));
				} catch (Exception e)
				{
					e.printStackTrace();
				}

			}
		}

		return block;
	}
}
