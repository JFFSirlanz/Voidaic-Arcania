package com.camellias.voidaicarcania.entities.render;

import com.camellias.voidaicarcania.Reference;
import com.camellias.voidaicarcania.entities.EntityVoidConstruct;
import com.camellias.voidaicarcania.entities.models.ModelVoidConstruct;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderVoidConstruct extends RenderLiving<EntityVoidConstruct>
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/voidic_construct.png");
	
	public RenderVoidConstruct(RenderManager manager) 
	{
		super(manager, new ModelVoidConstruct(), 0.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityVoidConstruct entity) 
	{
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(EntityVoidConstruct entity, float i, float yaw, float ticks)
	{
		super.applyRotations(entity, i, yaw, ticks);
	}
}