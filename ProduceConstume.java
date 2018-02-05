import java.util.concurrent.locks.*;

class Resource
{
	private String name;
	private String age;
	private final ReentrantLock lock = new ReentrantLock();
	private Condition read = lock.newCondition();
	private Condition write = lock.newCondition();

	private boolean flag = false;

	public void deposit(String name,String age) throws InterruptedException
	{	
		lock.lock();
		try{
			while (flag)
				read.await();
			this.name = name;
			this.age = age;
			flag = true;
			write.signal();
			try { Thread.sleep(10);} catch(Exception e) {} 
		}
		finally{
			lock.unlock();
		}
		
	}

	public void qu() throws InterruptedException
	{
		lock.lock();
		try{
			while (!flag)
				write.await();
			System.out.println(Thread.currentThread().getName()+" name is "+ name + " age is "+ age);
			read.signal();
			flag = false;
			try { Thread.sleep(10);} catch(Exception e) {} 
		}
		finally{
			lock.unlock();
		}
		
	}
}

class Du implements Runnable
{
	private Resource r;
	public Du(Resource r)
	{
		this.r = r;
	}

	public void run()
	{
		while (true) {
			try { r.qu();} catch(Exception e){}	
		}
	}
}

class Xie implements Runnable
{
	private Resource r;
	private static int flag = 1;
	public Xie(Resource r)
	{
		this.r = r;
	}

	public void run()
	{
		while (true) {
			if (this.flag % 2 == 1) {
				try { r.deposit("zhangsan","18");} catch(Exception e){}	
			} else {
				try { r.deposit("lisi","21"); } catch(Exception e){}	
			}
			this.flag ++;
		}
		
	}
}

public class ProduceConstume
{
	public static void main(String[] args)
	{
		Resource r = new Resource();
		Thread t1 = new Thread(new Du(r));	
		Thread t2 = new Thread(new Du(r));	
		Thread t3 = new Thread(new Du(r));	
		Thread t6 = new Thread(new Du(r));	
		Thread t4 = new Thread(new Xie(r));
		Thread t5 = new Thread(new Xie(r));


		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
	}
}