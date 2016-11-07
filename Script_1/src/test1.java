import java.io.*;

public class test1 {

	public static void main(String[] args) {
		String s="a\\\\b";
		//String s2=s.replace('\\', '-');
		String[] ss=s.split("\\\\");
		for(String s1:ss){
			System.out.println(s1);
		}
		// TODO Auto-generated method stub
	/*	File dir=new File("/home/luyunfei");

		//if(!dir.exists()){
		//	System.out.println("no");
			//if(dir.mkdirs())
			//	System.out.println("crt");;
		//}
		try {
			System.out.println(dir.getCanonicalPath());
			System.out.println(dir.getAbsolutePath());
			File[] ss=dir.listFiles();
			for(File f:ss){
				System.out.println(f.getCanonicalPath());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}
	

}