package java8.functionalinterfaces.basic;

import java.util.function.Supplier;

/**
 * Supplier Functional Interface Demo
 * 
 * Supplier<T> represents a supplier of results.
 * It has one abstract method: T get()
 * 
 * Common use cases:
 * - Providing values
 * - Generating data
 */
public class _04_SupplierDemo
{
	public static void main(String[] args)
	{
		Supplier<Integer> supplier = () -> 100;
		System.out.println(supplier.get());

		// Supplier to provide current system time in milliseconds
		Supplier<Long> currentTimeSupplier = () -> System.currentTimeMillis();
		System.out.println("Current time (ms): " + currentTimeSupplier.get());

		// Supplier that generates a random double between 0 and 1
		Supplier<Double> randomDoubleSupplier = () -> Math.random();
		System.out.println("Random double: " + randomDoubleSupplier.get());

		// Supplier that returns a new String on every call
		Supplier<String> greetingSupplier = () -> "Hello, Supplier!";
		System.out.println(greetingSupplier.get());

		// Supplier that provides the default value for an empty input
		Supplier<String> defaultValueSupplier = () -> "Default Value";
		System.out.println("Default: " + defaultValueSupplier.get());

		// Supplier that constructs an object (instance of a class)
		Supplier<StringBuilder> stringBuilderSupplier = () -> new StringBuilder("Start-");
		StringBuilder sb = stringBuilderSupplier.get().append("Java8");
		System.out.println(sb);

		// Supplier with lambda to supply a constant single-use password
		Supplier<String> passwordSupplier = () -> "pa$$w0rd" + ((int)(Math.random() * 10000));
		System.out.println("Generated password: " + passwordSupplier.get());

		// Supplier that supplies the next even number using a mutable holder (not thread-safe)
		final int[] nextEven = {0};
		Supplier<Integer> evenNumberSupplier = () -> {
			nextEven[0] += 2;
			return nextEven[0];
		};
		System.out.println("First even: " + evenNumberSupplier.get());
		System.out.println("Second even: " + evenNumberSupplier.get());
	}
}

