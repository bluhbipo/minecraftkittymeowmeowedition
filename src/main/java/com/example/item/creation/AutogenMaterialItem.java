package com.example.item.creation;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.src.*;

public enum AutogenMaterialItem
{
	INGOT(6),
	HELMET(7),
	CHESTPLATE(8),
	LEGGINGS(9),
	BOOTS(10),
	SWORD(11),
	SHOVEL(12),
	PICKAXE(13),
	AXE(14),
	HOE(15)
	;
	int textureHeight;
	AutogenMaterialItem(int t)
	{
		textureHeight=t;

	}
	public static AutogenMaterialItem getTypeByID(int id)
	{
		switch (id)
		{
			case 6:  return INGOT;
			case 7:  return HELMET;
			case 8:  return CHESTPLATE;
			case 9:  return LEGGINGS;
			case 10: return BOOTS;
			case 11: return SWORD;
			case 12: return SHOVEL;
			case 13: return PICKAXE;
			case 14: return AXE;
			case 15: return HOE;
			default: return null;
		}
	}
	public boolean isArmour()
	{
		return (this==HELMET||this==CHESTPLATE||this==LEGGINGS||this==BOOTS);
	}

	public boolean isTool()
	{
		return (this==PICKAXE||this==SWORD||this==AXE||this==SHOVEL||this==HOE);
	}


	public Block[] getEffectiveBlocks()
	{
		switch (this)
		{
			case AXE: return ItemAxe.blocksEffectiveAgainst;
			case PICKAXE: return ItemPickaxe.blocksEffectiveAgainst;
			case SHOVEL: return ItemSpade.blocksEffectiveAgainst;
			default: return null;
		}
	}
}
