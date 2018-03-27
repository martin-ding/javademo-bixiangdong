package network;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

/*测试Tcp*/
public class TcpSocketDemo
{
    public static void main(String[] args) {
        new Thread(new TcpClient()).start();
        new Thread(new TcpServer()).start();
    }
}

/*tcp 客户端*/
class TcpClient implements Runnable {
    public void run()
    {
        try {
            Socket s = new Socket(InetAddress.getByName("10.128.37.38"),3002);

            /*输出流*/
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String str = "";

            /*读取输入流*/
            BufferedReader bfr = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String rebackstr = null;
            while (!"over".equals(str = reader.readLine())) {
                PrintWriter pr = new PrintWriter(s.getOutputStream(),true);//发送数据的stream
                pr.println(str); 
                rebackstr = bfr.readLine(); //阻塞方法
                System.out.println("从socket服务器端回来的信息  "+ rebackstr);
            }

            bfr.close();
            s.shutdownOutput();
            s.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

/*tcp server端*/
class TcpServer implements Runnable {

    public void run(){
        try {
            ServerSocket  ss = new ServerSocket(3002);
            // ss.bind(InetAddress.getByName("10.128.37.38"));
            Socket cs = ss.accept();
            BufferedReader bis = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            String str = null;

            PrintWriter pr = new PrintWriter(cs.getOutputStream(),true);//发送数据的stream
            while(!"over".equals(str = bis.readLine())) {
                System.out.println(str + "-----");
                pr.println(str.toUpperCase());
            }

            cs.shutdownOutput();
            ss.close();
        } catch (Exception e) {

        }
    }
}