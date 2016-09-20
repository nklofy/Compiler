package Parser.ASTs;

import Parser.AST;
import Parser.CodeGenerator;

public class Stmt_Sg extends AST {
	SgStmt_AsgnVar asn_var; 
	SgStmt_DefVar def_var;
	SgStmt_CtrFlw ctrflw;
	Expr_Calc exp_calc;
	
	public boolean setSg(AST ast){
		switch(this.getASTType()){
		case "SgStmt_AsgnVar":
			this.asn_var=(SgStmt_AsgnVar)ast;			
			break;
		case "SgStmt_DefVar":
			this.def_var=(SgStmt_DefVar)ast;
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
		switch(this.getASTType()){
		case "SgStmt_AsgnVar":
			this.asn_var.genCode(codegen);			
			break;
		case "SgStmt_DefVar":
			this.def_var.genCode(codegen);
			break;
		case "SgStmt_CtrFlw":
			this.ctrflw.genCode(codegen);
			break;
		case "Expr_Calc":
			this.exp_calc.genCode(codegen);
			break;
		default:break;
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		switch(this.getASTType()){
		case "SgStmt_AsgnVar":
			return this.asn_var.genSymTb(codegen);
		case "SgStmt_DefVar":
			return this.def_var.genSymTb(codegen);
		case "SgStmt_CtrFlw":
			return this.ctrflw.genSymTb(codegen);
		case "Expr_Calc":
			return this.exp_calc.genSymTb(codegen);
		default:return false;
		}
	}
	public boolean checkType(CodeGenerator codegen){
		switch(this.getASTType()){
		case "SgStmt_AsgnVar":
			return this.asn_var.checkType(codegen);
		case "SgStmt_DefVar":
			return this.def_var.checkType(codegen);
		case "SgStmt_CtrFlw":
			return this.ctrflw.checkType(codegen);
		case "Expr_Calc":
			return this.exp_calc.checkType(codegen);
		default:return false;
		}
	}
}
