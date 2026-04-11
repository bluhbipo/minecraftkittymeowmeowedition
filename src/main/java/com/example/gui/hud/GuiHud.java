package com.example.gui.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraftforge.common.ForgeHooks;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;
import java.util.Random;
@SuppressWarnings("redundant")
public class GuiHud extends GuiIngame
{
	private static final RenderItem itemRenderer = new RenderItem();
	private final Random rand = new Random();
	private final Minecraft mc;
	public float prevVignetteBrightness = 1.0F;
	private final GuiNewChat persistantChatGUI;
	private int updateCounter = 0;
	private String recordPlaying = "";
	private int recordPlayingUpFor = 0;
	private boolean recordIsPlaying = false;
	public GuiHud(Minecraft minecraft)
	{
		super(minecraft);
		mc = minecraft;
		this.persistantChatGUI = new GuiNewChat(minecraft);
	}


	@Override
	public void renderGameOverlay(float par1, boolean par2, int par3, int par4) {
		ScaledResolution resolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
		int screenX = resolution.getScaledWidth();
		int screenY = resolution.getScaledHeight();
		FontRenderer var8 = this.mc.fontRenderer;
		this.mc.entityRenderer.setupOverlayRendering();
		GL11.glEnable(3042);
		if (Minecraft.isFancyGraphicsEnabled()) {
			this.renderVignette(this.mc.thePlayer.getBrightness(par1), screenX, screenY);
		} else {
			GL11.glBlendFunc(770, 771);
		}

		ItemStack helmetItem = this.mc.thePlayer.inventory.armorItemInSlot(3);
		if (this.mc.gameSettings.thirdPersonView == 0 && helmetItem != null && helmetItem.itemID == Block.pumpkin.blockID) {
			this.renderPumpkinBlur(screenX, screenY);
		}

		if (!this.mc.thePlayer.isPotionActive(Potion.confusion)) {
			float portalTime = this.mc.thePlayer.prevTimeInPortal + (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * par1;
			if (portalTime > 0.0F) {
				this.renderPortalOverlay(portalTime, screenX, screenY);
			}
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glBindTexture(3553, this.mc.renderEngine.getTexture("/gui/gui.png"));
		InventoryPlayer inventory = this.mc.thePlayer.inventory;
		this.zLevel = -90.0F;
		this.drawTexturedModalRect(screenX / 2 - 91, screenY - 22, 0, 0, 182, 22);
		this.drawTexturedModalRect(screenX / 2 - 91 - 1 + inventory.currentItem * 20, screenY - 22 - 1, 0, 22, 24, 22);

		int iconWidth = 9;

		int heartEmptyTextureX = 16;
		int heartTextureX = 16 + 4 * iconWidth;
		int heartPoisonTextureOffsetX = 4 * iconWidth;
		int heartWitherTextureOffsetX = 8 * iconWidth;

		int heartTextureY = 0;
		int heartHardcoreTextureY = 5*iconWidth;

		int hungerEmptyTextureX = 16;
		int hungerTextureX = 16 + 4 * iconWidth;
		int hungerHungerTextureOffsetX = 4 * iconWidth;

		int hungerTextureY = 3*iconWidth;

		int regenTickOffset = 2*iconWidth;
		int halfOffset = iconWidth;

		int armourEmptyTextureX = 16;
		int armourHalfTextureX = 16+iconWidth;
		int armourFullTextureX = 16+iconWidth*2;
		int armourTextureY = iconWidth;


		int bubbleTextureX = 16;
		int bubblePopTextureX = 16+iconWidth;

		int bubbleTextureY = 2*iconWidth;

		boolean isRegenTick = this.mc.thePlayer.hurtResistantTime / 3 % 2 == 1;
		if (this.mc.thePlayer.hurtResistantTime < 10) {
			isRegenTick = false;
		}
		boolean isPoisoned = this.mc.thePlayer.isPotionActive(Potion.poison);
		boolean isWithered = false;
		boolean isHungry = this.mc.thePlayer.isPotionActive(Potion.hunger);
		boolean isSubmerged = this.mc.thePlayer.isInsideOfMaterial(Material.water);

		int halfHeart = this.mc.thePlayer.getHealth()%2;
		int fullHearts = (this.mc.thePlayer.getHealth()-halfHeart)/2;

		int halfHunger = this.mc.thePlayer.getFoodStats().getFoodLevel()%2;
		int fullHunger = (this.mc.thePlayer.getFoodStats().getFoodLevel()-halfHunger)/2;

		int halfArmor = ForgeHooks.getTotalArmorValue(this.mc.thePlayer)%2;
		int fullArmor = (ForgeHooks.getTotalArmorValue(this.mc.thePlayer)-halfArmor)/2;

		int leftBarX = screenX / 2 - 91;
		int rightBarX = screenX / 2 + 91;

		int xpBarY = screenY - 32 + 3;
		int row1Y = xpBarY - 10;
		int row2Y = row1Y - 10;



		
		GL11.glBindTexture(3553, this.mc.renderEngine.getTexture("mods/themod/textures/gui/icons.png"));
		GL11.glEnable(3042);
		GL11.glBlendFunc(775, 769);
		//crosshair
		this.drawTexturedModalRect(screenX / 2 - 7, screenY / 2 - 7, 0, 0, 16, 16);
		GL11.glDisable(3042);
		this.rand.setSeed((this.getUpdateCounter() * 312871L));
		this.mc.mcProfiler.startSection("bossHealth");
		this.renderBossHealth();
		this.mc.mcProfiler.endSection();
		if (this.mc.playerController.shouldDrawHUD()) {
			this.mc.mcProfiler.startSection("expBar");
			int xpBarCap = this.mc.thePlayer.xpBarCap();
			int xpWidthPixels = 182;
			int xpFractionPixels = (int)(this.mc.thePlayer.experience*xpWidthPixels);
			if(xpBarCap > 0)
			{
				this.drawTexturedModalRect(leftBarX, xpBarY, 0, 64, 182, 5);
				if(xpFractionPixels>0)
				{
					this.drawTexturedModalRect(leftBarX, xpBarY, 0, 69, 182, 5);
				}
			}
			this.mc.mcProfiler.endStartSection("healthArmor");
			if(fullArmor+halfArmor>0)
			{
				int overarmourFactor = 2*iconWidth*(int)((float)fullArmor/10f);
				for(int i = 0; i < fullArmor%10; i++)
				{
					this.drawTexturedModalRect(leftBarX+i*8, row2Y, armourFullTextureX+overarmourFactor, armourTextureY, iconWidth, iconWidth);
				}
				if(halfArmor == 1)
				{
					this.drawTexturedModalRect(leftBarX+(fullArmor%10)*8, row2Y, armourHalfTextureX+overarmourFactor, armourTextureY, iconWidth, iconWidth);
				}
				for(int i = fullArmor%10+halfArmor; i<10; i++)
				{
					this.drawTexturedModalRect(leftBarX+i*8, row2Y, armourEmptyTextureX+overarmourFactor, armourTextureY, iconWidth, iconWidth);
				}
			}

			int lowHealthOffset = 0;
			if(this.mc.thePlayer.getHealth()<=8)
			{
				lowHealthOffset = this.rand.nextInt(2);
			}
			int extraHeartHeight = 0;
			if (this.mc.thePlayer.isPotionActive(Potion.regeneration)) {
				extraHeartHeight = this.getUpdateCounter() % 25 == 1?-2:0;
			}
			int heartFillTextureX = heartTextureX;
			int heartFillTextureY = this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled() ? heartHardcoreTextureY : heartTextureY;
			if(isPoisoned)
			{
				heartFillTextureX += heartPoisonTextureOffsetX;
			}else{
				if(isWithered) heartFillTextureX += heartWitherTextureOffsetX;
			}
			if(isRegenTick)
			{
				heartFillTextureX+=regenTickOffset;
			}
			for(int i = 0; i < fullHearts; i++)
			{
				this.drawTexturedModalRect(leftBarX+i*8, row1Y, heartEmptyTextureX, heartTextureY, iconWidth, iconWidth);
				this.drawTexturedModalRect(leftBarX+i*8, row1Y, heartFillTextureX, heartFillTextureY, iconWidth, iconWidth);
			}
			if(halfHeart == 1)
			{
				this.drawTexturedModalRect(leftBarX+fullHearts*8, row1Y, heartEmptyTextureX, heartTextureY, iconWidth, iconWidth);
				this.drawTexturedModalRect(leftBarX+fullHearts*8, row1Y, heartFillTextureX+halfOffset, heartFillTextureY, iconWidth, iconWidth);
			}
			for(int i = fullHearts+halfHeart; i < 10; i++)
			{
				this.drawTexturedModalRect(leftBarX+i*8, row1Y, heartEmptyTextureX, heartTextureY, iconWidth, iconWidth);
			}
			this.mc.mcProfiler.endStartSection("food");
			int hungerFillTextureX = hungerTextureX;
			if(isHungry)
			{
				hungerFillTextureX+=hungerHungerTextureOffsetX;
			}
			for(int i = 0; i < fullHunger; i++)
			{
				this.drawTexturedModalRect(rightBarX-(i+1)*8, row1Y, hungerEmptyTextureX, hungerTextureY, iconWidth, iconWidth);
				this.drawTexturedModalRect(rightBarX-(i+1)*8, row1Y, hungerFillTextureX, hungerTextureY, iconWidth, iconWidth);
			}
			if(halfHunger == 1)
			{
				this.drawTexturedModalRect(rightBarX-fullHunger*8, row1Y, hungerEmptyTextureX, hungerTextureY, iconWidth, iconWidth);
				this.drawTexturedModalRect(rightBarX-fullHunger*8, row1Y, hungerFillTextureX+halfOffset, hungerTextureY, iconWidth, iconWidth);
			}
			for(int i = fullHunger+halfHunger; i < 10; i++)
			{
				this.drawTexturedModalRect(rightBarX-(i+1)*8, row1Y, hungerEmptyTextureX, hungerTextureY, iconWidth, iconWidth);
			}
			this.mc.mcProfiler.endStartSection("air");
			if(isSubmerged)
			{
				for(int i = 0; i < this.mc.thePlayer.getAir(); i++)
				{
					this.drawTexturedModalRect(rightBarX - i * 8, row2Y, 16, 18, 9, 9);
				}
			}
			this.mc.mcProfiler.endSection();
			}

			GL11.glDisable(3042);
			this.mc.mcProfiler.startSection("actionBar");
			GL11.glEnable(32826);
			RenderHelper.enableGUIStandardItemLighting();

			for(int i = 0; i < 9; ++i) {
				int var19 = screenX / 2 - 90 + leftBarX * 20 + 2;
				int var20 = screenY - 16 - 3;
				this.renderInventorySlot(i, var19, var20, par1);
			}

			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(32826);
			this.mc.mcProfiler.endSection();


		float var33;
		int var38;
		if (this.mc.thePlayer.getSleepTimer() > 0) {
			this.mc.mcProfiler.startSection("sleep");
			GL11.glDisable(2929);
			GL11.glDisable(3008);
			var38 = this.mc.thePlayer.getSleepTimer();
			var33 = (float)var38 / 100.0F;
			if (var33 > 1.0F) {
				var33 = 1.0F - (float)(var38 - 100) / 10.0F;
			}

			int var12 = (int)(220.0F * var33) << 24 | 1052704;
			drawRect(0, 0, screenX, screenY, var12);
			GL11.glEnable(3008);
			GL11.glEnable(2929);
			this.mc.mcProfiler.endSection();
		}

		int var40;
		String var36;
		if (this.mc.playerController.func_78763_f() && this.mc.thePlayer.experienceLevel > 0) {
			this.mc.mcProfiler.startSection("expLevel");
			isRegenTick = false;
			int var12 = isRegenTick ? 16777215 : 8453920;
			var36 = "" + this.mc.thePlayer.experienceLevel;
			var40 = (screenX - var8.getStringWidth(var36)) / 2;
			var38 = screenY - 31 - 4;
			var8.drawString(var36, var40 + 1, var38, 0);
			var8.drawString(var36, var40 - 1, var38, 0);
			var8.drawString(var36, var40, var38 + 1, 0);
			var8.drawString(var36, var40, var38 - 1, 0);
			var8.drawString(var36, var40, var38, var12);
			this.mc.mcProfiler.endSection();
		}

		if (this.mc.isDemo()) {
			this.mc.mcProfiler.startSection("demo");
			var36 = "";
			if (this.mc.theWorld.getWorldTime() >= 120500L) {
				var36 = StatCollector.translateToLocal("demo.demoExpired");
			} else {
				var36 = String.format(StatCollector.translateToLocal("demo.remainingTime"), StringUtils.ticksToElapsedTime((int)(120500L - this.mc.theWorld.getWorldTime())));
			}

			int var12 = var8.getStringWidth(var36);
			var8.drawStringWithShadow(var36, screenX - var12 - 10, 5, 16777215);
			this.mc.mcProfiler.endSection();
		}

		if (this.mc.gameSettings.showDebugInfo) {
			this.mc.mcProfiler.startSection("debug");
			GL11.glPushMatrix();
			var8.drawStringWithShadow("Minecraft 1.3.2 (" + this.mc.debug + ")", 2, 2, 16777215);
			var8.drawStringWithShadow(this.mc.debugInfoRenders(), 2, 12, 16777215);
			var8.drawStringWithShadow(this.mc.getEntityDebug(), 2, 22, 16777215);
			var8.drawStringWithShadow(this.mc.debugInfoEntities(), 2, 32, 16777215);
			var8.drawStringWithShadow(this.mc.getWorldProviderName(), 2, 42, 16777215);
			long var41 = Runtime.getRuntime().maxMemory();
			long var34 = Runtime.getRuntime().totalMemory();
			long var42 = Runtime.getRuntime().freeMemory();
			long var43 = var34 - var42;
			String var45 = "Used memory: " + var43 * 100L / var41 + "% (" + var43 / 1024L / 1024L + "MB) of " + var41 / 1024L / 1024L + "MB";
			this.drawString(var8, var45, screenX - var8.getStringWidth(var45) - 2, 2, 14737632);
			var45 = "Allocated memory: " + var34 * 100L / var41 + "% (" + var34 / 1024L / 1024L + "MB)";
			this.drawString(var8, var45, screenX - var8.getStringWidth(var45) - 2, 12, 14737632);
			this.drawString(var8, String.format("x: %.5f", this.mc.thePlayer.posX), 2, 64, 14737632);
			this.drawString(var8, String.format("y: %.3f (feet pos, %.3f eyes pos)", this.mc.thePlayer.boundingBox.minY, this.mc.thePlayer.posY), 2, 72, 14737632);
			this.drawString(var8, String.format("z: %.5f", this.mc.thePlayer.posZ), 2, 80, 14737632);
			this.drawString(var8, "f: " + (MathHelper.floor_double((double)(this.mc.thePlayer.rotationYaw * 4.0F / 360.0F) + 0.5) & 3), 2, 88, 14737632);
			int a = MathHelper.floor_double(this.mc.thePlayer.posX);
			int b = MathHelper.floor_double(this.mc.thePlayer.posY);
			int c = MathHelper.floor_double(this.mc.thePlayer.posZ);
			if (this.mc.theWorld != null && this.mc.theWorld.blockExists(a, b, c)) {
				Chunk var48 = this.mc.theWorld.getChunkFromBlockCoords(a, c);
				this.drawString(var8, "lc: " + (var48.getTopFilledSegment() + 15) + " b: " + var48.getBiomeGenForWorldCoords(a & 15, c & 15, this.mc.theWorld.getWorldChunkManager()).biomeName + " bl: " + var48.getSavedLightValue(EnumSkyBlock.Block, a & 15, b, c & 15) + " sl: " + var48.getSavedLightValue(EnumSkyBlock.Sky, a & 15, b, c & 15) + " rl: " + var48.getBlockLightValue(a & 15, b, c & 15, 0), 2, 96, 14737632);
			}

			this.drawString(var8, String.format("ws: %.3f, fs: %.3f, g: %b", this.mc.thePlayer.capabilities.getWalkSpeed(), this.mc.thePlayer.capabilities.getFlySpeed(), this.mc.thePlayer.onGround), 2, 104, 14737632);
			GL11.glPopMatrix();
			this.mc.mcProfiler.endSection();
		}

		int recordPlayingUpFor = 0;
		if (recordPlayingUpFor > 0) {
			this.mc.mcProfiler.startSection("overlayMessage");
			var33 = (float) recordPlayingUpFor - par1;
			int var12 = (int)(var33 * 256.0F / 20.0F);
			if (var12 > 255) {
				var12 = 255;
			}

			if (var12 > 0) {
				GL11.glPushMatrix();
				GL11.glTranslatef((float)(screenX / 2), (float)(screenY - 48), 0.0F);
				GL11.glEnable(3042);
				GL11.glBlendFunc(770, 771);
				int var13 = 16777215;
				boolean recordIsPlaying = false;
				if (recordIsPlaying) {
					var13 = Color.HSBtoRGB(var33 / 50.0F, 0.7F, 0.6F) & 16777215;
				}

				String recordPlaying = "";
				var8.drawString(recordPlaying, -var8.getStringWidth(recordPlaying) / 2, -4, var13 + (var12 << 24));
				GL11.glDisable(3042);
				GL11.glPopMatrix();
			}

			this.mc.mcProfiler.endSection();
		}

		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glDisable(3008);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, (float)(screenY - 48), 0.0F);
		this.mc.mcProfiler.startSection("chat");
		this.persistantChatGUI.drawChat(this.getUpdateCounter());
		this.mc.mcProfiler.endSection();
		GL11.glPopMatrix();
		if (this.mc.gameSettings.keyBindPlayerList.pressed && (!this.mc.isIntegratedServerRunning() || this.mc.thePlayer.sendQueue.playerInfoList.size() > 1)) {
			this.mc.mcProfiler.startSection("playerList");
			NetClientHandler var37 = this.mc.thePlayer.sendQueue;
			List var39 = var37.playerInfoList;
			int var13 = var37.currentServerMaxPlayers;
			var40 = var13;

			for(var38 = 1; var40 > 20; var40 = (var13 + var38 - 1) / var38) {
				++var38;
			}

			int var16 = 300 / var38;
			if (var16 > 150) {
				var16 = 150;
			}

			int var17 = (screenX - var38 * var16) / 2;
			byte var44 = 10;
			drawRect(var17 - 1, var44 - 1, var17 + var16 * var38, var44 + 9 * var40, Integer.MIN_VALUE);

			for(int i = 0; i < var13; ++i) {
				int var20 = var17 + i % var38 * var16;
				int expBarHeight = var44 + i / var38 * 9;
				drawRect(var20, expBarHeight, var20 + var16 - 1, expBarHeight + 8, 553648127);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glEnable(3008);
				if (i < var39.size()) {
					GuiPlayerInfo var46 = (GuiPlayerInfo)var39.get(i);
					var8.drawStringWithShadow(var46.name, var20, expBarHeight, 16777215);
					this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture("/gui/icons.png"));
					byte var51 = 0;
					boolean var49 = false;
					byte var50;
					if (var46.responseTime < 0) {
						var50 = 5;
					} else if (var46.responseTime < 150) {
						var50 = 0;
					} else if (var46.responseTime < 300) {
						var50 = 1;
					} else if (var46.responseTime < 600) {
						var50 = 2;
					} else if (var46.responseTime < 1000) {
						var50 = 3;
					} else {
						var50 = 4;
					}

					this.zLevel += 100.0F;
					this.drawTexturedModalRect(var20 + var16 - 12, expBarHeight, 0 + var51 * 10, 176 + var50 * 8, 10, 8);
					this.zLevel -= 100.0F;
				}
			}
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(2896);
		GL11.glEnable(3008);
	}



	private void renderBossHealth() {
		if (RenderDragon.entityDragon != null) {
			EntityDragon var1 = RenderDragon.entityDragon;
			RenderDragon.entityDragon = null;
			FontRenderer var2 = this.mc.fontRenderer;
			ScaledResolution var3 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
			int var4 = var3.getScaledWidth();
			short var5 = 182;
			int var6 = var4 / 2 - var5 / 2;
			int var7 = (int)((float)var1.getDragonHealth() / (float)var1.getMaxHealth() * (float)(var5 + 1));
			byte var8 = 12;
			this.drawTexturedModalRect(var6, var8, 0, 74, var5, 5);
			this.drawTexturedModalRect(var6, var8, 0, 74, var5, 5);
			if (var7 > 0) {
				this.drawTexturedModalRect(var6, var8, 0, 79, var7, 5);
			}

			String var9 = "Boss health";
			var2.drawStringWithShadow(var9, var4 / 2 - var2.getStringWidth(var9) / 2, var8 - 10, 16711935);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glBindTexture(3553, this.mc.renderEngine.getTexture("/gui/icons.png"));
		}

	}

	private void renderPumpkinBlur(int par1, int par2) {
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(3008);
		GL11.glBindTexture(3553, this.mc.renderEngine.getTexture("%blur%/misc/pumpkinblur.png"));
		Tessellator var3 = Tessellator.instance;
		var3.startDrawingQuads();
		var3.addVertexWithUV(0.0, (double)par2, -90.0, 0.0, 1.0);
		var3.addVertexWithUV((double)par1, (double)par2, -90.0, 1.0, 1.0);
		var3.addVertexWithUV((double)par1, 0.0, -90.0, 1.0, 0.0);
		var3.addVertexWithUV(0.0, 0.0, -90.0, 0.0, 0.0);
		var3.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(2929);
		GL11.glEnable(3008);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	private void renderVignette(float par1, int par2, int par3) {
		par1 = 1.0F - par1;
		if (par1 < 0.0F) {
			par1 = 0.0F;
		}

		if (par1 > 1.0F) {
			par1 = 1.0F;
		}

		this.prevVignetteBrightness = (float)((double)this.prevVignetteBrightness + (double)(par1 - this.prevVignetteBrightness) * 0.01);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(0, 769);
		GL11.glColor4f(this.prevVignetteBrightness, this.prevVignetteBrightness, this.prevVignetteBrightness, 1.0F);
		GL11.glBindTexture(3553, this.mc.renderEngine.getTexture("%blur%/misc/vignette.png"));
		Tessellator var4 = Tessellator.instance;
		var4.startDrawingQuads();
		var4.addVertexWithUV(0.0, (double)par3, -90.0, 0.0, 1.0);
		var4.addVertexWithUV((double)par2, (double)par3, -90.0, 1.0, 1.0);
		var4.addVertexWithUV((double)par2, 0.0, -90.0, 1.0, 0.0);
		var4.addVertexWithUV(0.0, 0.0, -90.0, 0.0, 0.0);
		var4.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(2929);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glBlendFunc(770, 771);
	}

	private void renderPortalOverlay(float par1, int par2, int par3) {
		if (par1 < 1.0F) {
			par1 *= par1;
			par1 *= par1;
			par1 = par1 * 0.8F + 0.2F;
		}

		GL11.glDisable(3008);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, par1);
		GL11.glBindTexture(3553, this.mc.renderEngine.getTexture("/terrain.png"));
		float var4 = (float)(Block.portal.blockIndexInTexture % 16) / 16.0F;
		float var5 = (float)(Block.portal.blockIndexInTexture / 16) / 16.0F;
		float var6 = (float)(Block.portal.blockIndexInTexture % 16 + 1) / 16.0F;
		float var7 = (float)(Block.portal.blockIndexInTexture / 16 + 1) / 16.0F;
		Tessellator var8 = Tessellator.instance;
		var8.startDrawingQuads();
		var8.addVertexWithUV(0.0, (double)par3, -90.0, (double)var4, (double)var7);
		var8.addVertexWithUV((double)par2, (double)par3, -90.0, (double)var6, (double)var7);
		var8.addVertexWithUV((double)par2, 0.0, -90.0, (double)var6, (double)var5);
		var8.addVertexWithUV(0.0, 0.0, -90.0, (double)var4, (double)var5);
		var8.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(2929);
		GL11.glEnable(3008);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	private void renderInventorySlot(int par1, int par2, int par3, float par4) {
		ItemStack var5 = this.mc.thePlayer.inventory.mainInventory[par1];
		if (var5 != null) {
			float var6 = (float)var5.animationsToGo - par4;
			if (var6 > 0.0F) {
				GL11.glPushMatrix();
				float var7 = 1.0F + var6 / 5.0F;
				GL11.glTranslatef((float)(par2 + 8), (float)(par3 + 12), 0.0F);
				GL11.glScalef(1.0F / var7, (var7 + 1.0F) / 2.0F, 1.0F);
				GL11.glTranslatef((float)(-(par2 + 8)), (float)(-(par3 + 12)), 0.0F);
			}

			itemRenderer.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, var5, par2, par3);
			if (var6 > 0.0F) {
				GL11.glPopMatrix();
			}

			itemRenderer.renderItemOverlayIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, var5, par2, par3);
		}

	}

	public void updateTick() {
		if (this.recordPlayingUpFor > 0) {
			--this.recordPlayingUpFor;
		}

		++this.updateCounter;
	}

	public void setRecordPlayingMessage(String par1Str) {
		this.recordPlaying = "Now playing: " + par1Str;
		this.recordPlayingUpFor = 60;
		this.recordIsPlaying = true;
	}

	public GuiNewChat getChatGUI() {
		return this.persistantChatGUI;
	}

	public int getUpdateCounter() {
		return this.updateCounter;
	}
}
