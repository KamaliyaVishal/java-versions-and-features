package java8.streams.basics;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Demonstrates basic usage and operations of Java 8 Streams.
 * <p>
 * Covers various ways to create streams from collections, arrays,
 * Stream static methods (of, iterate, generate), and demonstrates
 * key intermediate and terminal stream operations such as filter, map,
 * peek, skip, distinct, sorted, and collect.
 * <p>
 * Also includes examples that illustrate laziness of intermediate
 * operations, terminal operations, and the use of flatMap to flatten
 * nested collections.
 */

public class StreamDemo
{
	public static void main(String[] args)
	{
		//Ways to create stream
		
		//Example of stream from collection
		List<Integer> numList = Arrays.asList(3, 255, 22, 45, 367, 1, 52, 0);
		Stream<Integer> stream = numList.stream();

		//Example of stream from array
		String[] strAr = {"A", "B", "C"};
		Stream<String> stringStream = Arrays.stream(strAr);

		//Example of stream from of
		Stream<Integer> integerStream = Stream.of(12, 45, 36, -1, 75);

		//Example of stream from iterate
		Stream<Integer> integerStream1 = Stream.iterate(0, n -> n + 2).limit(10);
		System.out.println(integerStream1.collect(Collectors.toList()));

		//Example of stream from generate
		Stream<Integer> integerStream2 = Stream.generate(() -> (int) (Math.random() * 100)).limit(10);
		System.out.println(integerStream2.collect(Collectors.toList()));

		//Stream operations
		List<Integer> integerList = Stream
				.iterate(1, x -> x + 1)
				.limit(20)
				.filter(x -> x % 2 == 0)
				.map(x -> x / 3)
				.peek((x) -> System.out.print(x + " "))
				.skip(2)
				.distinct()
				.sorted((a, b) -> b - a)
				.toList();
		System.out.println("integerList : " + integerList);

		//Example stream operations are not performed before any Terminal operation
		List<String> strings = Arrays.asList("abc", "a", "nvdf", "ABC", "a");
		Stream<String> stringStream1 = strings.stream().filter(n -> {
			System.out.println(n);
			return n.length() > 2;
		});

		System.out.println();
		System.out.println(stringStream1.toList());

		//Example of stream from flatMap
		List<List<String>> listOfLists = Arrays.asList(
				Arrays.asList("Red", "Green", "Blue"),
				Arrays.asList("Dog", "Cat", "cat"),
				Arrays.asList("Java", "Python", "C++", "Java"));

		System.out.println(listOfLists.stream()
				.flatMap(ar -> ar.stream())
				.map(str -> str.toLowerCase())
				.toList());

		listOfLists.stream().forEach(System.out::println);

		//Example of stream from collect
		LinkedHashSet<List<String>> stringLinkedHashSet = listOfLists.stream().collect(Collectors.toCollection(()-> new LinkedHashSet<>()));
		System.out.println(stringLinkedHashSet);
	}
}

