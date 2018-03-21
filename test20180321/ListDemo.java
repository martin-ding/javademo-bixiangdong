package test20180321;
import java.util.*;

public class ListDemo
{
    public static void main(String[] args) {
        LinkedList<Person> list = new LinkedList<Person>();
        list.add(new Student("lisi",12));
        list.add(new Student("abao",29));
        list.add(new Worker("zhaowu",20));
        for (Person p:list) {
            System.out.println(p);
        }

        Collections.sort(list,new CompareByAge());
        for (Person p:list) {
            System.out.println(p);
        }

    }
}

class Person implements Comparable<Person>
{
    private String name;
    private int age;


    public Person(String name, int age)
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

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Person)) {
            throw new ClassCastException("xxx");
        }
        Person std = (Person) obj;
        boolean flag = new Integer(this.age).equals(new Integer(std.getAge()));
        if(flag) {
            return this.name.equals(std.getName());
        }
        return flag;
    }

    public int compareTo(Person p)
    {
        int flag = new Integer(this.age).compareTo(new Integer(p.getAge()));
        if(flag == 0) {
            return this.name.compareTo(p.getName());
        }
        return flag;
    }
}

class Student extends Person implements Comparable<Person>
{
    public Student(String name, int age)
    {
        super(name, age);
    }

    public void Studing()
    {
        System.out.println("Studing");
    }

}

class Worker extends Person
{
    public Worker(String name, int age)
    {
        super(name, age);
    }

    public void working()
    {
        System.out.println("working");
    }
}

/*一个比较器 只按照用户的age 进行排序*/
class CompareByAge implements Comparator<Person>
{
    public int compare(Person p1, Person p2)
    {
        return - new Integer(p1.getAge()).compareTo(p2.getAge());
    }
}

