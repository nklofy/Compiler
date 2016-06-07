package Parser.TypeSys;

import java.util.*;

import Parser.CodeGenerator;

public class T_Generic extends T_Type {
	private String core_type;	
	LinkedHashMap<String,String> type_args=new LinkedHashMap<String,String>();//why linkedhashmap, link for order of gen-sig, map for sym-tbl
	
	public String getCoreType() {
		return core_type;
	}
	public void setCoreType(String core_type) {
		this.core_type = core_type;
	}
	public HashMap<String, String> getTypeArgTb() {
		return type_args;
	}
	//public void setTypeArgTb(LinkedHashMap<String, String> type_args) {
	//	this.type_args = type_args;
	//}
	public void genTypeSig(CodeGenerator codegen) {
		String s=codegen.getTypeInSymTb(this.core_type).getTypeSig()+"<";
		for(String name:this.type_args.keySet()){
			s+=codegen.getTypeInSymTb(name).getTypeSig()+":"+
		codegen.getTypeInSymTb(this.type_args.get(name)).getTypeSig()+",";
		}
		s+=">";		
		this.setTypeSig(s);
	}
	public boolean isEqType(T_Generic t){
		if(this.getTypeSig().equals(t.getTypeSig()))
			return true;
		else
			return false;
	}
}
