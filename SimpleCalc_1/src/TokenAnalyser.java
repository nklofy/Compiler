import java.util.*;

//词法分析器
class TokenAnalyser{
	Scanner file_scan;
	String new_line=null;
	int ln_no=1;
	int ln_index=0;
	int ln_length=0;
	char ln_char='\0';
	int ln_bg=0;
	int ln_ed=0;
	Token this_token=new Token(), pre_token=new Token();
	boolean has_pre=false;	
	//若是LR需要栈，则构造多个Token，若是LL(1),只需要2个token。	
	List<Token> token_buffer=new LinkedList<Token>();
	char[] buffer=new char[1024];
	int buffer_index=0;
	Lex_State lx_state=Lex_State.start;
	
	public TokenAnalyser(Scanner file_scan){
		this.file_scan=file_scan;
	}
	
	public boolean lookAhead(){
		Token token=new Token();
		getToken(token);
		token_buffer.add(token);
		return true;
	}	
	
	public Token take(){
		if(token_buffer.isEmpty()){
			lookAhead();
		}
		return token_buffer.remove(0);
	}
	
	public Token peak(){
		if(token_buffer.isEmpty()){
			lookAhead();
		}
		return token_buffer.get(0);
	}
	
	//get与unGet适用于LL(1)的paser
	public Token get(){
		if(has_pre){
			has_pre=false;
			return pre_token;
		}
		getToken(this_token);
		return this_token;
	}
	
	public boolean unGet(){
		pre_token=this_token;
		has_pre=true;
		return true;
	}
	
	public boolean hasNext(){
		if(!file_scan.hasNext() && lx_state==Lex_State.end || lx_state==Lex_State.finish )
			return false;
		return true;
	}
	
	public boolean getToken(Token token){
		if(lx_state==Lex_State.end){
			if(!file_scan.hasNext()){
				lx_state=Lex_State.finish;
				token.type=Token_Type.mark;
				token.name="finish";
				return true;
			}
			else{
				lx_state=Lex_State.start;
				token.type=Token_Type.mark;
				token.name="end";
				return true;
			}
		}
		if(lx_state==Lex_State.start){
			new_line=file_scan.nextLine();
			System.out.println("line "+ ln_no+ ": "+ new_line);		
			ln_no++;
			ln_index=0;
			ln_length=new_line.length();
		}
		getNextToken(new_line,token);
		return true;
	}
	
	Boolean getNextToken(String new_line,Token current_token){
		while(ln_index<ln_length){
			ln_char=new_line.charAt(ln_index++);
			switch(lx_state){
			//-------------------------------------------------------------------------start state
			case start:	
				buffer_index=0;
				switch(recognize(ln_char)){
				case "space":
					lx_state=Lex_State.space;
					break;
				case "digit":
					current_token.type=Token_Type.digit; buffer[buffer_index++]=ln_char;
					lx_state=Lex_State.digit;					
					break;
				case "symbol":
					current_token.type=Token_Type.symbol;buffer[buffer_index++]=ln_char;
					lx_state=Lex_State.symbol;
					break;
				case "operator":
					current_token.type=Token_Type.operator;buffer[buffer_index++]=ln_char;
					lx_state=Lex_State.operator;
					break;
				}
				break;
			//-------------------------------------------------------------------------space state
			case space:	
				switch(recognize(ln_char)){
				case "space":
					lx_state=Lex_State.space;
					break;
				case "digit":
					current_token.type=Token_Type.digit; buffer[buffer_index++]=ln_char;
					lx_state=Lex_State.digit;
					break;
				case "symbol":
					current_token.type=Token_Type.symbol;buffer[buffer_index++]=ln_char;
					lx_state=Lex_State.symbol;
					break;
				case "operator":
					current_token.type=Token_Type.operator;buffer[buffer_index++]=ln_char;
					lx_state=Lex_State.operator;
					break;
				}
				break;
			//-------------------------------------------------------------------------digit state
			case digit:
				switch(recognize(ln_char)){
				case "space":
					current_token.type=Token_Type.digit;
					current_token.value=getValueFromBuffer(buffer,buffer_index);
					current_token.name="num";
					buffer_index=0;lx_state=Lex_State.space;
					return true;
				case "digit":
					buffer[buffer_index++]=ln_char;
					break;
				case "operator":
					if(ln_char=='.'){
						buffer[buffer_index++]=ln_char;
						lx_state=Lex_State.decimal;
						break;
					}else{
						current_token.type=Token_Type.digit;
						current_token.value=getValueFromBuffer(buffer,buffer_index);
						current_token.name="num";
						buffer_index=0;lx_state=Lex_State.operator;
						buffer[buffer_index++]=ln_char;
						return true;
					}
				}
				break;
			//-------------------------------------------------------------------------symbol state
			case symbol:	
				switch(recognize(ln_char)){
				case "space":
					current_token.type=Token_Type.symbol;
					current_token.name=getNameFromBuffer(buffer,buffer_index);
					buffer_index=0;lx_state=Lex_State.space;
					return true;
				case "digit":
					buffer[buffer_index++]=ln_char;
					break;
				case "symbol":
					buffer[buffer_index++]=ln_char;
					break;
				case "operator":
					current_token.type=Token_Type.symbol;
					current_token.name=getNameFromBuffer(buffer,buffer_index);
					buffer_index=0;lx_state=Lex_State.operator;
					buffer[buffer_index++]=ln_char;
					return true;
				}
				break;
			//-------------------------------------------------------------------------operator state
			case operator:
				switch(recognize(ln_char)){
				case "space":
					current_token.type=Token_Type.operator;
					current_token.name=getNameFromBuffer(buffer,buffer_index);
					buffer_index=0;lx_state=Lex_State.space;
					return true;
				case "digit":
					current_token.type=Token_Type.operator;
					current_token.name=getNameFromBuffer(buffer,buffer_index);
					buffer_index=0;lx_state=Lex_State.digit;
					buffer[buffer_index++]=ln_char;
					return true;
				case "symbol":
					current_token.type=Token_Type.operator;
					current_token.name=getNameFromBuffer(buffer,buffer_index);
					buffer_index=0;lx_state=Lex_State.symbol;
					buffer[buffer_index++]=ln_char;
					return true;
				case "operator":
					current_token.type=Token_Type.operator;
					current_token.name=getNameFromBuffer(buffer,buffer_index);
					buffer_index=0;lx_state=Lex_State.operator;
					buffer[buffer_index++]=ln_char;
					return true;
				}
				break;
			//-------------------------------------------------------------------------decimal state
			case decimal:	
				switch(recognize(ln_char)){
				case "space":
					current_token.type=Token_Type.digit;
					current_token.value=getValueFromBuffer(buffer,buffer_index);
					current_token.name="num";
					buffer_index=0;lx_state=Lex_State.space;
					return true;
				case "digit":
					buffer[buffer_index++]=ln_char;					
					break;
				case "operator":
					current_token.type=Token_Type.digit;
					current_token.value=getValueFromBuffer(buffer,buffer_index);
					current_token.name="num";
					buffer_index=0;lx_state=Lex_State.operator;
					buffer[buffer_index++]=ln_char;
					return true;
				}
				break;
			default:
				break;
			
			}//switch(lx_state)

		}//while(ln_index<ln_length)

		//the last char of a line
		if(ln_index==ln_length){
			switch(lx_state){
			case symbol:
				current_token.type=Token_Type.symbol;
				current_token.name=getNameFromBuffer(buffer,buffer_index);
				buffer_index=0;lx_state=Lex_State.end;
				return true;
				//-------------------------------------------------------------------------operator state
			case operator:
				current_token.type=Token_Type.operator;
				current_token.name=getNameFromBuffer(buffer,buffer_index);
				buffer_index=0;lx_state=Lex_State.end;
				return true;
				//-------------------------------------------------------------------------digit state
			case digit:	
				current_token.type=Token_Type.digit;
				current_token.value=getValueFromBuffer(buffer,buffer_index);
				current_token.name="num";
				buffer_index=0;lx_state=Lex_State.end;
				return true;
				//-------------------------------------------------------------------------decimal state
			case decimal:
				current_token.type=Token_Type.digit;
				current_token.value=getValueFromBuffer(buffer,buffer_index);
				current_token.name="num";
				buffer_index=0;lx_state=Lex_State.end;
				return true;
			default:
				current_token.type=Token_Type.mark;
				current_token.name="end";
				buffer_index=0;lx_state=Lex_State.end;
				return true;
			}
		}
		return false;
	}

	String recognize(char ln_char){
		if(ln_char<='9' && ln_char>='0'){
			//System.out.println("digit "+ ln_char);
			return "digit";
		}
		else if((ln_char<='z' && ln_char>='a') || (ln_char<='Z' && ln_char>='A') || ln_char=='_'){
			//System.out.println("symbol "+ ln_char);
			return "symbol";
		}
		else if(ln_char=='.' || ln_char=='+' || ln_char=='-' || ln_char=='*' || ln_char=='/' 
				|| ln_char=='=' || ln_char=='(' || ln_char==')'){
			//System.out.println("operator "+ ln_char);
			return "operator";				
		}
		else if(ln_char==9 || ln_char==10|| ln_char==32){
			//System.out.println("space "+ ln_char);
			return "space";
		}
		else return "unknown";
	}
	
	double getValueFromBuffer(char[] buffer,int buffer_index){
		double result=0.0;
		boolean decimal=false;
		double index=1;
		for(int i=0; i<buffer_index; ++i){
			if(decimal==true){
				index*=10;
				result=result+((double)(buffer[i]-48))/index;
			}
			if(buffer[i]!='.' && decimal==false){
				result=result*10+(buffer[i]-48);
			}
			if(buffer[i]=='.' && decimal==false){
				decimal=true;
			}
		}
		return result;
	}

	String getNameFromBuffer(char[] buffer,int buffer_index){
		String result="";
		for(int i=0;i<buffer_index;++i){
			result+=buffer[i];
		}
		return result;
		}
}

enum Token_Type{digit,operator,symbol,mark};

enum Lex_State{start,end,digit,operator,symbol,space,decimal,finish};

class Token{	
	public Token_Type type;
	public double value;
	public String name;
}