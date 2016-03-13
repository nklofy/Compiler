package Parser.TypeSys;

public class T_Array extends T_Type {
	T_Type ele_type;
	int dims;
	

	public T_Type getEleType() {
		return ele_type;
	}
	public void setEleType(T_Type ele_type) {
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
}
