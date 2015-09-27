import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Parser{
	AST ast_tree;
	public void parse(Scanner file_scan){
		TokenAnalyser token_analyser=new TokenAnalyser(file_scan);
		ast_tree=new AST();
		create_ast_tree(token_analyser,ast_tree);
	}
	public void eval(){
		ast_tree.process();
	}
	
	boolean create_ast_tree(TokenAnalyser token_analyser,AST ast_tree){
		while(token_analyser.hasNext() ){
			Stmt stmt=new Stmt();
			stmt.exp=createExp(token_analyser);
			if(stmt.exp!=null)
				ast_tree.stmt_list.add(stmt);
		}
		return true;
	}
	Expression createExp(TokenAnalyser token_analyser){
		Expression exp=parseExp(token_analyser);
		/*while(exp!=null&&token_analyser.hasNext()){
			exp.parse(token_analyser);
		}*/
		return exp;
	}
	Expression parseExp(TokenAnalyser token_analyser){		
		Token token=token_analyser.get();		
		if(token.type==Token_Type.mark&&token.name.equals("end")){
			return null;
		}
		else if(token.type==Token_Type.mark&&token.name.equals("finish")){
			return null;
		}
		switch(token.type){
		case symbol:{
			if(token.name.equals("var")){								//var a=1
				Exp_Def exp=new Exp_Def();
				Exp_Pri exp1=parsePri(token_analyser);
				
				Token token1=token_analyser.get();
				
				if(token1.type==Token_Type.operator && token1.name.equals("=")){
					Exp_Opt exp2=parseOpt(token_analyser);
					exp.create(exp1,exp2);
					return exp;
				}
				else{
					token_analyser.unGet();
					exp.create(exp1);
					return exp;
				}
			}
			else if(token.name.equals("let")){
				
				Exp_Pri exp1=parsePri(token_analyser);
				Token token1=token_analyser.get();
				if(token1.type==Token_Type.operator&&token1.name.equals("=")){			 //a=2
					
					Exp_Opt exp2=parseOpt(token_analyser);
					Exp_Let exp=new Exp_Let();
					exp.create(exp1,exp2);
					return exp;
				}else{
					return null;
				}
			}
			else{
				token_analyser.unGet();
				Exp_Opt exp=parseOpt(token_analyser);
				return exp;
				
			}
			}
		case digit:{													//1+a
			token_analyser.unGet();										
			Exp_Opt exp=parseOpt(token_analyser);
			return exp;
			}
		case operator:{											//-1+a
			token_analyser.unGet();
			Exp_Opt exp=parseOpt(token_analyser);
			return exp;
			}
		default:
			break;
		}
		return null;
	}
	Exp_Opt parseOpt(TokenAnalyser token_analyser) {
		Exp_Opt exp=new Exp_Opt();
		Token token=token_analyser.get();
		if(token.name.equals("end")&&token.type==Token_Type.mark){
			token_analyser.unGet();return null;
		}
		else if(token.type==Token_Type.mark&&token.name.equals("finish")){
			token_analyser.unGet();return null;
		}
		else if(token.name.equals(")")&&token.type==Token_Type.operator){
			token_analyser.unGet();	return null;
		}
		token_analyser.unGet();
		Exp_Add exp1=parseAdd(token_analyser);
		Exp_Opt exp2=parseOpt(token_analyser);
		exp.exp1=exp1;
		exp.exp2=exp2;
		//System.out.println("parse opt exp");
		return exp;
	}
	Exp_Add parseAdd(TokenAnalyser token_analyser) {
		Exp_Add exp=new Exp_Add();
		exp.opt=new Opt();
		exp.opt.name="+";
		Token token=token_analyser.get();
		if(token.name.equals("end")&&token.type==Token_Type.mark){
			token_analyser.unGet();	return null;
		}
		else if(token.type==Token_Type.mark&&token.name.equals("finish")){
			token_analyser.unGet();	return null;
		}
		else if(token.name.equals(")")&&token.type==Token_Type.operator){
			token_analyser.unGet();	return null;
		}
		else if(token.name.equals("-")&&token.type==Token_Type.operator){
			exp.opt.name="-";
		}
		else if(token.name.equals("+")&&token.type==Token_Type.operator){
			exp.opt.name="+";
		}
		else{
			token_analyser.unGet();
		}
		exp.exp1=parseMul(token_analyser);
		exp.exp2=parseAdd(token_analyser);
		
		//System.out.println("parse add exp");
		return exp;
	}
	Exp_Mul parseMul(TokenAnalyser token_analyser) {
		Exp_Mul exp=new Exp_Mul();
		Token token=token_analyser.get();
		exp.opt=new Opt();
		exp.opt.name="*";
		if(token.name.equals("end")&&token.type==Token_Type.mark){
			token_analyser.unGet();	return null;
		}
		else if(token.type==Token_Type.mark&&token.name.equals("finish")){
			token_analyser.unGet();	return null;
		}
		else if(token.name.equals("+")&&token.type==Token_Type.operator){
			token_analyser.unGet();	return null;
		}
		else if(token.name.equals("-")&&token.type==Token_Type.operator){
			token_analyser.unGet(); return null;
		}
		else if(token.name.equals(")")&&token.type==Token_Type.operator){
			token_analyser.unGet();	return null;
		}
		else if(token.name.equals("/")&&token.type==Token_Type.operator){
			exp.opt.name="/";
		}else if(token.name.equals("*")&&token.type==Token_Type.operator){
			exp.opt.name="*";
		}
		else{
			token_analyser.unGet();			
		}	
		Exp_Ter exp1=parseTer(token_analyser);
		Exp_Mul exp2=parseMul(token_analyser);
		exp.exp1=exp1;
		exp.exp2=exp2;
		//System.out.println("parse mul exp");
		return exp;
	}
	Exp_Ter parseTer(TokenAnalyser token_analyser) {
		Exp_Ter exp=new Exp_Ter();
		Token token=token_analyser.get();
		if(token.name.equals("end")&&token.type==Token_Type.mark){
			token_analyser.unGet();return null;
		}
		else if(token.name.equals("finish")&&token.type==Token_Type.mark){
			token_analyser.unGet();return null;
		}	
		else if(token.name.equals("(")&&token.type==Token_Type.operator){
			Exp_Opt exp1=parseOpt(token_analyser);
			token=token_analyser.get();
			if(token.name.equals(")")&&token.type==Token_Type.operator){
				exp.exp1=exp1;
				return exp;
			}
		}	
		else if(token.type==Token_Type.digit || token.type==Token_Type.symbol){
			token_analyser.unGet();
			Exp_Pri exp2=parsePri(token_analyser);
			exp.exp2=exp2;
			//System.out.println("parse ter exp");
			return exp;
		}
		return null;
	}
	
	Exp_Pri parsePri(TokenAnalyser token_analyser) {
		Exp_Pri exp=new Exp_Pri();
		Token token=token_analyser.get();
		if(token.type==Token_Type.digit){
			token_analyser.unGet();
			Num num=parseNum(token_analyser);
			exp.num=num;
		}
		else if(token.type==Token_Type.symbol){
			token_analyser.unGet();
			Var var=parseVar(token_analyser);
			exp.var=var;
		}
		exp.exp_type=Exp_Type.pri;
		//System.out.println("parse pri exp");
		return exp;
	}
	Var parseVar(TokenAnalyser token_analyser) {
		Var var=new Var();
		Token token=token_analyser.get();
		var.name=token.name;
		var.exp_type=Exp_Type.var;
		return var;
	}
	Num parseNum(TokenAnalyser token_analyser) {
		Num num=new Num();
		Token token=token_analyser.get();
		num.value=token.value;
		num.exp_type=Exp_Type.num;
		return num;
	}

}