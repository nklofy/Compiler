package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class ExprCalc_NewArr extends AST {
	TypeExp type_exp;
	NewArr_DimLst dim_lst;
	NewArr_InitLst init_lst;
	String rst_val;
	String rst_type;
	String ref_type;
	
	public boolean setNewArr(TypeExp type_exp,NewArr_DimLst dim_lst,NewArr_InitLst init_lst){
		this.type_exp=type_exp;
		this.dim_lst=dim_lst;
		this.init_lst=init_lst;
		return true;
	}
	
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		if(this.type_exp!=null&&this.dim_lst!=null){
			this.type_exp.genCode(codegen);	
			this.dim_lst.genCode(codegen);
			IRCode code=new IRCode("newArr",this.type_exp.rst_type,this.rst_val,this.dim_lst.rst_val);
			codegen.addCode(code);
		}
		if(this.init_lst!=null){
			this.init_lst.genCode(codegen);
			IRCode code=new IRCode("newArrInit",this.rst_val,this.init_lst.rst_val,null);
			codegen.addCode(code);
		}
		return false;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(this.type_exp!=null&&this.dim_lst!=null){
			if(!this.type_exp.genSymTb(codegen))
				return false;
			if(!this.dim_lst.genSymTb(codegen))
				return false;
			this.rst_type="["+codegen.getTmpSn();
			T_Array t=new T_Array();
			t.setEleType(this.type_exp.rst_type);
			t.setDims(this.dim_lst.dims.size());
			codegen.putTypeInSymTb(this.rst_type, t);			
		}
		if(this.init_lst!=null){
			if(!this.init_lst.genSymTb(codegen))
				return false;
			this.rst_type=this.init_lst.rst_type;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(this.type_exp!=null&&this.dim_lst!=null){
			if(!this.type_exp.checkType(codegen))
				return false;
			if(!this.dim_lst.checkType(codegen))
				return false;
		}
		if(this.init_lst!=null){
			if(!this.init_lst.checkType(codegen))
				return false;
		}
		if(!codegen.getTypeInSymTb(this.rst_type).getTypeSig().equals(
				codegen.getTypeInSymTb(this.ref_type).getTypeSig()))
			return false;
		return true;
	}
}
