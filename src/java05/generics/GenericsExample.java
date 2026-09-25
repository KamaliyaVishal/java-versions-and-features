package java5.generics;

import java.util.*;

/**
 * Java 5 Generics Example Demonstrates type-safe generic programming
 */
public class GenericsExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 5 Generics ===\n");

		System.out.println("1. Generic Class");
		System.out.println("---------------");
		Box<String> stringBox = new Box<>();
		stringBox.setItem("Hello, Generics!");
		System.out.println("String box: " + stringBox.getItem());

		Box<Integer> intBox = new Box<>();
		intBox.setItem(42);
		System.out.println("Integer box: " + intBox.getItem());

		System.out.println("\n2. Generic Methods");
		System.out.println("-----------------");
		Integer[] intArray = {1, 2, 3, 4, 5};
		String[] strArray = {"Java", "Python", "C++"};
		printArray(intArray);
		printArray(strArray);

		System.out.println("\n3. Generic Collections");
		System.out.println("--------------------");
		List<String> list = new ArrayList<>();
		list.add("Java");
		list.add("Python");
		list.add("C++");
		System.out.println("List: " + list);
		String first = list.get(0); // No casting needed
		System.out.println("First element: " + first);

		Map<String, Integer> map = new HashMap<>();
		map.put("Java", 1995);
		map.put("Python", 1991);
		System.out.println("Map: " + map);
		Integer year = map.get("Java"); // No casting needed
		System.out.println("Java year: " + year);

		System.out.println("\n4. Wildcards");
		System.out.println("-----------");
		List<Integer> integers = Arrays.asList(1, 2, 3);
		List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);
		printNumbers(integers);
		printNumbers(doubles);

		System.out.println("\nKey Features:");
		System.out.println("- Type safety at compile time");
		System.out.println("- Eliminates casting");
		System.out.println("- Better code reusability");
		System.out.println("- Wildcards for flexibility");

		System.out.println("\nBenefits:");
		System.out.println("- Catch type errors at compile time");
		System.out.println("- No runtime ClassCastException");
		System.out.println("- More readable code");
		System.out.println("- Better IDE support");
	}

	// Generic method
	public static <T> void printArray(T[] array)
	{
		for (T element : array)
		{
			System.out.println("  " + element);
		}
	}

	// Method with upper bounded wildcard
	public static void printNumbers(List<? extends Number> numbers)
	{
		for (Number num : numbers)
		{
			System.out.println("  " + num);
		}
	}
}

// Generic class
class Box<T>
{
	private T item;

	public void setItem(T item)
	{
		this.item = item;
	}

	public T getItem()
	{
		return item;
	}
}

