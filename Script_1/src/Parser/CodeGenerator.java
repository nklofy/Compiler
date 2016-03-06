package Parser;
import java.util.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class CodeGenerator {
	int crt_line=1;//code's line
	int tmp_sn=1;//naming temp vars and labels
	ArrayList<IRCode> code_list=new ArrayList<IRCode> ();
	public HashMap<String, LinkedList<IRCode>> rps_code_list=new HashMap<String,LinkedList<IRCode>>();
	public HashMap<String, Integer> mp_label2line=new HashMap<String,Integer>();
	public LinkedList<String> labels_ifbd;
	public LinkedList<String> labels_elsbd;
	public LinkedList<String> labels_whlbd;
	public LinkedList<String> labels_whlend;
	//a type system for store/search type/name
	
	
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
			//String lb1=code.getOpd1();
			String lb2=code.getOpd2();
			String lb3=code.getOpd3();
			//String lb4=code.getOpd4();
			if(this.mp_label2line.containsKey(lb2)){
				code.setOpd2(this.mp_label2line.get(lb2).toString());
			}
			if(this.mp_label2line.containsKey(lb3)){
				code.setOpd3(this.mp_label2line.get(lb3).toString());
			}						
		}
		return true;
	}
	public T_Type getRTType(String name){
		return null;
	}
}
