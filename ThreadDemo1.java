
class Thread1 implements Runnable
{
	private int ticket =100 ;
	// Object obj = new Object(); 
	boolean flag = true;

	public void run()
	{
		if (flag) {
			while(true){
				synchronized(this) {
					if(ticket >0){
						try { Thread.sleep(10); } catch (Exception e) {}
						System.out.println(Thread.currentThread().getName() + "    ticket  " + ticket--);
					}
				}
				// try { Thread.sleep(10); } catch (Exception e) {}
			}
		} else {
			while(true) {
				show();
				// try { Thread.sleep(100); } catch (Exception e) {}
			}
		}
	}

	public synchronized void show() 
	{
		if(ticket >0){
			try { Thread.sleep(10); } catch (Exception e) {}
			System.out.println(Thread.currentThread().getName() + "    ticket  " + ticket--);
		}
	}
}

public class ThreadDemo1
{
	public static void main(String[] args) {
		Thread1 demoThread = new Thread1();
		Thread t = new Thread(demoThread);
		Thread t2 = new Thread(demoThread);
		
		t.start();
		try { t.sleep(100); }catch(Exception e) {}
		demoThread.flag = false;
		t2.start();
	}		
}