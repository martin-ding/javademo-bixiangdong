package io;
import java.io.*;
import java.util.*;

public class PipeDemo
{
    /*两个线程 一个负责读一个负责写*/
    public static void main(String[] args) {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = new PipedOutputStream();

        try {pis.connect(pos);} catch(IOException e){}
        Run1 run1 = new Run1(pis);
        Run2 run2 = new Run2(pos);
        new Thread(run1).start();
        new Thread(run2).start();
    }
}

/*input from System.in*/
class Run1 implements Runnable
{
    PipedInputStream pis = null;

    Run1(PipedInputStream pis) {
        this.pis = pis;
    }

    public void run()
    {
        try {
            byte[] b = new byte[1024];
            while (true) {
                //在输入数据可用、检测到流的末尾或者抛出异常前，此方法一直阻塞
                //所有 while 循环是没有问题的
                int len = pis.read(b);
                System.out.print(new String(b,0,len));
            }
        } catch(IOException e) {

        } finally {
            try {
                pis.close();
            } catch (IOException e) {

            }
        }
    }
}


class Run2 implements Runnable {
    PipedOutputStream pos = null;

    public Run2(PipedOutputStream pos)
    {
        this.pos = pos;
    }

    public void run()
    {
        try {
            BufferedInputStream bufis = new BufferedInputStream(System.in);
            
            byte[] b = new byte[1024];
            int len = 0;
            while(true) {
                /*read 方法也是阻塞方法 所以while 循环没有问题*/
                while ((len = bufis.read(b)) != -1) {
                    pos.write(b,0,len);
                }
            }
        } catch (IOException e) {

        } finally {
            try {
                pos.close();
            } catch (IOException ea) {

            } 
        }
    }
}