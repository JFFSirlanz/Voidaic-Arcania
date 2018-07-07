package com.camellias.voidaicarcania.entities.render;

import com.camellias.voidaicarcania.Reference;
import com.camellias.voidaicarcania.entities.EntityVoidHeartPassive;
import com.camellias.voidaicarcania.entities.models.ModelVoidHeart;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderVoidHeartPassive extends RenderLiving<EntityVoidHeartPassive>
{
public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/voidic_construct.png");
	
	public RenderVoidHeartPassive(RenderManager manager) 
	{
		super(manager, new ModelVoidHeart(), 0.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityVoidHeartPassive entity) 
	{
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(EntityVoidHeartPassive entity, float i, float yaw, float ticks)
	{
		super.applyRotations(entity, i, yaw, ticks);
	}
}
