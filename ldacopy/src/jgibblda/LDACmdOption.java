package jgibblda;

import org.kohsuke.args4j.*;

public class LDACmdOption {

	@Option(name = "-est", usage = "Specify whether we want to estimate model from scatch")
	public boolean est = false;

	@Option(name = "-estc", usage = "Specify whether we want to continue the last estimation")
	public boolean estc = false;

	@Option(name = "-dir", usage = "Specify directory")
	public String dir = "";

	@Option(name = "-dfile", usage = "Specify data file")
	public String dfile = "";

	@Option(name = "-wordmap", usage = "specify the wordmap file")
	public String wordMapFileName = "wordmap.txt";

}