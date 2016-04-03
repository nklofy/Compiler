package Parser.ASTs;

import Parser.*;
import Parser.IR.*;

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
	public boolean genCode(CodeGenerator codegen){
		this.left_hand.genCode(codegen);
		this.expr.genCode(codegen);
		IRCode code;
		code=new IRCode("mov",this.left_hand.ref_type,this.left_hand.rst_addr,this.expr.getRst());		
		codegen.addCode(code);
		codegen.incLineNo();
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		if(!this.left_hand.genSymTb(codegen))
			return false;
		this.expr.ref_type=this.left_hand.ref_type;
		return this.expr.genSymTb(codegen);
	}
	public boolean checkType(CodeGenerator codegen){
		return this.left_hand.checkType(codegen)&&this.expr.checkType(codegen);
	}
}
