package Parser.ASTs;

import Parser.*;

public class ExprPri_Num extends AST {
	String rst_val;
	String ref_type;
	String rst_type;
	long i_v;
	double d_v;
	
	public void setNum(String s, String value) {
		this.rst_val=value;
		this.rst_type=s;
	}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		if(this.rst_type.equals("int")){
			this.i_v=Long.parseLong(this.rst_val);
		}else if(this.rst_type.equals("double")){
			this.d_v=Double.parseDouble(this.rst_val);
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(this.ref_type==null){
			return true;
		}else if(this.ref_type.equals("int")){
			if(this.rst_type.equals("double")){
				this.i_v=(long)this.d_v;
				this.rst_val=String.valueOf(this.i_v);
			}
		}else if(this.ref_type.equals("double")){
			if(this.rst_type.equals("int")){
				this.d_v=(double)this.i_v;
				this.rst_val=String.valueOf(this.d_v);
			}
		}else if(this.ref_type.equals("string")){
			
		}else 
			return false;
		this.rst_type=this.ref_type;
		return true;
	}
}
