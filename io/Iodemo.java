package io;

import java.io.*;

/* 复制文件*/
class Iodemo
{
    public static void main(String[] args) {
        copyImg2();
    }

    /*复制文件*/
    public static void copyFile()
    {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader("/Applications/AMPPS/www/test/java_dir/ChatDemo.java"));
            writer = new BufferedWriter(new FileWriter("/Applications/AMPPS/www/test/java_dir/io/CharDemo2.java"));
            char [] buffer = new char[5];
            int len;
            while ((len = reader.read(buffer)) != -1){
                writer.write(buffer,0,len);
                // writer.flush();
            }
        } catch (IOException e) {

        } finally {
            try {
                reader.close();
            } catch (IOException e) {

            } finally {
                try {
                    writer.close();
                } catch(IOException e) {

                }
            }
        }

    }

    /*
    * 复制图片
    * 重点在于两个 句柄都要关闭 关闭之前 首先判断一下是不是null
    **/


    public static void copyImg()
    {
        BufferedInputStream bis = null; 
        BufferedOutputStream bos = null ;

        byte [] buff = new byte[10];
        int len;

        try {
            bis = new BufferedInputStream(new FileInputStream("/Applications/AMPPS/www/test/java_dir/imgtest.jpg"));
            bos = new BufferedOutputStream(new FileOutputStream("/Applications/AMPPS/www/test/java_dir/io/new.jpg"));
            while ((len = bis.read(buff)) != -1 ) {
                bos.write(buff,0,len);
            }
            bis = null;
            bos = null;

        } catch (IOException e) {

        } finally {
            try {
                if(bis != null) 
                    bis.close();
            } catch (IOException e) {

            } finally {
                try {
                    if(bos != null )
                    bos.close();
                } catch (IOException e) {

                }
            }
        }

    }

    /*
    * 通过readbuffer copy 图片 这个会有问题 
    * 因为img 是字节流 格式的 如果转成字符流 会自动加上一个编码 会有问题
    */
    public static void copyImg2()
    {
        BufferedReader bfr = null;
        BufferedWriter bfw = null;
        try {
            bfw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Applications/AMPPS/www/test/java_dir/io/new2.jpg")));
            bfr = new BufferedReader(new InputStreamReader(new FileInputStream("/Applications/AMPPS/www/test/java_dir/imgtest.jpg")));
            String buf = "";
            while ((buf = bfr.readLine())!= null) {
               bfw.write(buf);
               bfw.newLine();
            }
        } catch (IOException e) {

        } finally {
            try {
                if(bfw != null)
                bfw.close();
            } catch(IOException e) {

            } finally {
                try {
                    if(bfr != null)
                        bfr.close();
                } catch (IOException  e) {

                }
            }
        }
    }
}


