package com.example.item.creation;

import com.example.ItemOrBlock;
import com.example.block.ModBlock;
import com.example.block.ModBlockList;
import com.example.item.ModifiedItem;
import com.example.util.Map2;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemArmor;

public enum MaterialAG
{
	NETHERITE(0, 25, 45, 10f, Item.blazeRod),
	ENDERITE(1, 30, 60, 12f, Item.blazeRod),
	EMERALD(2, 15, 12, 6f, Item.stick)
	;
	public final int textureIndex;
	public final int enchantability;
	public final int durabilityFactor;
	public final int armourRenderIndex;
	public final float efficiencyOnCorrectBlock;
	public final Item toolStick;
	ItemArmor e;
	MaterialAG(int t, int e, int d, float b, Item s)
	{
		textureIndex = t;
		enchantability = e;
		durabilityFactor = d;
		efficiencyOnCorrectBlock = b;
		toolStick = s;
		armourRenderIndex = RenderingRegistry.addNewArmourRendererPrefix(this.toString().toLowerCase());
	}
	public ItemOrBlock getOre()
	{
		switch (this)
		{
			case NETHERITE: return (ItemOrBlock) ModBlockList.netheriteOre;
			default: return null;
		}
	}
	public ModBlock getBlock()
	{
		switch (this)
		{
			case NETHERITE: return (ModBlock) ModBlockList.netheriteBlock;
			case ENDERITE: return (ModBlock) ModBlockList.enderiteBlock;
			default: return null;
		}
	}

	public static Map2<MaterialAG, ItemTypeAG, ModifiedItem> modItemMap = new Map2<>();




	public ModifiedItem getFromAutogen(ItemTypeAG a)
	{


		return null;
	}

}
