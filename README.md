# Compiler
A compiler, including lex analyser, regex parser, LR(1) symtax parser and the LR(1) parser generator. 
These tools are used for compiling a script program language specified by me. Currently the IR is abstruct syntax tree and is interpreted by an interpreter. Lex and syntax specifications are defined in some txt files, as well as the parsing rules. The cript languege is like a subset of C languese, including if...else... expression, while(...){...} expression, variable defining and assignment, function defining and calling, such as "int a =1; int a (int b) {...return c;}... a(b);", ect.
In future, the virtual machine will be built, and the IR will be re-parsed to byte-code.
