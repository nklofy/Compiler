package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class TypeExp_Arr extends AST {
	TypeExp type_pre;
	String ele_type;
	String rst_type;
	int dim;
	
	public TypeExp getPreType() {
		return type_pre;
	}
	public void setPreType(TypeExp type_pre) {
		this.type_pre = type_pre;
	}
	public boolean genCode(CodeGenerator codegen){		
		IRCode code=new IRCode("ArrType",this.rst_type,this.ele_type,String.valueOf(this.dim));
		codegen.addCode(code);
		codegen.incLineNo();
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(!this.type_pre.checkType(codegen))return false;
		T_Type t=codegen.getRTType(this.type_pre.rst_type);
		T_Array t1=null;
		if(t.isArray()){
			t1=((T_Array)t);
			t1.incDims();
			this.ele_type=t1.getEleType().getTypeName();
		}else{
			t1=new T_Array();
			t1.setEleType(t);
			t1.setDims(1);
			this.ele_type=t1.getTypeName();
		}
		this.rst_type="["+codegen.getTmpSn();			
		codegen.addRTType(this.rst_type, t1);
		t1.setArray(true);
		this.dim=t1.getDims();
		return true;
	}
	
	
	
}
