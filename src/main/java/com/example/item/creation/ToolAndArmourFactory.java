package com.example.item.creation;

import com.example.TextUtils;

public class ToolAndArmourFactory
{
	public static ModItemBuilder[] getSet(Material m)
	{
		ModItemBuilder[] res = new ModItemBuilder[AutogenMaterialItem.values().length];
		int index = 0;
		for(AutogenMaterialItem t : AutogenMaterialItem.values())
		{
			res[index] = new ModItemBuilder()
				.setName(TextUtils.toTitleCase(m.name()) +" "+TextUtils.toTitleCase(t.name()))
				.setTextureCoords(m.textureIndex, t.textureHeight);

			if(t.isArmour())
			{
				res[index]
					.setArmourType(t.textureHeight-7)
					.setArmourRenderIndex(m.armourRenderIndex);
			}
			if(t.isTool())
			{
				res[index]
					.setToolType(t);
			}
			if(t.textureHeight != AutogenMaterialItem.INGOT.textureHeight)
			{
				res[index]
					.setEnchantability(m.enchantability)
					.setDurabilityFactor(m.durabilityFactor);
			}
			index++;
		}
		return res;
	}
}
