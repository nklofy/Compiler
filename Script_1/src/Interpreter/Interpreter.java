package Interpreter;
import Parser.*;

public class Interpreter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello");
		Parser parser=new Parser();
		parser.analyzeGrm("out_grammar.txt"); 	System.out.println("analyzeGrm out_grammar.txt");
		parser.analyzeAST("grammar_AST.txt");	System.out.println("analyzeAST grammar_AST.txt");
		parser.analyzeLex("out_lexAnalyzer.txt");	System.out.println("analyzeLex out_lexAnalyzer.txt");
		parser.input("script_test1.txt");	
		parser.parse();
		AST ast_tree=parser.getAST();
		Interpreter interpreter=new Interpreter();
		//interpreter.interpret((StmtListAST) ast_tree);
		parser.output("out_parser.txt");
		
	}
/*	
	public boolean interpret(StmtListAST ast){
		if(ast.stmt_list!=null){
			interpret(ast.stmt_list);
		}
		if(ast.stmt!=null){
			interpret(ast.stmt);			
		}
		return true;
	}
	public boolean interpret(StmtAST stmt){
		interpret(stmt.exp);
		stmt.num_type=stmt.exp.num_type;
		stmt.double_value=stmt.exp.double_value;
		stmt.int_value=stmt.exp.int_value;
		if (stmt.num_type.equals("int")){
			System.out.println("int result: "+stmt.int_value);
		}else if (stmt.num_type.equals("double")){
			System.out.println("double result: "+stmt.double_value);
		}
		return true;
	}

	public boolean interpret(ExpAST exp) {
		if(exp.opt==null){
			interpret(exp.add_exp);
			exp.num_type=exp.add_exp.num_type;
			exp.int_value=exp.add_exp.int_value;
			exp.double_value=exp.add_exp.double_value;
			return true;
		}else if(exp.exp==null){
			interpret(exp.add_exp);			
			if(exp.opt.equals("-")){
				exp.num_type=exp.add_exp.num_type;
				exp.int_value=-exp.add_exp.int_value;
				exp.double_value=-exp.add_exp.double_value;
				return true;
			}
		}else if(exp.opt.equals("+")){
			interpret(exp.exp);
			interpret(exp.add_exp);
			if(exp.exp.num_type.equals("int")&&exp.add_exp.num_type.equals("int")){
				exp.num_type="int";
				exp.int_value=exp.exp.int_value+exp.add_exp.int_value;
			}else{
				exp.num_type="double";
				if(exp.exp.num_type.equals("int")&&exp.add_exp.num_type.equals("double")){	
					exp.double_value=exp.exp.int_value+exp.add_exp.double_value;
				}else if(exp.exp.num_type.equals("double")&&exp.add_exp.num_type.equals("int")){
					exp.double_value=exp.exp.double_value+exp.add_exp.int_value;
				}else if(exp.exp.num_type.equals("double")&&exp.add_exp.num_type.equals("double")){
					exp.double_value=exp.exp.double_value+exp.add_exp.double_value;
				}		
			} 	
			return true;
		}else if(exp.opt.equals("-")){
			interpret(exp.exp);
			interpret(exp.add_exp);
			if(exp.exp.num_type.equals("int")&&exp.add_exp.num_type.equals("int")){
				exp.num_type="int";
				exp.int_value=exp.exp.int_value-exp.add_exp.int_value;
			}else{
				exp.num_type="double";
				if(exp.exp.num_type.equals("int")&&exp.add_exp.num_type.equals("double")){	
					exp.double_value=exp.exp.int_value-exp.add_exp.double_value;
				}else if(exp.exp.num_type.equals("double")&&exp.add_exp.num_type.equals("int")){
					exp.double_value=exp.exp.double_value-exp.add_exp.int_value;
				}else if(exp.exp.num_type.equals("double")&&exp.add_exp.num_type.equals("double")){
					exp.double_value=exp.exp.double_value-exp.add_exp.double_value;
				}		
			} 	
			return true;	
		}
		return false;
	}

	public boolean interpret(AddAST add) {//TODO
		if(add.opt==null){
			interpret(add.mul_exp);
			add.num_type=add.mul_exp.num_type;
			add.int_value=add.mul_exp.int_value;
			add.double_value=add.mul_exp.double_value;
			return true;
		}else if(add.opt.equals("*")){
			interpret(add.add_exp);
			interpret(add.mul_exp);
			if(add.add_exp.num_type.equals("int")&&add.mul_exp.num_type.equals("int")){
				add.num_type="int";
				add.int_value=add.add_exp.int_value*add.mul_exp.int_value;
			}else{
				add.num_type="double";
				if(add.add_exp.num_type.equals("int")&&add.mul_exp.num_type.equals("double")){	
					add.double_value=add.add_exp.int_value*add.mul_exp.double_value;
				}else if(add.add_exp.num_type.equals("double")&&add.mul_exp.num_type.equals("int")){
					add.double_value=add.add_exp.double_value*add.mul_exp.int_value;
				}else if(add.add_exp.num_type.equals("double")&&add.mul_exp.num_type.equals("double")){
					add.double_value=add.add_exp.double_value*add.mul_exp.double_value;
				}		
			} 	
			return true;
		}else if(add.opt.equals("/")){
			interpret(add.add_exp);
			interpret(add.mul_exp);
			add.num_type="double";
			if(add.add_exp.num_type.equals("int")&&add.mul_exp.num_type.equals("int")){				
				add.double_value=(double)add.add_exp.int_value/(double)add.mul_exp.int_value;
			}else if(add.add_exp.num_type.equals("int")&&add.mul_exp.num_type.equals("double")){	
				add.double_value=add.add_exp.int_value/add.mul_exp.double_value;
			}else if(add.add_exp.num_type.equals("double")&&add.mul_exp.num_type.equals("int")){
				add.double_value=add.add_exp.double_value/add.mul_exp.int_value;
			}else if(add.add_exp.num_type.equals("double")&&add.mul_exp.num_type.equals("double")){
				add.double_value=add.add_exp.double_value/add.mul_exp.double_value;
			}
			return true;	
		}
		return false;
	}

	public boolean interpret(MulAST mul) {
		interpret(mul.pri_exp);
		mul.num_type=mul.pri_exp.num_type;
		mul.double_value=mul.pri_exp.double_value;
		mul.int_value=mul.pri_exp.int_value;
		return true;
	}
	
	public boolean interpret(PriAST pri) {
		if(pri.num_exp!=null){
			interpret(pri.num_exp);
			pri.num_type=pri.num_exp.num_type;
			pri.double_value=pri.num_exp.double_value;
			pri.int_value=pri.num_exp.int_value;
			return true;
		}else if(pri.exp!=null){
			interpret(pri.exp);
			pri.num_type=pri.exp.num_type;
			pri.double_value=pri.exp.double_value;
			pri.int_value=pri.exp.int_value;
			return true;
		}
		return false;
	}

	public boolean interpret(NumAST num) {
		if(num.num_type.equals("int")){
			num.int_value=Integer.parseInt(num.buffer);
			return true;
		}else if(num.num_type.equals("double")){
			num.double_value=Double.parseDouble(num.buffer);
			return true;
		}
		return false;
	}*/
}
/*
public boolean copyValue(AST ast){
this.num_type=ast.num_type;
if(this.num_type.equals("int"))
	this.int_value=ast.int_value;
else if (this.num_type.equals("double"))
	this.double_value=ast.double_value;
return true;
}*/