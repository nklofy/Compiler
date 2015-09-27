import java.io.*;
import java.util.Scanner;

public class calculator {

	public static void main(String[] args) {
		System.out.print("input filename: ");
		
		@SuppressWarnings("resource")
		Scanner in=new Scanner(System.in);
		String filename=in.next();
		System.out.println("filename:  "+filename);		
		Scanner file_scan = null;		
		try {
			file_scan=new Scanner(new File(filename));
			Parser parser=new Parser();
			parser.parse(file_scan);
			parser.eval();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}



