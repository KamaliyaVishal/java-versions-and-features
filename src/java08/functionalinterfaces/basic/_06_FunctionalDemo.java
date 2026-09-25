package java8.functionalinterfaces.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Functional Demo
 * 
 * This class demonstrates the use of functional interfaces in Java 8.
 * 
 * Common use cases:
 * - Sorting collections
 * - Mapping collections
 */
public class _06_FunctionalDemo
{
	public static void main(String[] args)
	{
		//Example of list
		List<Integer> intList = new ArrayList<>();
		intList.add(5);
		intList.add(0);
		intList.add(22);
		intList.add(3248);
		intList.add(-1);
		//Default sorting ascending
		Collections.sort(intList);
		System.out.println("Ascending sorting intList : " + intList);

		//Descending sorting
		//Second arg of sort is Comparator, and Comparator is functional interface
		Collections.sort(intList, (a, b) -> b - a);
		System.out.println("Descending sorting intList via lambda expression : " + intList);

		//Example of Map
		Map<Integer, String> studentMap = new TreeMap<>();
		studentMap.put(101, "Student 3");
		studentMap.put(2, "Student 2");
		studentMap.put(-5, "Student 1");
		studentMap.put(152, "Student 4");
		System.out.println("Ascending sorting studentMap : " + studentMap);

		//Treemap constructor arg accept Comparator, and Comparator is functional interface
		Map<Integer, String> studentTreeMap = new TreeMap<>((a, b) -> b - a);
		studentTreeMap.put(101, "Student 3");
		studentTreeMap.put(2, "Student 2");
		studentTreeMap.put(-5, "Student 1");
		studentTreeMap.put(152, "Student 4");
		System.out.println("Descending sorting studentTreeMap via lambda expression : " + studentTreeMap);

	}
}

