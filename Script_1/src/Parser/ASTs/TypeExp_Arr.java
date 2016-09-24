package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class TypeExp_Arr extends AST {
	TypeExp type_pre;
	String ele_type;
	String rst_type;
	int dim;
	T_Type t_type;
	
	public TypeExp getPreType() {
		return type_pre;
	}
	public void setPreType(TypeExp type_pre) {
		this.type_pre = type_pre;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{		
		IRCode code=new IRCode("ArrType",this.rst_type,this.ele_type,String.valueOf(this.dim));
		codegen.addCode(code);
		codegen.incLineNo();
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(!this.type_pre.genSymTb(codegen))return false;
		T_Type t=codegen.getTypeInSymTb(this.type_pre.rst_type);
		T_Array t1=null;
		if(t.getKType()==T_Type.KType.t_arr){
			t1=((T_Array)t);
			t1.incDims();
			this.ele_type=codegen.getTypeInSymTb(t1.getEleType()).getTypeName();
		}else{
			t1=new T_Array();
			t1.setDims(1);
			this.ele_type=t1.getTypeName();
		}
		this.rst_type="["+codegen.getTmpSn();			
		codegen.putTypeInSymTb(this.rst_type, t1);
		t1.setKType(T_Type.KType.t_arr);
		this.dim=t1.getDims();
		t1.setTypeSig(this.ele_type+"["+this.dim+"]");
		this.t_type=t1;
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		return this.type_pre.checkType(codegen);
	}
	
	
	
}
