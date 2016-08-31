package Parser.ASTs;

import Parser.AST;
import Parser.CodeGenerator;

public class Gnrc_Par extends AST {
	ExprPri_Var var;
	TypeExp_Idn idn;//no use, will add in future
	String rst_val;
	public Gnrc_Par(ExprPri_Var var){
		this.var=var;
	}
	public Gnrc_Par(ExprPri_Var var,TypeExp_Idn idn){
		this.var=var;
		this.idn=idn;
	}
	public boolean genCode(CodeGenerator codegen){
		this.rst_val=this.var.rst_val;
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		if(!this.var.genSymTb(codegen))
			return false;	
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		
		return true;
	}
}
