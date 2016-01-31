package Parser.ASTs;

import java.util.*;
import Parser.*;
import Parser.ASTs.Scp_Info.en_Scp;

public class Scp_InfoLst extends AST {
	boolean isE=false;
	public boolean isE() {
		return isE;
	}
	public void setE() {
		this.isE = true;
	}
	LinkedList<Scp_Info> scp_infos;
	public void addScp(Scp_Info scp){
		if(this.scp_infos==null){
			this.scp_infos=new LinkedList<Scp_Info>();
		}
		this.scp_infos.add(scp);
	}
}
