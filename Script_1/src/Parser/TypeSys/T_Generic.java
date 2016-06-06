package Parser.TypeSys;

import java.util.*;

import Parser.CodeGenerator;

public class T_Generic extends T_Type {
	private String core_type;	
	HashMap<String,String> type_args=new HashMap<String,String>();
	
	public String getCoreType() {
		return core_type;
	}
	public void setCoreType(String core_type) {
		this.core_type = core_type;
	}
	public HashMap<String, String> getTypeArgTb() {
		return type_args;
	}
	public void setTypeArgTb(HashMap<String, String> type_args) {
		this.type_args = type_args;
	}
	public void genTypeCode(CodeGenerator codegen) {//TODO
		String s=codegen.getTypeInSymTb(this.core_type).getTypeSig()+"<";
		for(String name:this.type_args.keySet()){
			s+=codegen.getTypeInSymTb(name).getTypeSig()+":"+
		codegen.getTypeInSymTb(this.type_args.get(name)).getTypeSig()+",";
		}
		s+=">";		
		this.setTypeSig(s);
	}
	public boolean isEqType(T_Generic t){
		if(this.getKType()!=t.getKType())
			return false;
		if(this.getTypeSig().equals(t.getTypeSig()))
			return true;
		else
			return false;
	}
}
