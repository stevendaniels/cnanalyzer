package zzzhc.parser;

import java.util.ArrayList;

import zzzhc.parser.CharacterIterator;

import junit.framework.TestCase;

public class CharacterIteratorTest extends TestCase {

	/*
	 * Test method for 'zzzhc.dict.impl.CharacterIterator.next()'
	 */
	public void testNext() {
		String s = "testÖÐ¹ú";
		char[] chars = s.toCharArray();
		CharacterIterator ite = new CharacterIterator(chars, 0, chars.length,
				true);
		ArrayList list = new ArrayList();
		while (ite.hasNext()) {
			Character c = (Character) ite.next();
			list.add(c);
		}
		assertEquals(chars.length, list.size());
		for (int i = 0; i < chars.length; i++) {
			assertEquals(Character.valueOf(chars[i]), list.get(i));
		}

		list.clear();
		ite = new CharacterIterator(chars, 0, chars.length, false);
		list = new ArrayList();
		while (ite.hasNext()) {
			Character c = (Character) ite.next();
			list.add(c);
		}
		assertEquals(chars.length, list.size());
		for (int i = 0; i < chars.length; i++) {
			assertEquals(Character.valueOf(chars[i]), list.get(chars.length - i
					- 1));
		}
		
		ite = new CharacterIterator(chars, 0, chars.length, true);
		ite.next();
		ite.mark();
		ite.next();
		ite.reset();
		System.out.println(ite.next());
	}

}
