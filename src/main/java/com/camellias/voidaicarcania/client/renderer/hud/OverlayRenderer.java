package com.camellias.voidaicarcania.client.renderer.hud;

import com.camellias.voidaicarcania.Reference;
import com.camellias.voidaicarcania.core.init.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OverlayRenderer
{
	public static OverlayRenderer instance = new OverlayRenderer();
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/hud/overlay.png");
	private static Minecraft mc = Minecraft.getMinecraft();
	protected float zLevel;
	private int chunkEssence = 0;
	private int maxChunkEssence = 1500;
	private int chunkCorruption = 0;
	private int maxChunkCorruption = 1000;
	private int playerCorruption = 0;
	private int maxPlayerCorruption = 1200;
	
	public void setEssenceCorruption(int chunkEssence, int chunkCorruption, int playerCorruption)
	{
		this.chunkEssence = chunkEssence;
		this.chunkCorruption = chunkCorruption;
		this.playerCorruption = playerCorruption;
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderGameOverlayEvent(RenderGameOverlayEvent event)
	{
		if(event.isCancelable() || event.getType() != ElementType.ALL)
		{
			return;
		}
		
		if((OverlayRenderer.mc.player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() != ModItems.VOID_RESONATOR))
		{
			return;
		}
		
		GuiIngame ingame = new GuiIngame(mc);
		
		GlStateManager.disableLighting();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		FontRenderer fontRenderer = ingame.getFontRenderer();
		TextureManager textureManager = OverlayRenderer.mc.getTextureManager();
		int x = 0;
		int y = 0;
		textureManager.bindTexture(TEXTURES);
		
		int chunkEssenceMeter = chunkEssenceMeter(49);
		ingame.drawTexturedModalRect(x + 34, y + 25, 20, 78, chunkEssenceMeter, 12);
		
		int chunkCorruptionMeter = chunkCorruptionMeter(49);
		ingame.drawTexturedModalRect(x + 34, y + 48, 20, 78, chunkCorruptionMeter, 12);
		
		int playerCorruptionMeter = playerCorruptionMeter(49);
		ingame.drawTexturedModalRect(x + 11, y + 18, 0, 72, 12, playerCorruptionMeter);
		
		ingame.drawTexturedModalRect(x + 10, y + 10, 0, 0, 81, 65);
		
		if(OverlayRenderer.mc.player.isSneaking())
		{
			fontRenderer.drawString("Chunk VE: " + chunkEssence, x + 28, y + 15, 0xe500ce);
			fontRenderer.drawString("Chunk VC: " + chunkCorruption, x + 28, y + 39, 0xe500ce);
			fontRenderer.drawString("Player VC: " + playerCorruption, x + 1, y + 1, 0xe500ce);
		}
	}
	
	private int chunkEssenceMeter(int pixels)
	{
		int i = this.chunkEssence;
		int j = this.maxChunkEssence;
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}
	
	private int chunkCorruptionMeter(int pixels)
	{
		int i = this.chunkCorruption;
		int j = this.maxChunkCorruption;
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}
	
	private int playerCorruptionMeter(int pixels)
	{
		int i = this.playerCorruption;
		int j = this.maxPlayerCorruption;
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}
}
