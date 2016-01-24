package Parser.TypeSys;

import Parser.*;
import Parser.TypeSys.*;
import Parser.TypeSys.R_Record.en_Kind;

public class R_Record {
	en_Kind kind;	
	public en_Kind getKind() {
		return kind;
	}
	public void setKind(en_Kind kind) {
		this.kind = kind;
	}	
	public enum en_Kind{k_var,k_type,k_func;}
}