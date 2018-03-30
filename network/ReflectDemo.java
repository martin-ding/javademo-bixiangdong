package network;

import java.io.*;
import java.net.*;

class ReflectDemo
{
    private PCIA p;

    public ReflectDemo(PCIA p)
    {
        this.p = p;
    }

    public static void main(String[] args)
    {
        ReflectDemo ref = new ReflectDemo(new SoundCard());
        ref.usePCI();
    }

    public void usePCI()
    {
        p.open();
        p.close();
    }
}

/* 接口提高了扩展性 */
interface PCIA
{
    public abstract void open();
    public abstract void close();
}

class SoundCard implements PCIA
{
    public void open()
    {
        System.out.println("soundcard is opening .....|||| ");
    }

    public void close()
    {
        System.out.println("soundcard is closing .....|||| ");
    }
}