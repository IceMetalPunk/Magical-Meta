package com.icemetalpunk.api.blocks;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockRegistry {
	private final HashMap<String, BasicBlock> registry = new HashMap<String, BasicBlock>();

	public BlockRegistry() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void add(String name, BasicBlock block) {
		registry.put(name, block);
	}

	public BasicBlock get(String name) {
		return registry.get(name);
	}

	@SubscribeEvent
	public void register(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> forgeRegistry = event.getRegistry();
		for (BasicBlock block : registry.values()) {
			forgeRegistry.register(block);
			block.oreDictManager.register();
		}
	}

	@SubscribeEvent
	public void registerItemBlocks(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> forgeRegistry = event.getRegistry();
		for (BasicBlock block : registry.values()) {
			if (block.itemBlock != null) {
				forgeRegistry.register(block.itemBlock);
			}
		}
	}

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		for (BasicBlock block : registry.values()) {
			block.registerModel();
		}
	}
}
