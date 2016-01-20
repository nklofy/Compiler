package Parser.TypeSys;


public class T_Type {
	en_Type type;
	public en_Type getType() {
		return type;
	}
	public void setType(en_Type type) {
		this.type = type;
	}
	public enum en_Type{t_class,t_itf,t_btype,t_func,t_gnrc;}
}
