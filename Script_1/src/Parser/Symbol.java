package Parser;

import java.util.*;

public class Symbol {//symbol in graph stack correspond to reduce path or merged paths
	AST ast;
	boolean isEmpty=true;
	boolean isMerged=false;
	HashSet<AST> asts;
	int line;
	Path path;
	String name;
	public boolean addAST(AST ast){
		if(isEmpty){
			isEmpty=false;
		}
		if(isMerged){
			if(this.asts.contains(ast)){
				return false;
			}
			this.asts.add(ast);		//do merge
		}else{
			if(this.ast==null){
				this.ast=ast;
			}else{
				if(this.ast==ast){
					return false;
				}
				this.asts=new HashSet<AST>();
				this.isMerged=true;
				this.asts.add(this.ast);	//do merge	
				this.ast.merged_asts=this.asts;
			}
		}
		return true;
	}
	public Symbol(){}
	public Symbol(Symbol sym){
		this.name=sym.name;
		this.ast=sym.ast;
		/*if(sym.asts!=null){
			this.asts=new ArrayList<AST>();
			this.asts.addAll(sym.asts);
		}*/
		this.asts=sym.asts;
		this.line=sym.line;
		this.isMerged=sym.isMerged;
		this.isEmpty=sym.isEmpty;
	}
	public boolean merge(Symbol symbol){
		if(this==symbol){
			return false;
		}
		if(symbol.isMerged){
			for(AST ast_t:symbol.asts){
				addAST(ast_t);//do merge
			}
		}else{
			addAST(symbol.ast);//do merge
		}
		return true;
	}
}