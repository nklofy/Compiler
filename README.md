
# Compiler
#YF Programming Language 


1. toolkit: Parser generator and tokenizer generator.
  - GLR parser generator. Write grammers in the file "grammer.txt", then run Parsergenerator.java. shift-reduce table will be generated in "out_grammar.txt". Before run AST＿Generator, define actions in "grammar_AST.txt" which should be aligned with actions in ASTGenerator.java.
  - Tokenizer generator. Write all words and regex expressions in the file "tokens.txt", then run LexAnalyzer.java. APIs in tokenizer.java can be used for get tokens in files.


2. script and type definition. script part and type definition can be in arbitrary order in the source file. In script part, var define and assignment statments, control structures, fucntion definition and calling are almost the same with convensional programming languages. In type definition part, class and interface are defined, as well as their field and method members.   


3. package management. A source file is end with ".yfl" postfix, and IR codes is end with ".yfc" postfix. Usually yfl files are in "src" file folder (or not), yfc files are in "bin" file folder which is generated automatically(no matter if yfl files in src folder). Current package and imported packages should be in the front of yfl file. Classes and interfaces in other packages can be used when imported, but variables and function in script part in other packages can not be imported.  



4. Staticly type checking. basic types, expression, class, interface, generic types, function type.



5. lambda expression. high order function. function variable.


6. IR code. Three address linear code. 

  


##older version: ____Script_1 with LR1 parser generator_____

  

 Compiler and interpreter, with tools of LR(1) parser generator and tokenizer generator. These tools are used for compiling a script program language "YFLang". The IR is just represented by AST. Lex and syntax specifications are defined in some txt files, as well as the parsing rules. The script languege is like a subset of C languese, including if...else... expression, while(...){...} expression, variable defining and assignment, function defining and calling, recursive function, for example: 
>int fact(int a){if(a<1) return 1; return a*fact(a-1);}
>int fib(int a){if(a<2) return 1; return fib(a-1)+fib(a-2);}
