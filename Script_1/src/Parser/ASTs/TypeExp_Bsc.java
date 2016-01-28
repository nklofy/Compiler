package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp_Bsc extends AST {
	T_Type t_type;

	public T_Type getTypeB() {
		return t_type;
	}

	public void setTypeB(T_Type t_type) {
		this.t_type = t_type;
	}
	
}
