package java8.programs;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Finding Duplicates in a String This program finds the duplicates in a given string and prints the result.
 */
public class _02_FindingDuplicates
{
	public static void main(String[] args)
	{
		String input = "iFollowJavaTutorials";

		//Approach 1
		List<Map.Entry<Character, Long>> collected = input.chars()
				.mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
				.collect(Collectors.filtering(x -> x.getValue() > 1, Collectors.toList()));

		System.out.println("Duplicates: " + collected);

		//Approach 2
		Map<Character, Long> characterLongMap = input.chars()
				.mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
				.filter(entry -> entry.getValue() > 1)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		System.out.println("Duplicates: " + characterLongMap.entrySet());

	}
}

