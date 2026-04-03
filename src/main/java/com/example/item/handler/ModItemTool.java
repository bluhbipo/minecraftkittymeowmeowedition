package com.example.item.handler;

import com.example.ItemOrBlock;
import com.example.item.ModItemDefaults;
import com.example.item.ModifiedItem;
import com.example.item.creation.ItemTypeAG;
import com.example.item.creation.ModItemBuilder;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.*;

public class ModItemTool extends ItemTool implements ModifiedItem, ItemOrBlock
{
	public final ModItemBuilder props;
	public ModItemTool(ModItemBuilder struct)
	{
		super(ModItemDefaults.id, 2, EnumToolMaterial.EMERALD, struct.autogenItemType.getEffectiveBlocks());
		ModItemDefaults.init(this, struct);
		props = struct;

		ModItem.getItemByID.put(256+ModItemDefaults.id, this);
		ModItemDefaults.id++;



	}

	@Override
	public boolean canHarvestBlock(Block block)
	{
		Material mat = block.blockMaterial;
		if(this.props.autogenItemType == ItemTypeAG.PICKAXE)
		{
			return mat == Material.rock || mat == Material.iron || mat == Material.glass;
		}
		if(this.props.autogenItemType == ItemTypeAG.AXE)
		{
			return mat == Material.wood || mat == Material.pumpkin;
		}
		if(this.props.autogenItemType == ItemTypeAG.SHOVEL)
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
		Block[] var3 = this.props.autogenItemType.getEffectiveBlocks();
		int var4 = var3.length;

		for(int var5 = 0; var5 < var4; ++var5) {
			Block var6 = var3[var5];
			if (var6 == par2Block) {
				return this.efficiencyOnProperMaterial;
			}
		}

		return 1.0F;
	}

	public EnumAction getItemUseAction(ItemStack itemStack) {
		if(props.autogenItemType == ItemTypeAG.SWORD)
		{
			return EnumAction.block;
		}
		return super.getItemUseAction(itemStack);
	}

	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 72000;
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
		return itemStack;
	}


	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
	@Override
	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		return props.name;
	}
}
