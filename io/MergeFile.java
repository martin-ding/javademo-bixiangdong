package io;

import java.io.*;
import java.util.*;

/**
 * 将拆分出来的文件 合并成一个文件 
 */
class MergeFile
{
    public static void main(String[] args) {
        mergeFile();
    }

    /*将文件夹 splitfiles 文件夹下面的 demo.avi.part 文件合并成hapi.avi */
    public static void mergeFile()
    {
        Enumeration<FileInputStream> e = getElement();
        FileOutputStream fos = null;
        SequenceInputStream sis = null;
        try {
            sis = new SequenceInputStream(e);
            fos = new FileOutputStream("/Applications/AMPPS/www/test/java_dir/splitfiles/testa.avi");    
            // 1M 大小
            byte [] b = new byte[1024*1024];
            int len = 0;
            while ((len = sis.read(b)) != -1) {
                fos.write(b,0,len);
            }
        } catch (Exception eax) {

        } finally {
            try {
                sis.close();
            } catch (IOException exc) {

            } finally {
                try {
                    fos.close();
                } catch (IOException exc2) {

                }
            }
        }
        
    }

    /*其实这边可以是返回类型的泛型 是 InputStream*/
    public static Enumeration<FileInputStream> getElement()
    {
        ArrayList<FileInputStream> al = new ArrayList<FileInputStream>();
        Enumeration<FileInputStream> e = null;
        try {
            for(int i =1 ; i<4 ; i++) {
                al.add(new FileInputStream("/Applications/AMPPS/www/test/java_dir/splitfiles/demo.avi.part"+i));
            }

            final Iterator<FileInputStream> ite = al.iterator();
            e = new Enumeration<FileInputStream>()
            {
                public boolean hasMoreElements()
                {
                    return ite.hasNext();
                }

                public FileInputStream nextElement() throws NoSuchElementException
                {
                    return ite.next();
                }
            };

            return e;

        } catch (IOException excp) {

        } finally {
           
        }
        return e;
    }
}