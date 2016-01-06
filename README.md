
# Compiler


A compiler, including lex analyser, regex parser, LR(1) symtax parser and the LR(1) parser generator.
These tools are used for compiling a script program language specified by me. Currently the IR is abstruct syntax tree and is interpreted by an interpreter. Lex and syntax specifications are defined in some txt files, as well as the parsing rules. The cript languege is like a subset of C languese, including if...else... expression, while(...){...} expression, variable defining and assignment, function defining and calling, recursive function, such as "int fact(int a){if(a<1) return 1; return a*fact(a-1);}", ect.


Recently,  parser generator is updated to GLR. Considering supporting complex or even ambiguous grammars such as generic and templates, LR(1) parser is not as powerful as GLR parser. So a GLR parser generator is constructed. IR is still AST form. The next is static analysis and optimization of interpretation, in front of implementation of virtual machine. 
