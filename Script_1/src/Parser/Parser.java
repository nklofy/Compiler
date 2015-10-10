//public API: input(String) parse()
package Parser;

import LexAnalyzer.Tokenizer;

public class Parser {
	Tokenizer tokenizer=new Tokenizer();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parser parser=new Parser();
		parser.analyze("script_test1.txt");
		parser.input("script_test1.txt");
		parser.parse();
		Tokenizer tokenizer=new Tokenizer();
		
		//tokenizer.getToken();
	}
	public boolean analyze(String filename){
		
		return false;
	}
	public boolean input(String filename){
		tokenizer.setScanFile(filename);
		return false;
	}
	
	public boolean parse(){
		
		return false;
	}
	public boolean output(String filename){
		
		return false;
	}
}

