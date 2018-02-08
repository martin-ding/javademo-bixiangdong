public class Extendsdemo{  
    public static void main(String args[]){  
        Person p;  
        Student s=new Student();  
        p=s;  
        System.out.println(p.a);      
    }  
}  
  
class Person{  
    int a;  
    public Person(){  
        a=1;  
    }  
    public void disp(){  
        System.out.println(a);  
    }  
}  
  
class Student extends Person{  
    // int a;  
    public Student(){  
        a=2;  
    }  
    public void disp(){  
        System.out.println(a);  
    }  
} 