package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;
import Interpreter.RT_CtrFlow;

public class AST_CtrFlw extends AST {
	private String flw_type;
	private AST_CalcExp calc_exp;
	public boolean setType(String type){
		this.flw_type=type;
		return true;
	}
	public boolean setCalcExp(AST_CalcExp exp){
		this.calc_exp=exp;
		return true;
	}
	public String getFlwType(){
		return this.flw_type;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		switch(flw_type){
		case "return":
			interpreter.interpret(this.calc_exp);
			interpreter.getCrtFrm().setRtnObj(this.calc_exp.data_obj);			
			interpreter.getCtrFlow().setFlow(RT_CtrFlow.Flow_State.s_return);
			break;
		case "break":
			interpreter.getCtrFlow().setFlow(RT_CtrFlow.Flow_State.s_break);
			break;
		case "continue":
			interpreter.getCtrFlow().setFlow(RT_CtrFlow.Flow_State.s_continue);
			break;
			default:
				break;
		}		
		return true;
	}

}
