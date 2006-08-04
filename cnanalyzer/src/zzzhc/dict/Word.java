package zzzhc.dict;

import java.io.Serializable;

public class Word implements Serializable {

	public static final String WORD_TYPE = "word".intern();

	public static final String DIGITAL_TYPE = "digital".intern();

	public static final String UNKNOWN_TYPE = "unknown".intern();

	private static final long serialVersionUID = 6985909567658289486L;

	private String termText; // the text of the term

	private int startOffset; // start in source text

	private int endOffset; // end in source text

	private String type = WORD_TYPE; // lexical type

	private int frenquency = 1;// ´ÊÆµ

	public Word() {

	}
	
	public Word(String s) {
		this(s,0,s.length());
	}

	public Word(String termText, int startOffset, int endOffset) {
		this(termText, startOffset, endOffset, WORD_TYPE);
	}

	public Word(String termText, int startOffset, int endOffset, String type) {
		this(termText, startOffset, endOffset, type, 1);
	}

	public Word(String termText, int startOffset, int endOffset, String type,
			int frenquency) {
		this.termText = termText;
		this.startOffset = startOffset;
		this.endOffset = endOffset;
		this.type = type;
		this.frenquency = frenquency;
	}

	public String toString() {
		return "(" + termText + "," + startOffset + "," + endOffset + ","
				+ type + "," + frenquency + ")";
	}

	public int getEndOffset() {
		return endOffset;
	}

	public void setEndOffset(int endOffset) {
		this.endOffset = endOffset;
	}

	public int getFrenquency() {
		return frenquency;
	}

	public void setFrenquency(int frenquency) {
		this.frenquency = frenquency;
	}

	public int getStartOffset() {
		return startOffset;
	}

	public void setStartOffset(int startOffset) {
		this.startOffset = startOffset;
	}

	public String getTermText() {
		return termText;
	}

	public void setTermText(String termText) {
		this.termText = termText;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
