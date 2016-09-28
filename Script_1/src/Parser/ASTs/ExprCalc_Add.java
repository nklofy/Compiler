package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class ExprCalc_Add extends AST {
	ExprCalc_Add add_1;
	ExprCalc_Add add_2;
	String opt;
	ExprCalc_Unary unary;
	ExprAccs accs;
	en_Add t_Add;
	String rst_val;
	String rst_type;
	String ref_type;
	
	public boolean setBiAdd(ExprCalc_Add add_1,	String opt, ExprCalc_Add add_2){
		this.t_Add=en_Add.t_biAdd;
		this.add_1=add_1;
		this.add_2=add_2;
		this.opt=opt;
		return true;
	}
	public boolean setBiMul(ExprCalc_Add add_1,	String opt, ExprAccs accs){
		this.t_Add=en_Add.t_biMul;
		this.add_1=add_1;
		this.accs=accs;
		this.opt=opt;
		return true;
	}
	public boolean setUnAdd(ExprCalc_Add add_1,	String opt){
		this.t_Add=en_Add.t_un;
		this.add_1=add_1;
		this.opt=opt;
		return true;
	}
	public void setUnary(ExprCalc_Unary unary) {
		this.t_Add=en_Add.t_un;
		this.unary = unary;
	}
	public enum en_Add{t_biAdd,t_biMul,t_un}
	
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		String s=null;
		IRCode code=null;
		if(this.ref_type!=null){
			if(this.ref_type.equals("int")){
				s="i";
			}else if(this.ref_type.equals("double")){
				s="d";
			}else
				s=this.ref_type;
		}
		switch(this.t_Add){
		case t_biAdd:
			this.add_1.genCode(codegen);
			this.add_2.genCode(codegen);
			if(this.opt.equals("+")){
				code=new IRCode("add_"+s,this.rst_val,this.add_1.rst_val,this.add_2.rst_val);
			}else if(this.opt.equals("-")){
				code=new IRCode("sub_"+s,this.rst_val,this.add_1.rst_val,this.add_2.rst_val);
			}
			codegen.addCode(code);
			codegen.incLineNo();
			break;
		case t_biMul:
			this.add_1.genCode(codegen);
			this.accs.genCode(codegen);
			if(this.opt.equals("*")){
				code=new IRCode("mul_"+s,this.rst_val,this.add_1.rst_val,this.accs.rst_val);
				codegen.addCode(code);
				codegen.incLineNo();
			}else if(this.opt.equals("/")){
				code=new IRCode("div_"+s,this.rst_val,this.add_1.rst_val,this.accs.rst_val);
				codegen.addCode(code);
				codegen.incLineNo();
			}
			break;
		case t_un:
			if(this.opt==null){
				this.unary.genCode(codegen);
			}else{
				this.add_1.genCode(codegen);
				if(this.opt.equals("-")){
					code=new IRCode("minus"+s,this.rst_val,this.add_1.rst_val,null);
					codegen.addCode(code);
					codegen.incLineNo();
				}else{

				}
			}
			break;
		default:
			break;
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(this.t_Add!=en_Add.t_un||this.opt!=null){
			R_Variable r=new R_Variable();
			this.rst_val="%"+codegen.getTmpSn();
			r.setVarName(this.rst_val);
			r.setTmpAddr(this.rst_val);
			r.setVarType(this.ref_type);
			this.rst_type=this.ref_type;//TODO
			codegen.putVarInSymTb(this.rst_val, r);
		}
		switch(this.t_Add){
		case t_biAdd:
			if(!this.add_1.genSymTb(codegen)||!this.add_2.genSymTb(codegen))
				return false;
			this.add_1.ref_type=this.ref_type;
			this.add_2.ref_type=this.ref_type;
			break;
		case t_biMul:
			if(!this.add_1.genSymTb(codegen)||!this.accs.genSymTb(codegen))
				return false;
			this.add_1.ref_type=this.ref_type;
			this.accs.ref_type=this.ref_type;
			break;
		case t_un:
			if(this.opt==null){
				this.unary.ref_type=this.ref_type;
				if(!this.unary.genSymTb(codegen))
					return false;
				this.rst_type=this.unary.rst_type;
				this.rst_val=this.unary.rst_val;
			}else{
				this.add_1.ref_type=this.ref_type;
				if(!this.add_1.genSymTb(codegen))
					return false;
				if(this.opt.equals("+")){
					this.rst_type=this.add_1.rst_type;
					this.rst_val=this.add_1.rst_val;
				}				
			}
			break;
		default:
			break;
		}
		return true;
	}
	public boolean checkType (CodeGenerator codegen) throws TypeCheckException{
		switch(this.t_Add){
		case t_biAdd:
			if(!this.add_1.checkType(codegen)||!this.add_2.checkType(codegen))
				return false;	
			T_Type t0=codegen.getTypeInSymTb(this.ref_type);
			T_Type t1=codegen.getTypeInSymTb(this.add_1.rst_type);
			T_Type t2=codegen.getTypeInSymTb(this.add_2.rst_type);
			if(t0!=null&&!TypeChecker.checkCast(codegen, t0, TypeChecker.checkOptFour(codegen, this.opt, t1, t2)))
				return false;
			break;
		case t_biMul:
			if(!this.add_1.checkType(codegen)||!this.accs.checkType(codegen))
				return false;
			t0=codegen.getTypeInSymTb(this.ref_type);
			t1=codegen.getTypeInSymTb(this.add_1.rst_type);
			t2=codegen.getTypeInSymTb(this.accs.rst_type);
			if(t0!=null&&!TypeChecker.checkCast(codegen, t0, TypeChecker.checkOptFour(codegen, this.opt, t1, t2)))
				return false;
			break;
		case t_un:
			if(this.opt==null){
				if(!this.unary.checkType(codegen))
					return false;	
				this.rst_type=this.unary.rst_type;
			}else{
				if(!this.add_1.checkType(codegen))
					return false;
				this.rst_type=this.add_1.rst_type;
				t0=codegen.getTypeInSymTb(this.ref_type);
				t1=codegen.getTypeInSymTb(this.add_1.rst_type);
				if(t0!=null&&!TypeChecker.checkCast(codegen, t0,t1)&&!TypeChecker.checkOptMinus(codegen, t1))
					return false;
			}
			break;
		default:
			break;
		}
		return true;
	}
}
