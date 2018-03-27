package network;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

class SocketDemo
{
    private Frame f;

    public static void main(String[] args) {
        // SocketDemo sd = new SocketDemo();
        // sd.init();  
        // 
        new Thread(new UdpClient()).start();
        new Thread(new UdpServer()).start();

    }

    /*初始化 一个frame 测试了一下 event 事件*/
    public void init()
    {
        f = new Frame("title demo");
        f.setBounds(100,100,600,800);
        
        f.setLayout(new BorderLayout());

        f.addWindowListener(new WindowAdapter(){
            
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }

        });

        MenuBar mb = getMenubar();
        f.setMenuBar(mb);

        Button b = new Button("click me");

        // b.addKeyListener(new KeyAdapter(){
        //     public void keyPressed(KeyEvent e)
        //     {
        //         System.out.println(e.getKeyCode() +"   "+ e.getKeyChar());
        //     }
        // });
        // 
        b.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                System.out.println(e.getClickCount());
            }
        });
        f.add(b, BorderLayout.NORTH);

        f.setVisible(true);
    }

    public MenuBar getMenubar()
    {
        MenuBar mb = new MenuBar();
        Menu m1 = new Menu("wow");
        MenuItem mi1 = new MenuItem("test1");
        MenuItem mi2 = new MenuItem("test2");
        m1.add(mi1);
        m1.add(mi2);
        mb.add(m1);

        Dialog d = new Dialog(f);
        d.setTitle("demo");
        d.setBounds(100,100,100,100);




        d.addWindowListener(new WindowAdapter(){
            
            public void windowClosing(WindowEvent e)
            {
                d.setVisible(false);
            }

        });

        mi1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) 
            {
                d.setVisible(true);
            }
        });
        return mb;
    }
}

/*UPD client */
class UdpClient implements Runnable {
    
    /*运行代码*/
    public void run()
    {
        try {  
            DatagramSocket ds = new DatagramSocket(3000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String str = null;

            /*开始读取你System.in 的代码*/
            while(!"over".equals(str = reader.readLine())) {
                if(str == null) {
                    ds.close();
                    System.exit(0);
                }
                byte[] buf = str.getBytes();
                DatagramPacket dgp = new DatagramPacket(buf,buf.length,InetAddress.getByName("10.128.37.38"),3002);
                ds.send(dgp);
            }
            ds.close();      
        } catch(Exception e) {
            e.getMessage();
        } 
    }
}

/*UDP 服务端*/
class UdpServer implements Runnable {

    public void run(){
        try{
            DatagramSocket dgs = new DatagramSocket(3002);
            byte[] b = new byte[1024]; 
            while (true) {
                DatagramPacket dgp = new DatagramPacket(b,b.length);
                dgs.receive(dgp);
                // System.out.println(dgp.getAddress().getHostAddress());
                System.out.println("数据是  " + new String(dgp.getData(),0,dgp.getLength()));  
                // System.out.println(new String(dgp.getData(),0,dgp.getLength()));  
                
            }
            
        } catch(Exception e) {

        }
    }
}