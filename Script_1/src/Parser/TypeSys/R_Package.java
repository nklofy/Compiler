package Parser.TypeSys;

import java.util.*;

public class R_Package {
	HashMap<String,R_Package> sub_pkgs;
	HashMap<String,T_Function> func_inPkg;
	HashMap<String,R_Variable>var_inPkg;
	HashMap<String,T_Type> type_inPkg;
	String pkg_name;
	
	public HashMap<String, R_Package> getSubPkgs() {
		return sub_pkgs;
	}
	public void addSubPkgs(String s, R_Package sub_pkg) {
		if(this.sub_pkgs==null)
			this.sub_pkgs=new HashMap<String,R_Package>();
		this.sub_pkgs.put(s,sub_pkg);
	}
	public HashMap<String, T_Function> getFuncsInPkg() {
		return func_inPkg;
	}
	public void addFuncInPkg(String s, T_Function func) {
		if(this.func_inPkg==null)
			this.func_inPkg=new HashMap<String,T_Function>();
		this.func_inPkg.put(s, func);
	}
	public HashMap<String, R_Variable> getVarsInPkg() {
		return var_inPkg;
	}
	public void addVarInPkg(String s, R_Variable var) {
		if(this.var_inPkg==null)
			this.var_inPkg= new HashMap<String,R_Variable>();
		this.var_inPkg.put(s, var);
	}
	public HashMap<String, T_Type> getTypesInPkg() {
		return type_inPkg;
	}
	public void addTypeInPkg(String s, T_Type t) {
		if(this.type_inPkg==null)
			this.type_inPkg=new HashMap<String,T_Type>();
		this.type_inPkg.put(s,t);
	}
	public String getPkgName() {
		return pkg_name;
	}
	public void setPkgName(String pkg_name) {
		this.pkg_name = pkg_name;
	}
}
