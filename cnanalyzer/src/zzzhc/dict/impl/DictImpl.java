package zzzhc.dict.impl;

import zzzhc.dict.Dict;
import zzzhc.dict.DictItem;
import zzzhc.dict.Word;

public class DictImpl implements Dict {
	private DictItemImpl rootItem = new DictItemImpl();

	public DictItem getRootItem() {
		return rootItem;
	}

	public void addWord(Word word) {
		char[] chars = word.getTermText().toCharArray();
		DictItemImpl item = rootItem;
		for (int j = 0; j < chars.length; j++) {
			char ch = chars[j];
			DictItemImpl childItem = (DictItemImpl) item.getChild(ch);
			if (childItem == null) {
				childItem = new DictItemImpl();
				childItem.setCh(Character.valueOf(ch));
				item.addChild(childItem);
			}
			if (j == chars.length - 1) {
				childItem.setWordEnd(true);
			}
			item = childItem;
		}
	}

}
