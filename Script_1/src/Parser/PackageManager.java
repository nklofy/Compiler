package Parser;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PackageManager {
	//if not parsed, parse and add ast to ast_4_symtbl
	//if already had YFC file, read YFC file and add new ast to ast_4_symtbl
	
	
	LinkedList<AST> asts_todo=new LinkedList<AST>();//asts in queue for gen YFC file
	HashMap<AST,CodeGenerator> astgen_map=new HashMap<AST,CodeGenerator>();//ASTs and codegens for them
	
	public void compile(Parser parser, String file_name) {
		// TODO Auto-generated method stub
		parser.parse(file_name);
		AST tree=parser.getAST();
		asts_todo.add(tree);
		CodeGenerator codegen;
		astgen_map.put(tree, new CodeGenerator());
		while(!asts_todo.isEmpty()){
			AST ast=asts_todo.removeFirst();
			codegen=astgen_map.get(ast);
			//set package and filename
			setPcks(parser,codegen);
			//import packages
			imptPcks(parser,codegen);
			//parse package files, add into asts_todo 
			//or import YFC files, 
			//update ast symtable
			
			/*for all package in pcks
			search for YFC files
			add new ast to codegen's asts_4symtable
			or search for YFL files
			parse YFL files
			add ast to asts_todo
			add ast to codegen's asts_4symtable
						
			*/
			
			
			ast.checkType(codegen);
			ast.genCode(codegen);
			codegen.outputFile();
		}
		
	}
	
	
	
	public boolean setPcks(Parser parser, CodeGenerator codegen){
		String file_name;
		LinkedList<String> pck_names;//package A.B.C	
		LinkedList<LinkedList<String>> impt_pcks; //all import packages
		pck_names=parser.getPckNames();		//check dirs
		file_name=parser.getFileName();
		String dir;
		try {
			dir = (new File("")).getCanonicalPath();
			String[] dirs=dir.split(File.separator);
			int l=pck_names.size();
			int l1=dirs.length;
			if(l1<=l)
				return false;
			int l_t=0;
			for(int i=0;i<l;i++){			
				if(!pck_names.get(l-i-1).equals(dirs[l1-i-1])){
					return false;
				}
				l_t+=dirs[l1-i-1].length()+1;
			}
			l_t=dir.length()-l_t;
			String dir_b="";
			if(dirs[l1-2-l].equals("src")){//..../src; ...../bin
				dir_b=dir.substring(0,l_t-4)+File.separator+"bin";				
			}else{// no /src; only .../bin
				dir_b=dir.substring(0,l_t)+File.separator+"bin";
			}
			for(int i=0;i<l;i++){
				dir_b+=File.separator+pck_names.get(i);
			}
			File f_yfc=new File(dir_b+File.separator+file_name+".yfc");
			if(f_yfc.exists()){
				//check version TODO
				//imptYFC(f_yfc); 
			}else{
				File dir_f=new File(dir_b);
				if(!dir_f.exists()){
					dir_f.mkdirs();					
				}
				f_yfc.createNewFile();
				//imptYFL(dir_b+File.separator+this.file_name+".yfl");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		//search in dirs for .yfc files in /bin
		
		//compiler .yfl if no .yfc file in /src
		
				
		return true;
	}
	
	public boolean imptPcks(Parser parser, CodeGenerator codegen){		
		return true;
	}
	public boolean imptYFL(String f_yfl){
		return true;
	}
	public boolean imptYFC(File f_ylc){
		return true;
	}
	public boolean genYFC(String f_yfl){
		return true;
	}
	
	
}
