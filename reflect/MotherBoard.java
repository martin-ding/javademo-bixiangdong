package reflect;
/*定义一个 主板类可以使用PCI接口设备*/
import java.io.*;
import java.util.*;

class MotherBoard
{
    public void run()
    {
        System.out.println("running .....");
    }

    public void usePCI(IPCI p)
    {
        p.open();
        p.close();
    }

    public static void main(String[] args) throws Exception {
        MotherBoard mb = new MotherBoard();
        mb.run();
        /* 为了提高扩展性,使用反射 */
        Properties prop = new Properties();
        File f = new File("reflect/ref.properties");
        if (f.exists() && f.isFile()) {
            FileInputStream stream = new FileInputStream(f);
            prop.load(stream);

            for (int x = 0; x < prop.size() ; x++ ) {
                String className = prop.getProperty("pci"+(x+1));
                Class clazz = Class.forName(className);
                IPCI pci = (IPCI)clazz.newInstance();
                pci.open();
                pci.close();
            }
            stream.close();
        } else {
            System.out.println("file not exists");
        }
    }
}