import java.io.*;
import java.util.*;

public class PipedStreamDemo
{
	public static void main(String[] args) throws Exception {
		method_2();
	}

	public static void method_2() throws Exception
	{
		// try {
			RandomAccessFile raf = new RandomAccessFile("demo.txt","rw");
			// raf.write("中国".getBytes());
			// raf.write("中国".getBytes());
			raf.writeInt(258);
			raf.close();
		// } catch (Exception e) {
			// e.getStackTrace();
		// }
		
	}


	public static void method_1(){

		try {
			PipedInputStream in = new PipedInputStream();
			PipedOutputStream out = new PipedOutputStream();
			in.connect(out);
			Read r = new Read(in);
			Write w = new Write(out);

			new Thread(r).start();
			new Thread(w).start();
		} catch(IOException e) {

		}
		
	}
}


class Read implements Runnable
{
	PipedInputStream in;

	Read(PipedInputStream in)
	{
		this.in = in;
	}

	public void run()
	{
		try {
			byte[] b = new byte[1024];
			int len = in.read(b);

			System.out.println(new String(b,0,len));
			in.close();
		} catch (IOException e) {
			throw new RuntimeException("read error");
		}
	}
}


class Write implements Runnable
{
	PipedOutputStream out;
	Write(PipedOutputStream out)
	{
		this.out = out;
	}

	public void run()
	{
		try {
			out.write("wwooow".getBytes());
			out.close();
		} catch (IOException e) {
			throw new RuntimeException("write error");
		}
		
	}
}
