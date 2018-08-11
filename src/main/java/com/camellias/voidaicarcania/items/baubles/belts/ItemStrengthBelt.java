package com.camellias.voidaicarcania.items.baubles.belts;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.camellias.voidaicarcania.Main;
import com.camellias.voidaicarcania.entities.EntityVoidHeartPassive;
import com.camellias.voidaicarcania.init.ModItems;
import com.camellias.voidaicarcania.items.baubles.ModelResistanceBelt;
import com.camellias.voidaicarcania.items.baubles.ModelStrengthBelt;
import com.camellias.voidaicarcania.util.IHasModel;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import baubles.api.render.IRenderBauble;
import baubles.api.render.IRenderBauble.RenderType;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemStrengthBelt extends Item implements IHasModel, IBauble, IRenderBauble
{
	@SideOnly(Side.CLIENT)
	private static ModelStrengthBelt model;
	
	public ItemStrengthBelt(String name)
	{
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(Main.voidictab);
		this.maxStackSize = 1;
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add("�4Inert");
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity, EnumHand hand)
	{
		if(entity.world.isRemote)
		{
			return false;
		}
		
		if(entity instanceof EntityVoidHeartPassive)
		{
			entity.setDead();
			player.addItemStackToInventory(new ItemStack(ModItems.A_STRENGTH_BELT));
			stack.shrink(1);
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public BaubleType getBaubleType(ItemStack itemstack) 
	{
		return BaubleType.BELT;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) 
	{
		if(!world.isRemote) 
		{
			IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
			
			for(int i = 0; i < baubles.getSlots(); i++) 
			{
				if((baubles.getStackInSlot(i) == null || baubles.getStackInSlot(i).isEmpty()) && 
						baubles.isItemValidForSlot(i, player.getHeldItem(hand), player)) 
				{
					baubles.setStackInSlot(i, player.getHeldItem(hand).copy());
					
					if(!player.capabilities.isCreativeMode)
					{
						player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
					}
					onEquipped(player.getHeldItem(hand), player);
					break;
				}
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onPlayerBaubleRender(ItemStack stack, EntityPlayer player, RenderType type, float partialTicks)
	{
		if (type == RenderType.BODY)
		{
			if (model == null)
			{
				model = new ModelStrengthBelt();
			}
			
			GL11.glPushMatrix();
			IRenderBauble.Helper.rotateIfSneaking(player);
			
			if(player.hasItemInSlot(EntityEquipmentSlot.CHEST))
			{
				GlStateManager.translate(0F, 0F, 0F);
				GlStateManager.scale(1.1F, 1.1F, 1.1F);
			}
			
			GL11.glRotated(180, 1, 0, 0);
			GL11.glTranslated(0, 0, 0.02);
			GL11.glScaled(0.12, 0.12, 0.12);
			IRenderBauble.Helper.translateToChest();
			IRenderBauble.Helper.defaultTransforms();
			
			model.render(player, player.limbSwing, player.limbSwingAmount, player.ticksExisted, player.rotationYaw, player.rotationPitch, 1);
			
			GL11.glPopMatrix();
		}
	}
}
