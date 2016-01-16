package Parser;

public class Symbol {//symbol in graph stack correspond to reduce path or merged paths
	AST ast;
	int line;
	ParseState path_start;	//start of reduce
	ParseState path_end;	//end of reduce
	int path_count; 	//edges in reduce path
	String type;
	String name;
	String value;
}