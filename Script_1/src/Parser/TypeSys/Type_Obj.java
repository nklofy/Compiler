//type information about object
package Parser.TypeSys;

import java.util.*;

public class Type_Obj {
	public String type_name;
	public Type_Base type_base;
	public Type_Func type_func;
	//class type
	public HashMap<String, Type_Obj> fields;
	public ArrayList<Type_Func> funcs;		//refactoring in future
	public HashMap<String,HashSet<Integer>> func_idx;	//need new method to search or match correct function
}
