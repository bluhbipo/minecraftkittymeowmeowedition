package com.example.item.handler;

import com.example.item.ModItemDefaults;
import com.example.item.ModifiedItem;
import com.example.item.creation.AutogenMaterialItem;
import com.example.item.creation.ModItemBuilder;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.*;

import java.util.List;

public class ModItemTool extends ItemTool implements ModifiedItem
{
	String name = "";
	AutogenMaterialItem toolType;
	public ModItemTool(ModItemBuilder struct)
	{
		super(ModItemDefaults.id, 2, EnumToolMaterial.EMERALD, struct.toolType.getEffectiveBlocks());
		ModItemDefaults.init(this, struct);

		toolType = struct.toolType;
		name=struct.name;

		ModItem.getItemByID.put(256+ModItemDefaults.id, this);
		ModItemDefaults.id++;



	}


	@Override
	public void addInformation(ItemStack par1ItemStack, List par2List)
	{
		super.addInformation(par1ItemStack, par2List);
		par2List.add("Line 2");
	}

	@Override
	public boolean canHarvestBlock(Block block)
	{
		Material mat = block.blockMaterial;
		if(this.toolType == AutogenMaterialItem.PICKAXE)
		{
			return mat == Material.rock || mat == Material.iron || mat == Material.glass;
		}
		if(this.toolType == AutogenMaterialItem.AXE)
		{
			return mat == Material.wood || mat == Material.pumpkin;
		}
		if(this.toolType == AutogenMaterialItem.SHOVEL)
		{
			return mat == Material.sand
				|| mat == Material.clay
				|| mat == Material.craftedSnow
				|| mat == Material.ground
				|| mat == Material.grass;
		}
		return false;
	}
	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		return par2Block == null
			|| par2Block.blockMaterial != Material.iron && par2Block.blockMaterial != Material.rock
			?
			getStrVsBlockSuper(par1ItemStack, par2Block)
			:
			this.efficiencyOnProperMaterial;
	}

	public float getStrVsBlockSuper(ItemStack par1ItemStack, Block par2Block) {
		Block[] var3 = this.toolType.getEffectiveBlocks();
		int var4 = var3.length;

		for(int var5 = 0; var5 < var4; ++var5) {
			Block var6 = var3[var5];
			if (var6 == par2Block) {
				return this.efficiencyOnProperMaterial;
			}
		}

		return 1.0F;
	}


	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
	@Override
	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		return name;
	}
}
