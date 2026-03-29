package themod.override;

import net.minecraft.src.BiomeGenBase;

public class BiomeOverride
{
	public static void inject()
	{
		BiomeGenBase.plains.color = 0x00FF00;
	}
}
