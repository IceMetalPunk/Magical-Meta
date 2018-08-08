package com.icemetalpunk.api.item;

import com.icemetalpunk.api.util.IModeledObject;
import com.icemetalpunk.api.util.ItemOreDictionaryManager;
import com.icemetalpunk.magicalmeta.MagicalMeta;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class BasicItem extends Item implements IModeledObject {

	public ItemOreDictionaryManager oreDictManager = new ItemOreDictionaryManager(this);

	public BasicItem(String mod, String name, CreativeTabs tab) {
		super();
		this.setRegistryName(mod + ":" + name).setCreativeTab(tab).setUnlocalizedName(mod + "." + name);
	}

	@Override
	public void registerModel() {
		ModelResourceLocation model = new ModelResourceLocation(this.getRegistryName(), "inventory");
		ModelLoader.registerItemVariants(this, model);
		ModelLoader.setCustomModelResourceLocation(this, 0, model);
		MagicalMeta.getLogger().info(model);
	}
}
