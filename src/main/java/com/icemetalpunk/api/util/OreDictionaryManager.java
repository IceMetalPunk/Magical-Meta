package com.icemetalpunk.api.util;

import java.util.HashSet;

import scala.actors.threadpool.Arrays;

public abstract class OreDictionaryManager<T> {
	protected T owner;
	private HashSet<String> suffixes = new HashSet<String>();
	private HashSet<String> prefixes = new HashSet<String>();
	private HashSet<String> customs = new HashSet<String>();

	protected OreDictionaryManager(T owner) {
		this.owner = owner;
	}

	public OreDictionaryManager addSuffixes(String... suffix) {
		suffixes.addAll(Arrays.asList(suffix));
		return this;
	}

	public OreDictionaryManager addPrefixes(String... prefix) {
		prefixes.addAll(Arrays.asList(prefix));
		return this;
	}

	public OreDictionaryManager addCustom(String... custom) {
		customs.addAll(Arrays.asList(custom));
		return this;
	}

	public String[] getSuffixes() {
		return (String[]) suffixes.toArray();
	}

	public String[] getPrefixes() {
		return (String[]) prefixes.toArray();
	}

	public String[] getCustom() {
		return (String[]) customs.toArray();
	}

	public abstract void registerOne(String ore);

	public void register() {
		for (String prefix : prefixes) {
			for (String suffix : suffixes) {
				registerOne(prefix + suffix);
			}
		}
		for (String custom : customs) {
			registerOne(custom);
		}
	}

}
