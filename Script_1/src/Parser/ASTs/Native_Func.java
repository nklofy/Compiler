package Parser.ASTs;

import java.util.*;

import Interpreter.*;
import Parser.TypeSys.*;

public class Native_Func {		
	static Data_Obj runScan(Interpreter interpreter){
		Scanner scan=new Scanner(System.in);
		String s=scan.nextLine();
		scan.close();
		Data_Obj obj=new Data_Obj();
		obj.setTypeObj(interpreter.getGlbEnv().getType("string"));
		obj.setStringV(s);
		return obj;
	}
	static Data_Obj runPrint(Interpreter interpreter, Data_Obj arg){
		String s=arg.getStringV();
		System.out.println(s);
		Data_Obj obj=new Data_Obj();
		obj.setTypeObj(interpreter.getGlbEnv().getType("bool"));
		obj.setBoolV(true);
		return obj;
	}
}
