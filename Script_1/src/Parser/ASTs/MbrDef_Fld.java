
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
		for(String s:var_def.getVarUp()){
			if(this.getVarTb()!=null && this.getVarTb().keySet().contains(s)){
				System.out.println("error existing symbol name: "+ s);
			}else{
				this.putVarTb(s, var_def.getVarTb().get(s));
			}
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
