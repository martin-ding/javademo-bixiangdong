package network;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

class UploadImgClient 
{
    public static void main(String[] args) {
        try {
        Socket s = new Socket("10.128.37.38",3002);
        /*读取文件 img*/
        FileInputStream fis = new FileInputStream("/Applications/AMPPS/www/test/java_dir/imgtest.jpg");
        BufferedReader sin = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedOutputStream sout = new BufferedOutputStream(s.getOutputStream());
        byte [] buf = new byte[1024];
        int len = 0;
        while((len = fis.read(buf)) != -1) {
            sout.write(buf,0,len);//写入到Server端
            sout.flush();
        }
        // sout.close(); // 关闭sout

        s.shutdownOutput(); //这里的作用其实 和sout.close() 类似

        String reback = sin.readLine();
        System.out.println("reback info is " + reback);

        fis.close();
        s.close();
        } catch (Exception e) {

        }
    }
}

class UploadImgServer
{
    public static void main(String[] args) {
        try {
        ServerSocket ss = new ServerSocket(3002);
        Socket s = ss.accept();
        FileOutputStream fos = new FileOutputStream("/Applications/AMPPS/www/test/java_dir/network/imgtest.jpg");
        BufferedInputStream oin = new BufferedInputStream(s.getInputStream());//从input 里面读取文件呢
        byte [] b = new byte[1024];
        int len = 0;
        /*读文件*/
        while ((len = oin.read(b)) != -1) {
            fos.write(b,0,len);
        }

        fos.close();// 保存图片完成
        BufferedWriter sout = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        sout.write("传输完成",0,4);
        sout.newLine();
        sout.flush();
        s.close();
        ss.close();

        } catch (Exception e) {

        }
    }
}
