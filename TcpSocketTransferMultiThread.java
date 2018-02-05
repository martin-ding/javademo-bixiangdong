import java.io.*;
import java.net.*;

class TcpSocketTransferMultiThread
{

}

class TcpClient
{
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("必须只包含一个文件");
            return;
        }

        String filename = args[0];
        if(! filename.endsWith(".jpg")) {
            System.out.println("必须为jpg 文件");
            return;
        }

        File f = new File(filename);

        // if (! f.getName().endsWith(".jpg")) {
        //     System.out.println("必须为jpg 文件");
        //     return;
        // }

        if(!(f.exists() && f.isFile())) 
        {
            System.out.println("指定文件不存在,或者不是文件");
            return;
        }

        if(f.length() > 1024*1024*5) {
            System.out.println("文件过大不允许上传");
            return;
        }

        Socket s = new Socket("10.128.37.30",9000);

        FileInputStream fis = new FileInputStream(f);

        OutputStream out = s.getOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = fis.read(buf)) != -1) {
            out.write(buf,0,len);
            out.flush();
        }
        fis.close();

        s.shutdownOutput();

        BufferedReader in  = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str = null;
        while ((str = in.readLine()) != null) {
            System.out.println(str);
        }

        // s.close();
    }
}

class TcpServerSocket implements Runnable
{
    private Socket s;
    TcpServerSocket(Socket s)
    {
        this.s = s;
    }

    public void run()
    {
        try {
            int count = 1;
            String ip = s.getInetAddress().getHostAddress();

            System.out.println(ip + ".....connected");

            InputStream in = s.getInputStream();
            File f = new File("imgtest_server("+count+").jpg");
            while (f.exists())
            {
                f = new File("imgtest_server("+(count++)+").jpg");
            }

            FileOutputStream fos = new FileOutputStream(f);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) != -1)
            {
                fos.write(buf,0,len);
            }
            fos.close();

            PrintWriter out = new PrintWriter(s.getOutputStream());
            out.print("上传完成");
            out.flush();
            s.shutdownOutput();
            s.close();

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

class TcpServer
{
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(9000);
        while (true) {
            Socket s = ss.accept();
            new Thread(new TcpServerSocket(s)).start();
        }
        // ss.close();
    }

}

