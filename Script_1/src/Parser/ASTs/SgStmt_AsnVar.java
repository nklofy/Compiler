package Parser.ASTs;

import Parser.*;
import Parser.IR.*;

public class SgStmt_AsnVar extends AST {
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
		code=new IRCode("mov",this.left_hand.tmp_addr,this.expr.getRst(),this.left_hand.tmp_type,this.expr.tmp_type);		
		codegen.addCode(code);
		codegen.incLineNo();
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(!this.left_hand.checkType(codegen)||!this.expr.checkType(codegen))
			return false;
		if(codegen.getRTType(this.expr.tmp_type).canAsn(codegen.getRTType(this.left_hand.tmp_type)))
			return true;
		return false;
	}
}
