package Parser.ASTs;

import Parser.*;

public class MbrDef extends AST {
	MbrDef_Fld fld;
	MbrDef_Mthd mthd;
	public boolean setMbr(AST ast){
		switch(this.getASTType()){
		case "MbrDef_Fld":
			this.fld=(MbrDef_Fld)ast;
			if(ast.getMergedAsts()!=null){
				break;
			}
			for(String s:ast.getVarUp()){
				if(this.getVarTb()!=null && this.getVarTb().keySet().contains(s)){
					System.out.println("error existing symbol name: "+ s);
				}else{
					this.putVarTb(s, ast.getVarTb().get(s));
				}
			}
			break;
		case "MbrDef_Mthd":
			this.mthd=(MbrDef_Mthd)ast;
			if(ast.getMergedAsts()!=null){
				break;
			}
			for(String s:ast.getFuncUp()){
				if(this.getFuncTb()!=null && this.getFuncTb().keySet().contains(s)){
					System.out.println("error existing symbol name: "+ s);
				}else{
					this.putFuncTb(s, ast.getFuncTb().get(s));
					this.addFuncUp(s);
				}
			}
			break;
		}
		return true;
	}
}
