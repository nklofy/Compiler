package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp_Bsc extends AST {
	T_Type t_type;//type_value

	public T_Type getTypeT() {
		return t_type;
	}
	public void setTypeT(T_Type t_type) {
		this.t_type = t_type;
	}
}
