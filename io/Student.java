package io;

import java.util.*;
import java.io.*;

public class Student implements Comparable<Student>{
    private int score;
    private int age;
    private String name;
    public static void main(String[] args) {
        TreeSet <Student> ts = storeStudent();
        Iterator<Student> it = ts.iterator();
        while (it.hasNext()) {
            Student std = it.next();
            System.out.println(std + "----" + std.getScore());
        }
        Object  
    }

    /*将System.in 的数据保存到 Student对象中使用TreeSet进行存储*/
    public static TreeSet<Student> storeStudent()
    {
        TreeSet <Student>ts = new TreeSet<Student>();
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(System.in);
            byte [] b = new byte[1024];
            int len = 0;
            while((len = bis.read(b)) != -1) {
                String inputStr = new String(b,0,len);
                inputStr = inputStr.replaceAll("\r|\n", "");
                String[] str = inputStr.split("\\s");

                if(! "over".equals(inputStr)){
                    ts.add(new Student(Integer.parseInt(str[0]),str[1],Integer.parseInt(str[2])));
                } else {
                    break;
                }
            }
        } catch(IOException e) {

        }

        return ts;
    }

    public int getScore()
    {
        return this.score;
    }

    public Student(int score,String name,int age)
    {
        this.score = score;
        this.name = name;
        this.age = age;
    }

    public String toString()
    {
        return this.score +"   "+ this.name + "   ";
    }

    public int compareTo(Student std) {
        return new Integer(this.score).compareTo(new Integer(std.getScore()));
    }
}