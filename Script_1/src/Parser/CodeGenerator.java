package Parser;
import java.util.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class CodeGenerator {
	int crt_line=1;//code's line
	int tmp_sn=1;//naming temp vars and labels
	ArrayList<IRCode> code_list=new ArrayList<IRCode> ();
	public HashMap<String, LinkedList<IRCode>> rps_code_list=new HashMap<String,LinkedList<IRCode>>();
	public HashMap<String, Integer> mp_label2line=new HashMap<String,Integer>();//for label and line No.
	public LinkedList<String> labels_ifbd=new LinkedList<String>();
	public LinkedList<String> labels_elsbd=new LinkedList<String>();
	public LinkedList<String> labels_whlbd=new LinkedList<String>();
	public LinkedList<String> labels_whlend=new LinkedList<String>();
	public LinkedList<String> ret_types=new LinkedList<String>();//check function return type and return statment's type
	
	//a type system for store/search type/name
	LinkedList<AST> block_4symtb;
	
	//HashMap<String,T_Type> type_tb=new HashMap<String,T_Type>();//table of type info in RT
		
	
	HashMap<String,R_Package> packages=new HashMap<String,R_Package>();//deal with package/name-space
	
	LinkedList<T_Type> type_file=new LinkedList<T_Type>();//used for generating symbol table in output file
	LinkedList<T_Function> func_file=new LinkedList<T_Function>();
	LinkedList<R_Variable> var_file=new LinkedList<R_Variable>();
	
	
	
	public int getLineNo() {
		return crt_line;
	}
	public void incLineNo() {
		this.crt_line ++;
	}
	public int getTmpSn() {
		return this.tmp_sn++;
	}
	public void IncTmpSn() {
		this.tmp_sn ++;
	}

	public void addCode(IRCode code){
		this.code_list.add(code);
	}
	public ArrayList<IRCode> getCodeList(){
		return this.code_list;
	}	
	public LinkedList<IRCode> getRpsLst(String lb){
		if(this.rps_code_list.keySet().contains(lb)){
			return this.rps_code_list.get(lb);
		}else{
			this.rps_code_list.put(lb, new LinkedList<IRCode>());
			return this.rps_code_list.get(lb);
		}
	}
	public boolean replaceLb(String lable){
		LinkedList<IRCode> rps_codes=this.rps_code_list.get(lable);
		for(IRCode code:rps_codes){
			String lb2=code.getOpd2();
			String lb3=code.getOpd3();
			if(this.mp_label2line.containsKey(lb2)){
				code.setOpd2(this.mp_label2line.get(lb2).toString());
			}
			if(this.mp_label2line.containsKey(lb3)){
				code.setOpd3(this.mp_label2line.get(lb3).toString());
			}						
		}
		return true;
	}
	public T_Type getTypeSymTb(String name){
		T_Type t=null;
		for(AST ast:this.block_4symtb){
			t=ast.type_table.get(name);
			if(t!=null)
				return t;
		}		
		return t;
	}
	public boolean addTypeSymTb(String name,T_Type type){
		AST ast=this.block_4symtb.getFirst();
		ast.type_table.put(name, type);
		type.setTypeName(name);
		return true;
	}
	public R_Variable getVarInTb(String name){//TODO
		return null;
	}	
	public boolean addVarSymTb(String name, R_Variable r){
		return true;
	}	
	public R_Function getFuncSymTb(String name){
		return null;
	}	
	public boolean addFuncSymTb(String name, T_Function f){
		return true;
	}
	public AST peekBlock4Sym() {
		return block_4symtb.peek();
	}
	public void pushBlock4Sym(AST block) {
		this.block_4symtb.addFirst(block);
	}
	public AST popBlock4Sym(){
		return this.block_4symtb.remove();
	}
	public R_Package getPackage(String name){
		return this.packages.get(name);
	}
	public boolean addPackage(String name, R_Package pck){
		this.packages.put(name, pck);
		return true;
	}
	public LinkedList<T_Type> getTypeInFile() {
		return type_file;
	}
	public void addTypeInFile(T_Type type_file) {
		this.type_file.add(type_file);
	}	
	public LinkedList<T_Function> getFuncInFile() {
		return func_file;
	}
	public void addtFuncInFile(T_Function func_file) {
		this.func_file.add(func_file);
	}
	
}
