package Parser.ASTs;

import Parser.AST;
import Parser.CodeGenerator;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

public class Scp_Info extends AST {
	en_Scp scp;
	public en_Scp getScp() {
		return scp;
	}
	public void setScp(en_Scp scp) {
		this.scp = scp;
	}
	public enum en_Scp{t_static,t_public,t_private,t_final;}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		
		return true;
	}
}
