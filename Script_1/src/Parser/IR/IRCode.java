package Parser.IR;

public class IRCode {
	String opt;
	String opd1;
	String opd2;
	String opd3;
	

	
	public IRCode(){}
	public IRCode(String opt,String opd1,String opd2,String opd3){
		this.opd1=opd1;
		this.opd2=opd2;
		this.opd3=opd3;
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
	public String getOpd3() {
		return opd3;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public void setOpd1(String opd1) {
		this.opd1 = opd1;
	}
	public void setOpd2(String opd2) {
		this.opd2 = opd2;
	}
	public void setOpd3(String opd3) {
		this.opd3 = opd3;
	}
}
