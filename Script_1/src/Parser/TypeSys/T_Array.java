package Parser.TypeSys;

import Parser.CodeGenerator;

public class T_Array extends T_Type {
	String ele_type;
	int dims;
	{this.setKType(KType.t_arr);}
	//public T_Array(){
	//	
	//}
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
	
	
	public boolean canCastFrom(CodeGenerator codegen,T_Type type2){
		if(type2.getKType()!=this.getKType())
			return false;
		if(((T_Array)type2).getDims()!=this.dims)
			return false;
		T_Type t1=codegen.getTypeInSymTb(this.ele_type);
		T_Type t2=codegen.getTypeInSymTb(((T_Array)type2).getEleType());
		return t1.canCastFrom(codegen, t2);	
	}
}
