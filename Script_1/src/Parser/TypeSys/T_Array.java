package Parser.TypeSys;

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
	public void genTypeCode() {
		String s=this.ele_type+"["+this.dims+"]";		
		this.setTypeSig(s);
	}
	public boolean isEqType(T_Array t){
		if(this.getKType()!=t.getKType())
			return false;
		if(this.getTypeSig().equals(t.getTypeSig()))
			return true;
		else
			return false;
	}
}
