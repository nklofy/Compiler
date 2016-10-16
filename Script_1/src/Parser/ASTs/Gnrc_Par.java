package Parser.ASTs;

import Parser.AST;
import Parser.CodeGenerator;
import Parser.IR.IRCode;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

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
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		IRCode code=new IRCode("defGnrcPar", this.var.rst_val, null, null);
		codegen.addCode(code);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(!this.var.genSymTb(codegen))
			return false;	
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		
		return true;
	}
}
