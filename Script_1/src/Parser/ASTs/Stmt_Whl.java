package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

public class Stmt_Whl extends AST {
	ExprCalc_Bool bool_exp;
	AST_StmtList stmt_list;
	
	
	public boolean setwhl(ExprCalc_Bool bool_exp,AST_StmtList stmt_list){
		this.bool_exp=bool_exp;
		this.stmt_list=stmt_list;
		return true;
	}
	public boolean genCode(CodeGenerator codegen) throws GenCodeException {
		this.bool_exp.genCode(codegen);
		String lb_whlbd=":"+codegen.getTmpSn();
		String lb_whlend=":"+codegen.getTmpSn();
		IRCode code=new IRCode("while",this.bool_exp.getVal(),lb_whlbd,lb_whlend);
		codegen.incLineNo();
		codegen.addCode(code);
		int ln_whlbd =codegen.getLineNo()+1;
		codegen.labels_whlbd.add(lb_whlbd);
		codegen.labels_whlend.add(lb_whlend);
		codegen.mp_label2line.put(lb_whlbd, ln_whlbd);
		codegen.getRpsLst(lb_whlbd).add(code);
		this.stmt_list.genCode(codegen);
		int ln_whlend =codegen.getLineNo()+1;
		codegen.mp_label2line.put(lb_whlend, ln_whlend);
		codegen.replaceLb(lb_whlbd);
		codegen.labels_whlbd.remove();
		codegen.labels_whlend.remove();
		codegen.mp_label2line.remove(lb_whlbd);
		codegen.mp_label2line.remove(lb_whlend);
		codegen.rps_code_list.remove(lb_whlbd);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		return this.bool_exp.checkType(codegen)&&this.stmt_list.checkType(codegen);
	}
}
