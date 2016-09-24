package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

public class SgStmt_CtrFlw extends AST {
	en_Ctrflw t_ctrflw;
	Expr_Calc return_exp;	

	public en_Ctrflw getCFT() {
		return t_ctrflw;
	}

	public void setCFT(en_Ctrflw t_ctrflw) {
		this.t_ctrflw = t_ctrflw;
	}

	public Expr_Calc getRtExp() {
		return return_exp;
	}

	public void setRtExp(Expr_Calc return_exp) {
		this.return_exp = return_exp;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		switch(this.t_ctrflw){
		case t_break:
			String lb_end=codegen.labels_whlend.peek();
			IRCode code=new IRCode("goto",codegen.mp_label2line.get(lb_end).toString(),null,null);
			codegen.addCode(code);
			codegen.incLineNo();
			break;
		case t_continue:
			String lb_st=codegen.labels_whlbd.peek();
			code=new IRCode("goto",codegen.mp_label2line.get(lb_st).toString(),null,null);
			codegen.addCode(code);
			codegen.incLineNo();
			break;			
		case t_return:
			code =new IRCode("ret",null,null,null);
			break;			
		case t_returnExp:
			code =new IRCode("retExp",this.return_exp.rst_type,this.return_exp.rst_val,null);
			break;
		default:return false;		
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{		
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		switch(this.t_ctrflw){
		case t_break:
			if(!codegen.labels_whlbd.isEmpty())
				return true;
			break;
		case t_continue:
			if(!codegen.labels_whlbd.isEmpty())
				return true;
			break;
		case t_return:
			if(codegen.ret_types.peek().equals("void"))
				return true;
			break;
		case t_returnExp:
			if(codegen.getTypeInSymTb(codegen.ret_types.peek())==codegen.getTypeInSymTb(this.return_exp.rst_type))
				return true;
			break;
		default:break;
		}
		return false;
	}
	public enum en_Ctrflw{t_break,t_continue,t_return,t_returnExp;}
}
