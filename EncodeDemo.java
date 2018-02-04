import java.util.*;

class EncodeDemo
{ 
	public static void main(String[] args) throws Exception {
		method_1();
	}

	public static void method_1() throws Exception
	{
		String s = "你联通";
		byte [] b = s.getBytes("GBK");
		for (byte a : b) {
			System.out.println(Integer.toBinaryString(a & 255));
		}

		System.out.println(new String(b,"utf8"));
	}
}