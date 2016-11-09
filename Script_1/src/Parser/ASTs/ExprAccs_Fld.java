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
	boolean inGType=false;
	
	public void setAccs(ExprAccs_Fld pre_fld,ExprPri_Var var,String sign){
		this.pre_fld=pre_fld;
		this.var=var;
		this.sign=sign;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		IRCode code=null;
		if(this.pre_fld!=null){
			this.pre_fld.genCode(codegen);
			if(this.var!=null){
				code=new IRCode("getField",this.rst_val,this.pre_fld.rst_val,this.var.name);
			}else if(sign.equals("class")){
				code=new IRCode("getClass",this.rst_val,this.pre_fld.rst_val,null);
			}
			codegen.addCode(code);
		}else{
			if(this.var!=null){
				this.var.genCode(codegen);
			}else if(sign.equals("super")){
				
			}else if(sign.equals("this")){
				
			}
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(this.pre_fld!=null){
			if(!this.pre_fld.genSymTb(codegen)){
				return false;
			}
		}
		if(this.var!=null){
			if(!this.var.genSymTb(codegen)) return false;
			if(this.pre_fld==null){		//a
				//this.var.ref_type=this.ref_type;
				this.rst_val=this.var.rst_val;
				//this.rst_type=this.var.rst_type;
				return true;
			}
			else
				this.rst_val="%"+codegen.getTmpSn();
		}
		else if(sign.equals("super")){
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
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(this.pre_fld!=null){		//
			if(!this.pre_fld.checkType(codegen)){
				return false;
			}
			T_Type t=null;
			if(!this.pre_fld.rst_val.equals("this")&&this.var!=null){	// A.b a.b
				if(codegen.getVarInSymTb(this.pre_fld.rst_val)!=null){//a.b
					R_Variable r=codegen.getVarInSymTb(this.pre_fld.rst_val);
					t=codegen.getTypeInSymTb(r.getVarType());
					if(t.getKType()==T_Type.KType.t_cls){
						//
					}else if(t.getKType()==T_Type.KType.t_gnrc){
						T_Generic t1=(T_Generic)t;
						codegen.gnrc_arg.addFirst(t1.getTypeArgTb());
						t=codegen.getTypeInSymTb(t1.getCoreType());
						this.inGType=true;
						if(t.getKType()!=T_Type.KType.t_cls)throw new TypeCheckException("TypeCheck Error: ");
					}else
						throw new TypeCheckException("TypeCheck Error: ");
				}else if(codegen.getTypeInSymTb(this.pre_fld.rst_val)!=null){//A.b
					t=codegen.getTypeInSymTb(this.pre_fld.rst_val);
					if(t.getKType()==T_Type.KType.t_cls){
						//
					}else if(t.getKType()==T_Type.KType.t_gnrc){
						T_Generic t1=(T_Generic)t;
						codegen.gnrc_arg.addFirst(t1.getTypeArgTb());
						this.inGType=true;
					}else
						throw new TypeCheckException("TypeCheck Error: ");
				}
			}else if(this.sign!=null&&this.sign.equals("class")){ 	//A.class
				if(!this.ref_type.equals("class")){
					throw new TypeCheckException("TypeCheck Error: ");
				}
				T_Type t1 =codegen.getTypeInSymTb(this.pre_fld.rst_val);
				if(t1.getKType()==T_Type.KType.t_cls){
					return true;
				}else if(t1.getKType()==T_Type.KType.t_gnrc){
					if(codegen.getTypeInSymTb(((T_Generic)t1).getCoreType()).getKType()==T_Type.KType.t_gnrc)
						return true;
				}else
					throw new TypeCheckException("TypeCheck Error: ");		
			}
			else{	//this.a ...
				t=codegen.getTypeInSymTb(codegen.getThisCls());
			}
			R_Variable r1=((T_Class)t).getField(codegen,var.name);
			if(r1==null)throw new TypeCheckException("Type error: not defined field in class "+codegen.getThisCls()+" "+var.name);
			this.rst_type=r1.getVarType();
			T_Type t1;
			if(this.inGType){
				t1=codegen.getTypeInSymTb(codegen.FindGnrcArgTb(this.rst_type));
			}else{
				t1=codegen.getTypeInSymTb(this.rst_type);
			}
			if(this.ref_type!=null&&!codegen.getTypeInSymTb(this.ref_type).canAsnFrom(codegen, t1))
				throw new TypeCheckException("TypeCheck Error: cannt asn "+this.rst_type+" to "+this.ref_type);
			//if(this.ref_type==null){
			//	this.ref_type=this.rst_type;
			//}
			R_Variable r0=new R_Variable();
			r0.setVarName(this.rst_val);
			r0.setVarType(this.rst_type);
			codegen.putVarInSymTb(this.rst_val, r0);
			if(this.inGType)
				codegen.gnrc_arg.remove();
			return true;
		}	//if(this.pre_fld!=null)
		else if(this.var!=null){	//a...
			if(codegen.isScopeIn("class")){
				T_Type t=codegen.getTypeInSymTb(codegen.getThisCls());
				R_Variable r1=((T_Class)t).getField(codegen,var.name);
				if(r1==null)throw new TypeCheckException("Type error: not defined field "+var.name+" in "+codegen.getThisCls());
				this.rst_type=r1.getVarType();
			}
			else{
				if(!this.var.checkType(codegen))	
						return false;
				this.rst_type=codegen.getVarInSymTb(var.name).getVarType();
			}
			T_Type t1=codegen.getTypeInSymTb(this.rst_type);
			if(this.ref_type!=null&&!codegen.getTypeInSymTb(this.ref_type).canAsnFrom(codegen, t1))
				throw new TypeCheckException("TypeCheck Error: ");
		}else if(sign.equals("super")){	//super...
			
		}else if(sign.equals("this")){	//this....
			
		}else
			throw new TypeCheckException("TypeCheck Error: ");
		return true;
	}
}
