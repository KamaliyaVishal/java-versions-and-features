package java8.foundation;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates the use of Lambda expressions in Java 8.
 * <p>
 * This class provides examples of:
 * <ul>
 *   <li>Using {@code ZeroParam} to create a Lambda expression with no parameters.</li>
 *   <li>Using {@code SingleParam} to create a Lambda expression with a single parameter.</li>
 *   <li>Using {@code MultiParam} to create a Lambda expression with multiple parameters.</li>
 *   <li>Using {@code GenericFunction} to create a Lambda expression with a generic parameter.</li>
 * </ul>
 */
@FunctionalInterface
interface ZeroParam
{
	void execute();
}

@FunctionalInterface
interface SingleParam<T>
{
	void execute(T t);
}

@FunctionalInterface
interface MultiParam<T>
{
	T operate(T a, T b);
}

@FunctionalInterface
interface GenericFunction<T, R>
{
	R execute(T t);
}

public class LambdaDemo
{
	public static void main(String[] args)
	{
		// 1. Zero Parameter Lambda
		ZeroParam greet = () -> System.out.println("Hello, Lambda!");
		greet.execute();

		// 2. Single Parameter Lambda
		SingleParam<String> printer = (msg) -> System.out.println("Message: " + msg);
		printer.execute("Learning Lambdas");

		// 3. Multiple Parameter Lambda with block
		MultiParam<Integer> add = (a, b) -> {
			System.out.println("Adding " + a + " and " + b);
			return a + b;
		};
		System.out.println("Sum: " + add.operate(5, 3));

		// 4. Generic Function with Lambda
		GenericFunction<Integer, String> converter = (num) -> String.valueOf(num * 2);
		System.out.println("Converted: " + converter.execute(10));

		// 5. Lambda with Collections
		List<String> names = new ArrayList<>();
		names.add("Alice");
		names.add("Bob");
		names.add("Charlie");

		// Using forEach with lambda
		System.out.println("\nPrinting names:");
		names.forEach(name -> System.out.println(name));

		// 6. Lambda with conditional operation
		System.out.println("\nNames with length > 4:");
		names.forEach(name -> {
			if (name.length() > 4)
			{
				System.out.println(name);
			}
		});

		// 7. Method reference (alternative to lambda)
		System.out.println("\nUsing method reference:");
		names.forEach(System.out::println);
	}
}

