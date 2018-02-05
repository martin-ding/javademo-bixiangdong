public class Wine {
    public void fun1(){
        System.out.println("Wine  Fun.....");
        fun2();
    }
    
    public void fun2(){
        System.out.println("Wine  Fun2...");
    }
}

class JNC extends Wine{
    public void fun1(String a){
        System.out.println("JNC   Fun1...");
        fun2();
    }
    
    public void fun2(){
        System.out.println("JNC  Fun2...");
    }
}

class Test {
    public static void main(String[] args) {
        JNC jnc = new JNC();
        jnc.fun1();
    }
}