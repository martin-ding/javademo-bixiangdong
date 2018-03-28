package network;

import java.net.*;
import java.io.*;


/* 登录多线程 */
class SocketLogin
{
    public static void main(String[] args) {
        try {
        Socket s = new Socket("10.128.37.38",3002);

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter sout = new PrintWriter(s.getOutputStream(),true);
        BufferedReader sin = new BufferedReader(new InputStreamReader(s.getInputStream()));

        for(int a = 0; a < 3; a++) {
            String str =  bfr.readLine();
            System.out.println("输入的内容是 "+ str);
            if (str == null) {
                break;
            } else {
                sout.println(str);
                String info = sin.readLine();//从服务端回传回来的消息
                System.out.println("返回来的内容  " + info);
                if(info == null) {
                    System.out.println("返回的内容是null");
                    continue;
                }
                if (info.contains("success")) {
                    break;
                }

                System.out.println("info " + info);
            }
        }

        bfr.close();
        s.close();

        } catch(Exception e) {
            throw new RuntimeException("错误......");
        }
    }
}

class LoginNewServer
{

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(3002);
            while (true) {
                Socket s = ss.accept();
                new Thread(new UserThread(s)).start();
            }
        } catch (Exception e) {

        }
    }
}

class UserThread implements Runnable
{
    private Socket s ;

    public UserThread(Socket s)
    {
        this.s = s;
    }

    public void run()
    {
        String ip = s.getInetAddress().getHostAddress();
        System.out.println("ip address is " + ip);

        try {
            BufferedReader sin = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter sout = new PrintWriter(s.getOutputStream(),true);
            for (int a = 0 ; a < 3 ; a++ ) {
                
                String str = sin.readLine();
                if(str == null){ //如果获取的数据是"" 表示ctrl c 了
                    break;
                }

                BufferedReader bfr = new BufferedReader(new FileReader("/Applications/AMPPS/www/test/java_dir/network/user.txt"));
                String line = null;
                Boolean hasUser = false;

                while ((line = bfr.readLine()) != null) {
                    if(line.equals(str)) {
                       hasUser = true;
                       break; 
                    }
                }

                bfr.close();

                if (hasUser)  {
                    System.out.println(str + " login success!");
                    sout.println(str + " login success!");
                    break;
                } else {
                    System.out.println(str + " login failed!");
                    sout.println(str + " login failed!");
                }
            }
            /* 干掉s */
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("校验失败.....");
        }
    }
} 
