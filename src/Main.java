import validators.ValidityCheck;

public class Main {

	/*
	 * Takes any number of newline-separated Swedish numbers and then logs which, if any, are invalid.
	 * @param args  - The file paths to 
	 * 
	 * @return None
	 */
	public static void main(String[] args) {
		if(args == null || args.length == 0) {
			ValidityCheck validityChecker = new ValidityCheck(System.in);
			validityChecker.validateInput();
			validityChecker.closeInput();
			
		}
		
		for(int i=0; i < args.length; ++i){
			ValidityCheck validityChecker = new ValidityCheck(args[i]);
			validityChecker.validateInput();
			validityChecker.closeInput();
		}
		
		
	}

}
