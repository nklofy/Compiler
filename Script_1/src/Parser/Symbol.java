package Parser;

import java.util.*;

public class Symbol {//symbol in graph stack correspond to reduce path or merged paths
	AST ast;
	boolean isEmpty=true;//e reduce
	boolean isMerged=false;
	ArrayList<AST> asts;
	int line;
	Path path;
	String name;
	public void addAST(AST ast){
		if(isEmpty){
			isEmpty=false;
		}
		if(isMerged){
			asts.add(ast);
		}else{
			if(this.ast==null){
				this.ast=ast;
			}else{
				this.isMerged=true;
				this.asts.add(this.ast);
				this.ast=null;				
				this.asts.add(ast);
			}
		}
	}
	public Symbol(){}
	public Symbol(Symbol sym){
		this.name=sym.name;
		this.ast=sym.ast;
		this.asts=sym.asts;
		this.line=sym.line;
		this.isMerged=sym.isMerged;
		this.isEmpty=sym.isEmpty;
	}
	//String value;
	
}