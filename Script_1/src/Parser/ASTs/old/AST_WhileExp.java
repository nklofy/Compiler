package Parser.ASTs.old;

import Interpreter.Interpreter;
import Interpreter.RT_CtrFlow;
import Interpreter.RT_Frame;
import Parser.AST;
import Parser.TypeSys.old.Type_Base;

public class AST_WhileExp extends AST {
	private AST_BoolExp bool_exp;
	private AST_StmtList stmt_list;
	public boolean setWhileExp(AST_BoolExp bool_exp,AST_StmtList stmt_list){
		this.bool_exp=bool_exp;
		this.stmt_list=stmt_list;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		interpreter.interpret(this.bool_exp);
		if(bool_exp.data_obj.getTypeObj().getTypeBase()==null){
			System.out.println("error WhileStmt eval boolExp type");
			return false;
		}					
		else if(bool_exp.data_obj.getTypeObj().getTypeBase()!=Type_Base.t_bool){
			System.out.println("error WhileStmt eval boolExp type");
			return false;
		}					
		boolean cond=this.bool_exp.data_obj.getBoolV(); 
		while(cond){
			interpreter.interpret(this.stmt_list);			
			if(interpreter.getCtrFlow().getFlow()==RT_CtrFlow.Flow_State.s_continue){
				interpreter.getCtrFlow().setFlow(RT_CtrFlow.Flow_State.s_go);
				continue;
			}
			else if(interpreter.getCtrFlow().getFlow()==RT_CtrFlow.Flow_State.s_break){
				interpreter.getCtrFlow().setFlow(RT_CtrFlow.Flow_State.s_go);
				break;
			}
			interpreter.interpret(this.bool_exp);
			cond=this.bool_exp.data_obj.getBoolV();
		}
		return true;
	}

}
