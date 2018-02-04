class Resource
{
	private String sex;
	private String name;
	private boolean flag = false;

	public synchronized void deposit(String name, String sex)
	{
		if( flag == false ) {
			this.name = name;
			this.sex = sex;
			flag = true;
			this.notify();
			System.out.println(Thread.currentThread().getName()  + "  is running" );
			try { Thread.sleep(10); } catch (Exception e) {}
			try { this.wait(); } catch (Exception e) {}
			System.out.println(Thread.currentThread().getName()  + "  is running ......" );
		}
		
	}

	public synchronized void draw()
	{
		if( flag == true) {
			System.out.println(Thread.currentThread().getName() + "   name is "+ name + " --- sex is "+ sex );
			this.notify();
			flag = false;
			try { Thread.sleep(10); } catch (Exception e) {}
			try { this.wait(); } catch (Exception e) {}
		}
	}
}

class Depositfunc implements Runnable
{
	private  Resource r;
	private int num = 1;

	public Depositfunc(Resource r)
	{
		this.r = r;
	}

	public void run()
	{
		while (true) {
			if (num%2 == 1){
				r.deposit("xiaoming","man");
			} else {
				r.deposit("zhangsan","woman");
			}
			num ++;
		}
	}	
}

class Drawfunc implements Runnable
{
	private Resource r;

	public Drawfunc(Resource r)
	{
		this.r = r;
	}

	public void run()
	{
		while (true) {
			r.draw();
		}
	}
}

public class ThreadCommunicate
{
	public static void main(String[] args) {
		Resource r = new Resource();
		Thread t1 = new Thread(new Depositfunc(r));
		Thread t2 = new Thread(new Depositfunc(r));
		Thread t3 = new Thread(new Drawfunc(r));
		Thread t4 = new Thread(new Drawfunc(r));
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}