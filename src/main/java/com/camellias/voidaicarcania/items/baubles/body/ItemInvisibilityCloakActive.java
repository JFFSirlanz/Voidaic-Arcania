package com.camellias.voidaicarcania.items.baubles.body;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemInvisibilityCloakActive extends ItemInvisibilityCloak
{
	public ItemInvisibilityCloakActive(String name)
	{
		super(name);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) 
	{
		return true;
	}
	
	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player)
	{
		EntityPlayer player1 = (EntityPlayer) player;
		
		player1.setInvisible(true);
	}
	
	/*@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player)
	{
		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(player.getMaxHealth() - 2);
	}
	
	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player)
	{
		player.setInvisible(false);
		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(player.getMaxHealth() + 2);
	}*/
}
