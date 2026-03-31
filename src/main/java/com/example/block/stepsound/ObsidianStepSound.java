package com.example.block.stepsound;

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

	@Override
	public float getVolume()
	{
		return super.getVolume() * 0.5f;
	}
}
