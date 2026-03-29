package themod.block.stepsound;

import net.minecraft.src.Block;
import net.minecraft.src.StepSound;

public class ObsidianStepSound extends StepSound
{
	public ObsidianStepSound()
	{
		super("stone", 1.0F, 1.0F);
	}
	@Override
	public String getBreakSound() {
		return "random.glass";
	}
	@Override
	public float getPitch() {
		return super.getPitch() * 0.5F;
	}
}
