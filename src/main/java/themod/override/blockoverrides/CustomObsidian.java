package themod.override.blockoverrides;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Block;
import net.minecraft.src.BlockGlass;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import themod.block.stepsound.ObsidianStepSound;

import java.util.Random;

public class CustomObsidian extends BlockGlass
{

	public CustomObsidian()
	{
		super(Block.obsidian.blockID, 0, Material.rock, false);
		this.stepSound = new ObsidianStepSound();
		this.blockHardness = 10f;
		this.blockIndexInTexture = 0;
	}

	@SideOnly(Side.CLIENT)
	public String getTextureFile()
	{
		return "/mods/themod/textures/blocks.png";
	}

	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public String getBlockName()
	{
		return "tile.obsidian";
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 1;
	}


	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return true;
		//return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
	}

	@Override
	protected boolean canSilkHarvest()
	{
		return true;
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}

}
