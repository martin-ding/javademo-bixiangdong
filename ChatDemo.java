import java.net.*;
import java.io.*;

class ChatDemo
{
	public static void main(String[] args) throws Exception {
		DatagramSocket sendds = new DatagramSocket(10000);
		DatagramSocket receiveds = new DatagramSocket(9999);

		new Thread(new Send(sendds)).start();
		new Thread(new Receive(receiveds)).start();
	}
}

class Send implements Runnable
{
	private DatagramSocket ds;
	Send(DatagramSocket ds)
	{
		this.ds = ds;
	}

	public void run()
	{
		try {
			BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
			String buf = null;
			while ((buf = bufr.readLine()) != null) {
				if ("over".equals(buf)) {
					break;
				} else {
					byte [] b = buf.getBytes(); 
					DatagramPacket dp = new DatagramPacket(b,b.length,InetAddress.getByName("192.168.1.103"),9999);
					ds.send(dp);
				}
			}
			ds.close();
		} catch (Exception e) {
			throw new RuntimeException("error 2");
		}
	}
}

class Receive implements Runnable
{
	private DatagramSocket ds;

	Receive(DatagramSocket ds)
	{
		this.ds = ds;
	}

	public void run()
	{
		try {
			byte[] b =  new byte[1024];
			while (true) {
				DatagramPacket dp = new DatagramPacket(b,b.length);
				ds.receive(dp);
				byte [] re = dp.getData();
				System.out.println(dp.getAddress().getHostName() +" : " + new String(re,0,dp.getLength()) +" : " + dp.getPort());
			}
			// ds.close();
		} catch (Exception e) {
			throw new RuntimeException("error 1");
		}
	}
}