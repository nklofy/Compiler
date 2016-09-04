package Parser.TypeSys;

import java.util.*;

import Parser.CodeGenerator;

public class T_Type {
	private KType k_type;//kind of type
	private String type_name;
	private String type_sig;//code types for hash, search, compare
	private boolean isGnrc;//has generic pars as a core type
	private LinkedList<String> pars_Gnrc=new LinkedList<String>();
	private T_Generic gnrc_wrap;
	private boolean isDummy=false;
	
	

	public boolean isEqType(T_Type t){
		if(!this.k_type.equals(t.k_type))
			return false;
		if(this.getTypeSig().equals(t.getTypeSig()))
			return true;
		else
			return false;
	}
	
	//sets and gets	
	public boolean isDummy() {
		return isDummy;
	}
	public void setDummy() {
		this.isDummy = true;
		this.k_type=KType.t_dummy;		
	}
	public boolean isGnrc() {
		return isGnrc;
	}
	public void setGnrc(boolean hasGnrcPar) {
		this.isGnrc = hasGnrcPar;
	}
	public String getTypeSig() {
		return type_sig;
	}
	public void setTypeSig(String type_code) {
		this.type_sig = type_code;
	}
	public void genTypeSig(CodeGenerator codegen){
		
	}
//	public boolean canOpt(CodeGenerator codegen, String opt, int position){//0 means return position, -1 means left, 1 means right
//		return false;
//	}
	public boolean canAsn(CodeGenerator codegen,T_Type type2){
		if(type2.canCast(codegen, this))
			return true;
		return false;
	}
	public boolean canCast(CodeGenerator codegen,T_Type type2){
		return false;
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
		this.type_sig=this.type_name;
	}
	public KType getKType() {
		return k_type;
	}
	public void setKType(KType k_type) {
		this.k_type = k_type;
	}
	public enum KType{t_bsc,t_arr,t_func,t_gnrc,t_cls,t_intf,t_dummy}
}
