package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp_Arr extends AST {
	TypeExp type_pre;
	String ret_type;
	
	public TypeExp getPreType() {
		return type_pre;
	}
	public void setPreType(TypeExp type_pre) {
		this.type_pre = type_pre;
	}
	public boolean genCode(CodeGenerator codegen){		
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		boolean b=this.type_pre.checkType(codegen);
		T_Type t=codegen.getRTType(this.type_pre.ret_type);
		T_Array t1=null;
		if(t.isArray){
			t1=((T_Array)t);
			t1.incDims();			
		}else{
			t1=new T_Array();
			t1.setEleType(t);
			t1.setDims(1);
		}
		this.ret_type="&"+codegen.getTmpSn();			
		codegen.addRTType(this.ret_type, t1);
		return b;
	}
	
	
	
}
