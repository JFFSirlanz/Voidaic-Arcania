package com.camellias.voidaicarcania.common.blocks.plants.trees;

import com.camellias.voidaicarcania.Main;
import com.camellias.voidaicarcania.core.init.ModBlocks;
import com.camellias.voidaicarcania.core.init.ModItems;
import com.camellias.voidaicarcania.core.util.IHasModel;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockWhitewoodLog extends BlockRotatedPillar implements IHasModel
{
	public BlockWhitewoodLog(Material material, String name)
	{
		super(material);
		this.setTranslationKey(name);
		this.setRegistryName(name);
		this.setHardness(2.0F);
		this.setSoundType(SoundType.WOOD);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}