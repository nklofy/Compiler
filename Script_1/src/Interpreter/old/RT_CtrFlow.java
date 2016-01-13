package Interpreter.old;

public class RT_CtrFlow {
	Flow_State flow_state=Flow_State.s_go;//go, continue, break 	
	public boolean setFlow(Flow_State s){
		this.flow_state=s;
		return true;
	}
	public Flow_State getFlow(){
		return this.flow_state;
	}
	public enum Flow_State{s_go,s_return,s_break,s_continue,s_exception;}
}

