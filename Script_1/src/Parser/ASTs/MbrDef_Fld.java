
package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

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
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(this.scps!=null){
			if(!this.scps.genSymTb(codegen))
				return false;
		}
		if(this.var_def!=null){
			if(!this.var_def.genSymTb(codegen))
				return false;			
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(this.scps!=null){
			if(!this.scps.checkType(codegen))
				return false;
		}
		if(this.var_def!=null){
			if(!this.var_def.checkType(codegen))
				return false;
		}
		return true;
	}
}
