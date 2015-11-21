package Parser;
import java.util.*;

class Node{
	String type;
	String name;
}

class AstRule{
	AstRule(){}
	AstRule(String method){		
		this.method=method;
	}
	String method;
	int symbol_count;
	ArrayList<Integer> parameters=new ArrayList<Integer>();
}

class Symbol{		//why use new symbol class instead of parsergenerator's symbol? 
	AST ast;		//because it is simpler without many unnecessary things 
	String type;
	String name;
	String num_value;
	public String getNumValue() {
		return num_value;
	}
	
}

class Grammar{
	String head;
	int symbol_count;
	ArrayList<String> symbols=new ArrayList<String>();
}

public abstract class AST {
	String num_type;
	String name;
	int int_value=0;
	double double_value=0.0;	
	public abstract boolean eval(Interpreter interpreter);
}

class StmtListAST extends AST{
	StmtListAST stmt_list;
	StmtAST stmt;
	public boolean eval(Interpreter interpreter) {
		interpreter.interpret(this);
		return true;
	}
}

class StmtAST extends AST{
	ExpAST exp;
	public boolean eval(Interpreter interpreter) {
		interpreter.interpret(this);
		return true;
	}
}

class ExpAST extends AST{
	ExpAST exp;
	AddAST add_exp;
	String opt;
	public boolean eval(Interpreter interpreter) {
		interpreter.interpret(this);
		return true;
	}
}

class AddAST extends AST{
	MulAST mul_exp;
	AddAST add_exp;
	String opt;
	public boolean eval(Interpreter interpreter) {
		interpreter.interpret(this);
		return true;
	}
}
class MulAST extends AST{
	PriAST pri_exp;
	public boolean eval(Interpreter interpreter) {
		interpreter.interpret(this);
		return true;
	}
}

class PriAST extends AST{
	ExpAST exp;
	NumAST num_exp;
	public boolean eval(Interpreter interpreter) {
		interpreter.interpret(this);
		return true;
	}
}

class NumAST extends AST{
	String num_type;
	String buffer;
	public boolean eval(){
		if(num_type==null){
			return false;
		}else if(num_type.equals("int")){
			this.int_value=Integer.parseInt(buffer);
			return true;
		}else if(num_type.equals("double")){
			this.double_value=Double.parseDouble(buffer);
			return true;
		}
		return false;
	}//TODO	
	public boolean eval(Interpreter interpreter) {
		interpreter.interpret(this);
		return true;
	}
}
