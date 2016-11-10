package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class SgStmt_CtrFlw extends AST {
	en_Ctrflw t_ctrflw;
	Expr return_exp;	

	public en_Ctrflw getCFT() {
		return t_ctrflw;
	}

	public void setCFT(en_Ctrflw t_ctrflw) {
		this.t_ctrflw = t_ctrflw;
	}

	public Expr getRtExp() {
		return return_exp;
	}

	public void setRtExp(Expr return_exp) {
		this.return_exp = return_exp;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		switch(this.t_ctrflw){
		case t_break:
			String lb_end=codegen.labels_whlend.peek(), lb_st=codegen.labels_whlbg.peek();
			if(lb_end==null)throw new GenCodeException("GenCode Error: not in while range");
			IRCode code=new IRCode("goto",lb_end,null,null);
			codegen.addCode(code);
			codegen.getRpsLst(lb_st).add(code);
			break;
		case t_continue:
			lb_st=codegen.labels_whlbg.peek();
			if(lb_st==null)throw new GenCodeException("GenCode Error: not in while range");
			code=new IRCode("goto",lb_st,null,null);
			codegen.addCode(code);
			codegen.getRpsLst(lb_st).add(code);
			
			break;			
		case t_return:
			code =new IRCode("ret",null,null,null);
			codegen.addCode(code);
			break;			
		case t_returnExp:
			this.return_exp.genCode(codegen);
			code =new IRCode("retExp",this.return_exp.rst_type,this.return_exp.rst_val,null);
			codegen.addCode(code);
			break;
		default:return false;		
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{		
		if(this.t_ctrflw==en_Ctrflw.t_returnExp){
			if(!this.return_exp.genSymTb(codegen)) return false;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		switch(this.t_ctrflw){
		//case t_break:
		//	if(!codegen.labels_whlend.isEmpty())
			//	return true;
			//else throw new TypeCheckException("Check Type Error: no while break lable");
		//case t_continue:
		//	if(!codegen.labels_whlbg.isEmpty())
		//		return true;
		//	else throw new TypeCheckException("Check Type Error: no while continue lable");
		case t_return:
			if(codegen.isInScope("function")
					&&!codegen.isInScope("lambda")
					&&codegen.ret_types.peek().equals("void"))
				return true;
			if(codegen.isInScope("lambda")){
				T_Type t=codegen.getTypeInSymTb("void");
				codegen.ret_types.addFirst(t);
				return true;
			}
			else throw new TypeCheckException("Check Type Error: return type is not void");
		case t_returnExp:
			if(!this.return_exp.checkType(codegen)) return false;
			if(codegen.isInScope("lambda")){
				codegen.ret_types.addFirst(codegen.getTypeInSymTb(this.return_exp.rst_type));
				return true;
			}
			else if(codegen.isInScope("function")){
				T_Type t1=codegen.ret_types.peek()
						,t2=codegen.getTypeInSymTb(this.return_exp.rst_type);
				if(t1.canCastFrom(codegen, t2))
					return true;	
			}
			//else if(){
			//	
			//}
			else throw new TypeCheckException("Check Type Error: return type mismatch");
		default:break;
		}
		return true;
	}
	public enum en_Ctrflw{t_break,t_continue,t_return,t_returnExp;}
}
