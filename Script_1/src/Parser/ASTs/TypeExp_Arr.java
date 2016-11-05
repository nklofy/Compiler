package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class TypeExp_Arr extends AST {
	TypeExp type_pre;
	String ele_type;
	String rst_type;
	int dim=1;
	
	public TypeExp getPreType() {
		return type_pre;
	}
	public void setPreType(TypeExp type_pre) {
		this.type_pre = type_pre;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{		
		//IRCode code=new IRCode("ArrType",this.rst_type,this.ele_type,String.valueOf(this.dim));
		//codegen.addCode(code);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(this.type_pre!=null){
			if(!this.type_pre.genSymTb(codegen))return false;			
			if(this.type_pre.getASTType().equals("TypeExp_Arr")){
				this.dim=this.type_pre.type_array.dim+1;
			}
			else return true;
		}			
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(this.type_pre!=null&&!this.type_pre.checkType(codegen))return false;
		if(this.type_pre.getASTType().equals("TypeExp_Arr")){
			this.ele_type=this.type_pre.type_array.ele_type;
		}else{
			this.ele_type=this.type_pre.rst_type;
		}
		this.rst_type=this.ele_type+"["+this.dim+"]";
		T_Array t=new T_Array();
		t.setDims(this.dim);
		t.setEleType(this.ele_type);
		codegen.putTypeInSymTb(this.rst_type, t);
		t.setKType(T_Type.KType.t_arr);
		t.setTypeSig(this.rst_type);
		return true;
	}
	
	
	
}
