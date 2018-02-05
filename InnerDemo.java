interface Ashow{
	public abstract void show();
}

public class InnerDemo{
	public static void main(String [] args){
		show(new Ashow(){
			public void show(){
				System.out.println("demo run ...");
			}
		});
	}

	public static void show(Ashow in){
		in.show();
	}
}