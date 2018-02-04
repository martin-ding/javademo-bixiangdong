import java.awt.*;
import java.awt.event.*;
import java.io.*;

class JavaWindowDemo
{
	public static void main(String[] args) 
	{
		new FrameDemo();
	}

}

class FrameDemo
{
	private Frame f;
	private Button b;
	private TextArea ta;
	private TextField tf;
	
	private Dialog d;
	private Button dbutton;
	private Label lb;

	FrameDemo()
	{
		init();
	}

	public void showDialog(String str)
	{
		d = new Dialog(f,"this is a Dialog",true);
		dbutton = new Button("sure");
		Label lb = new Label(str);
		dbutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				d.setVisible(false);
				System.out.println("...");
			}
		});
		d.setLayout(new FlowLayout());
		d.setBounds(300,150,100,200);
		
		d.add(lb);
		d.add(dbutton);
		d.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				d.setVisible(false);
			};
		});
		d.setVisible(true);
	}

	public void init()
	{
		f = new Frame("demo frame");
		f.setBounds(300,100,600,500);
		f.setLayout(new FlowLayout());
		b = new Button("new button");

		ta = new TextArea("init text");
		ta.setSize(20,100);
		tf = new TextField(50);

		myEvent(f);

		b.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				System.out.println(e.getKeyChar() +"   "+ e.getKeyCode() +"..."+ e.getKeyText(e.getKeyCode()));
			}
		});

		// b.addMouseListener(new MouseAdapter(){
		// 	int count = 0;
		// 	public void mouseEntered(MouseEvent e)
		// 	{
		// 		System.out.println(++count);
		// 	}

		// 	public void mouseClicked(MouseEvent e)
		// 	{
		// 		System.out.println(++count);
		// 	}
		// });

		// b.addActionListener(new ActionListener(){
		// 	public void actionPerformed(ActionEvent e)
		// 	{
		// 		System.out.println("hello");
		// 	}
		// });

		f.add(tf);
		f.add(b);
		f.add(ta);
		myEvent2();
		d = new Dialog(f,"tishi",true);
		f.setVisible(true);
	}

	private void myEvent(Frame f)
	{
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}

	private void myEvent2(){
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String str = tf.getText();
				File f = new File(str);
				if(f.exists()  && f.isDirectory()){
					ta.setText("");
					String[] ls = f.list();
					StringBuilder sb = new StringBuilder();
					for(String fi : ls) {
						sb.append(fi+"\r\n");
					}
					ta.setText(sb.toString());
				} else {
					showDialog("not a isDirectory");
				}
			}
		});
	}
}