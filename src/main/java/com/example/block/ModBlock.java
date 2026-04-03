package com.example.block;

import com.example.ItemOrBlock;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.*;

import java.util.Random;

public class ModBlock extends Block implements ModifiedBlock, ItemOrBlock
{
	public final ModBlockBuilder props;
	public ModBlock(ModBlockBuilder struct) {

		super(ModBlockDefaults.id, struct.material);
		this.props = struct;
		this.setCreativeTab(CreativeTabs.tabBlock);
		ModBlockDefaults.id++;
		ModBlockDefaults.init(this, struct);
	}

	@SideOnly(Side.CLIENT)
	public String getTextureFile()
	{
		return "/mods/themod/textures/blocks.png";
	}

	public int getRenderType()
	{
		if(props.renderer == null) return 0;
		return props.renderer.getRenderId();
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return props.renderer==null;
	}

	@Override
	public boolean isOpaqueCube()
	{
		if(props == null) return true;
		return props.renderer==null;
	}

	@Override
	public void onBlockAdded(World world, int par2, int par3, int par4) {
		if(!props.hasGravity) return;
		world.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate());
	}

	@Override
	public void onNeighborBlockChange(World world, int par2, int par3, int par4, int par5) {
		if(!props.hasGravity) return;
		world.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate());
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if (!par1World.isRemote && this.props.hasGravity) {
			this.tryToFall(par1World, par2, par3, par4);
		}

	}

	private void tryToFall(World world, int x, int y, int z) {
		if (canFallBelow(world, x, y - 1, z) && y >= 0) {
			byte var8 = 32;
			if (world.checkChunksExist(x - var8, y - var8, z - var8, x + var8, y + var8, z + var8)) {
				if (!world.isRemote) {
					EntityFallingSand fallingSand = new EntityFallingSand(world, (double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.blockID);
					world.spawnEntityInWorld(fallingSand);
				}
			} else {
				world.setBlockWithNotify(x, y, z, 0);

				while(canFallBelow(world, x, y - 1, z) && y > 0) {
					--y;
				}

				if (y > 0) {
					world.setBlockWithNotify(x, y, z, this.blockID);
				}
			}
		}

	}

	public int tickRate() {
		return 3;
	}

	public static boolean canFallBelow(World par0World, int x, int y, int z) {
		int blockIdBelow = par0World.getBlockId(x, y, z);
		if (blockIdBelow == 0) {
			return true;
		} else if (blockIdBelow == Block.fire.blockID) {
			return true;
		} else {
			Material blockMaterialBelow = Block.blocksList[blockIdBelow].blockMaterial;
			if (blockMaterialBelow == Material.water) {
				return true;
			} else {
				return blockMaterialBelow == Material.lava;
			}
		}
	}
}
