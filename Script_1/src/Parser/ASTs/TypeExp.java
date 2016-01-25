package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp extends AST {
	T_Type t_type;

	public T_Type getTypeT() {
		return t_type;
	}

	public void setTypeT(T_Type t_type) {
		this.t_type = t_type;
	}
}
