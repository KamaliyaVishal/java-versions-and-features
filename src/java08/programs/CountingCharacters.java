package java8.programs;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Counting Characters in a String
 * This program counts the occurrences of each character in a given string
 * and prints the result in a map.
 */

public class CountingCharacters
{
	public static void main(String[] args)
	{
		String input = "iFollowJavaTutorials";

		Map<Character, Long> characterLongMap = input 
				.chars() 															// Convert string to stream of integers (ASCII values)
				.mapToObj(c -> (char) c) 											// Convert stream of integers to stream of characters
				.collect(Collectors.groupingBy(x -> x, Collectors.counting())); 	// Group by character and count occurrences

		//Using Function.identity()(return t -> t) to group by character and count occurrences	
		Map<Character, Long> characterLongMap2 = input.chars()
				.mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		System.out.println(characterLongMap);	
		System.out.println(characterLongMap2);	
	}
}

