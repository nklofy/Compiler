//an object's properties
package Parser.ASTs;
import java.util.*;
public class Data_Obj {
	Type_Obj type_obj;
	Type_Obj type_ref;
	Data_Func func;
	String var_name;
	long int_value;
	double double_value;
	boolean bool_value;
	String string_value;
	char char_value;
	HashMap<String, Data_Obj> fields;
	ArrayList<Data_Func> funcs;
	Data_Obj getField(String var){
		return null;
	}
	Data_Func getFunc(String var, ArrayList<String> par_types){
		return null;
	}
}
