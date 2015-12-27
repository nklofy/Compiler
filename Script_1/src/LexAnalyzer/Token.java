package LexAnalyzer;
//4 types of token
public class Token {
	static Token create(String pattern, String buffer){
		Token token=new Token();
		switch(pattern){
		case "int":			
			token.createInt(buffer);
			break;
		case "double":
			token.createDouble(buffer);
			break;
		case "idn":			
			token.createIdn(buffer);
			break;
		case "res":			
			token.createRes(buffer);
			break;
		case "opt":
			token.createOpt(buffer);
			break;
		case "note":
			token.createNote(buffer);
			break;
		case "string":
			token.createStr(buffer);
			break;
		case "char":
			token.createChr(buffer);
			break;
		default:
			return null;
		}
		return token;
	}
	int line_in;
	String num_value;
	String res_name;
	String idn_name;
	String opt_name;
	String note_value;
	String str_value;
	String chr_value;	
	TokenType type;
	public int getLine(){
		return line_in;
	}
	public String getType(){
		switch(type){
		case t_int:
			return "int";
		case t_double:
			return "double";
		case t_idn:
			return "idn";
		case t_res:
			return "res";
		case t_opt:
			return "opt";
		case t_note:
			return "note";
		case t_str:
			return "string";
		case t_chr:
			return "char";
		}
		return null;
	}	
	public String getResName(){
		return res_name;		
	}
	public String getOptName(){
		return opt_name;
	}
	public String getIdnName(){
		return idn_name;
	}
	public String getNumValue(){
		return num_value;
	}
	public String getStrValue(){
		return str_value;
	}
	public String getChrValue(){
		return chr_value;
	}
	void createInt(String buffer){
		this.type=TokenType.t_int;
		this.num_value=buffer;
	}
	void createDouble(String buffer){
		this.type=TokenType.t_double;
		this.num_value=buffer;
	}
	void createIdn(String buffer){
		this.type=TokenType.t_idn;
		this.idn_name=buffer;
	}
	void createRes(String buffer){
		this.type=TokenType.t_res;
		this.res_name=buffer;
	}
	void createOpt(String buffer){
		this.type=TokenType.t_opt;
		this.opt_name=buffer;
	}
	void createNote(String buffer){
		this.type=TokenType.t_note;
		this.note_value=buffer;
	}
	void createStr(String buffer){
		this.type=TokenType.t_str;
		this.str_value=buffer;
	}
	void createChr(String buffer){
		this.type=TokenType.t_chr;
		this.chr_value=buffer;
	}
	enum TokenType{
		t_int,t_double,t_idn,t_res,t_opt,t_note,t_str,t_chr
	};
}

