//type information about object
package Parser.ASTs;

import java.util.*;

public class Type_Obj {
	String type_name;
	Type_Base type_base;
	HashMap<String, Type_Obj> fields; 
	ArrayList<Data_Func> funcs;
	HashMap<String,HashSet<Integer>> func_idx;
}
