//type information about object
package Parser.ASTs;

import java.util.*;

public class Type_Obj {
	String type_name;
	Type_Base type_base;
	Type_Func type_func;
	//class type
	HashMap<String, Type_Obj> fields;
	ArrayList<Type_Func> funcs;		//refactoring in future
	HashMap<String,HashSet<Integer>> func_idx;	//need new method to search or match correct function
}
