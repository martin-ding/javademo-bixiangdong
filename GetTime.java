public abstract class GetTime{
	public void getTime(){
		double start = System.currentTimeMillis();
		runcode();
		double end = System.currentTimeMillis();
		System.out.println("execute time is " +(end - start));
	}

	public abstract void runcode();

}