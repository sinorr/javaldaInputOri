package jgibblda;

import java.util.Vector;

public class Document{
	
	// Instance Variables
	
	public int [] words;
	public String rawStr;
	public int length;
	
	// constructors
	
	public Document(){
		words = null;
		rawStr = "";
		length = 0;
	}
	public Document(Vector<Integer> doc, String rawStr){
		this.length = doc.size();
		this.rawStr = rawStr;
		this.words = new int[length];
		for(int i = 0; i < length; i++){
			this.words[i] = doc.get(i);
		}
	}
	///###
}