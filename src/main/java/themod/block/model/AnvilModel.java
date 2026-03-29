package themod.block.model;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;

public class AnvilModel implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();

		// Define your anvil cuboids
		Cuboid[] cuboids = new Cuboid[]{
			Cuboid.TOP,     // top plate
			Cuboid.BEVEL,   // bevel
			Cuboid.STEM,    // stem
			Cuboid.BOTTOM   // bottom
		};

		for (Cuboid c : cuboids) {
			// Temporarily set block bounds to this cuboid
			block.setBlockBounds(c.minX, c.minY, c.minZ, c.maxX, c.maxY, c.maxZ);

			float scale = 1.1F; // 1.0 = full block, 0.9 = slight inset
			float cx = 0.5F;
			float cy = 1.9F;
			float cz = 0.5F;

			float minX = cx + (c.minX - cx) * scale;
			float maxX = cx + (c.maxX - cx) * scale;
			float minY = cy + (c.minY - cy) * scale;
			float maxY = cy + (c.maxY - cy) * scale;
			float minZ = cz + (c.minZ - cz) * scale;
			float maxZ = cz + (c.maxZ - cz) * scale;

			int texIndex = block.getBlockTextureFromSideAndMetadata(1, metadata); // example top texture
			float texUnit = 1.0F / 16.0F;
			int col = texIndex % 16;
			int row = texIndex / 16;

			float uMin = col * texUnit + 0.001F;
			float uMax = (col + 1) * texUnit - 0.001F;
			float vMin = row * texUnit + 0.001F;
			float vMax = (row + 1) * texUnit - 0.001F;

			// Bottom face (y = minY) — same as before
			tess.addVertexWithUV(minX, minY, maxZ, uMin, vMax);
			tess.addVertexWithUV(maxX, minY, maxZ, uMax, vMax);
			tess.addVertexWithUV(maxX, minY, minZ, uMax, vMin);
			tess.addVertexWithUV(minX, minY, minZ, uMin, vMin);

// Top face (y = maxY) — swap minY and maxY to correct orientation
			tess.addVertexWithUV(minX, maxY, maxZ, uMin, vMax);
			tess.addVertexWithUV(maxX, maxY, maxZ, uMax, vMax);
			tess.addVertexWithUV(maxX, maxY, minZ, uMax, vMin);
			tess.addVertexWithUV(minX, maxY, minZ, uMin, vMin);

			// North face (z = minZ, facing +Z)
			tess.addVertexWithUV(maxX, minY, minZ, uMax, vMax);
			tess.addVertexWithUV(minX, minY, minZ, uMin, vMax);
			tess.addVertexWithUV(minX, maxY, minZ, uMin, vMin);
			tess.addVertexWithUV(maxX, maxY, minZ, uMax, vMin);

			// South face (z = maxZ)
			tess.addVertexWithUV(minX, minY, maxZ, uMin, vMax);
			tess.addVertexWithUV(maxX, minY, maxZ, uMax, vMax);
			tess.addVertexWithUV(maxX, maxY, maxZ, uMax, vMin);
			tess.addVertexWithUV(minX, maxY, maxZ, uMin, vMin);

			// West face (x = minX)
			tess.addVertexWithUV(minX, minY, minZ, uMax, vMax);
			tess.addVertexWithUV(minX, minY, maxZ, uMin, vMax);
			tess.addVertexWithUV(minX, maxY, maxZ, uMin, vMin);
			tess.addVertexWithUV(minX, maxY, minZ, uMax, vMin);

			// East face (x = maxX)
			tess.addVertexWithUV(maxX, minY, maxZ, uMax, vMax);
			tess.addVertexWithUV(maxX, minY, minZ, uMin, vMax);
			tess.addVertexWithUV(maxX, maxY, minZ, uMin, vMin);
			tess.addVertexWithUV(maxX, maxY, maxZ, uMax, vMin);

		}
		tess.draw();
		// Reset block bounds to full block for world rendering
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
									Block block, int modelId, RenderBlocks renderer) {
		boolean rendered = false;

		// Render each cuboid using standard block renderer
		for (Cuboid c : Cuboid.values()) {
			block.setBlockBounds(c.minX, c.minY, c.minZ, c.maxX, c.maxY, c.maxZ);
			rendered |= renderer.renderStandardBlock(block, x, y, z);
		}

		// Reset bounds
		block.setBlockBoundsBasedOnState(world, x, y, z);
		return rendered;
	}
	private static float pixel = 1/16f;
	private enum Cuboid {



		BOTTOM(2*pixel, 0*pixel, 2*pixel, 14*pixel, 4*pixel, 14*pixel),

		// stem/pillar: 4×4×6 pixels
		STEM(6*pixel, 5*pixel, 4*pixel, 10*pixel, 10*pixel, 12*pixel),

		// bevel/sloped section: 8×8×2 pixels
		BEVEL(4*pixel, 4*pixel, 3*pixel, 12*pixel, 5*pixel, 13*pixel),
		// top plate: 16×16×4 pixels
		TOP(2*pixel, 10*pixel, 0*pixel, 14*pixel, 16*pixel, 16*pixel);



		final float minX, minY, minZ;
		final float maxX, maxY, maxZ;

		Cuboid(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
			this.minX = minX;
			this.minY = minY;
			this.minZ = minZ;
			this.maxX = maxX;
			this.maxY = maxY;
			this.maxZ = maxZ;
		}
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return 100;
	}

}
