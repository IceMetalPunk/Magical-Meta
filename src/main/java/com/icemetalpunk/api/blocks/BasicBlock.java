package com.icemetalpunk.api.blocks;

import com.icemetalpunk.api.util.BlockOreDictionaryManager;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;

public class BasicBlock extends Block {

	public ItemBlock itemBlock = new ItemBlock(this);
	public final BlockOreDictionaryManager oreDictManager = new BlockOreDictionaryManager(this);

	public BasicBlock(String mod, String name, CreativeTabs tab, Material blockMaterialIn, MapColor blockMapColorIn) {
		super(blockMaterialIn, blockMapColorIn);
		this.setRegistryName(mod + ":" + name).setCreativeTab(tab).setUnlocalizedName(mod + "." + name);
	}

	public BasicBlock(String mod, String name, CreativeTabs tab, Material blockMaterialIn) {
		this(mod, name, tab, blockMaterialIn, blockMaterialIn.getMaterialMapColor());
	}

	public void registerModel() {
		if (this.itemBlock != null) {
			ModelResourceLocation model = new ModelResourceLocation(this.getRegistryName(), "inventory");
			ModelLoader.registerItemVariants(this.itemBlock, model);
			ModelLoader.setCustomModelResourceLocation(this.itemBlock, 0, model);
		}
	}

}
