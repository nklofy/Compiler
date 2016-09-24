package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

public class NewArr_Init extends AST {
	Expr_Calc calc;
	NewArr_InitLst lst;
	
	public void setCalc(Expr_Calc calc) {
		this.calc = calc;
	}
	public void setLst(NewArr_InitLst lst) {
		this.lst = lst;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		
		return true;
	}
}
