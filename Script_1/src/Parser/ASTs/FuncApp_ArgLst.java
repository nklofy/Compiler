package Parser.ASTs;

import java.util.*;

import Parser.*;

public class FuncApp_ArgLst extends AST {
	LinkedList<Expr_Calc> args;
	boolean isE=false;
	String rst_val;
	int size;
	
	public boolean isE() {
		return isE;
	}
	public void setE() {
		this.isE = true;
	}
	public void addArg(Expr_Calc ast){
		if(this.args==null){
			this.args=new LinkedList<Expr_Calc>();
		}
		this.args.add(ast);
	}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		//new type, new var, new function, put in table
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return true;
	}
}
