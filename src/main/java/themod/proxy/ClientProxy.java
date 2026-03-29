package themod.proxy;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
	@SideOnly(Side.CLIENT)
	public static void registerRenderers() {
		MinecraftForgeClient.preloadTexture("/mods/themod/textures/items.png");
		MinecraftForgeClient.preloadTexture("/mods/themod/textures/blocks.png");
	}
}
