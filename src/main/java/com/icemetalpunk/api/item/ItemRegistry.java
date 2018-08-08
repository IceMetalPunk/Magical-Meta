package com.icemetalpunk.api.item;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemRegistry {
	private final HashMap<String, BasicItem> registry = new HashMap<String, BasicItem>();

	public ItemRegistry() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void add(String name, BasicItem item) {
		registry.put(name, item);
	}

	public BasicItem get(String name) {
		return registry.get(name);
	}

	@SubscribeEvent
	public void register(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> forgeRegistry = event.getRegistry();
		for (BasicItem item : registry.values()) {
			forgeRegistry.register(item);
			item.oreDictManager.register();
		}
	}

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		for (BasicItem item : registry.values()) {
			item.registerModel();
		}
	}
}
