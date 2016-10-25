package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

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
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		this.func_def.genCode(codegen);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(this.scps!=null&&!this.scps.genSymTb(codegen))
			return false;
		if(this.func_def!=null){
			if(!this.func_def.genSymTb(codegen))
				return false;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(this.scps!=null&&!this.scps.checkType(codegen))
			return false;
		if(this.func_def!=null&&!this.func_def.checkType(codegen))
			return false;
		return true;
	}
}
