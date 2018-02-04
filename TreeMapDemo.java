import java.util.*;

class StdNameComparator implements Comparator<Student>
{
	public int compare(Student std1, Student std2)
	{
		return std1.getName().compareTo(std2.getName());
	}
}

public class TreeMapDemo
{
	public static void main(String[] args) {
		TreeMap<Student,String> tm = new TreeMap<Student,String>(new StdNameComparator());	
		tm.put(new Student("zhangsan",21),"shanghai"); 
		tm.put(new Student("wangwu",22),"sushou"); 
		tm.put(new Student("zhaoliu",21),"changzhou"); 
		tm.put(new Student("zhangsan2",23),"shanghai"); 

		Iterator<Map.Entry<Student,String>> it =  tm.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Student, String> me = it.next();
			System.out.println( me.getKey() + "    " + me.getValue());
		}
	}	
}