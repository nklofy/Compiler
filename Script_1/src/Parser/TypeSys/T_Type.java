package Parser.TypeSys;

import java.util.*;

public class T_Type {
	boolean isArray;
	boolean isGnrc;
	LinkedList<String> Pars_Gnrc=new LinkedList<String>();	
	String type_name;
	
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
	public boolean isArray() {
		return isArray;
	}
	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}
	public boolean isGnrc() {
		return isGnrc;
	}
	public void setGnrc(boolean isGnrc) {
		this.isGnrc = isGnrc;
	}
	public LinkedList<String> getGnrcPars() {
		return Pars_Gnrc;
	}
	public void setGnrcPars(LinkedList<String> pars_Gnrc) {
		Pars_Gnrc = pars_Gnrc;
	}
	public String getTypeName() {
		return type_name;
	}
	public void setTypeName(String type_name) {
		this.type_name = type_name;
	}
}
