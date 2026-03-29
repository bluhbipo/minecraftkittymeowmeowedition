package themod;

import net.minecraft.src.EntityPlayer;

public abstract class OwnerCode<P2>
{
	public abstract boolean logic(EntityPlayer player, P2 p2);
	public boolean logic(EntityPlayer player)
	{
		return logic(player, null);
	};
}
