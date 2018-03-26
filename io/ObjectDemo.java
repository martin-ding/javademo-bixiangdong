package io;

import java.io.*;
  
public class ObjectDemo
{
    public static void main(String[] args) throws Exception, IOException, ClassNotFoundException
    {
      setObjectStream();
      getObjSetStream();
    }

    public static void setObjectStream() throws IOException
    {
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.txt"));

      oos.writeObject(new Person("zhangsan", 20));  //对象的本地持久化存贮.将对象记录到文件,需要用ObjectInpuStream来读取.
      oos.writeObject(new Person("lisi", 30));
      oos.writeObject(new Person("wangwu", 25));

      oos.close();
    }

    public static void getObjSetStream() throws Exception, IOException, ClassNotFoundException
    {
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.txt"));  //修改Person类后，读取的还是老文件，现在Person.class对应同一个UID值，现在也可以读取数据.

      Person p1 = (Person)ois.readObject();
      Person p2 = (Person)ois.readObject();
      Person p3 = (Person)ois.readObject();

      System.out.println(p1.toString());
      System.out.println(p2.toString());
      System.out.println(p3.toString());
    }
}

class Person implements Serializable {
  private int age;
  private String name;

  public Person(String name, int age)
  {
    this.name = name ;
    this.age = age;
  }
  
  public String toString()
  {
    return this.name +" --- " + this.age;
  }
}



