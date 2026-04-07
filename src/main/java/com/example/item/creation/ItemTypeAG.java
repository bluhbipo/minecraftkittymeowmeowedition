package com.example.item.creation;

import net.minecraft.src.*;

public enum ItemTypeAG
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
	ItemTypeAG(int t)
	{
		textureHeight=t;

	}
	enum SuperTypes
	{
		INGOT,
		ARMOUR,
		TOOL
		;
	}
	public SuperTypes getSuperType()
	{
		switch (this)
		{
			case AXE:
			case SWORD:
			case HOE:
			case SHOVEL:
			case PICKAXE:
				return SuperTypes.TOOL;
			case HELMET:
			case LEGGINGS:
			case CHESTPLATE:
			case BOOTS:
				return SuperTypes.ARMOUR;
			default:
				return  SuperTypes.INGOT;
		}
	}
	public static ItemTypeAG getTypeByID(int id)
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

	public static final String[] pickaxePattern   = new String[]{"mmm", " s ", " s "};
	public static final String[] axePattern1      = new String[]{"mm", "ms", " s"};
	public static final String[] axePattern2      = new String[]{"mm", "sm", "s "};
	public static final String[] shovelPattern    = new String[]{" m ", " s ", " s "};
	public static final String[] hoePattern1      = new String[]{"mm", "s ", "s "};
	public static final String[] hoePattern2      = new String[]{"mm", " s", " s"};
	public static final String[] swordPattern     = new String[]{"m", "m", "s"};
	public static final String[] helmetPattern    = new String[]{"mmm", "m m"};
	public static final String[] chestplatePattern = new String[]{"m m", "mmm", "mmm"};
	public static final String[] leggingsPattern  = new String[]{"mmm", "m m", "m m"};
	public static final String[] bootsPattern     = new String[]{"m m", "m m"};
	public static final String[] blockPattern     = new String[]{"mmm","mmm","mmm"};

}
