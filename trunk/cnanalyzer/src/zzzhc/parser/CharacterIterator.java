package zzzhc.parser;

import java.util.Iterator;

public class CharacterIterator implements Iterator {
	private char[] chars;

	private int offset;

	private int len;

	private int index;

	private boolean forward;

	private int mark;

	public CharacterIterator(char[] chars, int offset, int len) {
		this(chars, offset, len, true);
	}

	public CharacterIterator(char[] chars, int offset, int len, boolean forward) {
		this.chars = chars;
		this.offset = offset;
		this.len = len;
		this.forward = forward;
		if (forward) {
			index = offset;
		}
		else {
			index = offset + len - 1;
		}
	}

	public void mark() {
		mark = index;
	}

	public void reset() {
		index = mark;
	}

	public Object next() {
		char ch = chars[index];
		Character c = Character.valueOf(ch);
		if (forward == true) {
			index++;
		}
		else {
			index--;
		}
		return c;
	}

	public boolean hasNext() {
		if (forward == true) {
			return index < offset + len;
		}
		else {
			return index >= offset;
		}
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

}
