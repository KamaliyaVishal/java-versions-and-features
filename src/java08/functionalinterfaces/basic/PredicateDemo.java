package java8.functionalinterfaces.basic;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Predicate Functional Interface Demo
 *  
 * Predicate<T> represents a predicate (boolean-valued function) of one argument.
 * It has one abstract method: boolean test(T t)
 * 
 * Common use cases:
 * - Filtering collections
 * - Validating data
 * - Conditional checks in streams
 */
public class PredicateDemo
{
	public static void main(String[] args)
	{
		System.out.println("=== PREDICATE FUNCTIONAL INTERFACE DEMO ===\n");

		demonstrateBasicPredicate();
		demonstratePredicateChaining();
		demonstratePredicateWithStreams();
		demonstratePredicateStaticMethods();
	}

	/**
	 * Demonstrates basic Predicate usage with test() method
	 */
	private static void demonstrateBasicPredicate()
	{
		System.out.println("1. Basic Predicate - Testing if number is even:");
		
		// Predicate to check if a number is even
		Predicate<Integer> isEven = x -> x % 2 == 0;
		List<Integer> numberList = Arrays.asList(5, 0, 52120, -215, 0, 165468, 2561, 1323, 5444);

		for (Integer i : numberList)
		{
			System.out.println("  Number " + i + " is Even: " + isEven.test(i));
		}
		System.out.println();
	}

	/**
	 * Demonstrates Predicate chaining using and(), or(), negate()
	 */
	private static void demonstratePredicateChaining()
	{
		System.out.println("2. Predicate Chaining:");

		// Email validation using AND operator
		Predicate<String> containsAt = x -> x.contains("@");
		Predicate<String> containsDot = x -> x.contains(".");
		Predicate<String> emailValidator = containsAt.and(containsDot);

		String[] emails = {"a@gmail.com", "invalid.email", "test@domain", "valid@email.com"};
		for (String email : emails)
		{
			System.out.println("  " + email + " is valid email: " + emailValidator.test(email));
		}

		// Using OR operator
		Predicate<String> startsWithA = s -> s.startsWith("A");
		Predicate<String> startsWithB = s -> s.startsWith("B");
		Predicate<String> startsWithAOrB = startsWithA.or(startsWithB);

		String[] words = {"Apple", "Banana", "Cherry", "Apricot"};
		System.out.println("\n  Words starting with A or B:");
		for (String word : words)
		{
			if (startsWithAOrB.test(word))
			{
				System.out.println("    " + word);
			}
		}

		// Using negate() - opposite of the predicate
		Predicate<Integer> isEven = x -> x % 2 == 0;
		Predicate<Integer> isOdd = isEven.negate();
		
		System.out.println("\n  Using negate() to find odd numbers:");
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		numbers.forEach(n -> System.out.println("    " + n + " is odd: " + isOdd.test(n)));
		System.out.println();
	}

	/**
	 * Demonstrates Predicate usage with Streams API
	 */
	private static void demonstratePredicateWithStreams()
	{
		System.out.println("3. Predicate with Streams:");

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		// Filter even numbers
		Predicate<Integer> isEven = x -> x % 2 == 0;
		Predicate<Integer> greaterThanFive = x -> x > 5;
		
		List<Integer> filtered = numbers.stream()
			.filter(isEven.and(greaterThanFive))
			.collect(Collectors.toList());
		
		System.out.println("  Numbers that are even AND greater than 5: " + filtered);
		
		// Remove null values
		List<String> names = Arrays.asList("Alice", null, "Bob", null, "Charlie");
		Predicate<String> isNotNull = s -> s != null;
		List<String> validNames = names.stream()
			.filter(isNotNull)
			.collect(Collectors.toList());
		
		System.out.println("  Valid names (non-null): " + validNames);
		System.out.println();
	}

	/**
	 * Demonstrates Predicate static methods: isEqual()
	 */
	private static void demonstratePredicateStaticMethods()
	{
		System.out.println("4. Predicate Static Methods:");

		// Predicate.isEqual() - creates a predicate that tests if two arguments are equal
		Predicate<String> isHello = Predicate.isEqual("Hello");
		
		String[] testStrings = {"Hello", "World", "hello", "Hello"};
		System.out.println("  Testing strings against 'Hello':");
		for (String str : testStrings)
		{
			System.out.println("    '" + str + "' equals 'Hello': " + isHello.test(str));
		}
		
		// Combining with other predicates
		Predicate<String> isHelloAndNotEmpty = Predicate.<String>isEqual("Hello")
			.and(s -> !s.isEmpty());
		
		System.out.println("\n  Combined predicate (equals 'Hello' AND not empty):");
		for (String str : testStrings)
		{
			System.out.println("    '" + str + "': " + isHelloAndNotEmpty.test(str));
		}
	}
}
