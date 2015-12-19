package Parser.ASTs;

import java.util.ArrayList;

import Interpreter.*;
import Parser.*;
import Parser.TypeSys.*;

public class AST_Var extends AST {
	String name;
	Data_Obj data_obj;
	ArrayList<Type_Func> funcs=new ArrayList<Type_Func>();
	//Object obj_value; 
	public boolean setVar(String name){
		this.name=name;
		return true;
	}
	public Type_Func getFunc(ArrayList<Type_Obj> par_types){
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
				return f;			
		}
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
			this.data_obj= new Data_Obj(obj);
			return true;
		}else{
			System.out.println("not in env "+this.name);
			return false;
		}		
	}
}
