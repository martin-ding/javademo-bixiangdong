import java.util.*;

/**
* student -> name age 
* student has a address obj in this example we use string
* store the relationship in hashmap
*/
class StudentSetMapDemo
{
	public static void main(String[] args) {
		HashMap<Student,String> hashmap = new HashMap<Student,String>(); 

		hashmap.put(new Student("lisi1",21),"shanghai");
		hashmap.put(new Student("lisi1",21),"beijing");
		hashmap.put(new Student("lisi1",23),"suzhou");
		hashmap.put(new Student("lisi1",25),"nanjing");

		// Set<Student> stdSet = hashmap.keySet(); 
		// Iterator<Student> it = stdSet.iterator();

		// while (it.hasNext()) {
		// 	Student std = it.next();
		// 	System.out.println(std + " ------ " + hashmap.get(std));
		// }

		Set<Map.Entry<Student,String>> mp = hashmap.entrySet();
		Iterator<Map.Entry<Student,String>> it = mp.iterator();
		while (it.hasNext()) {
			Map.Entry<Student,String> me = it.next();
			Student key = me.getKey();
			String val = me.getValue();
			System.out.println(key + " ----==== " + val);
		}
	}
}

/*
* 我们创建一个类的时候为了周全起见 如果要存入到框架集合中 
* 存入到hashSet hashMap 需要拥有方法 public boolean equals(Object obj)
* 还要覆盖 public int hashCode() 方法 该对象中有多少个属性 可以在属性的基础上进行 相加操作
* 上面操作为了完成set 集合的唯一性 如果将对象存到了TreeSet 集合中 那么我们还要考虑它的排序
* 默认的要实现Comparable<E> 接口 实现里面的compareTo 方法
*/
class Student implements Comparable<Student>
{
	private String name;
	private int age;

	public Student(String name, int age
)	{
		this.name = name;
		this.age = age;
	}

	public String getName()
	{
		return this.name;
	}

	public int getAge()
	{
		return this.age;
	}

	public String toString()
	{
		return name + " : " + age;
	}

	public boolean equals(Object obj)
	{
		if (!(obj instanceof Student)) {
			throw new ClassCastException("--- should be Student Type!"); // runtime exception
		}

		Student st = (Student) obj;
		boolean num = new Integer(this.age).equals(new Integer(st.getAge()));

		if (num) {
			num = this.name.equals(st.getName());
		}

		return num;
	}

	public int hashCode()
	{
		return name.hashCode() + this.age * 23;
	}

	public int compareTo(Student st)
	{
		int num = new Integer(this.age).compareTo(new Integer(st.getAge()));

		if (num == 0) {
			return this.name.compareTo(st.getName());
		}

		return num;
	}
}