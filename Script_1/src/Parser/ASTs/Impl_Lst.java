package Parser.ASTs;

import java.util.*;

import Parser.*;

public class Impl_Lst extends AST {
	boolean isE=false;
	LinkedList<TypeExp_Idn> imps;
	public void addPar(TypeExp_Idn par){
		if(this.imps==null){
			this.imps=new LinkedList<TypeExp_Idn>();			
		}
		this.imps.add(par);
	}
	public boolean isE() {
		return isE;
	}
	public void setE() {
		this.isE = true;
	}
}
