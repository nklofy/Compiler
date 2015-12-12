//type information about function
package Parser.ASTs;

import java.util.ArrayList;

public class Type_Func {
	String func_name;
	Type_Obj return_type;
	ArrayList<Type_Obj> par_types;
	Data_Func data_func;
}
