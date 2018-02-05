import java.io.*;
import java.util.*;

class FileDemo
{
    public static void main(String[] args) {
        ArrayList<File> al = new ArrayList<File>();
        String dirname = "/Applications/AMPPS/www/test/java_dir/";
        getJavaFile(al,dirname);
        System.out.println(al.size());
        for (File f : al){
            System.out.println(f.getAbsolutePath());
        }
       storeInFile(al,"/Applications/AMPPS/www/test/java_dir/demo.txt");
    }   

    public static void method_1()
    {
        File f = new File("/Users/dingmac/Desktop");
        String[] list = f.list(new FilenameFilter(){
            public boolean accept(File dir,String name)
            {
                // System.out.println(dir);
                // System.out.println(name);
                if(name.endsWith(".txt"))
                    return true;
                return false;
            }
        });

        System.out.println(list.length);

        for (String name : list) {
            System.out.println(name);
        }
    }

    public static void method_2()
    {
        File f = new File("/Volumes/martin_doc/learning/毕向东");
        File[] files = f.listFiles();
        for(File li: files ) {
            System.out.println(li);
        }
    }

    public static void getJavaFile(List<File>list, String dirname)
    {
        File file = new File(dirname);
        for(File f : file.listFiles()) {
           if (f.isDirectory()) {
                getJavaFile(list, f.toString());
            } else {
                if(f.getName().endsWith(".java")){
                    list.add(f);
                }
            } 
        }


    }

    public static void storeInFile(List<File> list,String filename){
        
        BufferedWriter  bw = null;
        
        try {
            bw =  new BufferedWriter(new FileWriter(filename));
            // System.out.println(w);
            for (File f : list ) {
                bw.write(f.getAbsolutePath());
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            try {
                if( bw != null)
                    bw.close();
            } catch (IOException e) {
                
            }
        }
    }

}