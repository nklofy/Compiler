package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class ExprAccs_Arr extends AST {
	ExprAccs_Fld pre_fld;
	NewArr_DimLst dim_lst;
	String rst_val;
	String ref_type;
	String rst_type;
	
	public void setAccs(ExprAccs_Fld pre_fld,NewArr_DimLst dim_lst){
		this.pre_fld=pre_fld;
		this.dim_lst=dim_lst;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		this.pre_fld.genCode(codegen);
		this.dim_lst.genCode(codegen);
		IRCode code =new IRCode("getArray",this.rst_val,this.pre_fld.rst_val,this.dim_lst.rst_val);
		codegen.addCode(code);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(!this.pre_fld.genSymTb(codegen))
			return false;
		if(!this.dim_lst.genSymTb(codegen))
			return false;
		//this.rst_type="["+codegen.getTmpSn();
		this.rst_val="%"+codegen.getTmpSn();
		T_Array t=new T_Array();
		t.setKType(T_Type.KType.t_arr);
		t.setTypeName(this.rst_type);
		R_Variable r=new R_Variable();
		r.setVarName(this.rst_val);
		r.setVarType(this.rst_type);
		codegen.putVarInSymTb(this.rst_val, r);
		codegen.putTypeInSymTb(this.rst_type, t);
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(!this.pre_fld.checkType(codegen))
			return false;
		if(!this.dim_lst.checkType(codegen))
			return false;
		T_Type t1=codegen.getTypeInSymTb(this.pre_fld.rst_type);
		if(t1.getKType()!=T_Type.KType.t_arr)
			return false;
		int d1=((T_Array)t1).getDims();//pre_field's dimension
		if(d1<this.dim_lst.dim_n)
			return false;
		int d2=d1-this.dim_lst.dims.size();//rst_val's dimension
		if(d2==0){
			this.rst_type=((T_Array)t1).getEleType();
			if(this.ref_type==null)return true;
			if(codegen.getTypeInSymTb(this.ref_type).canAsnFrom(codegen,codegen.getTypeInSymTb(this.rst_type)))
				return true;
			else
				throw new TypeCheckException("type error: array exp "+this.pre_fld.rst_val);
		}else{
			String elet=((T_Array)t1).getEleType()+"["+d2+"]";
			this.rst_type=elet;
			if(this.ref_type==null)return true;
			T_Type t2=codegen.getTypeInSymTb(this.ref_type);
			if(t2.getKType()==T_Type.KType.t_arr&&((T_Array)t2).getEleType().equals(elet)&&((T_Array)t2).getDims()==d2){
				return true;
			}else
				throw new TypeCheckException("type error: array exp "+this.pre_fld.rst_val);			
		}
	}
}
