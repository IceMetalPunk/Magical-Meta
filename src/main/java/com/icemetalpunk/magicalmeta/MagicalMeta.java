package com.icemetalpunk.magicalmeta;

import org.apache.logging.log4j.Logger;

import com.icemetalpunk.api.blocks.BlockRegistry;
import com.icemetalpunk.api.item.ItemRegistry;
import com.icemetalpunk.magicalmeta.proxy.AbstractProxy;
import com.icemetalpunk.magicalmeta.util.RegistryUtil;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MagicalMeta.MODID, name = MagicalMeta.NAME, version = MagicalMeta.VERSION)
public class MagicalMeta {
	public static final String MODID = "magicalmeta";
	public static final String NAME = "Magical Meta";
	public static final String VERSION = "1.0";

	private static Logger logger;

	public static final CreativeTabs tab = new CreativeTabs("magicalMeta") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(MagicalMeta.items.get("ender_compass"));
		}

	};

	@SidedProxy(clientSide = "com.icemetalpunk.magicalmeta.proxy.ClientProxy", serverSide = "com.icemetalpunk.magicalmeta.proxy.ServerProxy")
	public static AbstractProxy proxy;
	public static BlockRegistry blocks = new BlockRegistry();
	public static ItemRegistry items = new ItemRegistry();

	public static Logger getLogger() {
		return logger;
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		RegistryUtil.registerBlocks();
		RegistryUtil.registerItems();
		proxy.preInit(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
