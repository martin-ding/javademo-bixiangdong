class DemoThread extends Thread 
{
	private static int ticket = 100;
	protected boolean flag = true;

	DemoThread(String name)
	{
		super(name);
	}

	public void run()
	{
		while (true) {
			if(ticket <= 0)
				break;
			System.out.println(currentThread().getName() + "  当前ticket 数量  " + ticket--);
		}
	}
}


public class TicketThread
{
	public static void main(String[] args) {
		DemoThread dt = new DemoThread("one ----- ");
		dt.flag = false;
		// dt.start();
		// DemoThread dt2 = new DemoThread("two ----- ");
		// dt2.start();
		// DemoThread dt3 = new DemoThread("three ----- ");
		// dt3.start();
		// DemoThread dt4 = new DemoThread("Four ----- ");
		// dt4.start();
	}
} 