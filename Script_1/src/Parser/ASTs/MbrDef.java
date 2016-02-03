package Parser.ASTs;

import Parser.*;

public class MbrDef extends AST {
	MbrDef_Fld fld;
	MbrDef_Mthd mthd;
	public MbrDef(){}
	public MbrDef(MbrDef_Fld fld){
		this.fld=fld;
		this.setASTType(MbrDef_Fld.class.getName());
	}
	public MbrDef(MbrDef_Mthd mthd){
		this.mthd=mthd;
		this.setASTType(MbrDef_Mthd.class.getName());
	}
	public boolean setMbr(AST ast){
		switch(this.getASTType()){
		case "MbrDef_Fld":
			this.fld=(MbrDef_Fld)ast;
			if(ast.getMergedAsts()!=null){
				break;
			}
			upAll(ast);
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
