package jgibblda;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class LDADataset{
	
	// Instance Variables
	
	public Dictionary localDict;
	public Document[] docs; 
	public int M;   // number of documents
	public int V;   // number of words;

	// map from local coordinates(id) to global ones
	// null if the global dictionary is not set
	public Map<Integer,Integer> lid2gid;
	
	// link to a global dictionary (optional), null for train data, not null for test data
	public Dictionary globalDict;
	
	// Constructor
	
	public LDADataset(){
		localDict = new Dictionary();
		M = 0;
		V = 0;
		docs = null;
		
		globalDict = null;
		lid2gid = null;
	}
	
	public LDADataset( int M){
		localDict = new Dictionary();
		this.M = M;
		this.V = 0;
		docs = new Document[M];
		
		globalDict = null;
		lid2gid = null;
	}
	// ###
	
	// Public Instance Methods
	
	public void setDoc(Document doc, int idx){
		if( 0 <= idx && idx < M ){
			docs[idx] = doc;
		}
	}
	
	public void setDoc(String str, int idx){
		if( 0 <= idx && idx <= M){
			String [] words = str.split("[ \\t\\n]");
			
			Vector<Integer> ids = new Vector<Integer>();
			
			for(String word: words){
				int _id = localDict.word2id.size();
				if(localDict.contains(word))
					_id = localDict.getID(word);
				
				if(globalDict != null){
					// get the global id
					Integer id = globalDict.getID(word);
					
					if( id != null ){
						localDict.addWord(word);
						
						lid2gid.put(_id, id);
						ids.add(_id);
						
					}
					else{
						 // not in global dictionary
						// do nothing currently
					}
				}
				else{
					localDict.addWord(word);
					ids.add(_id);
				}
			}
			
			Document doc = new Document(ids,str);
			docs[idx] = doc;
			V = localDict.word2id.size();
		}
	}
	
	// I/O methods;
	
	public static LDADataset readDataSet(String filename){
		try{
			BufferedReader  reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"));
			
			LDADataset data = readDataSet(reader);
			
			reader.close();
			return data;
		}catch(Exception e){
			System.out.println("Read Dataset Error: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	// ###
	
	public static LDADataset readDataSet(BufferedReader reader){
		try{
			// read number of document
			String line;
			line = reader.readLine();
			int M = Integer.parseInt(line);
			
			LDADataset data = new LDADataset(M);
			for( int i = 0; i < M; i++ ){
				line = reader.readLine();
				
				data.setDoc(line,i);
				
			}
			return data;
		}catch(Exception e){
			System.out.println("Read Dataset Error: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}