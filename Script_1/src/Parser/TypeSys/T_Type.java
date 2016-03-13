package Parser.TypeSys;

import java.util.*;

public class T_Type {
	public boolean isArray;
	public boolean isGnrc;
	public LinkedList<String> Pars_Gnrc;
	
	public boolean canAsn(T_Type type1){
		
		return true;
	}
	public boolean isSuperOf(T_Type type1){
		return true;
	}
	public boolean isSubOf(T_Type type1){
		return true;
	}
	public boolean canTrans(T_Type type1){
		return true;
	}
}
