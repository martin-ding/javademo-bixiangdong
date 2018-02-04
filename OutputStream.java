
import java.io.*;
class OutputStream
{
	public static void main(String[] args) throws IOException
	{
		method_1();
	}

	public static void method_1() throws IOException
	{
		FileInputStream fos = new FileInputStream("php_test.php");
		// byte [] buffer = new byte[1024];

		// int len = 0;
		// while ((len = fos.read(buffer)) != -1) {
		// 	System.out.print(new String(buffer,0,len));
		// }
		int len = 0;
		while(( len = fos.read()) != -1){
			System.out.println((char)len);
		}
		fos.close();

	}
}