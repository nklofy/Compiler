package Parser;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PackageManager {
	//if not parsed, parse and add ast to ast_4_symtbl
	//if already had YFC file, read YFC file and add new ast to ast_4_symtbl
	private String file_name;
	private LinkedList<String> pck_names;//package A.B.C	
	private LinkedList<LinkedList<String>> impt_pcks; //all import packages
	
	public boolean setPcks(Parser parser){
		this.setPckNames(parser.getPckNames());		//check dirs
		this.setFileName(parser.getFileName());
		String dir;
		try {
			dir = (new File("")).getCanonicalPath();
			String[] dirs=dir.split(File.separator);
			int l=this.pck_names.size();
			int l1=dirs.length;
			if(l1<=l)
				return false;
			int l_t=0;
			for(int i=0;i<l;i++){			
				if(!this.pck_names.get(l-i-1).equals(dirs[l1-i-1])){
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
				dir_b+=File.separator+this.pck_names.get(i);
			}
			File f_yfc=new File(dir_b+File.separator+this.file_name+".yfc");
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
	public boolean imptPcks(Parser parser){

		this.setImptPcks(parser.getImptPcks());
		return true;
	}
	public boolean imptYFL(String f_yfl){//TODO
		return true;
	}
	public boolean imptYFC(File f_ylc){
		return true;
	}
	public boolean genYFC(String f_yfl){
		return true;
	}
	public LinkedList<String> getPckNames() {
		return pck_names;
	}
	public void setPckNames(LinkedList<String> pck_names) {
		this.pck_names = pck_names;
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
}
