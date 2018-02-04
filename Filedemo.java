import java.io.*;

class Filedemo
{
	public static void main(String[] args) throws IOException
	{
		method_1();
	}

	public static void method_1() throws IOException
	{
		FileReader fr = new FileReader("php_test.php");
		char[] ch = new char[4];
		int flag = 0;
		while ((flag = fr.read(ch)) != -1) {
			System.out.print(new String(ch,0,flag));
		}

		fr.close();

	}

}