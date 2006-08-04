package zzzhc.dict;

public interface TokenParser {

	Word[] parse(char[] chars, int globalOffset, int charBufferOffset,
			int charBufferLength);

}
