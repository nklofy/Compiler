package Parser.ASTs;

import Parser.*;

public class ExprCalc_Add extends AST {
	ExprCalc_Add add_1;
	ExprCalc_Add add_2;
	String opt;
	ExprCalc_Unary unary;
	ExprAccs accs;
	en_Add t_Add;
	public boolean setBiAdd(ExprCalc_Add add_1,	String opt, ExprCalc_Add add_2){
		this.t_Add=en_Add.t_biAdd;
		this.add_1=add_1;
		this.add_2=add_2;
		this.opt=opt;
		return true;
	}
	public boolean setBiMul(ExprCalc_Add add_1,	String opt, ExprAccs accs){
		this.t_Add=en_Add.t_biMul;
		this.add_1=add_1;
		this.accs=accs;
		this.opt=opt;
		return true;
	}
	public boolean setUnAdd(ExprCalc_Add add_1,	String opt){
		this.t_Add=en_Add.t_un;
		this.add_1=add_1;
		this.opt=opt;
		return true;
	}
	public void setUnary(ExprCalc_Unary unary) {
		this.t_Add=en_Add.t_un;
		this.unary = unary;
	}
	public enum en_Add{t_biAdd,t_biMul,t_un}
}
