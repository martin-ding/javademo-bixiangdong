import java.util.*;

class MapDemo
{
	public static void main(String[] args) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("std1","name1");
		map.put("std2","name2");
		map.put("std3","name3");
		map.put("std4","name4");

		// // Collection<String> collection = map.values();
		// // Iterator<String> it = collection.iterator();
		// // while(it.hasNext()) {
		// // 	System.out.println(it.next());
		// // }
		// Set<String> key = map.keySet();
		// Iterator<String> it = key.iterator();

		// while(it.hasNext()) {
		// 	System.out.println(map.get(it.next()));
		// }

		Set<Map.Entry<String,String>> entrySet = map.entrySet();

		Iterator<Map.Entry<String,String>> it = entrySet.iterator();

		while(it.hasNext()) {
			Map.Entry<String,String> mapEntry = it.next();
			System.out.println(mapEntry.getKey() + " ----- " + mapEntry.getValue());
		}
	}
}


interface Mapd
{
	public static interface Entrya
	{
		public abstract Object getValue();
		public abstract Object getKey();
	}
} 

class DA
{
	class DM
	{
		
	}
}