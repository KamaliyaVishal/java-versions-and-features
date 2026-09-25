package java8.streams.operations;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Demonstrates the use of Java 8 Stream terminal operations.
 * <p>
 * This class provides examples of:
 * <ul>
 *   <li>Using {@code forEach()} to iterate over a stream.</li>
 *   <li>Using {@code collect()} to collect the results into a collection.</li>
 *   <li>Using {@code reduce()} to reduce the stream to a single value.</li>
 *   <li>Using {@code count()} to count the number of elements in a stream.</li>
 *   <li>Using {@code findFirst()} to find the first element in a stream.</li>
 *   <li>Using {@code allMatch()} to check if all elements match a condition.</li>
 *   <li>Using {@code anyMatch()} to check if any element matches a condition.</li>
 * </ul>
 */
public class _02_StreamTerminalOperationsExample
{
	public static void main(String[] args)
	{
		// Sample data
		List<String> names = Arrays.asList(
				"Reflection", "Collection", "Stream",
				"Structure", "Sorting", "State"
		);


		// forEach: Print each name
		System.out.println("forEach:");
		names.stream().forEach(System.out::println);

		// collect: Collect names starting with 'S' into a list
		List<String> sNames = names.stream()
				.filter(name -> name.startsWith("S"))
				.collect(Collectors.toList());
		System.out.println("\ncollect (names starting with 'S'):");
		sNames.forEach(System.out::println);

		// reduce: Concatenate all names into a single string
		String concatenatedNames = names.stream().reduce(
				"",
				(partialString, element) -> partialString + " " + element
		);
		System.out.println("\nreduce (concatenated names):");
		System.out.println(concatenatedNames.trim());

		// count: Count the number of names
		long count = names.stream().count();
		System.out.println("\ncount:");
		System.out.println(count);

		// findFirst: Find the first name
		Optional<String> firstName = names.stream().findFirst();
		System.out.println("\nfindFirst:");
		firstName.ifPresent(System.out::println);

		// allMatch: Check if all names start with 'S'
		boolean allStartWithS = names.stream().allMatch(
				name -> name.startsWith("S")
		);
		System.out.println("\nallMatch (all start with 'S'):");
		System.out.println(allStartWithS);

		// anyMatch: Check if any name starts with 'S'
		boolean anyStartWithS = names.stream().anyMatch(
				name -> name.startsWith("S")
		);
		System.out.println("\nanyMatch (any start with 'S'):");
		System.out.println(anyStartWithS);
	}
}

