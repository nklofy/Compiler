//an object's properties
package Parser.TypeSys;
import java.util.*;
public class Data_Obj {
	private Type_Obj type_obj;		//real type	
	private Type_Obj type_ref;		//ref pointer's type
	private boolean hasInit=false;
	//private String var_name;
	private long int_value;
	private double double_value;
	private boolean bool_value;
	private String string_value;
	private char char_value;
	private HashMap<String, Data_Obj> fields=new HashMap<String, Data_Obj>();
	public Type_Obj getTypeObj() {
		return type_obj;
	}
	public void setTypeObj(Type_Obj type_obj) {
		this.type_obj = type_obj;
	}
	public Type_Obj getTypeRef() {
		return type_ref;
	}
	public void setTypeRef(Type_Obj type_ref) {
		this.type_ref = type_ref;
	}	
	public boolean isInit() {
		return hasInit;
	}
	public void setInit(boolean hasInit) {
		this.hasInit = hasInit;
	}
	public long getIntV() {
		return int_value;
	}
	public void setIntV(long int_value) {
		this.int_value = int_value;
	}
	public double getDoubleV() {
		return double_value;
	}
	public void setDoubleV(double double_value) {
		this.double_value = double_value;
	}
	public boolean getBoolV() {
		return bool_value;
	}
	public void setBoolV(boolean bool_value) {
		this.bool_value = bool_value;
	}
	public String getStringV() {
		return string_value;
	}
	public void setStringV(String string_value) {
		this.string_value = string_value;
	}
	public char getCharV() {
		return char_value;
	}
	public void setCharV(char char_value) {
		this.char_value = char_value;
	}
	public HashMap<String, Data_Obj> getFields() {
		return fields;
	}
	public void setFields(HashMap<String, Data_Obj> fields) {
		this.fields = fields;
	}
	public Data_Obj getField(String var){//get obj field
		return fields.get(var);
	}
	public Data_Func getFunc(String name, ArrayList<Type_Obj> arg_types){//get obj method
		Set<Integer>idx=this.type_obj.getFuncIdx().get(name);
		for(int i:idx){
			Type_Func f=this.type_obj.getFuncs().get(i);
			if(arg_types.size()!=f.getParTypes().size())
				continue;
			boolean hasFn=true;
			for(int j=0;j<arg_types.size();j++){
				if(f.getParTypes().get(j)!=arg_types.get(j)){
					hasFn=false;
					break;
				}else
					continue;
			}
			if(hasFn){
				return f.getDataFunc();
			}
		}
		return null;
	}
	public Data_Obj(){
		
	}
	public Data_Obj(Data_Obj obj){
		copyObj(obj);
	}
	public boolean copyObj(Data_Obj obj){
		if(obj.type_obj!=null){
			this.type_obj=obj.type_obj;
			if(this.type_obj.getTypeBase()!=null){//base type
				switch(this.type_obj.getTypeBase()){
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
				for(String str:obj.type_obj.getFields().keySet()){
					Data_Obj tmp_obj=new Data_Obj();
					this.fields.put(str,tmp_obj);
					tmp_obj.copyObj(obj);
				}
			}
		}
		if(obj.type_ref!=null)
			this.type_ref=obj.type_ref;
		//this.var_name=obj.var_name;
		return true;
	}
	
}
