package com.icemetalpunk.magicalmeta.proxy;

import com.icemetalpunk.api.blocks.BlockRegistry;
import com.icemetalpunk.api.item.ItemRegistry;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public BlockRegistry blocks = new BlockRegistry(false);
	public ItemRegistry items = new ItemRegistry(false);

	public void preInit(FMLPreInitializationEvent event) {

	}

	public void init(FMLInitializationEvent event) {

	}

	public void postInit(FMLPostInitializationEvent event) {

	}
}
