package network;

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.reflect.*;
/*
* 注意点 
* 1. 要先将 SounBoard 进行编译 之后 才能运行 否则会找不到
* network.SoundBoard
* 2. 编译这个文件的时候使用 javac network/MotherBoardReflect.java
 */


class MotherBoardReflect
{
    public static void main(String[] args) {
        MotherBoardReflect mf = new MotherBoardReflect();
        mf.run();
    }

    /*使用 PCI*/
    public void usePCI(PCI p)
    {
        System.out.println("------>>>>>");
        p.open();
        p.close();
    }

    /*运行*/
    public void run()
    {
       try {
            File f = new File("network/pci.properties");
            if(f.exists()){
                Properties prop = new Properties();
                prop.load(new FileReader(f));
                Set <String> hs = prop.stringPropertyNames();
                Iterator<String> it = hs.iterator();
                while (it.hasNext())
                {
                    String classname = prop.getProperty(it.next());
                    System.out.println(classname);
                    Class clazz = Class.forName(classname);
                    PCI p = (PCI)clazz.newInstance();
                    usePCI(p);
                }
            }
        } catch(Exception e) {
            System.out.println("出现错误了");
            e.printStackTrace();
        }
    }

}

