package java8.programs;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Remove Duplicate Characters in a String
 * This program removes duplicate characters from a given string
 * and prints the result.
 */
public class _04_RemoveDuplicateCharacter
{
	public static void main(String[] args)
	{
		List<String> strList = Arrays.asList("Hello", "World");

		String result = strList.stream()
				.map(String::toLowerCase)           					// Convert to lowercase
				.flatMap(str -> str.chars()        						// Convert to char stream
						.distinct()                     				// Remove duplicates within each string
						.mapToObj(ch -> String.valueOf((char) ch))
				)
				.distinct()                         					// Remove duplicates across strings
				.collect(Collectors.joining());     					// Join characters into single string

		System.out.println(result);  // Output: helowrd

		String result2 = strList.stream()
				.map(String::toLowerCase)
				.flatMapToInt(String::chars)
				.distinct()
				.mapToObj(ch -> String.valueOf((char) ch))
				.collect(Collectors.joining());

		System.out.println(result2);  // Output: helowrd
	}
}

