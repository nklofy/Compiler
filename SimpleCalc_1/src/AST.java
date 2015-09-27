import java.util.*;

//³éÏóÓï·¨Ê÷
class AST{
	static Symbol_Table table_root=new Symbol_Table();
	List<Stmt> stmt_list=new LinkedList<Stmt>();	
	
	boolean process(){
		for(Stmt stmt:stmt_list){
			stmt.process();
		}
		return true;
	}
}

class Stmt{
	Expression exp;
	boolean process(){
		exp.eval();
		return true;
	}
	
}

class Expression{
	Exp_Type exp_type;
	double value;
	boolean eval(){
		return false;
	}
	
}

enum Exp_Type{pri,def,let,opt,ter,add,mul,var,num,finish}


class Exp_Def extends Expression{
	Exp_Def(){
		exp_type=Exp_Type.def;
	}
	Exp_Pri exp1;
	Exp_Opt exp2;
	boolean init=false;
	@Override
	boolean eval() {
		Var var=exp1.var;		
		if(this.init){
			exp2.eval();
			var.value=exp2.value;
			System.out.println("define exp "+var.name+": "+var.value);
			return true;
		}
		else{
			System.out.println("define exp: "+var.name);
			return false;}
	}
	Expression create(Exp_Pri exp1) {
		this.exp1=exp1;
		Symbol_Table table=AST.table_root;
		table.add(exp1.var.name,exp1.var);
		System.out.println("create def exp");
		return this;
	}
	Expression create(Exp_Pri exp1, Exp_Opt exp2){
		this.exp1=exp1;
		Symbol_Table table=AST.table_root;
		table.add(exp1.var.name,exp1.var);
		this.exp2=exp2;
		this.init=true;
		System.out.println("create def exp");
		return this;
	}
}

class Exp_Let extends Expression{
	Exp_Let(){
		exp_type=Exp_Type.let;
	}
	Exp_Pri exp1;
	Exp_Opt exp2;
	@Override
	boolean eval() {
		Symbol_Table table=AST.table_root;		
		Var var=table.get(exp1.var.name);		
		exp2.eval();
		var.value=exp2.value;
		System.out.println("let exp "+var.name+": "+var.value);
		return true;
	}
	Expression create(Exp_Pri exp12, Exp_Opt exp2) {
		this.exp1=exp12;
		this.exp2=exp2;
		System.out.println("create let exp");
		return this;
	}
}

class Exp_Opt extends Expression{
	Exp_Opt(){
		exp_type=Exp_Type.opt;
	}
	Exp_Add exp1;
	Exp_Opt exp2;
	@Override
	boolean eval() {
		double v1=0,v2=0;
		if(exp1!=null)
			{exp1.eval();v1=exp1.value;}
		if(exp2!=null)
			{exp2.eval();v2=exp2.value;}
		this.value=v1+v2;
		return true;
	}
}
class Exp_Add extends Expression{
	Exp_Add(){
		exp_type=Exp_Type.add;
	}
	Exp_Mul exp1;
	Exp_Add exp2;
	Opt opt;
	@Override
	boolean eval() {
		double v1=0,v2=0;
		if(exp1!=null)
			{exp1.eval();v1=exp1.value;}
		if(exp2!=null)
			{exp2.eval();v2=exp2.value;}		
		if(opt.name.equals("+"))
			this.value=v1+v2;
		if(opt.name.equals("-"))
			this.value=v2-v1;
		System.out.println("add exp "+opt.name+": "+v1+" "+v2+" = "+this.value);
		return true;
	}
}

class Exp_Mul extends Expression{
	Exp_Mul(){
		exp_type=Exp_Type.mul;
	}
	Exp_Ter exp1;
	Exp_Mul exp2;
	Opt opt;
	@Override
	boolean eval() {
		double v1=1,v2=1;
		if(exp1!=null)
			{exp1.eval();v1=exp1.value;}
		if(exp2!=null)
			{exp2.eval();v2=exp2.value;}
		
		if(opt.name.equals("*"))
			this.value=v1*v2;
		if(opt.name.equals("/"))
			this.value=v2/v1;
		System.out.println("mul exp "+opt.name+": "+v1+" "+v2+" = "+this.value);
		return true;
	}
}

class Exp_Ter extends Expression{
	Exp_Ter(){
		exp_type=Exp_Type.ter;
	}
	Exp_Opt exp1;
	Exp_Pri exp2;
	@Override
	boolean eval() {
		if(exp1!=null){
			exp1.eval();this.value=exp1.value;
			System.out.println("pri exp "+"exp: "+this.value);
		}
		else if(exp2!=null){
			exp2.eval();this.value=exp2.value;		
			System.out.println("pri exp "+"ter: "+this.value);
		}
		return true;
	}
}

class Exp_Pri extends Expression{
	Exp_Pri(){
		exp_type=Exp_Type.pri;
	}
	Var var;
	Num num;
	@Override
	boolean eval() {
		if(this.var!=null){
			var.eval();
			this.value=var.value;
			System.out.println("pri exp "+"var: "+this.value);
		}
		else if(this.num!=null){
			num.eval();
			this.value=num.value;
			System.out.println("opt exp "+"num: "+this.value);
		}
		return true;
	}
}

class Var extends Expression{
	String name;	
	boolean init=false;
	boolean eval() {
		Symbol_Table table=AST.table_root;
		Var var=table.get(this.name);
		this.value=var.value;
		return true;
	}
	
}

class Num extends Expression{
	boolean eval() {
		return true;
	}
}

class Opt extends Expression{
	String name;
}
class Finish extends Expression{
	
}