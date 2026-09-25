package java8.streams.operations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Demonstrates the use of Java 8 Stream intermediate operations.
 * <p>
 * This class provides examples of:
 * <ul>
 *   <li>Using {@code flatMap()} to flatten nested collections.</li>
 *   <li>Using {@code filter()} to filter elements based on a condition.</li>
 *   <li>Using {@code sorted()} to sort elements.</li>
 *   <li>Using {@code distinct()} to remove duplicate elements.</li>
 *   <li>Using {@code map()} to transform elements.</li>
 *   <li>Using {@code collect()} to collect the results into a collection.</li>
 * </ul>
 */
public class StreamIntermediateOperationsExample
{
	public static void main(String[] args)
	{
		// List of lists of names
		List<List<String>> listOfLists = Arrays.asList(
				Arrays.asList("Reflection", "Collection", "Stream"),
				Arrays.asList("Structure", "State", "Flow"),
				Arrays.asList("Sorting", "Mapping", "Reduction", "Stream")
		);
		System.out.println(listOfLists.stream()
				.flatMap(l -> l.stream())
				.filter(s -> s.startsWith("S"))
				.sorted()
				.distinct()
				.map(s -> s.toUpperCase())
				.collect(Collectors.toList()));
	}
}

