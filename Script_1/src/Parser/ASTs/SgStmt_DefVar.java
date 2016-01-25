package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class SgStmt_DefVar extends AST {
	SgStmt_DefVar pre_def;
	TypeExp type_exp;
	ExprPri_Var var;
	Expr expression;
	public SgStmt_DefVar getPreDef() {
		return pre_def;
	}
	public void setPredef(SgStmt_DefVar var_def) {
		this.pre_def = var_def;
		if(var_def.getMergedAsts()!=null){
			return;
		}
		for(String s:var_def.getVarUp()){
			if(this.getVarTb()!=null && this.getVarTb().keySet().contains(s)){
				System.out.println("error existing symbol name: "+ s);
			}else{
				this.putVarTb(s, var_def.getVarTb().get(s));
				this.addVarUp(s);
			}
		}
	}
	public TypeExp getTypeExp() {
		return type_exp;
	}
	public void setTypeExp(TypeExp type_exp) {
		this.type_exp = type_exp;
	}
	public ExprPri_Var getVar() {
		return var;
	}
	public void setVar(ExprPri_Var var) {
		this.var = var;
		R_Variable r=new R_Variable();
		r.setTypeT(this.type_exp.getTypeT());
		this.addVarUp(this.var.name);
		this.putVarTb(this.var.name, r);
	}
	public Expr getExpr() {
		return expression;
	}
	public void setExpr(Expr expression) {
		this.expression = expression;
	}
	
}
