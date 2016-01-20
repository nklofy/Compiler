package Parser.TypeSys;

public class T_BasicType extends T_Type {
	en_BType btype;
	public en_BType getBType() {
		return btype;
	}
	public void setType(en_BType btype) {
		this.btype = btype;
	}
	public enum en_BType{t_int,t_double,t_string,t_chr,t_bool;}
}
