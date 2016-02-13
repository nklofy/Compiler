package Parser.ASTs;

import Parser.AST;
import Parser.CodeGenerator;

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
			this.upAll(ast);
			break;
		case "SgStmt_CtrFlw":
			this.ctrflw=(SgStmt_CtrFlw)ast;			
			break;
		case "Expr_Calc":
			this.exp_calc=(Expr_Calc)ast;			
			break;
		default:break;
		}
		return true;
	}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return true;
	}
}
