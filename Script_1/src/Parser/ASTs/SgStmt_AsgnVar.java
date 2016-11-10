package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class SgStmt_AsgnVar extends AST {
	Expr_Left left_hand;
	Expr expr;
	
	public Expr_Left getLeft() {
		return left_hand;
	}
	public void setLeft(Expr_Left left_hand) {
		this.left_hand = left_hand;
	}
	public Expr getExpr() {
		return expr;
	}
	public void setExpr(Expr expr) {
		this.expr = expr;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		this.left_hand.genCode(codegen);
		this.expr.genCode(codegen);
		IRCode code;
		code=new IRCode("mov",this.left_hand.ref_type,this.left_hand.rst_addr,this.expr.getRst());		
		codegen.addCode(code);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(!this.left_hand.genSymTb(codegen))
			return false;
		if(!this.expr.genSymTb(codegen))
			return false;
		R_Variable r=codegen.getVarInSymTb(this.expr.rst_val);
		R_Variable nr=new R_Variable();
		nr.setVarName(this.left_hand.rst_addr);
		nr.setVarType(r.getVarType());
		nr.setRstVal(r.getRstVal());
		codegen.putVarInSymTb(this.left_hand.rst_addr,nr);
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(!this.left_hand.checkType(codegen))
			return false;
		this.expr.ref_type=this.left_hand.ref_type;		
		return this.expr.checkType(codegen);
	}
}
