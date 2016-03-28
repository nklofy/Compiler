
package Parser.ASTs;

import Parser.*;

public class MbrDef_Fld extends AST {
	Scp_InfoLst scps;
	SgStmt_DefVar var_def;
	
	public boolean setFld(Scp_InfoLst scps,SgStmt_DefVar var_def){
		this.scps=scps;
		this.var_def=var_def;
		if(var_def.getMergedAsts()!=null){
			return false;
		}
		return true;
	}
	public boolean genCode(CodeGenerator codegen){
		
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		
		return true;
	}
}
