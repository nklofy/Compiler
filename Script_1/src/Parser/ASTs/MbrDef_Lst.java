package Parser.ASTs;

import java.util.*;
import Parser.*;
import Parser.TypeSys.*;

public class MbrDef_Lst extends AST {
	boolean isE=false;
	LinkedList<MbrDef> mbrs;
	HashMap<String,T_Type> fields;
	HashMap<String,R_Function> methods; 
	
	public void addMbr(MbrDef par){
		if(this.mbrs==null){
			this.mbrs=new LinkedList<MbrDef>();			
		}
		this.mbrs.add(par);
		this.upAll(par);
	}
	public boolean isE() {
		return isE;
	}
	public void setE() {
		this.isE = true;
	}
	public boolean genCode(CodeGenerator codegen){
		
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		
		return true;
	}
}
