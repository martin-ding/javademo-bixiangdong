public class Demo{
   public static void main(String[] args) {
   		int [][] arr = new int [2][];

   		System.out.println(arr[0]);
   		transa(8,1,1);

   }
   //安徽的科技啊函数的
	public static void transa(int num,int base,int offset){
	char [] tb = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'}; 
	char [] basearr = new char[32];
	int pos = basearr.length;
		while (num != 0){
			// System.out.println(num & offset);
			basearr[--pos] = tb[num & offset];
			num = num >>> base;
		}
		for(int i = pos; i<basearr.length ;i++){
			System.out.print(basearr[i]);
		}
		System.out.println();
	}
}


