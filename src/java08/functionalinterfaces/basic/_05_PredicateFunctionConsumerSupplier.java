package java8.functionalinterfaces.basic;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Predicate, Function, Consumer, and Supplier Demo
 * 
 * These are the basic functional interfaces in Java 8.
 * 
 * Common use cases:
 * - Filtering collections
 * - Transforming data
 * - Processing data
 * - Generating data
 */
public class _05_PredicateFunctionConsumerSupplier
{
	public static void main(String[] args)
	{
		Predicate<Integer> isEven = x -> x % 2 == 0;
		Function<Integer, Integer> squareFun = x -> x * x;
		Consumer<Integer> consumer = x -> System.out.println(x);
		Supplier<Integer> supplier = () -> 100;

		if (isEven.test(supplier.get()))
		{
			consumer.accept(squareFun.apply(supplier.get()));
		}

		// --- Predicate Example: String starts with "A" ---
		Predicate<String> startsWithA = s -> s != null && s.startsWith("A");
		System.out.println("Predicate: Does 'Apple' start with A? " + startsWithA.test("Apple"));
		System.out.println("Predicate: Does 'Banana' start with A? " + startsWithA.test("Banana"));

		// --- Function Example: Convert String to its Length ---
		Function<String, Integer> stringLengthFun = s -> s == null ? 0 : s.length();
		System.out.println("Function: Length of 'Lambda': " + stringLengthFun.apply("Lambda"));

		// --- Function Chaining: Square then convert to String ---
		Function<Integer, String> squareThenString = squareFun.andThen(i -> "Squared value: " + i);
		System.out.println(squareThenString.apply(12)); // Squared value: 144

		// --- Consumer Example: Print elements of an array ---
		Consumer<String[]> printArray = arr -> {
			System.out.print("Consumer: ");
			for (String s : arr) System.out.print(s + " ");
			System.out.println();
		};
		printArray.accept(new String[]{"Java", "Python", "C++"});

		// --- Consumer Chaining: Print & then print length ---
		Consumer<String> print = System.out::println;
		Consumer<String> printLen = s -> System.out.println("Length: " + (s == null ? 0 : s.length()));
		print.andThen(printLen).accept("Consumer Chaining");

		// --- Supplier Example: Generate random number ---
		Supplier<Double> randomSupplier = Math::random;
		System.out.println("Supplier: Random double: " + randomSupplier.get());

		// --- Supplier Example: Supply custom object ---
		Supplier<StringBuilder> sbSupplier = () -> new StringBuilder("Java8-");
		System.out.println("Supplier: " + sbSupplier.get().append("Rocks!"));

		// --- Realistic Example: Filter and print even numbers from array using Predicate & Consumer ---
		Integer[] numbers = {10, 13, 22, 35, 40};
		Predicate<Integer> evenPredicate = n -> n % 2 == 0;
		System.out.print("Even numbers: ");
		for (Integer n : numbers)
			if (evenPredicate.test(n)) consumer.accept(n);
		
		// --- Compose: Use Supplier, Function, Consumer together ---
		Supplier<String> nameSupplier = () -> "Functional Interface";
		Function<String, String> greetingFun = name -> "Hello, " + name.toUpperCase() + "!";
		Consumer<String> printGreeting = System.out::println;

		String name = nameSupplier.get();
		String greeting = greetingFun.apply(name);
		printGreeting.accept(greeting);

	}
}

