package com.example.item.creation;

import com.example.util.TextUtils;

public class ToolAndArmourFactory
{
	public static ModItemBuilder[] getSet(MaterialAG m)
	{
		ModItemBuilder[] res = new ModItemBuilder[ItemTypeAG.values().length];
		int index = 0;
		for(ItemTypeAG t : ItemTypeAG.values())
		{
			res[index] = new ModItemBuilder()
				.setName(TextUtils.toTitleCase(m.name()) +" "+TextUtils.toTitleCase(t.name()))
				.setTextureCoords(m.textureIndex, t.textureHeight)
				.setAutogenMaterial(m)
				.setAutogenType(t)
				;

			if(t.getSuperType() == ItemTypeAG.SuperTypes.ARMOUR)
			{
				res[index].setArmourType(t.textureHeight-7);
			}
			if(t.textureHeight == ItemTypeAG.INGOT.textureHeight)
			{
				res[index].cookedFrom(m.getOre());
			}
			if(t.getSuperType() == ItemTypeAG.SuperTypes.TOOL)
			{
				res[index].mineSpeed = m.efficiencyOnCorrectBlock;
			}
			index++;
		}
		return res;
	}
}
