package com.example.item.creation;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.src.EnumArmorMaterial;

public enum Material
{
	NETHERITE(0, 25, 45),
	ENDERITE(1, 30, 60)
	;
	final int textureIndex;
	final int enchantability;
	final int durabilityFactor;
	final int armourRenderIndex;
	Material(int t, int e, int d)
	{
		textureIndex = t;
		enchantability = e;
		durabilityFactor = d;
		armourRenderIndex = RenderingRegistry.addNewArmourRendererPrefix(this.toString().toLowerCase());
	}

}
