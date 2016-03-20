package Parser.TypeSys;

import java.util.*;
import Parser.*;

public class T_Function extends T_Type {
	T_Type re_type;
	LinkedList<T_Type> par_types;
	AST funcdef_body;
	
	public boolean isEqType(T_Function t){
		if(!t.isFunc||!this.re_type.isEqType(t.re_type))return false;
		if(this.par_types.size()!=t.par_types.size())return false;
		for(int i=0;i<this.par_types.size();i++){
			if(!this.par_types.get(i).isEqType(par_types.get(i)))
				return false;
		}
		return true;
	}
}
