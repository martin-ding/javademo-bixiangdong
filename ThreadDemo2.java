class Test implements Runnable
{
	private int ticket = 100;
	public void run()
	{
		while(true){
			synchronized(this){
				if (ticket > 0) {
					try {Thread.sleep(10);} catch(Exception e){}
					System.out.println(Thread.currentThread().getName() + "  ticket    " + ticket-- );
				}
			}
			try {Thread.sleep(10);} catch(Exception e){}
		}
	}
}

public class ThreadDemo2
{
	public static void main(String[] args) {
		Test test = new Test();
		Thread t = new Thread(test);
		Thread t2 = new Thread(test);
		t.start();
		t2.start();
	}
}