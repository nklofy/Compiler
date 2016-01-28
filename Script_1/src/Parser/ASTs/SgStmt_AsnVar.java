package Parser.ASTs;

import Parser.AST;

public class SgStmt_AsnVar extends AST {
	Expr_Left left_hand;
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
	Expr expr;
}
