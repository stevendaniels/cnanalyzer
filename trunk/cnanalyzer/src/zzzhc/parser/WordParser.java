package zzzhc.parser;

import java.util.Iterator;

public interface WordParser {

	public abstract Iterator parse(char[] chars, int globalOffset,
			int charBufferOffset, int charBufferLength);

}