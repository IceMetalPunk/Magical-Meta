package com.icemetalpunk.api.util;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class ItemOreDictionaryManager extends OreDictionaryManager<Item> {

	public ItemOreDictionaryManager(Item owner) {
		super(owner);
	}

	@Override
	public void registerOne(String ore) {
		OreDictionary.registerOre(ore, this.owner);
	}

}
