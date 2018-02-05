import java.io.*;
import java.util.*;

class PrintWriterDemo
{
    public static void main(String[] args) throws IOException
    {
        merge();
    }

    public static void method() throws IOException
    {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String flag = null;
        while ((flag = bfr.readLine()) != null ){
            if ("over".equals(flag)){
                break;
            }
            pw.println(flag.toUpperCase());
            pw.flush();
        }
        bfr.close();
        pw.close();
    }

    public static void method2() throws IOException
    {
        Vector<FileInputStream> vector = new Vector<FileInputStream>();
        vector.add(new FileInputStream("file1.txt"));
        vector.add(new FileInputStream("file2.txt"));
        vector.add(new FileInputStream("file3.txt"));

        Enumeration<FileInputStream> e = vector.elements();

        SequenceInputStream stream = new SequenceInputStream(e);

        PrintStream ps = new PrintStream(new FileOutputStream("file4.txt"),true);

        byte [] b = new byte[1024];
        int len = 0;

        while ((len = stream.read(b)) != -1) {
            ps.write(b,0,len);
        }

        stream.close();
        ps.close();

    }


    public static void splitFile() throws IOException
    {
        FileInputStream fis = new FileInputStream("/Volumes/martin_doc/learning/毕向东/传智播客毕向东Java基础视频教程-day20-17-IO流(切割文件).avi");
        byte [] b = new byte[1024*1204*2];

        int len = 0,count = 0;
        while((len = fis.read(b)) != -1 ){
            FileOutputStream fos = new FileOutputStream("/Applications/AMPPS/www/test/java_dir/test/"+(count)+".part");
            fos.write(b,0,len);
            fos.close();
            count ++ ;
        }
        fis.close();
    }

    public  static void merge() throws IOException
    {
        ArrayList<InputStream> al = new ArrayList<InputStream>();
        for(int i =0 ;i<11 ; i++){
            al.add(new FileInputStream("/Applications/AMPPS/www/test/java_dir/test/"+ i + ".part"));
        }

        final Iterator<InputStream> it =  al.iterator();

        Enumeration<InputStream> e = new Enumeration<InputStream>(){
            public boolean hasMoreElements() 
            {
                return it.hasNext();
            }

            public InputStream nextElement()
            {
                return it.next();
            }
        };

        SequenceInputStream sis = new SequenceInputStream(e);
        byte[] b = new byte[1024*1024];
        int len = 0;
        FileOutputStream fos = new FileOutputStream("demo.avi");
        while((len = sis.read(b)) != -1) {
            fos.write(b,0,len);
        }
        fos.close();
        sis.close();

    }
}
