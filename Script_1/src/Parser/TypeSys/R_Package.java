package Parser.TypeSys;

import java.util.*;

public class R_Package extends R_Record{
	public HashMap<String,R_Package> sub_pkgs;
	public HashMap<String,R_Function>func_inPkg;
	public HashMap<String,R_Variable>var_inPkg;
	public HashMap<String,R_Type>type_inPkg;
}
