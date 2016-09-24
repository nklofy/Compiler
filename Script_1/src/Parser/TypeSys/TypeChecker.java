package Parser.TypeSys;


import java.util.*;
import Interpreter.*;
import Parser.*;
import Parser.TypeSys.T_BasicType.en_BType;

public class TypeChecker {//TODO
	
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
	static public T_Type checkOptPlus(CodeGenerator codegen, T_Type type1, T_Type type2) throws TypeCheckException{
		if(type1 instanceof T_BasicType && type2 instanceof T_BasicType){
			return checkBscPlus(codegen,(T_BasicType)type1,(T_BasicType)type2);
		}else
			throw new TypeCheckException(type1.getTypeSig()+" "+type2.getTypeSig()+" can not opt plus");
		//return null;
	}
	static public T_Type checkBscPlus(CodeGenerator codegen, T_BasicType type1, T_BasicType type2)throws TypeCheckException{
		if(type1.getTypeB()==en_BType.t_int&&type2.getTypeB()==en_BType.t_int){
			return codegen.getTypeInSymTb("int");
		}else if(type1.getTypeB()==en_BType.t_double||type2.getTypeB()==en_BType.t_double){
			return codegen.getTypeInSymTb("double");
		}else if(type1.getTypeB()==en_BType.t_string||type2.getTypeB()==en_BType.t_string){
			return codegen.getTypeInSymTb("string");
		}else if(type1.getTypeB()==en_BType.t_char||type2.getTypeB()==en_BType.t_char){
			return codegen.getTypeInSymTb("string");
		}
		throw new TypeCheckException(type1.getTypeSig()+" "+type2.getTypeSig()+" can not opt plus");
	}
	static public T_BasicType checkBscCmp(CodeGenerator codegen, T_Type type1, T_Type type2)throws TypeCheckException{
		
		return null;
	}
	static public boolean checkEq(){
		
		
	}
	
	//TODO check opt calc
	static public boolean checkCast() throws TypeCheckException{
		return true;
	}
}
