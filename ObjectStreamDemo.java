import java.io.*;

class ObjectStreamDemo
{
	public static void main(String[] args) throws IOException {
		method_1();
	}

	public static void method_1() throws IOException
	{
		FileOutputStream fos = new FileOutputStream("obj.txt");
		ObjectOutputStream  oos =  new ObjectOutputStream(fos);
		oos.writeObject(new Person("zhangsan", 12));
	}
}