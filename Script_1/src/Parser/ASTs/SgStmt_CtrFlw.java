package Parser.ASTs;

import Parser.AST;

public class SgStmt_CtrFlw extends AST {
	en_Ctrflw t_ctrflw;
	public en_Ctrflw getCFT() {
		return t_ctrflw;
	}

	public void setCFT(en_Ctrflw t_ctrflw) {
		this.t_ctrflw = t_ctrflw;
	}

	public Expr_Calc getRtExp() {
		return return_exp;
	}

	public void setRtExp(Expr_Calc return_exp) {
		this.return_exp = return_exp;
	}

	Expr_Calc return_exp;
	
	public enum en_Ctrflw{t_break,t_continue,t_return,t_returnExp;}
}
