import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class MyIE
{
    private Frame f;
    private TextField tf;
    private TextArea ta;
    private Button b;


    public static void main(String[] args) {
        MyIE myie = new MyIE();
        myie.init();
    }

    /*布局*/
    public void init()
    {
        f = new Frame();
        f.setLayout(new FlowLayout());
        f.setBounds(400,500,600,600);
        b = new Button("转到");
        tf =  new TextField(60);
        tf.setText("http://121.199.183.103:80/demo.html"); //demo url
        ta = new TextArea();
        ta.setSize(700, 900);

        f.add(tf);
        f.add(b);
        f.add(ta);

        myEvent();
        f.setVisible(true);
    }

    /*event 事件*/
    private void myEvent()
    {
        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
                displayPage();
            }
        });

        b.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) 
            {
                displayPage();
            }
        });

        tf.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    displayPage();
                }
            }
        });
    }


    /*显示页面*/
    private void displayPage()
    {
        String url = tf.getText();
        int pos1 = url.indexOf("//")+2;
        int pos2 = url.substring(pos1).indexOf("/");
        String request = url.substring(pos2+pos1);
        String hostport = url.substring(pos1,pos2+pos1);
        String [] req = hostport.split(":"); 
        String host = req[0];
        int port = Integer.parseInt(req[1]);
        tcpClient(host, port, request);
    }

    private void tcpClient(String host, int port, String request)
    {
        try {
            Socket s = new Socket(host, port);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream(),"UTF-8"));


            out.println("GET "+ request +" HTTP/1.1");
            out.println("Host: " + host);
            out.println("Connection: close");
            out.println("Accept-Language:zh-CN,zh;q=0.9,en;q=0.8");
            out.println("Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            out.println("Accept-Encoding: gzip");
            out.println("User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
            out.println(); //一定要记得这一行
            out.println();
            s.shutdownOutput();

            ta.setText("");
            String str = null;
            while (( str = in.readLine()) != null) {
                ta.append(str + "\n");
            }

            s.close();

        } catch(Exception e) {
            throw new RuntimeException("error ...");
        }
    }
}