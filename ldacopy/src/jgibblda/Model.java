package jgibblda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class Model{
	
	// Class Variavles
	// ###
	
	// Model Parameters and Variables
	public String wordMapFile;
	public String trainlogfile;
	
	public String dir;
	public String dfile;
	public LDADataset data;
	//###
	
	public int M; // dataset size
	public int V;
	//###
	
	public Model(){
		setDefaultValues();
	}
	
	// Set default values for variables
	
	public void setDefaultValues(){
		wordMapFile = "wordmap.txt";
		dir = "./";
		//###
		
		M = 0;
		V = 0;
		
	}
	
	// ###
	
	protected boolean init(LDACmdOption option){
		if(option == null )
			return false;
		dir = option.dir;
		if(dir.endsWith(File.separator))
			dir = dir.substring(0,dir.length() - 1);
		
		///###
		dfile = option.dfile;
		wordMapFile = option.wordMapFileName;
		
		return true;
	}
	// Init parameters for estimation
	
	public boolean initNewModel(LDACmdOption option){
		if(!init(option))
			return false;
		
		/*int m, n, w, k;
		p = new double[K];
		*/
		
		data = LDADataset.readDataSet(dir + File.separator + dfile);
		if( data == null ){
			System.out.println("Fail to read training data!\n");
			return false;
		}
		
		//  ###
		return true;
	}
}