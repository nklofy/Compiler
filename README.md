
# Compiler
#YF Programming Language 


1. toolkit: Parser generator and tokenizer generator.
  - GLR parser generator. Write grammers in the file "grammer.txt", then run Parsergenerator.java. shift-reduce table will be generated in "out_grammar.txt". Before run AST＿Generator, define actions in "grammar_AST.txt" which should be aligned with actions in ASTGenerator.java.
  - Tokenizer generator. Write all words and regex expressions in the file "tokens.txt", then run LexAnalyzer.java. APIs in tokenizer.java can be used for get tokens in files.


2. script and type definition. script part and type definition can be in arbitrary order in the source file. In script part, var define and assignment statments, control structures, fucntion definition and calling are almost the same with convensional programming languages. In type definition part, class and interface are defined, as well as their field and method members.   


3. package management. A source file is end with ".yfl" postfix, and IR codes is end with ".yfc" postfix. Usually yfl files are in "src" file folder (or not), yfc files are in "bin" file folder which is generated automatically(no matter if yfl files in src folder). Current package and imported packages should be in the front of yfl file. Classes and interfaces in other packages can be used when imported, but variables and function in script part in other packages can not be imported. There is a demo in  "testcode" dir, with "script1.yfl" as sorce file and "bin/script1.yfc" as object file. Run src/test.java to watch the compilation process.


4. Staticly type checking. basic types, expression, function, class, interface.

5. Dynamically type checking. type parameters of generic type and function, higher-order function.

6. lambda expression. higher-order function. 

7. IR code. Three address linear code. 

###example code: church number in lambda expr, functor and monad in OO style. All pass type checking.
```
//Church number
function zero=(function f, int s)->{return f(s);};
function inc_1=(function f, int s)->{return f(f(s));};
function add_2=(function m, function n, function f, int s)->{return (function f, int s)->{return m (f (n (f (s))));};};

//functor
class functor <T1>{
	T1 t;
	<T2> functor<T2> fmap (function f, functor<T1> wa) {
		T2 b=f(wa.t);
		functor<T2> fb=new functor<T2>();
		fb.t=b;
		return fb;
	}
}
//monad
class monad <T1> extends functor{
	monad<T1> rt(T1 t){//as "return" in monad of haskell
		monad <T1> ma=new monad<T1>();
		ma.t=t;
		return ma;
	}
	<T2> monad<T2> bd(Monad<T1> ma, function f){// as ">>=" in monad of haskell
		monad<T2> mb=f(ma.t);
		return mb;
	}
}
```

##older version: 
  Script_1 with LR1 parser generator.zip

  

 Compiler and interpreter, with tools of LR(1) parser generator and tokenizer generator. These tools are used for compiling a script program language "YFLang". The IR is just represented by AST. Lex and syntax specifications are defined in some txt files, as well as the parsing rules. The script languege is like a subset of C languese, including if...else... expression, while(...){...} expression, variable defining and assignment, function defining and calling, recursive function, for example: 
```
int fact(int a){if(a<1) return 1; return a*fact(a-1);}
int fib(int a){if(a<2) return 1; return fib(a-1)+fib(a-2);}
