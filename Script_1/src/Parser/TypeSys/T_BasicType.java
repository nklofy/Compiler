package Parser.TypeSys;

public class T_BasicType extends T_Type {
	private enum_BasicType type;
	public enum_BasicType getType() {
		return type;
	}
	public void setType(enum_BasicType type) {
		this.type = type;
	}
	public enum enum_BasicType{t_int,t_double,t_string,t_chr,t_bool;}
}
