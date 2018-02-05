import java.io.*;
import java.net.*;

class TcpSocketLogin
{

}

class LoginClient
{
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("10.128.37.30",9000);
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(s.getOutputStream(),true);
        BufferedReader in =  new BufferedReader(new InputStreamReader(s.getInputStream()));

        for (int x = 0; x < 3; x++ ) {
            String name = bufr.readLine();
            if(name == null) {
                break;
            }
            out.println(name);

            String back = in.readLine();
            if (back.contains("success")) {
                System.out.println(back);
                break;
            } else {
                System.out.println(back);
            }
        }

        bufr.close();
        s.close(); //一旦关闭之后 Server 端拿到的in.getLine() 就是null
    }
}

class UserLogin implements Runnable
{
    private Socket s;

    UserLogin(Socket s)
    {
        this.s = s;
    }

    public void run()
    {
        try {
            for(int i = 0; i<3; i++) {
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream(),true);
                BufferedReader file = new BufferedReader(new FileReader("loginname.txt"));
                
                boolean flag = false;
                String nameinfile = null;
                String name = in.readLine();
                if ( name == null) {
                    break;
                }

                while ((nameinfile = file.readLine()) != null) {
                    if (nameinfile.equals(name)) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    System.out.println(name +"  loginsuccess");
                    out.println("loginsuccess");
                    break;
                } else{
                    System.out.println(name +"  try login");
                    out.println("try login");
                }
                file.close();
            }
            s.close();
        } catch(Exception e) {
            throw new RuntimeException();
        }
    }
}

class LoginServer
{
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(9000);
        while (true) {
            Socket s = ss.accept();
            new Thread(new UserLogin(s)).start();
        }
    }

}