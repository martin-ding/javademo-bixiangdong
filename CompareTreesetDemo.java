import java.util.*;

class StringLengthCompare implements Comparator
{
	public int compare(Object o1, Object o2)
	{
		String s1 = (String) o1;
		String s2 = (String) o2;
		return s1.length() - s2.length();
	}
}

class CompareTreesetDemo
{
	public static void main(String[] args) {
		TreeSet strTree = new TreeSet(new StringLengthCompare());
		strTree.add("abc");
		strTree.add("bcha");
		strTree.add("bch");
		strTree.add("bchajaa");
		strTree.add("bchala");

		Iterator it = strTree.iterator();
		while (it.hasNext()) {
			String str = (String) it.next();
			System.out.println(str);
		}
	}
}