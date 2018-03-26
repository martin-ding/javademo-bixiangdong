package io;

import java.io.*;
import java.util.*;

/*自定义一个BuffererInputStream*/
class MyBufferedInputStream
{
    private InputStream is;
    private int pos,count,content;
    private byte[] buf = new byte[3];

    public MyBufferedInputStream(InputStream is) 
    {
        this.is = is;
    }

    public static void main(String[] args) {
        MyBufferedInputStream mybis = null;
        try {
            mybis = new MyBufferedInputStream(new FileInputStream("/Applications/AMPPS/www/test/java_dir/io/demo.txt"));
            int c = 0;
            // StringBuilder builder = new StringBuilder();
            while((c = mybis.myRead()) != -1 )
            {
                System.out.println((char)c);
            }
        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            try {
                if(mybis != null){
                    mybis.close();
                }
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }

    /*从缓冲区读*/
    public int myRead() throws IOException
    {
        /*count 表示buf 里面还剩多少个字节*/
        if(count == 0) {
            try {
                count = this.is.read(buf);//从input_stream 读取文件
                if (count == -1) return -1;
            } catch (IOException e) {

            }
            pos = 0;

            int b = buf[pos] & 0xff;
            pos ++;
            count --;
            return b;
        } else {
            int b = buf[pos] & 0xff;
            pos ++;
            count --;
            return b;
        }
    }

    public void close() throws IOException
    {
        this.is.close();
    }
} 