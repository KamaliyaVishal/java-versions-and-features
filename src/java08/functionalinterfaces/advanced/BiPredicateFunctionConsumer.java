package java8.functionalinterfaces.advanced;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * BiPredicate, BiFunction, and BiConsumer Demo
 * 
 * These are two-parameter versions of Predicate, Function, and Consumer:
 * 
 * - BiPredicate<T, U>: boolean test(T t, U u) - tests two arguments
 * - BiFunction<T, U, R>: R apply(T t, U u) - takes two arguments, returns a result
 * - BiConsumer<T, U>: void accept(T t, U u) - consumes two arguments, no return
 * 
 * Note: There is no BiSupplier because a function can only return one value.
 */
public class BiPredicateFunctionConsumer
{
	public static void main(String[] args)
	{
		System.out.println("=== BI-PREDICATE, BI-FUNCTION, BI-CONSUMER DEMO ===\n");

		demonstrateBiPredicate();
		demonstrateBiFunction();
		demonstrateBiConsumer();
		demonstrateRealWorldExamples();
	}

	/**
	 * Demonstrates BiPredicate - tests two arguments and returns boolean
	 */
	private static void demonstrateBiPredicate()
	{
		System.out.println("1. BiPredicate Examples:");

		// Check if both numbers are even
		BiPredicate<Integer, Integer> bothEven = (x, y) -> x % 2 == 0 && y % 2 == 0;
		System.out.println("  Both (2, 8) are even: " + bothEven.test(2, 8));
		System.out.println("  Both (2, 7) are even: " + bothEven.test(2, 7));

		// Check if first number is greater than second
		BiPredicate<Integer, Integer> isFirstGreater = (x, y) -> x > y;
		System.out.println("  10 > 5: " + isFirstGreater.test(10, 5));

		// Combining BiPredicates
		BiPredicate<Integer, Integer> bothEvenAndFirstGreater = bothEven.and(isFirstGreater);
		System.out.println("  Both (8, 4) are even AND 8 > 4: " + bothEvenAndFirstGreater.test(8, 4));

		// String comparison
		BiPredicate<String, String> reallyEqual = (s1, s2) -> s1.equals(s2);
		BiPredicate<String, String> reallyIgnoreCase = (s1, s2) -> s1.equalsIgnoreCase(s2);
		System.out.println("  'Hello' equals 'hello': " + reallyEqual.test("Hello", "hello"));
		System.out.println("  'Hello' equalsIgnoreCase 'hello': " + reallyIgnoreCase.test("Hello", "hello"));
		System.out.println();
	}

	/**
	 * Demonstrates BiFunction - takes two arguments and returns a result
	 */
	private static void demonstrateBiFunction()
	{
		System.out.println("2. BiFunction Examples:");

		// Calculate sum of string lengths
		BiFunction<String, String, Integer> totalLength = (x, y) -> x.length() + y.length();
		System.out.println("  Total length of 'Hello' and 'World': " + totalLength.apply("Hello", "World fast"));

		// Concatenate two strings
		BiFunction<String, String, String> concatenate = (s1, s2) -> s1 + " " + s2;
		System.out.println("  Concatenate: " + concatenate.apply("Hello", "World"));

		// Mathematical operations
		BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
		BiFunction<Integer, Integer, Double> divide = (a, b) -> (double) a / b;
		System.out.println("  Multiply (5, 6): " + multiply.apply(5, 6));
		System.out.println("  Divide (15, 4): " + divide.apply(15, 4));

		// Combining BiFunctions using andThen()
		BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
		BiFunction<Integer, Integer, String> addAndConvert = add.andThen(String::valueOf);
		System.out.println("  Add and convert to string (5, 3): " + addAndConvert.apply(5, 3));
		System.out.println();
	}

	/**
	 * Demonstrates BiConsumer - consumes two arguments without returning anything
	 */
	private static void demonstrateBiConsumer()
	{
		System.out.println("3. BiConsumer Examples:");

		// Simple addition and print
		BiConsumer<Integer, Integer> printSum = (x, y) -> {
			System.out.println("  Sum of " + x + " and " + y + " is: " + (x + y));
		};
		printSum.accept(4, 6);

		// Map operations
		Map<String, Integer> map = new HashMap<>();
		BiConsumer<String, Integer> mapPut = map::put; // Method reference
		mapPut.accept("Apple", 10);
		mapPut.accept("Banana", 20);
		mapPut.accept("Cherry", 30);
		System.out.println("  Map after using BiConsumer: " + map);

		// Chaining BiConsumers
		BiConsumer<String, Integer> print = (k, v) -> System.out.println("  Key: " + k + ", Value: " + v);
		BiConsumer<String, Integer> printUpper = (k, v) -> System.out.println("  KEY: " + k.toUpperCase() + ", VALUE: " + v);
		BiConsumer<String, Integer> combined = print.andThen(printUpper);
		combined.accept("Test", 100);
		System.out.println();
	}

	/**
	 * Real-world examples using BiPredicate, BiFunction, and BiConsumer
	 */
	private static void demonstrateRealWorldExamples()
	{
		System.out.println("4. Real-World Examples:");

		// BiPredicate: Validate age range for employee eligibility
		BiPredicate<Integer, Integer> isEligibleForPosition = (age, experience) -> 
			age >= 18 && age <= 65 && experience >= 2;
		System.out.println("  Employee (25, 3): Eligible = " + isEligibleForPosition.test(25, 3));
		System.out.println("  Employee (17, 5): Eligible = " + isEligibleForPosition.test(17, 5));

		// BiFunction: Calculate discount based on price and quantity
		BiFunction<Double, Integer, Double> calculateDiscount = (price, quantity) -> {
			double total = price * quantity;
			if (quantity > 10) return total * 0.15; // 15% discount
			if (quantity > 5) return total * 0.10;  // 10% discount
			return total * 0.05;                     // 5% discount
		};
		System.out.println("  Discount for (100.0, 12): " + calculateDiscount.apply(100.0, 12));
		System.out.println("  Discount for (100.0, 7): " + calculateDiscount.apply(100.0, 7));

		// BiConsumer: Log operations (username, action)
		BiConsumer<String, String> logUserAction = (username, action) -> {
			System.out.println("  [LOG] User: " + username + ", Action: " + action + ", Time: " + System.currentTimeMillis());
		};
		logUserAction.accept("john.doe", "LOGIN");
		logUserAction.accept("jane.smith", "LOGOUT");
	}
}

