import java.util.concurrent.locks.*;

public class LockDemo2
{
    public static void main(String[] args) {
        Resource r = new Resource();
        new Thread(new Run1(r)).start();
        new Thread(new Run2(r)).start();
        new Thread(new Run3(r)).start();
        new Thread(new Run3(r)).start();
    }
}

class Resource 
{
    private boolean hasSetflag = true; //用来标识是否已经读过一次了
    private String name;
    private int age;

    final Lock lock = new ReentrantLock();
    final Condition cond1 = lock.newCondition();
    final Condition cond2 = lock.newCondition();

    public void updateInfo(String name, int age){
        lock.lock();
        try {
            while(!hasSetflag){
                try{cond1.await();}catch(Exception e){}
            }
            this.name = name;
            this.age = age;
            this.hasSetflag = false;
            cond2.signal();
            try{Thread.sleep(100);}catch(Exception e){}
        } finally{
            lock.unlock();   
        }   
    }

    public void getInfo()
    {
        lock.lock();
        try{
            while(hasSetflag) {
                try{cond2.await();}catch(Exception e){}
            }

            System.out.println(name + " " + age);
            this.hasSetflag = true;
            cond1.signal();
            // try{Thread.sleep(100);}catch(Exception e){}
        } finally {
            lock.unlock();
        }
        
    }  
}


class Run1 implements Runnable
{
    Resource r;

    public Run1(Resource r)
    {
        this.r = r;
        try{Thread.sleep(10);}catch(Exception e){}
    }

    public void run()
    {
        while(true) {
            r.updateInfo("张三",12);
        }
    }
}

class Run2 implements Runnable
{
    Resource r;

    public Run2(Resource r)
    {
        this.r = r;
    }

    public void run()
    {
        while(true) {
            r.updateInfo("李四",19);
        }
    }
}

class Run3 implements Runnable
{
    Resource r;
    public Run3(Resource r)
    {
        this.r = r;
    }

    public void run()
    {
        while (true) {
            r.getInfo();
        }
    }
}