import java.util.*;

class Symbol_Table{
	Map<String,Var> table=new HashMap<String,Var>();
	Map<String,Var> getTable(){
		return table;
	}
	public void add(String name,Var var) {
		table.put(name,var);
	}
	public Var get(String name) {
		return table.get(name);
	}
}
class Frame{
	
}
