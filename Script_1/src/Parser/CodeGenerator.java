package Parser;
import java.util.*;
import Parser.IR.*;

public class CodeGenerator {
	int crt_line;
	int tmp_sn;
	public int getLineNo() {
		return crt_line;
	}
	public void incLineNo() {
		this.crt_line ++;
	}
	ArrayList<IRCode> code_list=new ArrayList<IRCode> ();
	public void addCode(IRCode code){
		this.code_list.add(code);
	}
	public ArrayList<IRCode> getCodeList(){
		return this.code_list;
	}
	public boolean replaceLb(int lb, int pos, int rps){
		return true;
	}
	public int getTmpSn() {
		return tmp_sn;
	}
	public void IncTmpSn() {
		this.tmp_sn ++;
	}
}
