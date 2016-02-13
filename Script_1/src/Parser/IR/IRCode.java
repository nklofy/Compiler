package Parser.IR;

public class IRCode {
	String opt;
	String opd1;
	String opd2;
	String targ;
	
	IRCode(String opt,String opd1,String opd2,String targ){
		this.opd1=opd1;
		this.opd2=opd2;
		this.targ=targ;
		this.opt=opt;
	}
	public String getOpt() {
		return opt;
	}
	public String getOpd1() {
		return opd1;
	}
	public String getOpd2() {
		return opd2;
	}
	public String getTarg() {
		return targ;
	}
}
