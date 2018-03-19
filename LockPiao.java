/*这个是一个多个线程卖票 卖票的实例 
* 使用的是Lock Condition 的方式
*  
* flag 标识工作的同一类(都是增加票的,都是减少票的) 一个生产完了 一个消费
* 1. 想要控制 出现多个生产 对应一个消费 可以像我在 buyTicket方法中控制循环 
* 以及 修改flag 
* 2. 同样的道理也可以只生产一次 多次消费
*
* 
* 
*/
import java.util.concurrent.locks.*;

class LockPiao
{
    public static void main(String[] args) {
        Resource r = new Resource();
        new Thread(new Run1(r)).start();
        new Thread(new Run1(r)).start();
        new Thread(new Run2(r)).start();
    }
}

class Resource
{
    private int ticket = 10;
    private boolean flag = false;
    final Lock lock = new ReentrantLock();
    Condition cond1 = lock.newCondition();
    Condition cond2 = lock.newCondition();

    /*增加票数*/
    public void addTicket(int num)
    {
        lock.lock();
        try {
            while (flag) {
                try{cond1.await();}catch(Exception e){}
            }

            /*比如说 到达100张票之后就先不生产让消费继续*/
            while(this.ticket > 3) {
                flag = true;
                cond2.signal();
                try{cond1.await();}catch(Exception e){}
            }


            this.ticket += num;
            System.out.println(Thread.currentThread().getName() + "   ADD ticket is " + this.ticket);
            flag = true;
            cond2.signal();
            try {Thread.sleep(100);}catch(Exception e){}
        } finally{
            lock.unlock();
        }
    }

    /*减少票数*/
    public void buyTicket(int num)
    {  
        lock.lock();
        try {
            while (!flag) {
                try{cond2.await();}catch(Exception e){}
            }

            while(this.ticket < num)
            {
                flag = false;
                cond1.signal();
                try{cond2.await();}catch(Exception e){}
            }
           
            
            this.ticket -= num;
            System.out.println(Thread.currentThread().getName() +"      Minus ticket is " + this.ticket);

            flag = false;
            cond1.signal();
            try {Thread.sleep(100);}catch(Exception e){}
        } finally{
            lock.unlock();
        }

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
            r.addTicket(10); 
        }
    }
}

class Run2 implements Runnable
{
    private Resource r;

    public Run2(Resource r)
    {
        this.r = r;
    }

    public void run()
    {
        while (true) {
            r.buyTicket(3); 
        }
    }
}