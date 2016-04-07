package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class ExprAccs_Fld extends AST {
	ExprAccs_Fld pre_fld;
	ExprPri_Var var;
	String sign;
	String rst_val;
	String ref_type;
	String rst_type;
	
	public void setAccs(ExprAccs_Fld pre_fld,ExprPri_Var var,String sign){
		this.pre_fld=pre_fld;
		this.var=var;
		this.sign=sign;
	}
	public boolean genCode(CodeGenerator codegen){
		IRCode code=null;
		if(this.pre_fld!=null){
			this.pre_fld.genCode(codegen);
			if(this.var!=null){
				code=new IRCode("getField",this.rst_val,this.pre_fld.rst_val,this.var.name);
			}else if(sign.equals("class")){
				code=new IRCode("getClass",this.rst_val,this.pre_fld.rst_val,null);
			}
		}else{
			if(this.var!=null){
				
			}else if(sign.equals("super")){
				
			}else if(sign.equals("this")){
				
			}
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		if(this.pre_fld!=null){
			if(!this.pre_fld.genSymTb(codegen)){
				return false;
			}
		}
		if(this.var!=null){
			if(!this.var.genSymTb(codegen)){
				return false;
			}
			if(this.pre_fld==null){
				this.rst_val=this.var.rst_val;
				this.rst_type=this.var.rst_type;
				this.var.ref_type=this.ref_type;
				return true;
			}
		}
		if(sign.equals("super")){
			this.rst_val="super";
		}else if(sign.equals("this")){
			this.rst_val="this";
		}else if(sign.equals("class")){
			this.rst_type="class";
			this.rst_val=this.pre_fld.rst_val;
		}else
			return false;
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(this.pre_fld!=null){
			if(!this.pre_fld.checkType(codegen)){
				return false;
			}
			if(this.var!=null){
				//find var as field in pre_fld
				R_Variable r=codegen.getVarInSymTb(this.pre_fld.rst_val);
				T_Type t=codegen.getTypeInSymTb(r.getVarType());
				if(t.getKType()!=T_Type.KType.t_cls)
					return false;
				R_Variable r1=((T_Class)t).getFields().get(var.name);
				if(r1==null)return false;
				this.rst_type=r1.getVarType();
				T_Type t1=codegen.getTypeInSymTb(r1.getVarType());
				if(this.ref_type!=null&&!codegen.canAsn(codegen.getTypeInSymTb(this.ref_type), t1))
					return false;
				this.rst_val="%"+codegen.getTmpSn();
				R_Variable r0=new R_Variable();
				r0.setVarName(this.rst_val);
				r0.setVarType(this.rst_type);
				codegen.putVarInSymTb(this.rst_val, r0);
			}else if(this.sign.equals("class")){
				if(!this.ref_type.equals("class")){
					return false;
				}
			}
		}else if(this.var!=null){
			if(this.var.checkType(codegen)){
				return false;
			}
			if(!codegen.canAsn(codegen.getTypeInSymTb(this.ref_type), codegen.getTypeInSymTb(this.rst_type)))
				return false;
		}else if(sign.equals("super")){
			
		}else if(sign.equals("this")){
			
		}else
			return false;
		return true;
	}
}
