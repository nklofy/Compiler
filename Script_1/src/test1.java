import java.io.File;
import java.io.IOException;

public class test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File dir=new File("");
		try {
			System.out.println(dir.getCanonicalPath());
			System.out.println(dir.getAbsolutePath());
			//System.out.println(dir.getPath());
			//System.out.println(dir.getParent());
			String[] dirs=dir.getCanonicalPath().split(File.separator);
			int l1=dirs.length;
			for(int i=0;i<l1;i++){
				System.out.println(dirs[l1-i-1]);				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
