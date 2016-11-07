package Parser.TypeSys;

import java.util.*;

import Parser.*;

public class T_Interface extends T_Type {
	private LinkedList<String> extd_types=new LinkedList<String>();
	private HashMap<String,R_Function> methods=new HashMap<String,R_Function>();
	HashSet<String> all_extd=new HashSet<String>();
	private String scope="global";
	
	
	{this.setKType(KType.t_intf);}
	public HashMap<String,R_Function> getMethods() {
		return methods;
	}
	public void setMethods(HashMap<String,R_Function> methods) {
		this.methods = methods;	
	}
	public LinkedList<String> getExtdTypes() {
		return extd_types;
	}
	public void setExtdTypes(LinkedList<String> extd_types) {
		this.extd_types = extd_types;		
	}	
	public HashSet<String> getAllExtd(){	
		return all_extd;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public void genTypeSig(CodeGenerator codegen) {
		//String s=codegen.getTypeInSymTb(this.getTypeName()).getTypeSig();
		//if(this.isGnrc())
		//	s=s+"<"+this.getGnrcPars().size()+">";
		//this.setTypeSig(s);
		String s="";
		if(this.isGnrc()){
			s+="<";
			for(String g:this.getGnrcPars()){
				s+=codegen.getTypeInSymTb(g).getTypeSig()+",";
			}
			s=s.substring(0,s.length()-1)+">";
		}
		s+=this.getTypeName();
		this.setTypeSig(s);
	}
	
	public boolean canCastFrom(CodeGenerator codegen,T_Type type2){
		if(this.all_extd.contains(type2.getTypeName()))
			return true;
		else{
			if(type2.canCastFrom(codegen, this))
				return true;
		}
		return false;
	}
	public boolean checkAllExtd(CodeGenerator codegen){
		for(String s:this.extd_types){
			T_Interface t = (T_Interface) codegen.getTypeInSymTb(s);
			for(String name:t.getAllExtd()){
				if(this.all_extd.contains(t))
					return false;
				this.all_extd.add(name);
			}
		}
		return true;
	}
	public boolean checkAllMthd(CodeGenerator codegen){//add all methods in this type
		for(String s:this.extd_types){
			T_Interface t = (T_Interface) codegen.getTypeInSymTb(s);
			for(String name:t.methods.keySet()){
				R_Function r=t.methods.get(name);
				if(!this.methods.containsKey(name)){
					this.methods.put(name, r);
				}else{
					R_Function r1=this.methods.get(name);
					if(r.isMulti()){
						for(String ts:r.getMulti().keySet()){
							if(!r1.isCntnNameType(r.getMulti().get(ts))){
								r1.addFuncR(r.getMulti().get(ts));
							}
							else return false;
						}
					}else{
						if(!r1.isCntnNameType(r))
							r1.addFuncR(r);
						else return false;
					}
				}
			}
		}
		return true;
	}
}
