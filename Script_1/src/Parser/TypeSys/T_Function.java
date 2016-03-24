package Parser.TypeSys;

import java.util.*;
import Parser.*;
import Parser.IR.*;

public class T_Function extends T_Type {
	T_Type re_type;
	LinkedList<T_Type> par_types;
	ArrayList<IRCode> func_body;
	public boolean isEqFunc(T_Function t){
		if (!this.getTypeName().equals(t.getTypeName())){
			return false;
		}
		if(!isEqType(t))
			return false;
		return true;
	}
	public boolean isEqType(T_Function t){
		if(!t.isFunc||!this.re_type.isEqType(t.re_type))return false;
		if(this.par_types.size()!=t.par_types.size())return false;
		if(this.isGnrc()!=t.isGnrc()) return false;
		if(this.isGnrc()&&t.isGnrc()&&this.getGnrcPars().size()!=t.getGnrcPars().size()) return false;
		for(int i=0;i<this.par_types.size();i++){
			if(!this.par_types.get(i).isEqType(par_types.get(i)))
				return false;
		}
		return true;
	}
}
