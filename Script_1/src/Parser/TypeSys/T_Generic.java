package Parser.TypeSys;

import java.util.*;

public class T_Generic extends T_Type {
	private String core_type;	
	HashMap<String,String> type_args=new HashMap<String,String>();
	
	public String getCoreType() {
		return core_type;
	}
	public void setCoreType(String core_type) {
		this.core_type = core_type;
	}
	public HashMap<String, String> getTypeArgTb() {
		return type_args;
	}
	public void setTypeArgTb(HashMap<String, String> type_args) {
		this.type_args = type_args;
	}
}
