package zzzhc.dict;

public interface DictItem {

	Character getChar();
	
	boolean isEnd();
	
	long getFrenquency();
	
	boolean isWordEnd();
	
	DictItem getChild(Character ch);
	
}
