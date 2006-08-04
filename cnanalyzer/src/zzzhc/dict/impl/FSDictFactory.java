package zzzhc.dict.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import zzzhc.dict.Dict;
import zzzhc.dict.DictFactory;
import zzzhc.dict.Word;

public class FSDictFactory implements DictFactory {
	private File dictFile;

	private String encoding;

	private boolean reverse;

	public boolean isReverse() {
		return reverse;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

	public FSDictFactory(String filePath) {
		this(filePath, System.getProperty("file.encoding"));
	}

	public FSDictFactory(String filePath, String encoding) {
		File file = new File(filePath);
		if (!file.exists() || file.isDirectory()) {
			throw new IllegalArgumentException("filePath must point to a file");
		}
		dictFile = file;
		this.encoding = encoding;
	}

	public Dict createDict() {
		DictImpl dict = new DictImpl();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(dictFile), encoding));
			String line;
			StringBuffer buf = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				String[] ss = line.split("[\\t]+");
				for (int i = 0; i < ss.length; i++) {
					buf.append(ss[i]);
					if (reverse) {
						buf.reverse();
					}
					Word word = new Word(buf.toString());
					dict.addWord(word);
					buf.setLength(0);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("create dict failed", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
		return dict;
	}

}
