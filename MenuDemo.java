package menudemo;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

class MenuDemo
{
    private Frame frame;
    private Menu fileMenu;
    private Menu fileMenuSubMenu;
    private MenuBar fileMenuBar;
    private MenuItem fileMenuItemExit;
    private MenuItem fileMenuItemSubItem;
    private MenuItem fileMenuItemSave;
    private MenuItem fileMenuItemOpen;
    private TextArea textarea;

    private FileDialog openDialog,saveDialog;

    private File fileStore;

    public static void main(String[] args)
    {
        new MenuDemo();
    }

    MenuDemo()
    {
        init();
    }

    public void init()
    {
        frame = new Frame("first frame");
        frame.setBounds(100,200,500,600);
        // frame.setLayout(new FlowLayout());
        fileMenuBar = new MenuBar();
        fileMenu = new Menu("file");
        fileMenuSubMenu = new Menu("subMenu");
        fileMenuItemOpen = new MenuItem("open");
        fileMenuItemSave = new MenuItem("save");
        fileMenuItemExit = new MenuItem("exit");

        textarea = new TextArea();

        fileMenuItemSubItem =  new MenuItem("subItem");
        fileMenuSubMenu.add(fileMenuItemSubItem);
        fileMenu.add(fileMenuItemOpen);
        fileMenu.add(fileMenuItemSave);
        fileMenu.add(fileMenuSubMenu);
        fileMenu.add(fileMenuItemExit);

        fileMenuBar.add(fileMenu);

        openDialog = new FileDialog(frame,"openDialog",FileDialog.LOAD);
        saveDialog = new FileDialog(frame,"saveDialog",FileDialog.LOAD);

        frame.setMenuBar(fileMenuBar);
        myEvent();
        frame.add(textarea);
        frame.setVisible(true);
    }

    private void myEvent()
    {
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        
        fileMenuItemExit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        fileMenuItemOpen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                openDialog.setVisible(true);
                openDialog();
            }
        });

        fileMenuItemSave.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (fileStore == null) {
                    saveDialog.setVisible(true);
                }
                saveDialog();
            }
        });

    }

    private void openDialog()
    {
        String dir = openDialog.getDirectory();
        String file = openDialog.getFile();

        if (dir == null || file == null){
            return;
        } else {
            String filePath = dir+file;
            fileStore = new File(filePath);
            textarea.setText("");
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(fileStore));
                String str = null;
                while((str = reader.readLine()) != null) {
                    textarea.append(str+"\n");
                }
            } catch(IOException e){
                throw new RuntimeException("error 1");
            } finally {
                if( reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        throw new RuntimeException("error 2");
                    }
                }
            }
        }
    }

    private void saveDialog()
    {
        if (fileStore == null) {
            String dir = saveDialog.getDirectory();
            String file = saveDialog.getFile();
            System.out.println(dir + "...." + file);
            if (dir == null || file == null){
                return;
            } 
            String filePath = dir+file;
            fileStore = (fileStore == null) ? new File(filePath) : fileStore;
        }
        
        String text = textarea.getText();
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileStore));
            writer.write(text);
        } catch(IOException e){
            throw new RuntimeException("error 1");
        } finally {
            if( writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException("error 2");
                }
            }
        }
    }
}