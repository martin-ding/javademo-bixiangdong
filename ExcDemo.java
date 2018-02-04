class DemoException extends Exception
{
	protected String other;
	DemoException(String msg)
	{
		super(msg);
	}

	DemoException(String msg, String other)
	{
		super(msg);
		this.other = other;
	}
}

class ExcDemo
{
	public static void main(String[] args)
	{
		float a;

		try {
			a = func(12,-1);
		} catch(DemoException e) {
			System.out.println("wpw");
			System.out.println(e.toString());
			System.exit(0``);
			return;
		} finally {
			System.out.println("finally");
		}
	}

	public static float func(int a, int b) throws DemoException
	{
		if (b <= 0) {
			throw new DemoException("wowow");
		} else {
			return (float) a / (float) b;
		}
	} 
}