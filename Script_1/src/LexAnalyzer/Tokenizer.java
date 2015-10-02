package LexAnalyzer;

public class Tokenizer {

	public static void main(String[] args) {
		LexAnalyzer lex_analyzer=new LexAnalyzer();
		lex_analyzer.input("tokens.txt");
		lex_analyzer.generateNFA();  	
		lex_analyzer.generateDFA();
		lex_analyzer.NFAtoDFA();
		lex_analyzer.combineDFA();
		lex_analyzer.getTokenTable();
		lex_analyzer.outputTable("out_lexAnalyzer.txt");
	
	}

}
