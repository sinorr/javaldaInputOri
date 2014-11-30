package jgibblda;

import java.io.File;
import java.util.Vector;

public class Estimator{
	// output model;
	
	protected Model trnModel;
	LDACmdOption option;
	
	public boolean init(LDACmdOption option){
		this.option = option;
		trnModel = new Model();
		
		if(option.est){
			if(!trnModel.initNewModel(option))
				return false;
			trnModel.data.localDict.writeWordMap(option.dir + File.separator + option.wordMapFileName);
			
		} else {
			// estc
		}
		return true;
	}
}