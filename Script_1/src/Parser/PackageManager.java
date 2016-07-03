package Parser;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PackageManager {
	//if not parsed, parse and add ast to ast_4_symtbl
	//if already had YFC file, read YFC file and add new ast to ast_4_symtbl
	
	
	LinkedList<AST> asts_todo=new LinkedList<AST>();//asts in queue for gen YFC file
	HashMap<AST,CodeGenerator> astgen_map=new HashMap<AST,CodeGenerator>();//ASTs and codegens for them
	String dir_lib;//lib files, maybe setup by system path in future
	
	public void compile(Parser parser, String file_name) throws IOException {
		parser.parse(file_name);
		AST tree=parser.getAST();
		asts_todo.add(tree);
		CodeGenerator codegen=new CodeGenerator();
		astgen_map.put(tree, codegen);
		codegen.setFileName(file_name);
		String dir=(new File("")).getCanonicalPath();
		
		String dir_b=(getDir(parser.getPckNames(),dir))[0];//.../bin
		String dir_s=(getDir(parser.getPckNames(),dir))[1];//.../src
		
		while(!asts_todo.isEmpty()){
			AST ast=asts_todo.removeFirst();
			codegen=astgen_map.get(ast);
			
			//TODO
			setPck(parser,codegen,dir_b);//set package for current YFL file, set output file path
			imptPcks(parser,codegen,dir_s,dir_b);//deal with packages imported, parse them and update symtable
			
			
			ast.checkType(codegen);
			ast.genCode(codegen);
			codegen.outputFile();
		}
		
	}
	
	
	
	public String[] getDir(LinkedList<String> pck_names, String dir){//get dir of ..../src	
		String[] dirs=dir.split(File.separator);
		String[] dir_r=new String[2];
		int l=pck_names.size();
		int l1=dirs.length;
		if(l1<=l)
			return null;
		int l_t=0;
		for(int i=0;i<l;i++){			
			if(!pck_names.get(l-i-1).equals(dirs[l1-i-1])){
				return null;
			}
			l_t+=dirs[l1-i-1].length()+1;
		}
		l_t=dir.length()-l_t;
		String dir_bin="";
		String dir_src="";
		if(dirs[l1-2-l].equals("src")){//..../src; ...../bin
			dir_bin=dir.substring(0,l_t-4)+File.separator+"bin";
		}else{// without ../src; only .../bin
			dir_bin=dir.substring(0,l_t)+File.separator+"bin";
		}
		dir_src=dir.substring(0,l_t);
		dir_r[0]=dir_bin;
		dir_r[1]=dir_src;
		return dir_r;
	}
	public String getDir_b(LinkedList<String> pck_names, String file_name, String dir){
		String dir_bin=dir;
		for(int i=0;i<pck_names.size();i++){
			dir_bin+=File.separator+pck_names.get(i);
		}
		return dir_bin;
	} 
	public boolean setPck(Parser parser, CodeGenerator codegen, String dir_b){		
		LinkedList<String> pck_names=parser.getPckNames();
		
		//search YFC files
		//check YFC files' version
		//read YFC add new ast to codegen's symtable
		
		/*String fname_b=dir_bin+File.separator+file_name+".yfc";
		File f_yfc=new File(fname_b);
		if(f_yfc.exists()){
			//check version 
			//imptYFC(f_yfc); 
		}else{
			File dir_f=new File(dir_bin);
			if(!dir_f.exists()){
				dir_f.mkdirs();					
			}
			//f_yfc.createNewFile();
			//imptYFL(dir_b+File.separator+this.file_name+".yfl");
		}*/
		return true;
	}
	public boolean imptPcks(Parser parser, CodeGenerator codegen, String dir_b, String dir_s){		
		LinkedList<LinkedList<String>> impt_pcks=parser.getImptPcks();
		//search YFC files
		//check YFC files' version
		//read YFC add new ast to codegen's symtable
		
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
