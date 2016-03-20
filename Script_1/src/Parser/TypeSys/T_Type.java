package Parser.TypeSys;

import java.util.*;

public class T_Type {
	boolean isArray;
	boolean isGnrc;
	boolean isCls;
	boolean isIntf;	
	boolean isFunc;
	LinkedList<String> pars_Gnrc=new LinkedList<String>();	
	String type_name;
	/*
	public boolean canAsn(T_Type type1){
		
		return true;
	}
	public boolean canTrans(T_Type type1){
		return true;
	}*/
	public boolean isEqType(T_Type t){
		if(this==t)
			return true;
		else
			return false;
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
		return pars_Gnrc;
	}
	public void setGnrcPars(LinkedList<String> pars_Gnrc) {
		this.pars_Gnrc = pars_Gnrc;
	}
	public String getTypeName() {
		return type_name;
	}
	public void setTypeName(String type_name) {
		this.type_name = type_name;
	}
	public boolean isCls() {
		return isCls;
	}
	public void setCls(boolean isCls) {
		this.isCls = isCls;
	}
	public boolean isIntf() {
		return isIntf;
	}
	public void setIntf(boolean isIntf) {
		this.isIntf = isIntf;
	}
	public boolean isFunc() {
		return isFunc;
	}
	public void setFunc(boolean isFunc) {
		this.isFunc = isFunc;
	}
}
