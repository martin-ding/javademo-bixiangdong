import java.util.*;

class MapTest
{
	public static void main(String[] args) {
		
		func2();
	}

	public static void func1()
	{
		TreeMap<Character,Integer> t = Tongji.tongji("Cha");
		// System.out.println(t);
		Iterator<Map.Entry<Character,Integer>> it = t.entrySet().iterator();

		StringBuilder sb = new StringBuilder();
		while (it.hasNext()) {
			Map.Entry<Character,Integer> me = it.next(); 
			sb.append(me.getKey()+"(" +me.getValue() + ")");
		}

		System.out.println(sb.toString());
	}

	public static void func2()
	{
		
		for (int i = 0; i< 10 ; i++ ) {
			int flag = 0;
			flag ++;
			System.out.println(flag);
		}
	}
}


class CharComparator implements Comparator<Character>
{
	public int compare(Character c1, Character c2)
	{
		return -(c1-c2);
	}
}

class Tongji
{
	public static TreeMap<Character,Integer> tongji(String str)
	{
		char[] ch = str.toCharArray();
		TreeMap<Character,Integer> tm = new TreeMap<Character,Integer>(new CharComparator());
		Set<Character> charkey = tm.keySet();
		for (int i = 0 ; i <ch.length ; i++ ) {
			if (charkey.contains(ch[i])) {
				int val = tm.get(ch[i]);
				tm.put(ch[i],++val);
			} else {
				tm.put(ch[i],1);
			}
		}
		return tm;
	}
	
}