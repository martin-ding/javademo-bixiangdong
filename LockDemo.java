public class LockDemo
{
    public static void main(String[] args) {
        Resource r = new Resource();
        new Thread(new Run1(r)).start();
        new Thread(new Run1_1(r)).start();
        new Thread(new Run1_2(r)).start();
        new Thread(new Run2(r)).start();
    }
}

class Resource
{
    private boolean hasSetflag = true; //用来标识是否已经读过一次了
    private String name;
    private int age;

    public Resource()
    {

    }

    /*获取age*/
    public int getAge()
    {
        return this.age;
    }

    /*获取name*/
    public String getName()
    {
        return this.name;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public synchronized void updateInfo(String name, int age){
        while(!hasSetflag) {
            try{this.wait();}catch(Exception e){}
        }

        this.name = name;
        this.age = age;
        this.hasSetflag = false;
        this.notifyAll();
        try{Thread.sleep(100);}catch(Exception e){}
    }

    public synchronized void getInfo()
    {
        while(hasSetflag) {
            try{this.wait();}catch(Exception e) {}
        }
        System.out.println(this.name + " " + this.age);
        hasSetflag = true;
        this.notifyAll();
        try{Thread.sleep(100);}catch(Exception e){}
    }  
}

class Run1 implements Runnable
{
    private Resource r;
    public Run1(Resource r)
    {
        this.r = r;
    }

    public void run()
    {
        while(true){
            r.updateInfo("zhangsan",12);
        }
    }
}

class Run1_1 implements Runnable
{
    private Resource r;
    public Run1_1(Resource r)
    {
        this.r = r;
    }

    public void run()
    {
        while(true) {
            r.updateInfo("lisi",18);
        }
    }
}

class Run1_2 implements Runnable
{
    private Resource r;
    public Run1_2(Resource r)
    {
        this.r = r;
    }

    public void run()
    {
        while(true) {
            r.updateInfo("zhaoliu",100);
        }
    }
}

class Run2 implements Runnable
{
    private Resource r ;
    public Run2(Resource r)
    {
        this.r = r;
    }

    public void run()
    {
        while(true) {
            r.getInfo();
        }
    }
}