package com.example.item.handler;

import com.example.ItemOrBlock;
import com.example.item.ModItemDefaults;
import com.example.item.ModifiedItem;
import com.example.item.creation.ModItemBuilder;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.ItemArmor;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.EnumHelper;

public class ModItemArmour extends ItemArmor implements ModifiedItem, ItemOrBlock
{
	public final ModItemBuilder props;
	private static final int[] maxDamageArray = new int[]{11, 16, 15, 13};
	public ModItemArmour(ModItemBuilder struct)
	{
		super(ModItemDefaults.id, EnumArmorMaterial.DIAMOND, struct.autogenMaterial.armourRenderIndex, struct.armourType);
		props = struct;
		this.setMaxDamage(maxDamageArray[struct.armourType]*struct.autogenMaterial.durabilityFactor);
		ModItemDefaults.init(this, struct);
		ModItem.getItemByID.put(256+ModItemDefaults.id, this);
		ModItemDefaults.id++;

	}
	@Override
	public int getItemEnchantability() {
		return props.autogenMaterial.enchantability;
	}
	@Override
	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		return props.name;
	}

}
