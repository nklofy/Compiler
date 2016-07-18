package Parser;
import java.io.*;
import java.util.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class CodeGenerator {
	private int crt_line=-1;//the last row's number of codes
	private int tmp_sn=1;//naming temp vars and labels
	private ArrayList<IRCode> code_list=new ArrayList<IRCode> ();
	public HashMap<String, LinkedList<IRCode>> rps_code_list=new HashMap<String,LinkedList<IRCode>>();
	public HashMap<String, Integer> mp_label2line=new HashMap<String,Integer>();//for label and line No.
	public LinkedList<String> labels_ifbd=new LinkedList<String>();
	public LinkedList<String> labels_elsbd=new LinkedList<String>();
	public LinkedList<String> labels_whlbd=new LinkedList<String>();
	public LinkedList<String> labels_whlend=new LinkedList<String>();
	public TypeSystem type_sys=new TypeSystem();
	public LinkedList<String> ret_types=new LinkedList<String>();//check function return type and return statment's type
	public LinkedList<HashMap<String,String>> gnrc_arg=new LinkedList<HashMap<String,String>>();//records of types for generic arg
	public LinkedList<HashMap<String,String>> func_arg=new LinkedList<HashMap<String,String>>();//records of name/vars for function arg 
	
	//a type system for store/search type/name
	private LinkedList<AST> block_4symtb;
	
	//HashMap<String,T_Type> type_tb=new HashMap<String,T_Type>();//table of type info in RT
		
	
	//private HashMap<String,R_Package> pkgs_impt=new HashMap<String,R_Package>();//deal with package/name-space
	
	private LinkedList<T_Type> type_file=new LinkedList<T_Type>();//used for generating symbol table in output file
	private LinkedList<T_Function> func_file=new LinkedList<T_Function>();
	private LinkedList<R_Variable> var_file=new LinkedList<R_Variable>();
	
	private String file_name;
	private String out_file;
	private LinkedList<String> pck_name;
	private LinkedList<LinkedList<String>> impt_pcks;
	
	public int getLineNo() {
		return crt_line;
	}
	public void incLineNo() {
		this.crt_line ++;
	}
	public void setLineNo(int i){
		this.crt_line=i;
	}
	public int getTmpSn() {
		return this.tmp_sn++;
	}
	public void IncTmpSn() {
		this.tmp_sn ++;
	}

	public void setCodeList(ArrayList<IRCode> code_list) {
		this.code_list = code_list;
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
	public T_Type getTypeInSymTb(String name){
		T_Type t=null;
		String s=FindGnrcArgTb(name);
		if(s!=null)
			name=s;
		for(AST ast:this.block_4symtb){
			t=ast.type_table.get(name);
			if(t!=null)
				return t;
		}
		return null;
	}
	public boolean putTypeInSymTb(String name,T_Type type){
		AST ast=this.block_4symtb.getFirst();
		if(ast.type_table.containsKey(name))
			return false;
		ast.type_table.put(name, type);
		return true;
	}
	public R_Variable getVarInSymTb(String name){
		R_Variable r=null;
		String s=FindFuncArgTb(name);
		if(s!=null)
			name=s;
		for(AST ast:this.block_4symtb){
			r=ast.var_table.get(name);
			if(r!=null)
				return r;
		}
		return null;
	}
	public boolean putVarInSymTb(String name, R_Variable r){
		AST ast=this.block_4symtb.getFirst();
		if(ast.var_table.containsKey(name))
			return false;
		ast.var_table.put(name, r);
		return true;
	}	
	public R_Function getFuncInSymTb(String name){
		R_Function f=null;
		for(AST ast:this.block_4symtb){
			f=ast.func_table.get(name);
			if(f!=null)
				return f;
		}
		return null;
	}	
	public boolean putFuncInSymTb(String name, R_Function f){//f can be polymorphic
		AST ast=this.block_4symtb.getFirst();
		if(ast.func_table.containsKey(name)){
			R_Function f1=ast.func_table.get(name);
			if(f.isMulti()){
				for(R_Function f2:f.getMulti().values()){
					if(f1.isCntnNameType(f2))
						return false;
					f1.addFuncR(f2);
				}
				return true;
			}
			if(f1.isCntnNameType(f))
				return false;
			f1.addFuncR(f);			
		}else
			ast.func_table.put(name, f);
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
//	public R_Package getPackage(String name){
//		return this.pkgs_impt.get(name);
//	}
//	public boolean addPackage(String name, R_Package pck){
//		this.pkgs_impt.put(name, pck);
//		return true;
//	}
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
	public LinkedList<R_Variable> getVarInFile() {
		return var_file;
	}
	public void setVarInFile(LinkedList<R_Variable> var_file) {
		this.var_file = var_file;
	}
	
	public String FindGnrcArgTb(String s){
		for(HashMap<String,String> map:this.gnrc_arg){
			if(map.containsKey(s))
				return map.get(s);
		}
		return null;
	}
	public String FindFuncArgTb(String s){
		for(HashMap<String,String> map:this.func_arg){
			if(map.containsKey(s))
				return map.get(s);
		}
		return null;
	}
	public LinkedList<String> getPckName() {
		return pck_name;
	}
	public void setPckName(LinkedList<String> linkedList) {
		this.pck_name = linkedList;
	}
	public LinkedList<LinkedList<String>> getImptPcks() {
		return impt_pcks;
	}
	public void setImptPcks(LinkedList<LinkedList<String>> impt_pcks) {
		this.impt_pcks = impt_pcks;
	}
	public String getFileName() {
		return file_name;
	}
	public void setFileName(String file_name) {
		this.file_name = file_name;
	}
	public String getOutFile() {
		return out_file;
	}
	public void setOutFile(String out_file) {
		this.out_file = out_file;
	}
	public boolean outputFile(){
		//constant pool
		//Class table
		//fields table
		//methods table
		//scripts
		PrintWriter out=null;
		try {
			out=new PrintWriter(new BufferedWriter(new FileWriter(out_file)));
			out.println("89597046");
			if(!this.pck_name.isEmpty()){
				
			}
			if(!this.impt_pcks.isEmpty()){
				
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}finally{
			out.close();
		}
		return true;
	}
	//TODO
	public boolean outputClsTb(PrintWriter out){//classes or interfaces, including fields and methods
		for(T_Type t:this.type_file){
			switch(t.getKType()){
			case t_cls:
				
				break;
			case t_intf:
				
				break;
			default:
				break;
			}
		}
		return true;
	}
	public boolean outputFldTb(PrintWriter out){//fields
		return true;
	}
	public boolean outputMthdTb(PrintWriter out){//methods
		return true;
	}
	public boolean outputVarTb(PrintWriter out){//static or global variable in script area
		//no use right now. for static variable in future
		return true;
	}
	public boolean outputFuncTb(PrintWriter out){//function defined in script area
		//no use right now. for static
		return true;
	}
	public boolean outputTypTb(PrintWriter out){//types (alias, lambda p, lambda d) defined in script area 
		//no use right now
		return true;
	}
	public boolean outputScrpTb(PrintWriter out){//script's codes
		
		return true;
	}
	public boolean outputConsTb(PrintWriter out){//const pool
		
		return true;
	}
}
