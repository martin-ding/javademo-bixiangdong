package io;

import java.io.*;
/*将文件拆分成很多块*/
class SplitFile
{
    public static void main(String[] args) {
        splitFile();
    }

    public static void splitFile()
    {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        
        try {
            fis = new FileInputStream("/Applications/AMPPS/www/test/java_dir/demo.avi");

            byte[] b = new byte[1024*1024];
            //1M 大小的数据
            int len = 0, count = 1,a = 0;

            fos = new FileOutputStream("/Applications/AMPPS/www/test/java_dir/splitfiles/demo.avi.part"+count);
            while((len = fis.read(b)) != -1) {
                /*每次存储10M的数据*/
                if(a <10) {
                    fos.write(b,0,len);
                }else {
                    fos.close();
                    a = 0;
                    count ++;
                    fos = new FileOutputStream("/Applications/AMPPS/www/test/java_dir/splitfiles/demo.avi.part"+count);
                    fos.write(b,0,len);
                }
                a++;

            } 
        } catch(IOException e){

        } finally {
            try {
                fis.close();
            } catch (IOException e) {

            } finally {
                try {
                    fos.close();
                } catch(IOException e) {

                }
            }
        }
        
    }
}