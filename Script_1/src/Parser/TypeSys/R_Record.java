package Parser.TypeSys;

import Parser.*;
import Parser.TypeSys.*;

public class R_Record {
	en_Kind kind;
	en_Scope scope;//visit scope
	boolean isStatic;
	
	public en_Kind getKind() {
		return kind;
	}
	public void setKind(en_Kind kind) {
		this.kind = kind;
	}
	public en_Scope getScope() {
		return scope;
	}
	public void setScope(en_Scope scope) {
		this.scope = scope;
	}
	public boolean isStatic() {
		return isStatic;
	}
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	
	public enum en_Scope{s_public, s_private, s_package, s_local, s_file, s_global;}
	public enum en_Kind{k_var,k_type,k_func;}
}