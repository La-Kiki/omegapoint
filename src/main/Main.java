package main;

import validators.*;
import java.io.*;
/*
 * 
 */
public class Main {

	/*
	 * Takes any number of newline-separated Swedish ID numbers and then logs which, if any, are invalid.
	 * @param args  - The file names containing the Swedish ID numbers that will be validated. Can be left empty to read from STDIN instead.
	 * 
	 * @return None
	 */
	public static void main(String[] args) throws IOException{
		if(args == null || args.length == 0) {
			ValidityCheck validityChecker = new ValidityCheck();
			validityChecker.validateInput();
			validityChecker.closeResources();
		}
		
		for(int i=0; i < args.length; ++i){
			if(args[i].isBlank()) {
				continue;
			}
			
			try {
				ValidityCheck validityChecker = new ValidityCheck(args[i]);
				validityChecker.validateInput();
				validityChecker.closeResources();
			}
			catch(FileNotFoundException e) {
				System.out.println("File with name " + args[i] + " not found, ignoring.");
				continue;
			}
		}
		
	}

}
