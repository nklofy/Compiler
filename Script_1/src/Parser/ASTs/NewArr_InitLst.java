package Parser.ASTs;

import java.util.*;
import Parser.*;

public class NewArr_InitLst extends AST {
	LinkedList<NewArr_Init> init_lst;
	String rst_val;
	String rst_type;
	
	public boolean addInit(NewArr_Init ast){
		if(this.init_lst==null){
			this.init_lst=new LinkedList<NewArr_Init>();
		}
		this.init_lst.add(ast);	
		return true;
	}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		//new type, new var, new function, put in table
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return true;
	}
}
