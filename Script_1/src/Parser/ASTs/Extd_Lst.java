package Parser.ASTs;

import java.util.*;

import Parser.*;

public class Extd_Lst extends AST {
	boolean isE=false;
	LinkedList<TypeExp_Idn> exts;
	public void addPar(TypeExp_Idn par){
		if(this.exts==null){
			this.exts=new LinkedList<TypeExp_Idn>();			
		}
		this.exts.add(par);
	}
	public boolean isE() {
		return isE;
	}
	public void setE() {
		this.isE = true;
	}
}
