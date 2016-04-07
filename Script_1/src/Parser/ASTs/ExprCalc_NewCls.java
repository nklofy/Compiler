package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class ExprCalc_NewCls extends AST {
	//new idn_type ( arg_list )  new generic_type ( arg_list ) 
	TypeExp_Idn idn_type;
	TypeExp_Gnrc gnrc_type;
	FuncApp_ArgLst args;
	String rst_val;
	String rst_type;
	String ref_type;
	
	public boolean setNewCls(TypeExp_Idn idn_type, TypeExp_Gnrc gnrc_type, FuncApp_ArgLst args){
		this.idn_type=idn_type;
		this.gnrc_type=gnrc_type;
		this.args=args;
		return true;
	}
	
	public boolean genCode(CodeGenerator codegen){
		if(this.idn_type!=null){
			this.idn_type.genCode(codegen);
		}
		if(this.gnrc_type!=null){
			this.gnrc_type.genCode(codegen);
		}
		int par_count;
		if(!this.args.isE()){
			this.args.genCode(codegen);
			par_count=this.args.args.size();
		}else
			par_count=0;
		IRCode code =new IRCode("newObj",this.rst_type,this.rst_val,String.valueOf(par_count));
		codegen.addCode(code);
		codegen.incLineNo();
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		//new type, new var, new function, put in table
		if(this.idn_type!=null){
			this.idn_type.genSymTb(codegen);
			this.rst_type=this.idn_type.rst_type;
		}
		if(this.gnrc_type!=null){
			this.gnrc_type.genSymTb(codegen);
			this.rst_type=this.gnrc_type.rst_type;
		}
		if(!this.args.isE()){
			this.args.genSymTb(codegen);
		}
		R_Variable r=new R_Variable();
		this.rst_val="%"+codegen.getTmpSn();
		r.setVarName(this.rst_val);
		r.setVarType(this.rst_type);
		r.setVarName(this.rst_val);
		codegen.putVarInSymTb(this.rst_val, r);
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(this.idn_type!=null&&!this.idn_type.checkType(codegen)){
			return false;
		}
		if(this.gnrc_type!=null&&!this.gnrc_type.checkType(codegen)){
			return false;
		}
		if(!this.args.isE()&&this.args.checkType(codegen)){
			return false;
		}
		if(codegen.getTypeInSymTb(this.rst_type).getKType()!=T_Type.KType.t_cls)
			return false;
		if(!codegen.canAsn(codegen.getTypeInSymTb(this.ref_type), 
				codegen.getTypeInSymTb(this.rst_type)))
			return false;
		return true;
	}
}
