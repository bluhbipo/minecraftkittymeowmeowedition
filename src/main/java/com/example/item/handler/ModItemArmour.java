package com.example.item.handler;

import com.example.item.ModItemDefaults;
import com.example.item.ModifiedItem;
import com.example.item.creation.ModItemBuilder;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.ItemArmor;
import net.minecraft.src.ItemStack;

public class ModItemArmour extends ItemArmor implements ModifiedItem
{
	String name = "";
	public int enchantability;
	private static final int[] maxDamageArray = new int[]{11, 16, 15, 13};
	public ModItemArmour(ModItemBuilder struct)
	{
		super(ModItemDefaults.id, EnumArmorMaterial.DIAMOND, struct.armourRenderIndex, struct.armourType);
		this.enchantability = struct.enchantability;
		this.setMaxDamage(maxDamageArray[struct.armourType]*struct.durabilityFactor);
		ModItemDefaults.init(this, struct);
		name=struct.name;
		ModItem.getItemByID.put(256+ModItemDefaults.id, this);
		ModItemDefaults.id++;

	}
	@Override
	public int getItemEnchantability() {
		return enchantability;
	}
	@Override
	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		return name;
	}

}
