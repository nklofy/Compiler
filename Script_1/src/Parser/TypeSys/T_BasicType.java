package Parser.TypeSys;

public class T_BasicType extends T_Type {
	en_BType btype;
	public en_BType getTypeB() {
		return btype;
	}
	public void setTypeB(en_BType btype) {
		this.btype = btype;
	}
	public enum en_BType{t_int,t_double,t_string,t_char,t_bool;}
}
