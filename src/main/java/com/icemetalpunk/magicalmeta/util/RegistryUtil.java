package com.icemetalpunk.magicalmeta.util;

import com.icemetalpunk.magicalmeta.MagicalMeta;
import com.icemetalpunk.magicalmeta.items.ItemEnderCompass;

public class RegistryUtil {
	public static void registerBlocks() {

	}

	public static void registerItems() {
		MagicalMeta.items.add("ender_compass", new ItemEnderCompass());
	}
}
