package Parser.TypeSys;


import java.util.*;
import Interpreter.*;
import Parser.*;
import Parser.TypeSys.T_BasicType.en_BType;

public class TypeSystem {//TODO
	
	/*static public boolean canOpt(String opt, int position, T_Type type){//0 means result position, -1 means left, 1 means right
		T_BasicType t_b=null;
		if(type.getKType()==T_Type.KType.t_bsc){
			t_b= (T_BasicType)type;
		}
		switch(opt){
			case "+":
				if(t_b.btype==en_BType.t_int||t_b.btype==en_BType.t_double||
						t_b.btype==en_BType.t_char||t_b.btype==en_BType.t_string)
					return true;
				break;
			case "-":
				if(t_b.btype==en_BType.t_int||t_b.btype==en_BType.t_double)
					return true;
				break;
			case "*":
				if(t_b.btype==en_BType.t_int||t_b.btype==en_BType.t_double)
					return true;
				break;
			case "/":
				if(t_b.btype==en_BType.t_int||t_b.btype==en_BType.t_double)
					return true;
				break;
			case "++":
				if(t_b.btype==en_BType.t_int)
					return true;
				break;
			case "--":
				if(t_b.btype==en_BType.t_int)
					return true;
				break;
			case ">":
				if(t_b.btype==en_BType.t_int||t_b.btype==en_BType.t_double||
				t_b.btype==en_BType.t_char||t_b.btype==en_BType.t_string)
			return true;
				break;
			case ">=":
				if(t_b.btype==en_BType.t_int||t_b.btype==en_BType.t_double||
				t_b.btype==en_BType.t_char||t_b.btype==en_BType.t_string)
			return true;
				break;
			case "<":
				if(t_b.btype==en_BType.t_int||t_b.btype==en_BType.t_double||
				t_b.btype==en_BType.t_char||t_b.btype==en_BType.t_string)
			return true;
				break;
			case "<=":
				if(t_b.btype==en_BType.t_int||t_b.btype==en_BType.t_double||
				t_b.btype==en_BType.t_char||t_b.btype==en_BType.t_string)
			return true;
				break;
/*			case "==":
				if(t_b.btype==en_BType.t_int||t_b.btype==en_BType.t_double||
				t_b.btype==en_BType.t_char||t_b.btype==en_BType.t_string)
			return true;
				break;
			case "!=":
				if(t_b.btype==en_BType.t_int||t_b.btype==en_BType.t_double||
				t_b.btype==en_BType.t_char||t_b.btype==en_BType.t_string)
			return true;
				break;
			case "!":
				if(t_b.btype==en_BType.t_bool)
					return true;
				break;
			default:
				break;
		}
		return false;
	}*/
	
	//TODO check type cast
	static public boolean checkOpt(){
		return true;
	}
	
	//TODO check opt calc
	static public boolean checkCast(){
		return true;
	}
}
