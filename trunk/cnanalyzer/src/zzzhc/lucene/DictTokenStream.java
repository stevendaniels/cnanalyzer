package zzzhc.lucene;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;

import zzzhc.dict.Word;
import zzzhc.parser.WordParser;

public class DictTokenStream extends TokenStream {
	private static final int DEFAULT_BUFFER_SIZE = 8 * 1024;

	private Reader reader;

	private WordParser parser;

	private char[] buf;

	private int bufDataLen;

	private int globalOffset;

	private boolean end = false;

	private Iterator iterator;

	public DictTokenStream(Reader reader, WordParser parser) {
		this(reader, parser, DEFAULT_BUFFER_SIZE);
	}

	public DictTokenStream(Reader reader, WordParser parser, int bufferSize) {
		this.reader = reader;
		this.parser = parser;
		buf = new char[bufferSize];
	}

	public Token next() throws IOException {
		if (end) {
			return null;
		}
		if (iterator == null) {
			globalOffset += bufDataLen;
			int bufDataLen = reader.read(buf);
			if (bufDataLen == -1) {
				end = true;
				return null;
			}
			iterator = parser.parse(buf, globalOffset, 0, bufDataLen);
		}
		if (!iterator.hasNext()) {
			iterator = null;
			return next();
		}
		Word word = (Word) iterator.next();
		Token token = new Token(word.getTermText(), word.getStartOffset(), word
				.getEndOffset(), word.getType());
		return token;
	}

	public void close() throws IOException {
		if (reader != null) {
			reader.close();
			reader = null;
		}
	}

}
