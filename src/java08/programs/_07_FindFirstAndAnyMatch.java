package java8.programs;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * FindFirst, FindAny, AnyMatch, AllMatch, NoneMatch Examples
 * 
 * Common interview questions about:
 * - findFirst() vs findAny()
 * - Short-circuit operations
 * - Matching operations
 */
public class _07_FindFirstAndAnyMatch
{
	public static void main(String[] args)
	{
		System.out.println("=== FIND AND MATCH OPERATIONS ===\n");

		demonstrateFindFirst();
		demonstrateFindAny();
		demonstrateMatchOperations();
		demonstrateDifferenceBetweenFindFirstAndFindAny();
	}

	/**
	 * Demonstrates findFirst() - returns first element in encounter order
	 */
	private static void demonstrateFindFirst()
	{
		System.out.println("1. findFirst() Examples:");

		List<Integer> numbers = Arrays.asList(5, 3, 8, 2, 9, 1, 7);

		// Find first even number
		Optional<Integer> firstEven = numbers.stream()
			.filter(n -> n % 2 == 0)
			.findFirst();

		System.out.println("  First even number: " + firstEven.orElse(-1));

		// Find first number greater than 5
		Optional<Integer> firstGreaterThanFive = numbers.stream()
			.filter(n -> n > 5)
			.findFirst();

		System.out.println("  First number > 5: " + firstGreaterThanFive.orElse(-1));

		// Find first string starting with 'A'
		List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Anna");
		Optional<String> firstStartsWithA = names.stream()
			.filter(s -> s.startsWith("A"))
			.findFirst();

		System.out.println("  First name starting with 'A': " + firstStartsWithA.orElse("None"));
		System.out.println();
	}

	/**
	 * Demonstrates findAny() - returns any element (non-deterministic in parallel)
	 */
	private static void demonstrateFindAny()
	{
		System.out.println("2. findAny() Examples:");

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		// Find any even number (in sequential stream, usually returns first)
		Optional<Integer> anyEven = numbers.stream()
			.filter(n -> n % 2 == 0)
			.findAny();

		System.out.println("  Any even number (sequential): " + anyEven.orElse(-1));

		// Find any even number in parallel stream (may return any element)
		Optional<Integer> anyEvenParallel = numbers.parallelStream()
			.filter(n -> n % 2 == 0)
			.findAny();

		System.out.println("  Any even number (parallel): " + anyEvenParallel.orElse(-1));
		System.out.println("    Note: In parallel streams, findAny() may return different results");
		System.out.println();
	}

	/**
	 * Demonstrates match operations: anyMatch, allMatch, noneMatch
	 */
	private static void demonstrateMatchOperations()
	{
		System.out.println("3. Match Operations (anyMatch, allMatch, noneMatch):");

		List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10);

		// anyMatch - checks if any element matches the predicate
		boolean anyOdd = numbers.stream().anyMatch(n -> n % 2 != 0);
		boolean anyGreaterThanFive = numbers.stream().anyMatch(n -> n > 5);
		System.out.println("  anyMatch examples:");
		System.out.println("    Any odd number? " + anyOdd);
		System.out.println("    Any number > 5? " + anyGreaterThanFive);

		// allMatch - checks if all elements match the predicate
		boolean allEven = numbers.stream().allMatch(n -> n % 2 == 0);
		boolean allGreaterThanTen = numbers.stream().allMatch(n -> n > 10);
		System.out.println("\n  allMatch examples:");
		System.out.println("    All even? " + allEven);
		System.out.println("    All > 10? " + allGreaterThanTen);

		// noneMatch - checks if no elements match the predicate
		boolean noneOdd = numbers.stream().noneMatch(n -> n % 2 != 0);
		boolean noneGreaterThanTen = numbers.stream().noneMatch(n -> n > 10);
		System.out.println("\n  noneMatch examples:");
		System.out.println("    None odd? " + noneOdd);
		System.out.println("    None > 10? " + noneGreaterThanTen);

		// Practical example: Validate email list
		List<String> emails = Arrays.asList("user1@example.com", "user2@test.com", "invalid.email");
		boolean allValidEmails = emails.stream()
			.allMatch(e -> e.contains("@") && e.contains("."));
		System.out.println("\n  Email validation:");
		System.out.println("    All valid emails? " + allValidEmails);
		System.out.println();
	}

	/**
	 * Demonstrates the difference between findFirst() and findAny()
	 */
	private static void demonstrateDifferenceBetweenFindFirstAndFindAny()
	{
		System.out.println("4. Difference between findFirst() and findAny():");

		System.out.println("  Key Differences:");
		System.out.println("    - findFirst(): Always returns first element in encounter order");
		System.out.println("    - findAny(): Returns any element (non-deterministic in parallel)");
		System.out.println("    - findAny() is faster in parallel streams due to less synchronization");

		// Demonstrate with a large list
		List<Integer> largeList = IntStream.range(1, 1000000)
			.boxed()
			.collect(java.util.stream.Collectors.toList());

		long startTime = System.currentTimeMillis();
		Optional<Integer> first = largeList.stream()
			.filter(n -> n % 1000 == 0)
			.findFirst();
		long findFirstTime = System.currentTimeMillis() - startTime;

		startTime = System.currentTimeMillis();
		Optional<Integer> any = largeList.parallelStream()
			.filter(n -> n % 1000 == 0)
			.findAny();
		long findAnyTime = System.currentTimeMillis() - startTime;

		System.out.println("\n  Performance comparison (large list):");
		System.out.println("    findFirst() time: " + findFirstTime + "ms, result: " + first.orElse(-1));
		System.out.println("    findAny() time (parallel): " + findAnyTime + "ms, result: " + any.orElse(-1));
		System.out.println("\n  When to use:");
		System.out.println("    - Use findFirst() when order matters");
		System.out.println("    - Use findAny() in parallel streams when any match is sufficient");
	}
}

