package java5.loops;

import java.util.*;

/**
 * Java 5 Enhanced For-Loop (For-Each) Example Demonstrates simplified iteration
 */
public class EnhancedForLoopExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 5 Enhanced For-Loop (For-Each) ===\n");

		System.out.println("1. Iterating over Arrays");
		System.out.println("----------------------");
		int[] numbers = {1, 2, 3, 4, 5};
		System.out.print("Numbers: ");
		for (int num : numbers)
		{
			System.out.print(num + " ");
		}
		System.out.println();

		System.out.println("\n2. Iterating over Collections");
		System.out.println("----------------------------");
		List<String> languages = new ArrayList<>();
		languages.add("Java");
		languages.add("Python");
		languages.add("C++");
		System.out.println("Languages:");
		for (String lang : languages)
		{
			System.out.println("  - " + lang);
		}

		System.out.println("\n3. Iterating over Maps");
		System.out.println("---------------------");
		Map<String, Integer> map = new HashMap<>();
		map.put("Java", 1995);
		map.put("Python", 1991);
		map.put("C++", 1985);
		System.out.println("Programming languages:");
		for (Map.Entry<String, Integer> entry : map.entrySet())
		{
			System.out.println("  " + entry.getKey() + " - " + entry.getValue());
		}

		System.out.println("\n4. Comparison with Traditional For-Loop");
		System.out.println("----------------------------------------");
		System.out.println("Traditional:");
		System.out.println("  for (int i = 0; i < array.length; i++) {");
		System.out.println("      System.out.println(array[i]);");
		System.out.println("  }");
		System.out.println("\nEnhanced:");
		System.out.println("  for (int num : array) {");
		System.out.println("      System.out.println(num);");
		System.out.println("  }");

		System.out.println("\nKey Features:");
		System.out.println("- Simpler syntax");
		System.out.println("- Less error-prone");
		System.out.println("- More readable");
		System.out.println("- Automatic iteration");

		System.out.println("\nBenefits:");
		System.out.println("- No index management");
		System.out.println("- No off-by-one errors");
		System.out.println("- Works with arrays and collections");
		System.out.println("- Cleaner code");
	}
}

