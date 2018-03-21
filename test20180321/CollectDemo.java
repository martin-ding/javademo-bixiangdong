package test20180321;

import java.util.*;

public class CollectDemo
{
    public static void main(String[] args) {
        testCollect();  
    }

    /*测试collection*/
    public static void testCollect()
    {
        Map<Student,String> stdmap = new HashMap<Student,String>();
        stdmap.put(new Student("xiaoming",22),"上海");
        stdmap.put(new Student("zhaoliu",23),"上海");
        stdmap.put(new Student("zhaoliu",67),"上海");
        stdmap.put(new Student("zhaoliu",23),"上海");
        Set<Student> keys = stdmap.keySet();
        Iterator<Student> it = keys.iterator();
        while (it.hasNext()){
            Student std = it.next();
            System.out.println("Student : " + std.toString() + stdmap.get(std));
        }
        
    }
}

class Student implements Comparable<Student>
{   
    private String name;
    private int age;


    public Student(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public String getName()
    {
        return this.name;
    }

    public int getAge()
    {
        return this.age;
    }

    public String toString()
    {
        return this.name + "   " +this.age;
    }

    public void getNameAge()
    {
        System.out.println(this.name + "   " +this.age);
    }

    public int compareTo(Student std)
    {
        int flag = this.name.compareTo(std.getName());
        if (flag == 0) {
            return new Integer(this.age).compareTo(std.getAge());
        }
        return flag;
    }

    public int hashCode()
    {
        return this.name.hashCode() + this.age * 23;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof Student)) {
            throw new ClassCastException();
        }

        Student std = (Student) obj;
        boolean flag = new Integer(age).equals(new Integer(std.getAge()));
        if (flag) {
            return name.equals(std.getName());
        }
        return flag;
    }
}





