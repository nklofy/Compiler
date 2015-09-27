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
		case "variable":			
			token.createDouble(buffer);
			break;
		case "reserve":			
			token.createDouble(buffer);
			break;
		default:
			return null;
		}
		return token;
	}
	int int_value;
	double double_value;
	String res_name;
	String var_name;
	TokenType type;
	void createInt(String buffer){
		this.type=TokenType.t_int;
		this.int_value=Integer.getInteger(buffer);
	}
	void createDouble(String buffer){
		this.type=TokenType.t_double;
		this.double_value=Double.valueOf(buffer);
	}
	void createVar(String buffer){
		this.type=TokenType.t_var;
		this.var_name=buffer;
	}
	void createRes(String buffer){
		this.type=TokenType.t_res;
		this.res_name=buffer;
	}
	enum TokenType{
		t_int,t_double,t_var,t_res
	};
}

