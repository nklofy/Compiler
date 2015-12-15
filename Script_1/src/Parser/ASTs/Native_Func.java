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
		obj.type_obj=interpreter.getGlbEnv().getType("string");
		obj.string_value=s;
		return obj;
	}
	static Data_Obj runPrint(Interpreter interpreter, Data_Obj arg){
		String s=arg.string_value;
		System.out.println(s);
		Data_Obj obj=new Data_Obj();
		obj.type_obj=interpreter.getGlbEnv().getType("bool");
		obj.bool_value=true;
		return obj;
	}
}
