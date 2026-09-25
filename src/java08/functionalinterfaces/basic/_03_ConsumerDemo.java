package java8.functionalinterfaces.basic;

import javax.imageio.plugins.tiff.ExifInteroperabilityTagSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Consumer Functional Interface Demo
 * 
 * Consumer<T> represents an operation that accepts one argument and returns no result.
 * It has one abstract method: void accept(T t)
 * 
 * Common use cases:
 * - Printing values
 * - Processing data
 */
public class _03_ConsumerDemo
{
	public static void main(String[] args)
	{
		// Examples
		Consumer<String> printStmt = x -> System.out.println(x);
		printStmt.accept("Say Hello!");

		// Print list
		Consumer<List<Integer>> printList = li -> {
			for (Integer i : li)
				System.out.println(i);
		};

		// add grace marks to the list
		Consumer<List<Integer>> addGraceMark5 = li -> {
			for (Integer i : li)
				System.out.println(i + 5);
		};

		List<Integer> markList = Arrays.asList(45, 88, 25, 92, 33);
		System.out.println("Original marks");
		printList.accept(markList);
		System.out.println("After grace marks");
		addGraceMark5.accept(markList);

	}
}

