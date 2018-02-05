import java.util.*;
import java.io.*;

class PropertiesDemo
{
    public static void main(String[] args) throws IOException
    {
        // readProperty();
        setTimeProperty();
    }

    public static void setProperty() 
    {
        Properties prop = new Properties();
        prop.setProperty("lisi","23");
        prop.setProperty("wangwu","33");
        // System.out.println(prop);

        // System.out.println(prop.getProperty("lisi"));
        Set<String> ps = prop.stringPropertyNames();

        for(String s : ps) {
            System.out.println(s+"  : " +prop.getProperty(s));
        }
    }

    public static void readProperty() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("info.txt"));
        Properties prop = new Properties();
        prop.load(br);
        prop.setProperty("lisi","32");
        prop.list(System.out);

        BufferedWriter bw = new BufferedWriter(new FileWriter("info.txt"));
        prop.store(bw,"");
        br.close();
        bw.close();

    }

    public static void setTimeProperty() throws IOException
    {
        Properties prop = new Properties();
        File file = new File("info.txt");
        if( ! file.exists())
            file.createNewFile();

        BufferedReader bfr = new BufferedReader(new FileReader(file));
        prop.load(bfr);

        String time = prop.getProperty("times");
        int timenum = 0;

        if (time != null) {
             timenum = Integer.parseInt(time);
             timenum++;
        } 
        
        if(timenum > 5) {
            System.out.println("拿钱!");
        } else {
            prop.setProperty("times",(new Integer(timenum)).toString());
            
            BufferedWriter bfw = new BufferedWriter(new FileWriter(file));
            prop.store(bfw, "");

            bfw.close();
        }
        bfr.close();

    }
}