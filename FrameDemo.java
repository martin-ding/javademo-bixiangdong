import java.awt.*;
import java.awt.event.*;
import java.io.*;

class FrameDemo
{
    private Frame f;
    private Button b;
    private TextArea ta;
    private TextField tf;

    private Dialog d;
    private Label  l;
    private Button dbutton;

    public static void main(String[] args) {
        new FrameDemo();
    }

    FrameDemo()
    {
        init();
    }

    public void init()
    {
        f = new Frame("first frame");
        f.setBounds(100,200,500,600);
        b = new Button("I am a button");
        f.setLayout(new FlowLayout());


        ta = new TextArea("this is a textarea");
        ta.setSize(500,500);
        tf = new TextField(60);
        f.add(tf);
        f.add(b);
        f.add(ta);

        myEvent();
        
        f.setVisible(true);
    }

    public void myEvent()
    {
        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        tf.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    showDirlist();
                }
            }
        });

        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showDirlist();
            }
        });

        
    }
    private void showDirlist()
    {
        String str = tf.getText();
        File f = new File(str);
        if (f.exists() && f.isDirectory()) {
            ta.setText("");
            for (String fi : f.list()) {
                ta.append(fi + "\n");
            }
        } else {
            showDialogM("directory not exists!!");
        }
    }

    public void showDialogM(String str)
    {
        d = new Dialog(f,"dialog",true);
        dbutton = new Button("sure");
        d.setLayout(new FlowLayout());
        d.setBounds(600,600,300,300);
        d.add(dbutton);
        d.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e)
            {
                d.setVisible(false);
            }
        });

        dbutton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                d.setVisible(false);
            }
        });
        d.setVisible(true);
    }

}

