package Parser.ASTs;

import Parser.AST;

public class Stmt_Sg extends AST {
	SgStmt_AsnVar asn_var; 
	SgStmt_DefVar def_var;
	SgStmt_CtrFlw ctrflw;
	Expr_Calc exp_calc;
	public boolean setSg(AST ast){
		switch(this.getASTType()){
		case "SgStmt_AsnVar":
			this.asn_var=(SgStmt_AsnVar)ast;			
			break;
		case "SgStmt_DefVar":
			this.def_var=(SgStmt_DefVar)ast;
			if(ast.getMergedAsts()!=null){
				break;
			}
			for(String s:ast.getVarUp()){
				if(this.getVarTb()!=null && this.getVarTb().keySet().contains(s)){
					System.out.println("error existing symbol name: "+ s);
				}else{
					this.putVarTb(s, ast.getVarTb().get(s));
					this.addVarUp(s);
				}
			}
			break;
		case "SgStmt_CtrFlw":
			this.ctrflw=(SgStmt_CtrFlw)ast;			
			break;
		case "Expr_Calc":
			this.exp_calc=(Expr_Calc)ast;			
			break;
		}
		return true;
	}
}
