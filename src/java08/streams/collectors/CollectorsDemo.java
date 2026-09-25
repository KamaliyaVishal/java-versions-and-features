package java8.streams.collectors;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Demonstrates the use of Java 8 Collectors to collect streams into various data structures.
 * <p>
 * This class provides examples of:
 * <ul>
 *   <li>Collecting streams into lists, sets, and specific collections using {@code toList()}, {@code toSet()}, and {@code toCollection()}.</li>
 *   <li>Joining strings using {@code joining()} with and without format.</li>
 *   <li>Summarizing statistics using {@code summarizingInt()}, {@code averagingInt()}, and {@code summingInt()}.</li>
 *   <li>Grouping elements by a property using {@code groupingBy()}, and partitioning elements using {@code partitioningBy()}.</li>
 *   <li>Mapping and collecting streams into maps using {@code toMap()}.</li>
 * </ul>
 */
public class CollectorsDemo
{
	public static void main(String[] args)
	{
		//1. Collecting to a list
		List<String> names = Arrays.asList("Apple", "Banana", "Orange", "Apple", "banana");
		List<String> stringList = names.stream().collect(Collectors.toList());
		System.out.println("Collecting to a list : " + stringList);
		System.out.println("OR Collecting to a list (Java 16) : " + names.stream().toList());

		//2. Collecting to a set
		Set<String> stringSet = names.stream().collect(Collectors.toSet());
		System.out.println("Collecting to a set :" + stringSet);

		//3. Collecting to a specific Collection
		ArrayDeque<String> stringArrayDeque = names.stream().collect(Collectors.toCollection(() -> new ArrayDeque<>()));
		System.out.println("Collecting to a specific Collection :" + stringArrayDeque);

		//4. Joining string
		String joinedString = names.stream().map(String::toUpperCase).collect(Collectors.joining());
		System.out.println("Collecting to a Joining string :" + joinedString);
		String formatJoinedString = names.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
		System.out.println("Collecting to a Joining string with format :" + formatJoinedString);

		//5. Summing data
		List<Integer> numList = Arrays.asList(55, 88, 352, 1, 0, 7, 89, 224, 12);
		IntSummaryStatistics intSummaryStatistics = numList.stream().collect(Collectors.summarizingInt(x -> x));
		System.out.println("Collecting to a Summing data :" + intSummaryStatistics);
		System.out.println("Collecting to a Summing data getAverage:" + intSummaryStatistics.getAverage());
		System.out.println("Collecting to a Summing data getCount:" + intSummaryStatistics.getCount());
		System.out.println("Collecting to a Summing data getMax:" + intSummaryStatistics.getMax());
		System.out.println("Collecting to a Summing data getMin:" + intSummaryStatistics.getMin());
		System.out.println("Collecting to a Summing data getSum:" + intSummaryStatistics.getSum());
		//OR direct way to achieve
		System.out.println("Collecting to a  averagingInt:" + numList.stream().collect(Collectors.averagingInt(x -> x)));
		System.out.println("Collecting to a  summingInt:" + numList.stream().collect(Collectors.summingInt(x -> x * 2)));
		System.out.println("mapToInt :" + (Integer) numList.stream().mapToInt(x -> x * 2).sum());

		//6. Grouping  element
		Map<Integer, List<String>> integerListMap = names.stream().collect(Collectors.groupingBy(x -> x.length()));
		System.out.println("Collecting and groupingBy:" + integerListMap);
		System.out.println("Collecting and groupingBy with joining:" + names.stream().collect(Collectors.groupingBy(String::length, Collectors.joining(", "))));
		System.out.println("Collecting and groupingBy with counting:" + names.stream().collect(Collectors.groupingBy(String::length, Collectors.counting())));
		TreeMap<Integer, String> integerStringTreeMap = names.stream().collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.joining(" | ")));
		System.out.println("Collecting and groupingBy with Supplier:" + integerStringTreeMap);

		//7. Partitioning element
		//Returns a Collector which partitions the input elements according to a Predicate
		Map<Boolean, List<String>> booleanListMap = names.stream().collect(Collectors.partitioningBy(x -> x.length() > 5));
		System.out.println("Collecting and partitioningBy:" + booleanListMap);

		//8. Mapping and collecting
		List<String> stringList1 = names.stream().map(String::toLowerCase).toList();
		System.out.println("Collecting and mapping:" + stringList1);

		//Examples 1: Collecting Names by length
		Map<Integer, List<String>> integerListMap1 = names.stream().collect(Collectors.groupingBy(String::length));
		System.out.println("Collecting Names by length : " + integerListMap1);

		//Example 2: Counting words occurrence
		String sentence = "hello world hello java world";
		Map<String, Long> wordCountMap = Arrays.stream(sentence.split(" ")).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
		System.out.println("Counting words occurrence : " + wordCountMap);

		//Example 3: Partitioning Even and odd numbers
		List<Integer> integerList = Arrays.asList(1, 2, 8, 4, 3, 5, 11, 63, 42, 78, 02);
		Map<Boolean, List<Integer>> evenOddPart = integerList.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0));
		System.out.println("Partitioning Even and odd numbers : " + evenOddPart);

		Map<String, List<Integer>> evenOddGroup = integerList.stream().collect(Collectors.groupingBy(x -> x % 2 == 0 ? "even" : "odd", Collectors.toList()));
		System.out.println("Grouping Even and odd numbers with : " + evenOddGroup);

		//Example 4: Summing values in a Map
		Map<String, Integer> items = new HashMap<>();
		items.put("Apple", 10);
		items.put("Apple", 45);
		items.put("Apple", 12);
		System.out.println("Summing values in a Map : " + items.values().stream().collect(Collectors.summingInt(x -> x)));
		System.out.println("Summing values in a Map by mapToInt: " + (Integer) items.values().stream().mapToInt(x -> x).sum());
		System.out.println("Summing values in a Map by reduce: " + items.values().stream().reduce(Integer::sum));

		//Example 5: Creating a map from stream
		Map<String, Integer> stringIntegerMap = names.stream().collect(Collectors.toMap(x -> x.toLowerCase(), x -> 1, Integer::sum));
		System.out.println("Creating a map from stream : " + stringIntegerMap);

	}
}

