package java8.functionalinterfaces.advanced;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * UnaryOperator and BinaryOperator Demo
 * 
 * - UnaryOperator<T>: Extends Function<T, T>
 *   - Represents an operation on a single operand that produces a result of the same type
 *   - Abstract method: T apply(T t)
 * 
 * - BinaryOperator<T>: Extends BiFunction<T, T, T>
 *   - Represents an operation upon two operands of the same type, producing a result of the same type
 *   - Abstract method: T apply(T t1, T t2)
 * 
 * Common use cases:
 * - UnaryOperator: Transformations (square, negate, increment, etc.)
 * - BinaryOperator: Reductions (sum, max, min, concatenate, etc.)
 */
public class _02_UnaryBinaryOperator
{
	public static void main(String[] args)
	{
		System.out.println("=== UNARY AND BINARY OPERATOR DEMO ===\n");

		demonstrateUnaryOperator();
		demonstrateBinaryOperator();
		demonstrateOperatorWithStreams();
		demonstrateRealWorldExamples();
	}

	/**
	 * Demonstrates UnaryOperator - single operand, same type result
	 */
	private static void demonstrateUnaryOperator()
	{
		System.out.println("1. UnaryOperator Examples:");

		// Square a number
		UnaryOperator<Integer> square = x -> x * x;
		System.out.println("  Square of 5: " + square.apply(5));
		System.out.println("  Square of 8: " + square.apply(8));

		// Negate a number
		UnaryOperator<Integer> negate = x -> -x;
		System.out.println("  Negate of 10: " + negate.apply(10));

		// Increment by 1
		UnaryOperator<Integer> increment = x -> x + 1;
		System.out.println("  Increment 5: " + increment.apply(5));

		// Convert to uppercase (for strings)
		UnaryOperator<String> toUpperCase = String::toUpperCase;
		System.out.println("  Uppercase 'hello': " + toUpperCase.apply("hello"));

		// Chaining UnaryOperators
		UnaryOperator<Integer> multiplyBy2 = x -> x * 2;
		UnaryOperator<Integer> add10 = x -> x + 10;
		System.out.println("  Chain (5): multiply by 2, then add 10 = " + multiplyBy2.andThen(add10).apply(5));
		System.out.println();
	}

	/**
	 * Demonstrates BinaryOperator - two operands, same type result
	 */
	private static void demonstrateBinaryOperator()
	{
		System.out.println("2. BinaryOperator Examples:");

		// Addition
		BinaryOperator<Integer> add = (a, b) -> a + b;
		System.out.println("  Add (10, 20): " + add.apply(10, 20));

		// String concatenation
		BinaryOperator<String> concatenate = (s1, s2) -> s1 + " " + s2;
		System.out.println("  Concatenate: " + concatenate.apply("Hello", "World"));

		// Maximum value
		BinaryOperator<Integer> max = Integer::max; // Method reference
		System.out.println("  Max (15, 25): " + max.apply(15, 25));

		// Minimum value
		BinaryOperator<Integer> min = Integer::min;
		System.out.println("  Min (15, 25): " + min.apply(15, 25));

		// Multiplication
		BinaryOperator<Integer> multiply = (a, b) -> a * b;
		System.out.println("  Multiply (6, 7): " + multiply.apply(6, 7));

		// Static methods from BinaryOperator
		BinaryOperator<Integer> maxOperator = BinaryOperator.maxBy(Integer::compareTo);
		BinaryOperator<Integer> minOperator = BinaryOperator.minBy(Integer::compareTo);
		System.out.println("  MaxBy (30, 20): " + maxOperator.apply(30, 20));
		System.out.println("  MinBy (30, 20): " + minOperator.apply(30, 20));
		System.out.println();
	}

	/**
	 * Demonstrates using Operators with Streams API
	 */
	private static void demonstrateOperatorWithStreams()
	{
		System.out.println("3. Operators with Streams:");

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

		// Using UnaryOperator with map()
		UnaryOperator<Integer> square = x -> x * x;
		List<Integer> squared = numbers.stream()
			.map(square)
			.collect(Collectors.toList());
		System.out.println("  Original: " + numbers);
		System.out.println("  Squared: " + squared);

		// Using BinaryOperator with reduce()
		BinaryOperator<Integer> sum = (a, b) -> a + b;
		Integer total = numbers.stream()
			.reduce(0, sum);
		System.out.println("  Sum using reduce: " + total);

		// Using method references
		Integer product = numbers.stream()
			.reduce(1, (a, b) -> a * b);
		System.out.println("  Product: " + product);

		// Find maximum using BinaryOperator
		BinaryOperator<Integer> max = Integer::max;
		Integer maximum = numbers.stream()
			.reduce(max).orElse(0);
		System.out.println("  Maximum: " + maximum);
		System.out.println();
	}

	/**
	 * Real-world examples using UnaryOperator and BinaryOperator
	 */
	private static void demonstrateRealWorldExamples()
	{
		System.out.println("4. Real-World Examples:");

		// UnaryOperator: Format phone numbers
		UnaryOperator<String> formatPhoneNumber = phone -> {
			if (phone.length() == 10) {
				return "(" + phone.substring(0, 3) + ") " + 
				       phone.substring(3, 6) + "-" + 
				       phone.substring(6);
			}
			return phone;
		};
		System.out.println("  Format phone '1234567890': " + formatPhoneNumber.apply("1234567890"));

		// UnaryOperator: Calculate tax (add 10%)
		UnaryOperator<Double> addTax = price -> price * 1.10;
		System.out.println("  Price with tax (100.0): " + addTax.apply(100.0));

		// BinaryOperator: Calculate average
		BinaryOperator<Double> average = (a, b) -> (a + b) / 2;
		System.out.println("  Average (10.0, 20.0): " + average.apply(10.0, 20.0));

		// BinaryOperator: Combine lists (in real scenario, would merge lists)
		BinaryOperator<String> combinePaths = (path1, path2) -> path1 + "/" + path2;
		System.out.println("  Combine paths: " + combinePaths.apply("/home/user", "documents"));

		// BinaryOperator: Concatenate with separator
		BinaryOperator<String> joinWithDash = (s1, s2) -> s1 + " - " + s2;
		System.out.println("  Join with dash: " + joinWithDash.apply("First", "Second"));
	}
}
