//type information about function
package Parser.TypeSys;

import java.util.ArrayList;

public class Type_Func {
	private String func_name;
	private Type_Obj return_type;
	private ArrayList<Type_Obj> par_types;
	private Data_Func data_func;
	public String getFunc_name() {
		return func_name;
	}
	public void setFuncName(String func_name) {
		this.func_name = func_name;
	}
	public Type_Obj getReturnType() {
		return return_type;
	}
	public void setReturnType(Type_Obj return_type) {
		this.return_type = return_type;
	}
	public ArrayList<Type_Obj> getParTypes() {
		return par_types;
	}
	public void setParTypes(ArrayList<Type_Obj> par_types) {
		this.par_types = par_types;
	}
	public Data_Func getDataFunc() {
		return data_func;
	}
	public void setDataFunc(Data_Func data_func) {
		this.data_func = data_func;
	}
	
}
