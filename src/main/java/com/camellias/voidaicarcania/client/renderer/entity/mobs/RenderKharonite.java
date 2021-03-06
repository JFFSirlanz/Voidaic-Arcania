package com.camellias.voidaicarcania.client.renderer.entity.mobs;

import com.camellias.voidaicarcania.Reference;
import com.camellias.voidaicarcania.client.models.entity.mobs.ModelKharonite;
import com.camellias.voidaicarcania.common.entities.mobs.EntityKharonite;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKharonite extends RenderLiving<EntityKharonite>
{
	public RenderKharonite(RenderManager rendermanager)
	{
		super(rendermanager, new ModelKharonite(false), 0.5F);
	}
	
	@Override
	public ResourceLocation getEntityTexture(EntityKharonite entity)
	{
		return new ResourceLocation(Reference.MODID + ":textures/entity/kharonite.png");
	}
}
