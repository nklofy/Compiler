package Parser.TypeSys;

import java.util.*;

public class T_Generic extends T_Type {
	public T_Type core_type;
	public HashMap<String,T_Type> type_args=new HashMap<String,T_Type>();
	
}
