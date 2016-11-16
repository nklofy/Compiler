import Parser.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class test {

	public static void main(String[] args) {
		
		
		Parser parser=new Parser();
		parser.analyzeGrm("out_grammar.txt"); 		//System.out.println("import out_grammar.txt");
		parser.analyzeAST("grammar_AST.txt");		//System.out.println("import grammar_AST.txt");
		parser.analyzeLex("out_lexAnalyzer.txt");	//System.out.println("import out_lexAnalyzer.txt");
		System.out.println("parser ready");
		String file_name="testcode"+File.separator+"yftest"+File.separator+"test"+File.separator+"script1";
		if(args.length>0){
			if(args[0].equals("-c"))
				file_name=args[1];
		}
		
		PackageManager pkgman=new PackageManager();
		try {
			pkgman.compile(parser, file_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("finish compiling "+file_name);
		//parser.input(file_name);	
		//parser.parse();							System.out.println("finish parsing");
		//parser.output("out_parser.txt");
		//CodeGenerator codegen=new CodeGenerator();
		//AST tree=parser.getAST();
		//asts_cq.add(tree);
		//asts_cgmap.put(tree, codegen);	
		
		
		//tree.genSymTb(codegen);
		//tree.checkType(codegen);
		//tree.genCode(codegen);
		//codegen.outputFile("out_IR.txt");
	}
}
