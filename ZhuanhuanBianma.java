import java.io.*;
import java.util.*;
class ZhuanhuanBianma
{
	public static void main(String[] args)  {
		Properties p = System.getProperties();

		p.list(System.out);

		try{
			
		}
	}

	public static void method_1()
	{

		BufferedWriter bw = null;
		BufferedReader br = null;
		try{
			bw = new BufferedWriter(new FileWriter("demo.txt"));
			br = new BufferedReader(new InputStreamReader(System.in,"GBK"));
			String s = null;

			while ((s = br.readLine()) != null) {
				if( "over".equals(s)){
					break;
				}
				bw.write(s);
				bw.newLine();
				bw.flush();
			}

		} catch (IOException e) {
			System.out.println("error...");
		} finally {
			if (bw != null){
				try{
					bw.close();
				} catch (IOException e) {
					System.out.println("...");
				} finally {
					if ( br != null ){
						try{
							br.close();
						} catch (IOException e) {
							System.out.println("...");
						}
					}
				}
			}
		}
		
	}

	public static void method_2()
	{
		InputStream fis = null;
		FileOutputStream fos = null;

		try{
			fis = System.in;
			// fos = new FileOutputStream("demo.txt");
			// byte [] b = new byte[4];
			// int len = 0;
			// while ((len = fis.read(b))!= -1) {
			// 	fos.write(b,0,len);
			// }
			StringBuilder sb = new StringBuilder();
			
			while(true){
				int readdata = fis.read();
				if(readdata == '\r')
					continue;
				if(readdata == '\n'){
					String s = sb.toString();
					if("over".equals(s))
						break;
					sb.delete(0,sb.length());
				} else {
					sb.append((char)readdata);
				}
			}

		} catch(IOException e) {

		} finally {
			if( fis != null) {
				try {
					fis.close();
				} catch (IOException e) {

				} finally {
					if(fos != null) {
						try {
							fos.close();
						} catch (IOException e) {

						}
					}
				}
			}
		}

	}

	public static void method_3() throws IOException
	{
		FileReader fw = new FileReader("Demo.java");
		OutputStreamWriter osw = new OutputStreamWriter(System.out,"UTF-8");
		int chardata = 0;
		while((chardata = fw.read())!= -1){
			osw.write((char)chardata);
			osw.flush();
		}
		fw.close();
		osw.close();


	}
}