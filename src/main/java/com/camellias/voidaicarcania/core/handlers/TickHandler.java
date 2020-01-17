package com.camellias.voidaicarcania.core.handlers;

import com.camellias.voidaicarcania.api.capabilities.corruption.chunk.ChunkCorruptionProvider;
import com.camellias.voidaicarcania.api.capabilities.corruption.player.PlayerCorruptionCapability;
import com.camellias.voidaicarcania.api.capabilities.essence.EssenceProvider;
import com.camellias.voidaicarcania.core.network.NetworkHandler;
import com.camellias.voidaicarcania.core.network.packets.PressKeyMessage;
import com.camellias.voidaicarcania.core.network.packets.SyncDataMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class TickHandler
{
	@SubscribeEvent
	public void onWorldTick(WorldTickEvent event)
	{
		if(event.phase == Phase.START && !event.world.isRemote)
		{
			World world = event.world;
			
			for(Entity entity : world.loadedEntityList)
			{
				if(!(entity instanceof EntityPlayer))
				{
					if(entity.dimension == -64)
			        {
						entity.setNoGravity(true);
						if(entity.motionY < 0)
						{
							entity.motionY = 0;
						}
					}
			        else
			        {
			        	if(entity.ticksExisted % 20 == 0) entity.setNoGravity(false);
			        }
				}
			}
			
			for(EntityPlayer player : world.playerEntities)
			{
				Chunk chunk = world.getChunk(player.getPosition());
				
				if(player.hasCapability(PlayerCorruptionCapability.CAPABILITY, null))
				{
					PlayerCorruptionCapability corruption = player.getCapability(PlayerCorruptionCapability.CAPABILITY, null);
					
					if(corruption.isCorrupted())
					{
						if(corruption.getCorruption() < 1199)
						{
							if(corruption.getCorruption() >= 300)
							{
								player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 45, 1, true, false));
							}
							if(corruption.getCorruption() >= 600)
							{
								player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 45, 1, true, false));
							}
							if(corruption.getCorruption() >= 900)
							{
								player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 45, 0, true, false));
							}
						}
					}
					
					if(player.ticksExisted % 20 == 0)
					{
						if(chunk.hasCapability(EssenceProvider.essenceCapability, null) && 
								chunk.hasCapability(ChunkCorruptionProvider.corruptionCapability, null))
						{
							NetworkHandler.INSTANCE.sendTo(new SyncDataMessage(chunk.getCapability(EssenceProvider.essenceCapability, null).saveNBT(),
									chunk.getCapability(ChunkCorruptionProvider.corruptionCapability, null).saveNBT()), (EntityPlayerMP) player);
							
							if(player.dimension == -64)
							{
								if(!corruption.isCorrupted())
								{
									corruption.setCorrupted(true);
								}
								else
								{
									if(corruption.getCorruption() < 1200)
									{
										corruption.setCorruption(corruption.getCorruption() + 1);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event)
	{
		EntityPlayer player = event.player;
		World world = player.world;
		
		if(player.dimension == -64)
        {
			if(!player.isElytraFlying() || !player.capabilities.isFlying)
			{
				if(world.isRemote)
				{
					GameSettings settings = Minecraft.getMinecraft().gameSettings;
					KeyBinding jump = settings.keyBindJump;
					GuiScreen gui = Minecraft.getMinecraft().currentScreen;
					
					if(GameSettings.isKeyDown(jump) && gui == null)
					{
						NetworkHandler.INSTANCE.sendToAll(new PressKeyMessage(player));
					}
				}
				
				if(player.isSneaking())
				{
					player.motionY = -0.1D;
				}
				if((player.motionY <= 0.075D && player.motionY >= -0.08D))
				{
					player.motionY = player.motionY / 1.05D;
				}
				
				player.setNoGravity(true);
				player.jumpMovementFactor *= 1.0F;
				player.fallDistance = 0.0F;
			}
			else
			{
				player.setNoGravity(false);
			}
		}
		else
		{
			player.setNoGravity(false);
		}
	}
}
