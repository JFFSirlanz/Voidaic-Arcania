package com.camellias.voidaicarcania.items.baubles.body;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemInvisibilityCloakActive extends ItemInvisibilityCloak
{
	public ItemInvisibilityCloakActive(String name)
	{
		super(name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add("\u00A7aActivated");
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
