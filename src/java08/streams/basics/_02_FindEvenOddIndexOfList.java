package java8.streams.basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Demonstrates how to find even and odd numbers in a list and their indices.
 * <p>
 * This class provides examples of:
 * <ul>
 *   <li>Using {@code forEach()} to iterate over a list and perform operations on each element.</li>
 *   <li>Using {@code parallelStream()} to perform parallel operations on a list.</li>
 *   <li>Using {@code forEachOrdered()} to perform operations on a list in order.</li>
 * </ul>
 */
public class _02_FindEvenOddIndexOfList
{
	public static void main(String[] args)
	{
		ArrayList<Integer> integerArrayList = new ArrayList<>(List.of(5, 7, 9, 10, 2, 6, 3, 1));

		HashMap<Integer, Integer> oddListMap = new HashMap<>();
		HashMap<Integer, Integer> evenListMap = new HashMap<>();

		integerArrayList.forEach((num) -> {
			if (num % 2 == 0)
			{
				evenListMap.put(num, integerArrayList.indexOf(num));
			}
			else
			{
				oddListMap.put(num, integerArrayList.indexOf(num));
			}
		});

		System.out.println("oddList " + oddListMap);
		System.out.println("evenList " + evenListMap);

		integerArrayList.forEach(System.out::print);
		System.out.println();
		integerArrayList.parallelStream().forEach(System.out::print);
		System.out.println();
		integerArrayList.parallelStream().forEachOrdered(System.out::print);

		System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		System.out.println();
		// Using parallel stream
		numbers.parallelStream()
				.peek(n -> System.out.print("Processing: " + n +
						" on thread: " + Thread.currentThread().getName()))
				.forEach(System.out::println);

	}
}

