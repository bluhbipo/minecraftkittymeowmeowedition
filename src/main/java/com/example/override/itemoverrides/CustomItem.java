package com.example.override.itemoverrides;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.*;

import java.util.List;

public class CustomItem extends Item
{
	ItemStack a;
	public final int shiftedIndex;
	private Item containerItem;
	private String potionEffect;
	private String currentTexture;
	public final Item source;
	public CustomItem(Item source)
	{
		super(source.shiftedIndex-256);
		this.shiftedIndex = source.shiftedIndex;
		this.maxStackSize = source.getItemStackLimit();
		setMaxDamage(source.getMaxDamage());
		this.iconIndex = source.getIconIndex(new ItemStack(this));
		this.bFull3D = source.isFull3D();
		this.hasSubtypes = source.getHasSubtypes();
		this.containerItem = source.getContainerItem();
		this.potionEffect = source.getPotionEffect();
		this.canRepair = source.isRepairable();
		this.isDefaultTexture = source.isDefaultTexture;
		this.currentTexture = source.getTextureFile();

		this.source = source;

		setCreativeTab(source.getCreativeTab());

	}

	@Override
	public void addInformation(ItemStack itemStack, List list)
	{
		ToolTipRules.getTooltip(source, itemStack, list);
	}

	@SideOnly(Side.CLIENT)
	public String getItemDisplayName(ItemStack itemStack) {
		return (StringTranslate.getInstance().translateNamedKey(this.getLocalItemName(itemStack))).trim();
	}
	@SideOnly(Side.CLIENT)
	public String getLocalItemName(ItemStack itemStack) {
		String itemName = this.getItemNameIS(itemStack);
		return itemName == null ? "" : StatCollector.translateToLocal(itemName);
	}
	public String getItemNameIS(ItemStack itemStack) {
		return source.getItemName();
	}

	@Override
	public boolean canHarvestBlock(Block target)
	{
		if(!(source instanceof ItemTool)) return false;
		return source.canHarvestBlock(target);
	}

	public float getStrVsBlock(ItemStack itemStack, Block target) {
		if(!(source instanceof ItemTool)) return 1.0F;
		Block[] mineable = null;
		if(source instanceof ItemPickaxe) mineable = ItemPickaxe.blocksEffectiveAgainst;
		if(source instanceof ItemSpade) mineable = ItemSpade.blocksEffectiveAgainst;
		if(source instanceof ItemAxe) mineable = ItemAxe.blocksEffectiveAgainst;
		if(mineable == null) return 1.0F;
		for (Block block : mineable)
		{
			if (block != target) continue;
			float efficiency = 1.0f;
			if (source.getItemName().contains("Wood"))
			{
				efficiency = EnumToolMaterial.WOOD.getEfficiencyOnProperMaterial();
				return efficiency;
			}
			if (source.getItemName().contains("Stone"))
			{
				efficiency = EnumToolMaterial.STONE.getEfficiencyOnProperMaterial();
				return efficiency;
			}
			if (source.getItemName().contains("Iron"))
			{
				efficiency = EnumToolMaterial.IRON.getEfficiencyOnProperMaterial();
				return efficiency;
			}
			if (source.getItemName().contains("Gold"))
			{
				efficiency = EnumToolMaterial.GOLD.getEfficiencyOnProperMaterial();
				return efficiency;
			}
			if (source.getItemName().contains("Diamond"))
			{
				efficiency = EnumToolMaterial.EMERALD.getEfficiencyOnProperMaterial();
				return efficiency;
			}
			return efficiency;

		}
		return 1.0F;
	}

	public boolean hitEntity(ItemStack itemStack, EntityLiving damager, EntityLiving damagee) {
		itemStack.damageItem(2, damagee);
		return true;
	}
	public EnumAction getItemUseAction(ItemStack itemStack) {
		if(source instanceof ItemSword) return EnumAction.block;
		return super.getItemUseAction(itemStack);
	}

	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 72000;
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
		return itemStack;
	}
	public boolean onBlockDestroyed(ItemStack itemStack, World world, int blockID, int x, int y, int z, EntityLiving player) {
		if ((double)Block.blocksList[blockID].getBlockHardness(world, x, y, z) != 0.0) {
			itemStack.damageItem(1, player);
		}

		return true;
	}

}