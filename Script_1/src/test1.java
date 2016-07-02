import java.io.File;
import java.io.IOException;

public class test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File dir=new File("/home/luyunfei/y/l/yc");

		//if(!dir.exists()){
		//	System.out.println("no");
			//if(dir.mkdirs())
			//	System.out.println("crt");;
		//}
		try {
			System.out.println(dir.getCanonicalPath());
			System.out.println(dir.getAbsolutePath());
			if(dir.mkdirs())
				System.out.println("ok1");
			File f=new File(dir.getCanonicalPath()+File.separator+"yf");
			if(f.createNewFile())
				System.out.println("ok2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
