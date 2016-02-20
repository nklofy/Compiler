package Parser.IR;

public class IRCode {
	String opt;
	String addr1;
	String addr2;
	String addr3;
	boolean rps=false;
	public boolean isRps() {
		return rps;
	}
	public void setRps(boolean rps) {
		this.rps = rps;
	}
	public IRCode(){}
	public IRCode(String opt,String addr1,String addr2,String addr3){
		this.addr1=addr1;
		this.addr2=addr2;
		this.addr3=addr3;
		this.opt=opt;
	}
	public String getOpt() {
		return opt;
	}
	public String getAddr1() {
		return addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public String getAddr3() {
		return addr3;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}
}
