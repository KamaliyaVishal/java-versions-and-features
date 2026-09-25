package java1_2.collections;

import java.util.*;

/**
 * Java 1.2 Collections Framework Example Demonstrates core collection classes
 */
public class CollectionsFrameworkExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 1.2 Collections Framework ===\n");

		System.out.println("1. List - ArrayList");
		System.out.println("------------------");
		List<String> list = new ArrayList<>();
		list.add("Java");
		list.add("Python");
		list.add("C++");
		list.add("Java"); // Duplicates allowed
		System.out.println("List: " + list);
		System.out.println("Size: " + list.size());
		System.out.println("Element at index 1: " + list.get(1));

		System.out.println("\n2. Set - HashSet");
		System.out.println("---------------");
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(1); // Duplicate, ignored
		System.out.println("Set: " + set);
		System.out.println("Size: " + set.size());
		System.out.println("Contains 2: " + set.contains(2));

		System.out.println("\n3. Map - HashMap");
		System.out.println("---------------");
		Map<String, Integer> map = new HashMap<>();
		map.put("Java", 1995);
		map.put("Python", 1991);
		map.put("C++", 1985);
		System.out.println("Map: " + map);
		System.out.println("Java year: " + map.get("Java"));
		System.out.println("Keys: " + map.keySet());
		System.out.println("Values: " + map.values());

		System.out.println("\n4. LinkedList");
		System.out.println("------------");
		LinkedList<String> linkedList = new LinkedList<>();
		linkedList.add("First");
		linkedList.add("Second");
		linkedList.add("Third");
		System.out.println("LinkedList: " + linkedList);
		linkedList.addFirst("Zero");
		linkedList.addLast("Fourth");
		System.out.println("After adding: " + linkedList);

		System.out.println("\n5. TreeSet (Sorted)");
		System.out.println("------------------");
		TreeSet<Integer> treeSet = new TreeSet<>();
		treeSet.add(5);
		treeSet.add(2);
		treeSet.add(8);
		treeSet.add(1);
		System.out.println("TreeSet (sorted): " + treeSet);

		System.out.println("\n6. TreeMap (Sorted)");
		System.out.println("------------------");
		TreeMap<String, Integer> treeMap = new TreeMap<>();
		treeMap.put("Zebra", 1);
		treeMap.put("Apple", 2);
		treeMap.put("Banana", 3);
		System.out.println("TreeMap (sorted by key): " + treeMap);

		System.out.println("\nKey Features:");
		System.out.println("- Unified API for collections");
		System.out.println("- Type-safe implementations");
		System.out.println("- High performance");
		System.out.println("- Reusable data structures");
		System.out.println("- Standard algorithms");

		System.out.println("\nCore Interfaces:");
		System.out.println("- Collection: Root interface");
		System.out.println("- List: Ordered collection with duplicates");
		System.out.println("- Set: No duplicates");
		System.out.println("- Map: Key-value pairs");
		System.out.println("- Iterator: Traversal mechanism");
	}
}

