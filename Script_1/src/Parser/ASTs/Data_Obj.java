//an object's properties
package Parser.ASTs;
import java.util.*;
public class Data_Obj {
	Type_Obj type_obj;		//real type
	Type_Obj type_ref;		//ref pointer's type
	String var_name;
	long int_value;
	double double_value;
	boolean bool_value;
	String string_value;
	char char_value;
	HashMap<String, Data_Obj> fields;
	Data_Obj getField(String var){
		return null;
	}
	Data_Func getFunc(String name, ArrayList<String> par_types){
		Set<Integer>idx=this.type_obj.func_idx.get(name);
		for(int i:idx){
			Type_Func f=this.type_obj.funcs.get(i);
			if(par_types.size()!=f.par_types.size())
				continue;
			boolean hasFn=true;
			for(int j=0;j<par_types.size();j++){
				if(!f.par_types.get(j).type_name.equals(par_types.get(j))){
					hasFn=false;
					break;
				}else
					continue;
			}
			if(hasFn){
				return f.data_func;
			}
		}
		return null;
	}
	Data_Obj(){
		
	}
	Data_Obj(Data_Obj obj){
		copyObj(obj);
	}
	boolean copyObj(Data_Obj obj){
		if(obj.type_obj!=null){
			this.type_obj=obj.type_obj;
			if(this.type_obj.type_base!=null){//base type
				switch(this.type_obj.type_base){
				case t_int:
					this.int_value=obj.int_value;
					break;
				case t_double:
					this.double_value=obj.double_value;
					break;
				case t_bool:
					this.bool_value=obj.bool_value;
					break;
				case t_string:
					this.string_value=obj.string_value;
					break;
				case t_char:
					this.char_value=obj.char_value;
					break;
					default:
						break;
				}
			}else{//object type
				for(String str:obj.type_obj.fields.keySet()){
					Data_Obj tmp_obj=new Data_Obj();
					this.fields.put(str,tmp_obj);
					tmp_obj.copyObj(obj);
				}
			}
		}
		if(obj.type_ref!=null)
			this.type_ref=obj.type_ref;
		this.var_name=obj.var_name;
		return true;
	}
}
