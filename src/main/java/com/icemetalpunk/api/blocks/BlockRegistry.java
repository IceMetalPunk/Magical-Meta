package com.icemetalpunk.api.blocks;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockRegistry {
	private final HashMap<String, BasicBlock> registry = new HashMap<String, BasicBlock>();
	private boolean isClientSide;

	public BlockRegistry(boolean isClient) {
		MinecraftForge.EVENT_BUS.register(this);
		this.isClientSide = isClient;
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
			if (isClientSide) {
				block.registerModel();
			}
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
}
