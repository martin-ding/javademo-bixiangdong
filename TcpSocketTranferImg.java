
import java.net.*;
import java.io.*;

/*transfer image by tcp socket*/
class TcpSocketTranferImg 
{

}

/* client */
class TcpSocket
{
    public static void main(String[] args) throws Exception{
        Socket s = new Socket("10.128.37.30",9000);
        FileInputStream fstream = new FileInputStream("imgtest.jpg");
        byte [] buf = new byte[1024];
        BufferedOutputStream out = new BufferedOutputStream(s.getOutputStream());

        OutputStream stream = s.getOutputStream();
        int len = 0;
        while ((len = fstream.read(buf)) != -1) {
            out.write(buf,0,len);
        }

        s.shutdownOutput();

        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str = null;
        System.out.println(bufIn.readLine());
        if ((str = bufIn.readLine()) != null){
            System.out.println(str);
        }
        
        // InputStream in = s.getInputStream();

        // byte[] b = new byte[1024];

        // int lenin = 0;
        // if((lenin = in.read(b)) != -1)
        //     System.out.println(new String(b,0,lenin));

        fstream.close();
        s.close();
    }
}

/*server*/
class TcpServerSocket
{
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(9000);
        Socket s = ss.accept();
        File f = new File("imgtest_server.jpg");
        FileOutputStream foutstream = new FileOutputStream(f);
        InputStream in = s.getInputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = in.read(buf)) != -1) {
            foutstream.write(buf,0,len);
        }

        PrintWriter out = new PrintWriter(s.getOutputStream(),true);
        out.println("it was done !");
        s.close();
        ss.close();
    }
}


