import java.net.*;
import java.io.*;

class UDPsend
{
	public static void main(String[] args) throws Exception {
		DatagramSocket ds = new DatagramSocket(10000);
		// BufferedInputStream bufin = new BufferedInputStream(System.in);
		// byte [] buf = new byte[1024];
		// int len = 0;
		// while ((len = bufin.read(buf,0,buf.length)) != -1)
		// {
		// 	DatagramPacket dp = new DatagramPacket(buf,len,iaddress,8888);
		// 	ds.send(dp);
		// }
		
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		String buf = null;
		while (!("over".equals((buf = bufr.readLine())))) {
			byte [] b = buf.getBytes(); 
			DatagramPacket dp = new DatagramPacket(b,b.length,InetAddress.getByName("192.168.1.103"),8888);
			ds.send(dp);
		}
		ds.close();
	}
}
class UDPreceive
{
	public static void main(String[] args) throws Exception {

		DatagramSocket ds = new DatagramSocket(8888);
		
		while (true) {
			byte [] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf,buf.length);
			ds.receive(dp);
			// System.out.println(dp.getAddress());
			System.out.println(new String(dp.getData(),0,dp.getLength()));

			System.out.println(dp.getPort());
		}

		// ds.close();
	}
}