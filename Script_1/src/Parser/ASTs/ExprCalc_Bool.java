package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class ExprCalc_Bool extends AST {
	ExprCalc_Add add_1;
	ExprCalc_Add add_2;
	ExprCalc_Bool bool_1;
	ExprCalc_Bool bool_2;
	String opt;
	boolean isTrue=false;
	public en_Bl t_exp;
	String rst_val;
	String rst_type;
	String ref_type;
	
	public boolean setBiBool(ExprCalc_Bool bool_1,String opt,ExprCalc_Bool bool_2){
		this.t_exp=en_Bl.t_biBool;
		this.bool_1=bool_1;
		this.bool_2=bool_2;
		this.opt=opt;
		return true;
	}
	public boolean setBiCmp(ExprCalc_Add add_1,String opt,ExprCalc_Add add_2){
		this.t_exp=en_Bl.t_biCmp;
		this.add_1=add_1;
		this.add_2=add_2;
		this.opt=opt;
		return true;
	}
	public boolean setAdd(ExprCalc_Add add_1){
		this.t_exp=en_Bl.t_un;
		this.add_1=add_1;
		return true;
	}
	public boolean setUnAdd(ExprCalc_Add add_1,String opt){
		this.t_exp=en_Bl.t_un;
		this.add_1=add_1;
		this.opt=opt;
		return true;
	}
	public boolean setTrue(){
		this.t_exp=en_Bl.t_cnst;
		this.isTrue=true;
		return true;
	}
	public boolean setFalse(){
		this.t_exp=en_Bl.t_cnst;
		return true;
	}
	public String getVal(){
		return null;
	}
	public enum en_Bl{t_cnst,t_biCmp,t_biBool,t_un}
	
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		switch(this.t_exp){
		case t_biBool:
			this.bool_1.genCode(codegen);
			this.bool_2.genCode(codegen);
			IRCode code=null;
			if(this.opt.equals("||")){
				code=new IRCode("AND",this.rst_val,this.bool_1.rst_val,this.bool_2.rst_val);
			}else if(this.opt.equals("&&")){
				code=new IRCode("OR",this.rst_val,this.bool_1.rst_val,this.bool_2.rst_val);
			}
			codegen.addCode(code);
			codegen.incLineNo();
			break;
		case t_biCmp:
			this.add_1.genCode(codegen);
			this.add_2.genCode(codegen);
			code=null;
			switch(this.opt){
			case "<":
				code=new IRCode("LT",this.rst_val,this.add_1.rst_val,this.add_2.rst_val);
				break;
			case ">":
				code=new IRCode("GT",this.rst_val,this.add_1.rst_val,this.add_2.rst_val);
				break;
			case ">=":
				code=new IRCode("GE",this.rst_val,this.add_1.rst_val,this.add_2.rst_val);
				break;
			case "<=":
				code=new IRCode("LE",this.rst_val,this.add_1.rst_val,this.add_2.rst_val);
				break;
			case "==":
				code=new IRCode("EQ",this.rst_val,this.add_1.rst_val,this.add_2.rst_val);
				break;
			case "!=":
				code=new IRCode("NE",this.rst_val,this.add_1.rst_val,this.add_2.rst_val);
				break;
			default:
				break;
			}
			codegen.addCode(code);
			codegen.incLineNo();
			break;
		case t_un:
			this.add_1.genCode(codegen);
			code=null;
			if(this.opt!=null&&this.opt.equals("!")){
				code=new IRCode("NOT",this.rst_val,this.add_1.rst_val,null);
				codegen.addCode(code);
				codegen.incLineNo();
			}
			break;
		case t_cnst:
			
			break;
		default:
			break;
		}
		return true;
	}
	
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(this.t_exp!=en_Bl.t_cnst&&this.t_exp!=en_Bl.t_un||this.opt!=null){
			R_Variable r=new R_Variable();
			this.rst_val="%"+codegen.getTmpSn();
			r.setVarName(this.rst_val);
			r.setTmpAddr(this.rst_val);
			r.setVarType("bool");
			codegen.putVarInSymTb(this.rst_val, r);
			this.rst_type="bool";
		}
		switch(this.t_exp){
		case t_biBool:
			if(!this.bool_1.genSymTb(codegen)||!this.bool_2.genSymTb(codegen))
				return false;	
			this.bool_1.ref_type="bool";
			this.bool_2.ref_type="bool";
			break;
		case t_biCmp:
			if(!this.add_1.genSymTb(codegen)||!this.add_2.genSymTb(codegen))
				return false;	
			//this.add_1.ref_type="bool";
			//this.add_2.ref_type="bool";		
			break;
		case t_un:
			
			if(this.opt!=null&&this.opt.equals("!")){
				this.add_1.ref_type="bool";
				if(!this.add_1.genSymTb(codegen))
					return false;
			}else{
				if(!this.add_1.genSymTb(codegen))
					return false;
				this.rst_val=this.add_1.rst_val;
				this.rst_type=this.add_1.rst_type;
			}
			break;
		case t_cnst:
			if(this.isTrue)
				this.rst_val="true";//or set the r_variable has a value of "true"? 
			else
				this.rst_val="false";
			this.rst_type="bool";
			break;
		default:
			break;
		}
		return true;
	}
	
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{//TODO
		//if(this.ref_type!=null&&!this.ref_type.equals("bool"))//something wrong
		//	return false;
		switch(this.t_exp){
		case t_biBool:
			if(!this.bool_1.checkType(codegen)||!this.bool_2.checkType(codegen)
					||!this.bool_1.rst_type.equals("bool")||!this.bool_2.rst_type.equals("bool"))
				return false;
			if(this.ref_type!=null&&!this.ref_type.equals("bool"))//this exp should has bool ref type if it has
					return false;
			break;
		case t_biCmp:
			if(!this.add_1.checkType(codegen)||!this.add_2.checkType(codegen)
					//||!this.add_1.rst_type.equals("bool")||!this.add_2.rst_type.equals("bool")
					)
				return false;
			if(this.ref_type!=null&&!this.ref_type.equals("bool"))//this exp should has bool ref type if it has
				return false;
			break;
		case t_un:
			if(this.opt!=null&&this.opt.equals("!"))
				this.add_1.ref_type="bool";
			else
				this.add_1.ref_type=this.ref_type;
			if(!this.add_1.checkType(codegen)//||!this.add_1.rst_type.equals("bool")
					)
				return false;
			break;
		case t_cnst:
			if(this.ref_type!=null&&!this.ref_type.equals("bool"))
				return false;
			break;
		default:
			break;
		}		
		return true;
	}
}
