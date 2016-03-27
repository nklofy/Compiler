package Parser.ASTs;

import Parser.*;

public class MbrDef_Mthd extends AST {
	Scp_InfoLst scps;
	Stmt_DefFunc func_def;
	public boolean setMthd(Scp_InfoLst scps,Stmt_DefFunc func_def){
		this.scps=scps;
		this.func_def=func_def;
/*		if(func_def.getMergedAsts()!=null){
			return false;
		}
		for(String s:func_def.getFuncUp()){
			if(this.getFuncTb()!=null && this.getFuncTb().keySet().contains(s)){
				System.out.println("error existing symbol name: "+ s);
			}else{
				this.putFuncTb(s, func_def.getFuncTb().get(s));
			}
		}*/
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
