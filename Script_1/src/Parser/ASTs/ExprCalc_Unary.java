package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class ExprCalc_Unary extends AST {
	ExprAccs accs;
	String opt;
	ExprUnr_Cast cast;
	String rst_val;
	String rst_type;
	String ref_type;
	
	public boolean setUnary(ExprAccs accs,String opt){
		this.accs=accs;
		this.opt=opt;
		return true;
	}
	public boolean setCast(ExprUnr_Cast cast){
		this.cast=cast;
		return true;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		IRCode code=null;
		if(this.opt!=null){
			this.accs.genCode(codegen);
			switch(this.opt){
			case "++":
				code=new IRCode("inc_1",this.accs.rst_val,null,null);
				break;
			case "--":
				code=new IRCode("dec_1",this.accs.rst_val,null,null);
				break;
			case "++p":
				if(this.ref_type==null) this.ref_type=this.rst_type;
				IRCode code1=new IRCode("mov",this.ref_type,this.rst_val,this.accs.rst_val);
				codegen.addCode(code1);
				code=new IRCode("inc_1",this.accs.rst_val,null,null);
				break;
			case "--p":
				if(this.ref_type==null) this.ref_type=this.rst_type;
				code1=new IRCode("mov",this.ref_type,this.rst_val,this.accs.rst_val);
				codegen.addCode(code1);
				code=new IRCode("dec_1",this.accs.rst_val,null,null);
				break;
			default:
				break;
			}
			codegen.addCode(code);
		}else if(this.accs!=null){
			this.accs.genCode(codegen);
		}
		if(this.cast!=null){
			this.cast.genCode(codegen);
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(this.opt!=null){
			if(!this.accs.genSymTb(codegen))
				return false;
			if(this.opt.equals("++")||this.opt.equals("--")){
				this.rst_val=this.accs.rst_val;
			}else if(this.opt.equals("++p")||this.opt.equals("--p")){			
				this.rst_val="%"+codegen.getTmpSn();
				R_Variable r=new R_Variable();
				r.setVarType(this.ref_type);
				codegen.putVarInSymTb(this.rst_val, r);
			}				
		}else if(this.accs!=null){
			if(!this.accs.genSymTb(codegen))
				return false;
			//this.rst_type=this.accs.rst_type;
			this.rst_val=this.accs.rst_val;
		}
		if(this.cast!=null){
			if(!this.cast.genSymTb(codegen))
				return false;
			//this.rst_type=this.cast.rst_type;
			this.rst_val=this.cast.rst_val;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(this.opt!=null){
			this.accs.ref_type=this.ref_type;
			if(!this.accs.checkType(codegen))
				return false;
			this.rst_type=this.accs.rst_type;
			if(!TypeChecker.checkOptInc(codegen,codegen.getTypeInSymTb(this.accs.rst_type)))
				return false;
			if(this.opt.equals("++p")||this.opt.equals("--p")){		
				R_Variable r=new R_Variable();
				r.addRstVal(this.rst_val);
				r.setVarType(this.rst_type);
				codegen.putVarInSymTb(this.rst_val, r);
			}	
		}else if(this.accs!=null){
			this.accs.ref_type=this.ref_type;
			if(!this.accs.checkType(codegen))
				return false;
			this.rst_type=this.accs.rst_type;
		}
		if(this.cast!=null){
			this.cast.ref_type=this.ref_type;
			if(!this.cast.checkType(codegen))
				return false;
			this.rst_type=this.cast.rst_type;
		}
		return true;
	}
}
