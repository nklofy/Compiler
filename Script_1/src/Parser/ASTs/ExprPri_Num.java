package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

public class ExprPri_Num extends AST {
	String rst_val;
	String ref_type;
	String rst_type;
	String tmp_val;
	long i_v;
	double d_v;
	String s_v;
	
	public void setNum(String s, String value) {
		this.tmp_val=value;
		this.rst_type=s;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		IRCode code =null;
		if(this.ref_type!=null){
			switch(this.ref_type){
			case "int":
				code = new IRCode("load_i",this.rst_val,String.valueOf(this.i_v),null);
				codegen.addCode(code);
				break;
			case "double":
				code = new IRCode("load_d",this.rst_val,String.valueOf(this.d_v),null);
				codegen.addCode(code);
				break;
			case "string":
				code = new IRCode("load_s",this.rst_val,null,null);
				codegen.addCode(code);
				code = new IRCode(this.tmp_val,null,null,null);
				codegen.addCode(code);
				break;
				default:break;
			}
		}else{
			switch(this.rst_type){
			case "int":
				code = new IRCode("load_i",this.rst_val,String.valueOf(this.i_v),null);
				codegen.addCode(code);
				break;
			case "double":
				code = new IRCode("load_d",this.rst_val,String.valueOf(this.d_v),null);
				codegen.addCode(code);
				break;
			case "string":
				code = new IRCode("load_s",this.rst_val,null,null);
				codegen.addCode(code);
				code = new IRCode(this.tmp_val,null,null,null);
				codegen.addCode(code);
				break;
				default:break;
			}
		}
		
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(this.rst_type.equals("int")){
			this.i_v=Long.parseLong(this.tmp_val);
		}else if(this.rst_type.equals("double")){
			this.d_v=Double.parseDouble(this.tmp_val);
		}
		this.rst_val="%"+codegen.getTmpSn();
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(this.ref_type==null){
			return true;
		}else if(this.ref_type.equals("int")){
			if(this.rst_type.equals("double")){
				this.i_v=(long)this.d_v;
			}
		}else if(this.ref_type.equals("double")){
			if(this.rst_type.equals("int")){
				this.d_v=(double)this.i_v;
			}
		}else if(this.ref_type.equals("string")){
			this.s_v=this.tmp_val;
		}else 
			throw new TypeCheckException("fail type checking: number cast to "+ codegen.getTypeInSymTb(this.ref_type).getTypeSig());
		this.rst_type=this.ref_type;
		return true;
	}
}
