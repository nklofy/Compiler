package Parser.TypeSys;

import Parser.CodeGenerator;

public class T_Array extends T_Type {
	String ele_type;
	int dims;
	

	public String getEleType() {
		return ele_type;
	}
	public void setEleType(String ele_type) {
		this.ele_type = ele_type;
	}
	public int getDims() {
		return dims;
	}
	public void setDims(int dims) {
		this.dims = dims;
	}
	public void incDims(){
		this.dims++;
	}
	public void genTypeSig(CodeGenerator codegen) {
		String s=codegen.getTypeInSymTb(this.ele_type).getTypeSig()+"["+this.dims+"]";		
		this.setTypeSig(s);
	}
	
	
	public boolean canCast(CodeGenerator codegen,T_Type type2){
		TODO covariant
		return true;
	}
}
