
# Compiler

  Recently, parser generator and parser is updated to GLR. For supporting complex or even ambiguous grammars such as sub-class, generic, lambda expression, LR(1) parser is not as powerful as GLR parser. So the GLR parser generator is constructed. IR is still AST form. The next step may be static analysis or optimization of interpreter, as well as  implementation of virtual machine.

  A compiler, including lex analyser, regex parser, LR(1) parser and the LR(1) parser generator. These tools are used for compiling a script program language specified by myself. Currently the IR is abstruct syntax tree and is interpreted by an interpreter. Lex and syntax specifications are defined in some txt files, as well as the parsing rules. The script languege is like a subset of C languese, including if...else... expression, while(...){...} expression, variable defining and assignment, function defining and calling, recursive function, such as "int fact(int a){if(a<1) return 1; return a*fact(a-1);}".
