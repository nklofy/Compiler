package Parser.TypeSys;

public class R_Variable extends R_Record{
	boolean isFinal;
	T_Type type;
	public boolean isFinal() {
		return isFinal;
	}
	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	public T_Type getType() {
		return type;
	}
	public void setType(T_Type type) {
		this.type = type;
	}
}
