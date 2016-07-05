package Parser;

import java.io.*;
import java.util.*;

import javax.swing.filechooser.FileFilter;

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
		String file_name=codegen.getFileName();
		//search YFC files
		//check YFC files' version
		//read YFC add new ast to codegen's symtable
		
		String fname_b=dir_b+File.separator+file_name+".yfc";
		codegen.setOutFile(fname_b);
		File f_yfc=new File(fname_b);
		if(f_yfc.exists()){
			f_yfc.delete();
		}else{
			File dir_f=new File(dir_b);
			if(!dir_f.exists()){
				dir_f.mkdirs();					
			}			
		}
		return true;
	}
	public boolean imptPcks(Parser parser, CodeGenerator codegen, String dir_b, String dir_s){		
		LinkedList<LinkedList<String>> impt_pcks=parser.getImptPcks();
		
		for(LinkedList<String>pck:impt_pcks){
			//search YFC files in dir_src/dir_lib
			//check YFC files' version
			//read YFC add new ast to codegen's symtable
			String dir_f="";
			if(pck.getLast().equals("*")){//src code like import A.B.*
				for(int i=0;i<pck.size()-1;i++){
					dir_f+=File.separator+pck.get(i);
				}
				File f_b=new File(dir_b+dir_f);
				File f_s=new File(dir_s+dir_f);
				File f_l=new File(this.dir_lib+dir_f);
				if(f_s.exists()){
					if(f_b.exists()){
						File[] files=f_s.listFiles();
						for(File f1:files){
							if(f1.isFile()){
								String fn1=f1.getName();
								if(!fn1.substring(fn1.indexOf(File.separator), fn1.length()).equals(".yfl"))
									continue;
								String s1=dir_b+dir_f+fn1.substring(0,fn1.indexOf(File.separator));
								File f2=new File(s1+File.separator+"yfc");
								if(f2.exists()&&f2.lastModified()<f1.lastModified()){//find yfc file and check version
									continue;
								}else{//parse or re-parse yfl files
									try {
										parser.parse(f1.getCanonicalPath());
									} catch (IOException e) {
										e.printStackTrace();
									}
									this.asts_todo.add(parser.getAST());
									CodeGenerator codegen1=new CodeGenerator();
									this.astgen_map.put(parser.getAST(), codegen1);
								}
							}
						}
					}else{//if(f_b.exists()){
						File[] files=f_s.listFiles();
						for(File f1:files){
							if(f1.isFile()){//parse yfl files 
								try {
									parser.parse(f1.getCanonicalPath());
								} catch (IOException e) {
									e.printStackTrace();
								}
								this.asts_todo.add(parser.getAST());
								CodeGenerator codegen1=new CodeGenerator();
								this.astgen_map.put(parser.getAST(), codegen1);
							}
						}						
					}
				}else if(f_b.exists()){
					File[] files=f_b.listFiles();
					for(File f2:files){
						if(f2.isFile()){
							String fn2=f2.getName();
							if(!fn2.substring(fn2.indexOf(File.separator), fn2.length()).equals(".yfc"))
								continue;
							this.imptYFC(f2);
						}
					}
				}else if(f_l.exists()){
					File[] files=f_l.listFiles();
					for(File f2:files){
						if(f2.isFile()){
							String fn2=f2.getName();
							if(!fn2.substring(fn2.indexOf(File.separator), fn2.length()).equals(".yfc"))
								continue;
							this.imptYFC(f2);
						}
					}
				}
			}else{//src code like import A.B.C
				for(String s:pck){
					dir_f+=File.separator+s;				
				}
				File f_b=new File(dir_b+dir_f+File.separator+"yfc");
				File f_s=new File(dir_s+dir_f+File.separator+"yfl");
				File f_l=new File(this.dir_lib+dir_f+File.separator+"yfc");
				if(f_s.exists()){
					if(f_b.exists()){
						
					}else{//if(f_b.exists()){
												
					}
				}else if(f_b.exists()){
					
				}else if(f_l.exists()){
					
				}
			}
		}
		return true;
	}	
	public boolean imptYFC(File f_ylc){
		
		return true;
	}
	public boolean genYFC(String f_yfl){
		
		return true;
	}
	
	
}
