package zzzhc.dict.impl;

import java.util.Map;

import zzzhc.dict.DictItem;
import zzzhc.dict.impl.MapStrategy.TreeMapStrategy;

public class DictItemImpl implements DictItem {
	private static final MapStrategy DEFAULT_MAP_STRATEGY = new TreeMapStrategy();

	private Character ch;

	private Map children;

	private boolean wordEnd;

	private long frenquency;

	private MapStrategy mapStrategy;

	public void setCh(Character ch) {
		this.ch = ch;
	}

	public void setChildren(Map children) {
		this.children = children;
	}

	public void setFrenquency(long frenquency) {
		this.frenquency = frenquency;
	}

	public void setWordEnd(boolean wordEnd) {
		this.wordEnd = wordEnd;
	}

	public void setMapStrategy(MapStrategy mapStrategy) {
		this.mapStrategy = mapStrategy;
	}

	public Character getChar() {
		return ch;
	}

	public boolean isEnd() {
		return children == null || children.size() == 0;
	}

	public long getFrenquency() {
		return frenquency;
	}

	public boolean isWordEnd() {
		return wordEnd;
	}

	public DictItem getChild(char ch) {
		Character c = Character.valueOf(ch);
		return getChild(c);
	}

	public DictItem getChild(Character ch) {
		if (children == null) {
			return null;
		}
		return (DictItem) children.get(ch);
	}

	public void addChild(DictItem item) {
		if (children == null) {
			if (mapStrategy == null) {
				mapStrategy = DEFAULT_MAP_STRATEGY;
			}
			children = mapStrategy.createMap();
		}
		children.put(item.getChar(), item);
	}
}
