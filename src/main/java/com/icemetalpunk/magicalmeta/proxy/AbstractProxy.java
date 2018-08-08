package com.icemetalpunk.magicalmeta.proxy;

import com.icemetalpunk.api.blocks.BlockRegistry;
import com.icemetalpunk.api.item.ItemRegistry;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public abstract class AbstractProxy {

	public BlockRegistry blocks;
	public ItemRegistry items;

	public abstract void preInit(FMLPreInitializationEvent event);

	public abstract void init(FMLInitializationEvent event);

	public abstract void postInit(FMLPostInitializationEvent event);
}
