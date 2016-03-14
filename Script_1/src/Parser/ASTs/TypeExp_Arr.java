package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class TypeExp_Arr extends AST {
	TypeExp type_pre;
	String ele_type;
	String ret_type;
	int dims;
	
	public TypeExp getPreType() {
		return type_pre;
	}
	public void setPreType(TypeExp type_pre) {
		this.type_pre = type_pre;
	}
	public boolean genCode(CodeGenerator codegen){		
		IRCode code=new IRCode("ArrType",this.ret_type,this.ele_type,String.valueOf(this.dims));
		codegen.addCode(code);
		codegen.incLineNo();
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		boolean b=this.type_pre.checkType(codegen);
		T_Type t=codegen.getRTType(this.type_pre.ret_type);
		T_Array t1=null;
		if(t.isArray){
			t1=((T_Array)t);
			t1.incDims();
			this.ele_type=t1.getEleType().type_name;
		}else{
			t1=new T_Array();
			t1.setEleType(t);
			t1.setDims(1);
			this.ele_type=t1.type_name;
		}
		this.ret_type="["+codegen.getTmpSn();			
		codegen.addRTType(this.ret_type, t1);
		this.dims=t1.getDims();
		return b;
	}
	
	
	
}
