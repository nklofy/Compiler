package Parser.ASTs.old;

import java.util.ArrayList;

import Interpreter.*;
import Interpreter.old.Interpreter;
import Parser.*;
import Parser.TypeSys.*;
import Parser.TypeSys.old.Data_Func;
import Parser.TypeSys.old.Data_Obj;
import Parser.TypeSys.old.Type_Func;
import Parser.TypeSys.old.Type_Idn;
import Parser.TypeSys.old.Type_Obj;

public class AST_Var extends AST {
	String name;
	Type_Idn idn_type;
	Data_Obj data_obj;
	ArrayList<Type_Func> funcs=new ArrayList<Type_Func>();
	//Object obj_value; 
	public boolean setVar(String name){
		this.name=name;
		return true;
	}
	public Data_Func getFunc(ArrayList<Type_Obj> par_types){
		for(Type_Func f :funcs){
			if(f.getParTypes().size()!=par_types.size()){
				continue;
			}
			ArrayList<Type_Obj> ts=f.getParTypes();	
			
			boolean hasF=false;
			for(int i=0;i<ts.size();i++){
				if(ts.get(i)==par_types.get(i)){
					hasF=true;
				}else
					hasF=false;
					break;
			}
			if(hasF)
				return f.getDataFunc();			
		}
		System.out.println("error unknown function "+this.name);
		return null;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		ArrayList<Type_Func> fs=interpreter.getCrtFrm().getCrtEnv().getFunc(this.name);
		if(fs==null){
			fs=interpreter.getGlbEnv().getFunc(this.name);
		}
		if(fs==null){
			fs=interpreter.getStcEnv().getFunc(this.name);
		}
		if(fs!=null){
			this.funcs=fs;
			this.idn_type=Type_Idn.t_func;
			return true;
		}
		Data_Obj obj=interpreter.getCrtFrm().getCrtEnv().getObj(this.name);
		if(obj==null){
			obj=interpreter.getGlbEnv().getObj(this.name);
		}
		if(obj==null){
			obj=interpreter.getStcEnv().getObj(this.name);
		}
		if(obj!=null){
			//System.out.println(this.name+" "+obj.getIntV());
			this.data_obj= new Data_Obj(obj);
			this.idn_type=Type_Idn.t_obj;
			return true;
		}else{
			//System.out.println("new var "+this.name);
			return false;
		}		
	}
}
