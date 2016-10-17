package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.IR.IRCode;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

public class FuncApp_ArgLst extends AST {
	LinkedList<Expr_Calc> args;
	LinkedList<String> arg_types;
	boolean isE=false;
	String rst_val;
	//int size;
	
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
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		//code=new IRCode("pushFArgs",this.arg_lst.rst_val,null,null);
		//codegen.addCode(code);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{		
		if(this.isE)
			return true;
		for(Expr_Calc exp:this.args){
			if(!exp.genSymTb(codegen))
				return false;
			this.rst_val+=exp.rst_val+",";
			//this.size++;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(this.isE)
		return true;
		if(this.arg_types==null) this.arg_types=new LinkedList<String>();
		for(Expr_Calc exp:this.args){
			if(!exp.checkType(codegen))
				return false;
			this.arg_types.add(exp.rst_type);
		}
		return true;
	}
}
