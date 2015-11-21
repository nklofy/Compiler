//public API: setScanFile(String) getToken() 
package LexAnalyzer;

import java.io.*;
import java.util.*;

public class Tokenizer {
	private Token eof_token=Token.create("res","eof");
	private Token token;
	private HashMap<Integer,HashMap<Character, Integer>> transfer_table=new HashMap<Integer,HashMap<Character, Integer>> ();
	private HashMap<Integer,String> terminal_opt=new HashMap<Integer,String> ();
	private HashMap<Integer,String> terminal_res=new HashMap<Integer,String> ();
	private HashMap<Integer,String> terminal_idn=new HashMap<Integer,String> ();
	
	public static void main(String[] args) {
		Tokenizer tokenizer=new Tokenizer();
		tokenizer.analyze("out_lexAnalyzer.txt"); System.out.println("finish analyze lex");
		tokenizer.input("script_test1.txt");		System.out.println("finish get all tokens");
		tokenizer.output("out_script_lexer.txt");	System.out.println("finish output file");
	}
	public boolean analyze(String filename){
		Scanner in = null;
		String word;
		try {
			in=new Scanner(new BufferedReader(new FileReader(filename)));
			if(in.hasNext()){
				word=in.nextLine();
				if(!word.equals("//transfer table"))
					return false;
			}
			else
				return false;			
			if(in.hasNext())
				word=in.nextLine();
			else
				return false;
			while(!word.equals("//regex") && !word.equals("")){				
				String words_char[]=word.split(" ");
				int index_state=Integer.valueOf(words_char[0]);
				transfer_table.put(index_state,new HashMap<Character, Integer>() );
				
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
				String words_state[]=word.split(" ");				
				for(int i=1;i<words_char.length;i++){
					if(!words_char[i].equals("")){						
						transfer_table.get(index_state).put(words_char[i].charAt(0),Integer.parseInt(words_state[i]));
					}					
				}
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}//while(!word.equals("//regex") && !word.equals(""))
			while(word.equals("") || word.equals("//regex")){
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}
			while(!word.equals("//res") && !word.equals("")){
				String words_idn[]=word.split(" ");
				int index_state=Integer.valueOf(words_idn[0]);
				terminal_idn.put(index_state, words_idn[1]);
				//System.out.println(words_idn[0]+":"+words_idn[1]);
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}//while(!word.equals("//res") && !word.equals(""))
			while(word.equals("") || word.equals("//res")){
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}
			while(!word.equals("//opt") && !word.equals("")){
				String words_res[]=word.split(" ");
				int index_state=Integer.valueOf(words_res[0]);
				terminal_res.put(index_state, words_res[1]);
				//System.out.println(words_res[0]+":"+words_res[1]);
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}//while(!word.equals("//opt") && !word.equals(""))
			while(word.equals("") || word.equals("//opt")){
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}
			while(!word.equals("//end") && !word.equals("")){	
				String words_opt[]=word.split(" ");
				int index_state=Integer.valueOf(words_opt[0]);
				terminal_opt.put(index_state, words_opt[1]);
				//System.out.println(words_opt[0]+":"+words_opt[1]);
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}//while(!word.equals("//end") && !word.equals(""))
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			in.close();
		}
		return true;
	}
	
	private Scanner in;
	
	public boolean setScanFile(String filename){
		try {
			in=new Scanner(new BufferedReader(new FileReader(filename)));
		} catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	private String buffered_line=null;
	private int index_pre=0;
	private int index_crt=0;
	
	public Token getToken(){		
		if(buffered_line==null){
			if(!in.hasNextLine()){
				in.close();
				return eof_token;
			}else{
				buffered_line=in.nextLine();
				index_pre=0;
				index_crt=0;
			}
		}		
		String buffer="";
		String pattern="";
		char chr;
		int state=1;
		boolean is_inToken=false;
		while(index_crt<buffered_line.length()){
			chr=buffered_line.charAt(index_crt++);
			if(chr==' '|| chr=='	'){
				if(is_inToken){
					index_crt--;
					break;
				}else{
					index_pre=index_crt;
					continue;
				}
			}
			if(!transfer_table.get(state).keySet().contains(chr)){
				if(state!=1){		//for example, int oprator 
					index_crt--;
					break;
				}
				else{			//unrecognized token
					index_pre=index_crt;
					continue;
				}				
			}			
			state=transfer_table.get(state).get(chr);
			is_inToken=true;
		}//while(index_crt<buffered_line.length())
		if(state==1){
			if(index_crt>=buffered_line.length()){
				buffered_line=null;	
				index_pre=0;
				index_crt=0;
			}
			token=getToken();
			return token;
		}
		if(terminal_opt.keySet().contains(state)){
			buffer=buffered_line.substring(index_pre,index_crt);
			pattern="opt";
			index_pre=index_crt;
		}else if(terminal_res.keySet().contains(state)){
			buffer=buffered_line.substring(index_pre,index_crt);
			pattern="res";
			index_pre=index_crt;					
		}else if(terminal_idn.keySet().contains(state)){
			buffer=buffered_line.substring(index_pre,index_crt);
			String idn_t=terminal_idn.get(state);
			if(idn_t.equals("int_num")){
				pattern="int";
			}else if(idn_t.equals("double_num")){
				pattern="double";
			}else{
				pattern="idn";				
			}
			index_pre=index_crt;			
		}else{
			System.out.println("unkown token "+buffered_line.substring(index_pre,index_crt)+" in "+buffered_line); //TODO
		}
		if(index_crt>=buffered_line.length()){
			buffered_line=null;			
		}	
		token=Token.create(pattern, buffer);
			
		return token;		
	}
	private LinkedList<Token> all_tokens;
	private boolean input(String filename){		
		setScanFile(filename);
		all_tokens=new LinkedList<Token>();
		Token token;
		while(true){
			token=getToken();
			//System.out.println(token.type);	
			if(token==eof_token){
				all_tokens.addLast(token);				
				break;
			}
			if(token!=null){
				all_tokens.addLast(token);				
			}
			else{
				System.out.println("null token");
			}				
		}
		return true;
	}
	private boolean output(String filename){
		PrintWriter out=null;
		String line="";
		try {
			out=new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			for(Token token:all_tokens){
				if(token==eof_token){
					line="eof: end of file!";
					out.println(line);
					return true;
				}
				switch(token.type){
				case t_int:
					line="int_number :"+token.num_value;
					out.println(line);
					break;
				case t_double:
					line="double_number :"+token.num_value;
					out.println(line);
					break;
				case t_idn:
					line="identifier :"+token.idn_name;
					out.println(line);
					break;
				case t_res:
					line="resserved :"+token.res_name;
					out.println(line);
					break;
				case t_opt:
					line="oprator :"+token.opt_name;
					out.println(line);
					break;
				default:
					line="others :"+token;
					out.println(line);
					break;					
				}				
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}finally{
			out.close();
		}
		return false;
	}

}
