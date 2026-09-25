package java8.programs;

import java.util.Arrays;

/**
 * Longest String in an Array This program finds the longest string in an array and prints the result.
 */
public class _03_LongestString
{
	public static void main(String[] args)
	{
		String[] input = {"Vishal", "Kamaliya", "follow", "java8", "Microservice", "Springboot"};

		String loggestString = Arrays.stream(input)
				.reduce((a, b) -> a.length() > b.length() ? a : b)
				.orElse(null);

		System.out.println(loggestString);

	}
}

