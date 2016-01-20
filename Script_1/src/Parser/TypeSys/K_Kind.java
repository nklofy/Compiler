package Parser.TypeSys;

public class K_Kind {
	en_Kind kind;
	public en_Kind getKind() {
		return kind;
	}
	public void setKind(en_Kind kind) {
		this.kind = kind;
	}
	T_Type type;
	public T_Type getType() {
		return type;
	}
	public void setType(T_Type type) {
		this.type = type;
	}
	public enum en_Kind{k_var,k_type,k_func;}
}
