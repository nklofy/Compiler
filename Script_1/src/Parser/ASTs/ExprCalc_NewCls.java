package Parser.ASTs;

import Parser.*;

public class ExprCalc_NewCls extends AST {
	//new idn_type ( arg_list )  new generic_type ( arg_list ) 
	TypeExp_Idn idn_type;
	TypeExp_Gnrc gnrc_type;
	FuncApp_ArgLst args;
	String rst_val;
	String rst_type;
	
	public boolean setNewCls(TypeExp_Idn idn_type, TypeExp_Gnrc gnrc_type, FuncApp_ArgLst args){
		this.idn_type=idn_type;
		this.gnrc_type=gnrc_type;
		this.args=args;
		return true;
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
