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
		default:
			return null;
		}
		return token;
	}
	int int_value;
	double double_value;
	String res_name;
	String idn_name;
	String opt_name;
	TokenType type;
	void createInt(String buffer){
		this.type=TokenType.t_int;
		this.int_value=Integer.parseInt(buffer);
	}
	void createDouble(String buffer){
		this.type=TokenType.t_double;
		this.double_value=Double.parseDouble(buffer);
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
	enum TokenType{
		t_int,t_double,t_idn,t_res,t_opt
	};
}

