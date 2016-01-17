package Parser;
import java.util.*;
import Parser.IR.*;

public class CodeGenerator {
	ArrayList<IRCode> code_list=new ArrayList<IRCode> ();
	public void addCode(IRCode code){
		this.code_list.add(code);
	}
	public ArrayList<IRCode> getCodeList(){
		return this.code_list;
	}
}
