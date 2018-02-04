import java.util.*;

class CollectionsDemo
{
	public static void main(String[] args) {
		func1();
	}

	//sort string list
	public static void func1()
	{
		CollectionsDemo.StrComparator comp = new CollectionsDemo() . new StrComparator();
		List<String> list = new ArrayList<String>();
		list.add("asak");
		list.add("bjs");
		list.add("djsasasak");
		list.add("kaew");

		System.out.println(list);

		// Collections.sort(list,comp);

		// System.out.println(list);
		// // System.out.println(Collections.binarySearch(list,"je",comp));

		// System.out.println(halfSearch(list,"haslkj"));

		fillpartList(list,"pp",1,3);

		System.out.println(list);

	}

	class StrComparator implements Comparator<String>
	{
		public int compare(String s1, String s2)
		{
			return s1.length() - s2.length();
		}
	}

	//search index of str in list
	public static int halfSearch(List<String> list ,String key)
	{
		int start,end,mid;
		start = 0;
		end = list.size();
		mid = 0;
		while (start < end) {
			mid = (start+end)/2;
			String str = list.get(mid);
			int a = str.compareTo(key);
			if (a > 0) { 
				end = mid-1;
			} else if (a == 0) {
				return mid;
			} else {
				start = mid+1;
			}
		}

		return (-(mid)-1);
	}

	public static void fillpartList(List<String> list,String pp,int start,int end)
	{
		// List<String> list1 = list.subList(0,start);
		List<String> list2 = list.subList(start,end);
		// List<String> list3 = list.subList(end,list.size());

		// List<String> listsum = new ArrayList<String>();
		// listsum.addAll(list1);
		Collections.fill(list2,pp);
		// listsum.addAll(list2);
		// listsum.addAll(list3);

		System.out.println(list);
	}
}

