package com.camellias.voidaicarcania.common.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockFullSlabBase extends BlockSlabBase
{
	public BlockFullSlabBase(Material material, String name, BlockSlab half, @Nullable String tool, int level, SoundType sound, float hardness, float resistance)
	{
		super(material, name, half);
		if(tool != null) this.setHarvestLevel(tool, level);
		this.setSoundType(sound);
		this.setHardness(hardness);
		this.setResistance(resistance);
	}
	
	@Override
	public boolean isDouble()
	{
		return true;
	}
}
