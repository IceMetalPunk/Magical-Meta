package com.icemetalpunk.magicalmeta;

import org.apache.logging.log4j.Logger;

import com.icemetalpunk.magicalmeta.proxy.AbstractProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
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
			// TODO: Use a real tab icon!
			return new ItemStack(Items.COMPASS);
		}

	};

	@SidedProxy(clientSide = "com.icemetalpunk.magicalmeta.proxy.ClientProxy", serverSide = "com.icemetalpunk.magicalmeta.proxy.ServerProxy")
	public static AbstractProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
	}

	public static Logger getLogger() {
		return logger;
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		logger.info("No crashes? Good!");
	}
}
