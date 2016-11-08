package Parser.TypeSys;

import java.util.*;

import Parser.CodeGenerator;

public class T_Generic extends T_Type {
	private String core_type;	
	LinkedHashMap<String,String> type_args=new LinkedHashMap<String,String>();//why linkedhashmap, link for order of gen-sig, map for sym-tbl
	
	{this.setKType(KType.t_gnrc);this.setGnrc(true);}
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
	
	public boolean canCastFrom(CodeGenerator codegen,T_Type type2){
		if(type2.getKType()!=KType.t_gnrc)
			return false;
		T_Type t1=codegen.getTypeInSymTb(this.core_type);
		T_Type t2=codegen.getTypeInSymTb(((T_Generic)type2).getCoreType());
		if(!t1.canCastFrom(codegen, t2))
			return false;
		if(this.type_args.size()!=((T_Generic)type2).getTypeArgTb().size())
			return false;
		for(String s1:this.type_args.keySet()){
			T_Type t3=codegen.getTypeInSymTb(this.type_args.get(s1));
			T_Type t4=codegen.getTypeInSymTb(((T_Generic)type2).getTypeArgTb().get(s1));
			if(!t3.canCastFrom(codegen, t4))
				return false;
		}
		return true;
	}
	public void genTypeSig(CodeGenerator codegen) {
		String s=codegen.getTypeInSymTb(this.core_type).getTypeName()+"<";
		for(String name:this.type_args.keySet()){
			s+=name+":"+
		codegen.getTypeInSymTb(this.type_args.get(name)).getTypeSig()+",";
		}
		s=s.substring(0, s.length()-1)+">";		
		this.setTypeSig(s);
	}
	
}
