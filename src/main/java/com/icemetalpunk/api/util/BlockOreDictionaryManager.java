package com.icemetalpunk.api.util;

import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;

public class BlockOreDictionaryManager extends OreDictionaryManager<Block> {

	public BlockOreDictionaryManager(Block owner) {
		super(owner);
	}

	@Override
	public void registerOne(String ore) {
		OreDictionary.registerOre(ore, this.owner);
	}

}
