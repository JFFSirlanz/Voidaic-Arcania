package com.camellias.voidaicarcania.blocks;

import java.util.Random;

import com.camellias.voidaicarcania.Main;
import com.camellias.voidaicarcania.init.ModBlocks;
import com.camellias.voidaicarcania.init.ModItems;
import com.camellias.voidaicarcania.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;

public class BlockWhiteWoodLeaves extends Block implements IHasModel
{
	public BlockWhiteWoodLeaves(String name, Material material) 
	{
		super(material);
		
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(Main.voidictab);
		this.setSoundType(SoundType.PLANT);
		this.setHardness(3.5F);
		this.setResistance(10.0F);
		
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isTranslucent(IBlockState state) {
		return true;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	protected boolean canSilkHarvest() {
		return true;
	}
	
	@Override
	public int quantityDropped(Random random)
    {
        return 0;
    }
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
