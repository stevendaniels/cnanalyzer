package zzzhc.dict.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public interface MapStrategy {

	Map createMap();

	public static class HashMapStrategy implements MapStrategy {

		public Map createMap() {
			return new HashMap();
		}

	}
	
	public static class TreeMapStrategy implements MapStrategy {

		public Map createMap() {
			return new TreeMap();
		}

	}


}
