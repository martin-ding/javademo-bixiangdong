class GetSublime extends GetTime {
	public void runcode(){
		for (int i = 0; i<1000; i++ ) {
			System.out.print(i);
		}
	}
	public static void main(String [] args){
		GetSublime g = new GetSublime();
		g.getTime();
	}
}