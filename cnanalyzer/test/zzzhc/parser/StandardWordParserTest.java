package zzzhc.parser;

import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.TestCase;
import zzzhc.dict.Dict;
import zzzhc.dict.Word;
import zzzhc.dict.impl.SimpleDictFactory;

public class StandardWordParserTest extends TestCase {

	public void testParse() {

		String[] testData = new String[] { "ocp背后的主要机制是抽象和多态", "abcdef",
				"aabbaac", "123334335", "123456789", "123456789", "abcdef",
				"abaacbaef"
				
		};
		String[][] testDict = new String[][] {
				{ "ocp", "背后", "主要", "机制", "抽象", "多态" },
				{ "abe", "bcd", "ef" }, { "ab", "baa", "aac" },
				{ "334", "335" },
				{ "123", "456", "1234", "12345", "67", "678", "89" },
				{ "123", "456", "1234", "12345", "67", "678", "89" },
				{ "fed", "cb" },
				{"fe","fea","aaab","aaba"}
		};
		String[][] testResult = new String[][] {
				{ "ocp", "背后", "的", "主要", "机制", "是", "抽象", "和", "多态" },
				{ "a", "bcd", "ef" }, { "a", "ab", "baa", "c" },
				{ "123", "334", "335" }, { "123", "456", "7", "89" },
				{ "12345", "678", "9" }, { "def", "bc", "a" } ,
				{"aef","cb","abaa"}
		};
		
		String[] style = {
				StandardWordParser.FORWARD_MAX,
				StandardWordParser.FORWARD_MAX,
				StandardWordParser.FORWARD_MAX,
				StandardWordParser.FORWARD_MAX,
				StandardWordParser.FORWARD_MIN,
				StandardWordParser.FORWARD_MAX,
				StandardWordParser.BACKWARD_MAX,
				StandardWordParser.BACKWARD_MAX,
		};

		for (int i = 0; i < testData.length; i++) {
			SimpleDictFactory factory = new SimpleDictFactory();
			factory.setWords(testDict[i]);
			Dict dict = factory.createDict();
			ArrayList result = parseWords(testData[i], dict,style[i]);
			assertEquals(testResult[i].length, result.size());
			for (int j = 0; j < testResult[i].length; j++) {
				Word word = (Word) result.get(j);
				assertEquals(testResult[i][j], word.getTermText());
			}
		}
	}

	private ArrayList parseWords(String s, Dict dict,String style) {
		StandardWordParser parser = new StandardWordParser(style);
		parser.setDict(dict);
		ArrayList list = new ArrayList();
		Iterator ite = parser.parse(s.toCharArray(), 0, 0,
				s.toCharArray().length);
		while (ite.hasNext()) {
			list.add(ite.next());
		}
		return list;
	}

}
