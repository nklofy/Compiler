package Parser;

import java.util.*;
import Parser.TypeSys.*;

public class SymTable {
	HashMap<String,R_Record> var_table;
	HashMap<String,R_Record> type_table;
	HashMap<String,R_Record> func_table;
	LinkedList<String> var_up;
	LinkedList<String> type_up;
	LinkedList<String> func_up;
}
