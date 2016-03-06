package Parser.ASTs;

import Parser.*;

public class ExprCalc_Bool extends AST {
	ExprCalc_Add add_1;
	ExprCalc_Add add_2;
	ExprCalc_Bool bool_1;
	ExprCalc_Bool bool_2;
	String opt;
	boolean isTrue=false;
	boolean isFalse=false;
	public en_Bl t_exp;
	String tmp_rst;	//temp variable for calc result
	
	public boolean setBiBool(ExprCalc_Bool bool_1,String opt,ExprCalc_Bool bool_2){
		this.t_exp=en_Bl.t_biBool;
		this.bool_1=bool_1;
		this.bool_2=bool_2;
		this.opt=opt;
		return true;
	}
	public boolean setBiCmp(ExprCalc_Add add_1,String opt,ExprCalc_Add add_2){
		this.t_exp=en_Bl.t_biCmp;
		this.add_1=add_1;
		this.add_2=add_2;
		this.opt=opt;
		return true;
	}
	public boolean setAdd(ExprCalc_Add add_1){
		this.t_exp=en_Bl.t_un;
		this.add_1=add_1;
		return true;
	}
	public boolean setUnAdd(ExprCalc_Add add_1,String opt){
		this.t_exp=en_Bl.t_un;
		this.add_1=add_1;
		this.opt=opt;
		return true;
	}
	public boolean setTrue(){
		this.t_exp=en_Bl.t_cnst;
		this.isTrue=true;
		return true;
	}
	public boolean setFalse(){
		this.t_exp=en_Bl.t_cnst;
		this.isFalse=true;
		return false;
	}
	public String getRst(){
		return null;
	}
	public enum en_Bl{t_cnst,t_biCmp,t_biBool,t_un}
}
