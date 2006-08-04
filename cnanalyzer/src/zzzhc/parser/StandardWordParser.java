package zzzhc.parser;

import java.util.Iterator;

import zzzhc.dict.Dict;
import zzzhc.dict.Word;

public class StandardWordParser implements WordParser {

	public static final String FORWARD_MAX = "forward-max";

	public static final String FORWARD_MIN = "forward-min";

	public static final String BACKWARD_MAX = "backward-max";

	public static final String BACKWARD_MIN = "backward-min";

	private String style;

	private Dict dict;

	public StandardWordParser() {

	}

	public StandardWordParser(String style) {
		this.style = style;
	}

	public void setDict(Dict dict) {
		this.dict = dict;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	private boolean isForward() {
		return style == null || style.startsWith("forward-");
	}

	private boolean isMaxMode() {
		return style == null || style.endsWith("-max");
	}

	/*
	 * @see zzzhc.parser.WordParser#parse(char[], int, int, int)
	 */
	public Iterator parse(char[] chars, int globalOffset, int charBufferOffset,
			int charBufferLength) {
		boolean forward = isForward();
		boolean maxMode = isMaxMode();
		CharacterIterator characterIterator = new CharacterIterator(chars,
				charBufferOffset, charBufferLength, forward);

		WordPicker picker = new WordPicker(dict, maxMode);

		return new WordIterator(characterIterator, picker, forward,
				globalOffset);
	}

	private static class WordIterator implements Iterator {
		private CharacterIterator characterIterator;

		private WordPicker picker;

		private boolean forward;

		private int offset;

		public WordIterator(CharacterIterator characterIterator,
				WordPicker picker, boolean forward, int globalOffsetBase) {
			this.characterIterator = characterIterator;
			this.picker = picker;
			this.forward = forward;
			this.offset = globalOffsetBase;
		}

		public boolean hasNext() {
			return characterIterator.hasNext();
		}

		public Object next() {
			Word word = picker.pickWord(characterIterator);
			if (word == null) {
				return null;
			} else {
				// ÐÞÕýÆ«ÒÆÁ¿
				word.setStartOffset(offset);
				offset += word.getTermText().length();
				word.setEndOffset(offset);
				if (forward) {
					return word;
				} else {
					StringBuffer buf = new StringBuffer();
					buf.append(word.getTermText());
					buf.reverse();
					word.setTermText(buf.toString());
					return word;
				}
			}
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
}
