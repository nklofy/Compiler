package Parser.ASTs;

import java.util.*;
import Parser.*;
import Parser.ASTs.Scp_Info.en_Scp;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

public class Scp_InfoLst extends AST {
	boolean isE=false;
	public boolean isE() {
		return isE;
	}
	public void setE() {
		this.isE = true;
	}
	LinkedList<Scp_Info> scp_infos;
	public void addScp(Scp_Info scp){
		if(this.scp_infos==null){
			this.scp_infos=new LinkedList<Scp_Info>();
		}
		this.scp_infos.add(scp);
	}
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
