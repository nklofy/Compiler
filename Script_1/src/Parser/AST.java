package Parser;
import java.util.*;

import Interpreter.*;
import Parser.TypeSys.*;

public class AST {
	int line;
	String ast_type;
	AST ast_deMrg;	//de-merge, choose correct one
	HashSet<AST> merged_asts;
	boolean isMerged=false;
	HashMap<String,R_Variable> var_table;
	HashMap<String,T_Type> type_table;
	HashMap<String,R_Function> func_table;
//	LinkedList<String> var_up;
//	LinkedList<String> type_up;
//	LinkedList<String> func_up;	
	
	public String getASTType() {
		return ast_type;
	}
	public void setASTType(String type) {
		String[] ss=type.split("\\.");
		this.ast_type = ss[ss.length-1];
	}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean upSymTbl(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return true;
	}
	public HashMap<String, R_Variable> getVarTb() {
		return var_table;
	}
	public void setVarTb(HashMap<String, R_Variable> t) {
		this.var_table=t;
	}
	public void putVarTb(String name, R_Variable r) {
		if(this.var_table != null){
			this.var_table.put(name, r);
		}else{
			this.var_table=new HashMap<String, R_Variable>();
			this.var_table.put(name, r);
		}
	}
	public HashMap<String, T_Type> getTypeTb() {
		return type_table;
	}
	public void setTypeTb(HashMap<String, T_Type> t) {
		this.type_table=t;
	}
	public void putTypeTb(String name, T_Type r) {
		if(this.type_table != null){
			this.type_table.put(name, r);
		}else{
			this.type_table=new HashMap<String, T_Type>();
			this.type_table.put(name, r);
		}
	}
	public HashMap<String, R_Function> getFuncTb() {
		return func_table;
	}
	public void setFuncTb(HashMap<String, R_Function> t) {
		this.func_table=t;
	}
	public void putFuncTb(String name, R_Function r) {
		if(this.func_table != null){
			if(this.func_table.containsKey(name)){
				if(this.func_table.get(name).isMulti()){
					this.func_table.get(name).addMulti(r);
				}else{
					this.func_table.get(name).setMulti();
					this.func_table.get(name).addMulti(r);
				}
			}else{
				this.func_table.put(name, r);
			}			
		}else{
			this.func_table=new HashMap<String, R_Function>();
			this.func_table.put(name, r);
		}
	}
/*	public LinkedList<String> getVarUp() {
		return var_up;
	}
	public void setVarUp(LinkedList<String> var_up) {
		this.var_up = var_up;
	}
	public void addVarUp(String name){
		if(this.var_up!=null)
			this.var_up.add(name);
		else{
			this.var_up=new LinkedList<String>();
			this.var_up.add(name);
		}			
	}
	public LinkedList<String> getTypeUp() {
		return type_up;
	}
	public void setTypeUp(LinkedList<String> type_up) {
		this.type_up = type_up;
	}
	public void addTypeUp(String name) {
		if(this.type_up!=null)
			this.type_up.add(name);
		else{
			this.type_up=new LinkedList<String>();
			this.type_up.add(name);
		}
	}
	public LinkedList<String> getFuncUp() {
		return func_up;
	}
	public void setFuncUp(LinkedList<String> func_up) {
		this.func_up = func_up;
	}
	public void addFuncUp(String name) {
		if(this.func_up!=null)
			this.func_up.add(name);
		else{
			this.func_up=new LinkedList<String>();
			this.func_up.add(name);
		}
	}
	private boolean upVar(AST ast){
		if(ast.getVarUp()==null){
			return false;
		}
		for(String s:ast.getVarUp()){
			if(this.var_table!=null && this.var_table.keySet().contains(s)){
				System.out.println("error existing symbol name: "+ s);
			}else{
				this.putVarTb(s, ast.getVarTb().get(s));
				this.addVarUp(s);
			}
		}
		return true;
	}
	private boolean upFunc(AST ast){
		if(ast.getFuncUp()==null){
			return false;
		}
		for(String s:ast.getFuncUp()){
			if(this.func_table!=null && this.func_table.keySet().contains(s)){
				System.out.println("error existing symbol name: "+ s);
			}else{
				this.putFuncTb(s, ast.getFuncTb().get(s));
				this.addFuncUp(s);
			}
		}
		return true;
	}
	private boolean upType(AST ast){
		if(ast.getTypeUp()==null){
			return false;
		}
		for(String s:ast.getTypeUp()){
			if(this.type_table!=null && this.type_table.keySet().contains(s)){
				System.out.println("error existing symbol name: "+ s);
			}else{
				this.putTypeTb(s, ast.getTypeTb().get(s));
				this.addTypeUp(s);
			}
		}
		return true;
	}
	public boolean upAll(AST ast){			
		upVar(ast);
		upFunc(ast);
		upType(ast);
		return true;
	}
*/
	public boolean isMerged() {
		return isMerged;
	}
	public void setMerged() {
		this.isMerged = true;
	}
	public AST getDeMrg(CodeGenerator codegen) {
		//de-merge and return 
		return ast_deMrg;
	}
	public HashSet<AST> getMergedAsts() {
		return merged_asts;
	}
	public void setMergedAsts(HashSet<AST> merged_asts) {
		this.merged_asts = merged_asts;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
}