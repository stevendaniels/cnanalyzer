package zzzhc.lucene;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;

import zzzhc.parser.WordParser;

public class DictAnalyzer extends Analyzer {
	private WordParser parser;

	public DictAnalyzer(WordParser parser) {
		this.parser = parser;
	}

	public TokenStream tokenStream(String fieldName, Reader reader) {
		return new DictTokenStream(reader, parser);
	}

}
