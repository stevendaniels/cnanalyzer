package zzzhc.lucene;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import junit.framework.TestCase;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import zzzhc.dict.Dict;
import zzzhc.dict.DictFactory;
import zzzhc.dict.impl.FSDictFactory;
import zzzhc.dict.impl.FSDictFactoryTest;
import zzzhc.parser.StandardWordParser;

public class DictAnalyzerTest extends TestCase {
	private String s;

	private DictFactory factory;

	protected void setUp() throws Exception {
		super.setUp();
		BufferedReader reader = new BufferedReader(new FileReader(
				FSDictFactoryTest.class.getResource("/data/text1.txt")
						.getFile()));
		String line;
		StringBuffer data = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			data.append(line);
		}
		s = data.toString();
		FSDictFactory fsDictFactory = new FSDictFactory(FSDictFactoryTest.class
				.getResource("/comwordlib.txt").getFile());
		fsDictFactory.setReverse(true);
		factory = fsDictFactory;
	}

	public void testParser() throws IOException {
		Dict dict = factory.createDict();
		StandardWordParser parser = new StandardWordParser();
		parser.setStyle(StandardWordParser.BACKWARD_MAX);
		parser.setDict(dict);
		Analyzer dictAnalyzer = new DictAnalyzer(parser);
		Reader reader = new StringReader(s);
		long start = System.nanoTime();
		int count1 = 0;
		TokenStream tokenStream = dictAnalyzer.tokenStream("ff", reader);
		while (tokenStream.next() != null) {
			count1++;
		}
		long end = System.nanoTime();
		long time1 = end - start;
		Analyzer a = new StandardAnalyzer();
		reader = new StringReader(s);
		start = System.nanoTime();
		int count2 = 0;
		tokenStream = a.tokenStream("ff", reader);
		while (tokenStream.next() != null) {
			count2++;
		}
		end = System.nanoTime();
		long time2 = end - start;
		a = new SimpleAnalyzer();
		reader = new StringReader(s);
		start = System.nanoTime();
		int count3 = 0;
		tokenStream = a.tokenStream("ff", reader);
		while (tokenStream.next() != null) {
			count3++;
		}
		end = System.nanoTime();
		long time3 = end - start;
		System.out.println("time1=" + time1 + "\tcount1=" + count1+"\tspeed="+(time1/count1)+" ns/token");
		System.out.println("time2=" + time2 + "\tcount2=" + count2+"\tspeed="+(time2/count2)+" ns/token");
		System.out.println("time3=" + time3 + "\tcount3=" + count3+"\tspeed="+(time3/count3)+" ns/token");
	}

}
