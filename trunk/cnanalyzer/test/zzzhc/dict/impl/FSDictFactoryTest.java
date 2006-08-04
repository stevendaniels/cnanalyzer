package zzzhc.dict.impl;

import zzzhc.dict.Dict;
import junit.framework.TestCase;

public class FSDictFactoryTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testCreateDict() {
		FSDictFactory factory = new FSDictFactory(FSDictFactoryTest.class
				.getResource("/comwordlib.txt").getFile());
		Dict dict = factory.createDict();
		assertNotNull(dict);
		
	}

}
