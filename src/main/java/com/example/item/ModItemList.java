package com.example.item;

import com.example.ItemOrBlock;
import com.example.gui.anvil.GuiAnvil;
import com.example.item.creation.ItemTypeAG;
import com.example.item.creation.MaterialAG;
import com.example.item.creation.ModItemBuilder;
import com.example.item.creation.ToolAndArmourFactory;
import com.example.item.handler.ModItem;

import java.util.HashSet;
import java.util.Set;

public class ModItemList
{

	protected static Set<ModItem> allItems = new HashSet<>();
	public static Set<ModItem> get()
	{
		if(allItems.isEmpty()) generate();
		return allItems;
	}
	public static ModifiedItem mutton;
	public static ModifiedItem cookedMutton;
	public static ModifiedItem enderite;
	public static void generate()
	{


		mutton = new ModItemBuilder()
			.setTextureCoords(0, 0)
			.setName("Raw Mutton")
			.isEdible()
			.setHungerPoints(3)
			.setSaturation(0.3f)
			.buildAndRegister();
		cookedMutton = new ModItemBuilder()
			.setTextureCoords(0, 1)
			.setName("Cooked Mutton")
			.isEdible()
			.setHungerPoints(8)
			.setSaturation(0.8f)
			.cookedFrom((ItemOrBlock) mutton)
			.buildAndRegister();
		ModItemBuilder[] netheriteGear = ToolAndArmourFactory.getSet(MaterialAG.NETHERITE);
		for (ModItemBuilder mm : netheriteGear)
		{
			ModifiedItem result = mm.buildAndRegister();
			if(mm.autogenItemType == ItemTypeAG.INGOT)
			{
				GuiAnvil.materialItemIDs.add(((ModItem)result).shiftedIndex+256);
			}
		}
		ModItemBuilder[] enderiteGear = ToolAndArmourFactory.getSet(MaterialAG.ENDERITE);
		for (ModItemBuilder mm : enderiteGear)
		{
			if(mm.name.equals("Enderite Ingot"))
			{
				mm.name = "Enderite";
			}
			ModifiedItem result = mm.buildAndRegister();
			if(mm.autogenItemType == ItemTypeAG.INGOT)
			{
				GuiAnvil.materialItemIDs.add(((ModItem)result).shiftedIndex+256);
				enderite = result;
			}
		}
	}
}
