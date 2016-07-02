import Parser.*;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parser parser=new Parser();
		parser.analyzeGrm("out_grammar.txt"); 	System.out.println("analyzeGrm out_grammar.txt");
		parser.analyzeAST("grammar_AST.txt");	System.out.println("analyzeAST grammar_AST.txt");
		parser.analyzeLex("out_lexAnalyzer.txt");	System.out.println("analyzeLex out_lexAnalyzer.txt");
		parser.input("script_test1.txt");	
		parser.parse();							System.out.println("finish parsing");
		parser.output("out_parser.txt");
		CodeGenerator codegen=new CodeGenerator();
		AST tree=parser.getAST();
		
		tree.genSymTb(codegen);
		tree.checkType(codegen);
		tree.genCode(codegen);
		codegen.outputFile("out_IR.txt");
	}

}
