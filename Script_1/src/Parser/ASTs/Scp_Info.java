package Parser.ASTs;

import Parser.AST;

public class Scp_Info extends AST {
	en_Scp scp;
	public en_Scp getScp() {
		return scp;
	}
	public void setScp(en_Scp scp) {
		this.scp = scp;
	}
	public enum en_Scp{t_static,t_public,t_private,t_final;}
}
