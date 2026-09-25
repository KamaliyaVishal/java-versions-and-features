package java8.programs;

import java.util.Arrays;
import java.util.Optional;

/**
 * Second Highest Number in an Array This program finds the second highest number in an array and prints the result.
 */
public class _06_SecondHighestNumberinanArray
{
	public static void main(String[] args)
	{
		int[] input = {5, 9, 2, 13, 42, 56, 24, 36};

		Optional<Integer> first = Arrays.stream(input)
				.boxed()
				.sorted((a, b) -> b - a)
				//or .sorted(Comparator.reverseOrder())
				.skip(1)
				.findFirst();

		System.out.println(first);

	}
}

