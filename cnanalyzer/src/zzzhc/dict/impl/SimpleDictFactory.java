package zzzhc.dict.impl;

import zzzhc.dict.Dict;
import zzzhc.dict.DictFactory;
import zzzhc.dict.Word;

public class SimpleDictFactory implements DictFactory {
	private String[] words;

	public SimpleDictFactory() {
	}

	public SimpleDictFactory(String[] words) {
		this.words = words;
	}

	public Dict createDict() {
		if (words == null) {
			return new DictImpl();
		}
		DictImpl dict = new DictImpl();
		for (int i = 0; i < words.length; i++) {
			dict.addWord(new Word(words[i]));
		}
		return dict;
	}

	public String[] getWords() {
		return words;
	}

	public void setWords(String[] words) {
		this.words = words;
	}

}
