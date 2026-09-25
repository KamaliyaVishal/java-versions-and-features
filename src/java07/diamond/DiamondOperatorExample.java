package java7.diamond;

import java.util.*;

/**
 * Java 7 Diamond Operator Example Demonstrates type inference in generics
 */
public class DiamondOperatorExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 7 Diamond Operator ===\n");

		System.out.println("1. List with Diamond Operator");
		System.out.println("----------------------------");
		// Java 7+: Type inferred from left side
		List<String> list = new ArrayList<>();
		list.add("Java");
		list.add("Python");
		list.add("C++");
		System.out.println("List: " + list);

		System.out.println("\n2. Map with Diamond Operator");
		System.out.println("---------------------------");
		Map<String, Integer> map = new HashMap<>();
		map.put("Java", 1995);
		map.put("Python", 1991);
		System.out.println("Map: " + map);

		System.out.println("\n3. Set with Diamond Operator");
		System.out.println("--------------------------");
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(2);
		set.add(3);
		System.out.println("Set: " + set);

		System.out.println("\n4. Nested Generics");
		System.out.println("-----------------");
		Map<String, List<String>> nestedMap = new HashMap<>();
		List<String> languages = new ArrayList<>();
		languages.add("Java");
		languages.add("Python");
		nestedMap.put("Languages", languages);
		System.out.println("Nested Map: " + nestedMap);

		System.out.println("\nComparison:");
		System.out.println("Before Java 7:");
		System.out.println("  List<String> list = new ArrayList<String>();");
		System.out.println("\nJava 7+ (Diamond Operator):");
		System.out.println("  List<String> list = new ArrayList<>();");

		System.out.println("\nKey Features:");
		System.out.println("- Type inference from context");
		System.out.println("- Less verbose code");
		System.out.println("- More readable");
		System.out.println("- Works with nested generics");

		System.out.println("\nBenefits:");
		System.out.println("- Less repetition");
		System.out.println("- Cleaner code");
		System.out.println("- Type safety maintained");
		System.out.println("- Easier to read");
	}
}

