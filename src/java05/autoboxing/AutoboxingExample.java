package java5.autoboxing;

import java.util.*;

/**
 * Java 5 Autoboxing and Unboxing Example Demonstrates automatic conversion between primitives and wrappers
 */
public class AutoboxingExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 5 Autoboxing and Unboxing ===\n");

		System.out.println("1. Autoboxing (Primitive -> Wrapper)");
		System.out.println("-----------------------------------");
		Integer i = 10; // Autoboxing: int -> Integer
		Double d = 3.14; // Autoboxing: double -> Double
		Boolean b = true; // Autoboxing: boolean -> Boolean
		System.out.println("Integer: " + i);
		System.out.println("Double: " + d);
		System.out.println("Boolean: " + b);

		System.out.println("\n2. Unboxing (Wrapper -> Primitive)");
		System.out.println("------------------------------------");
		int intValue = i; // Unboxing: Integer -> int
		double doubleValue = d; // Unboxing: Double -> double
		boolean boolValue = b; // Unboxing: Boolean -> boolean
		System.out.println("int: " + intValue);
		System.out.println("double: " + doubleValue);
		System.out.println("boolean: " + boolValue);

		System.out.println("\n3. Autoboxing with Collections");
		System.out.println("----------------------------");
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1); // Autoboxing: int -> Integer
		numbers.add(2);
		numbers.add(3);
		System.out.println("List: " + numbers);
		int first = numbers.get(0); // Unboxing: Integer -> int
		System.out.println("First element: " + first);

		System.out.println("\n4. Arithmetic Operations");
		System.out.println("----------------------");
		Integer a = 10;
		Integer num2 = 20;
		Integer sum = a + num2; // Unboxing, addition, autoboxing
		System.out.println(a + " + " + num2 + " = " + sum);

		System.out.println("\n5. Comparison");
		System.out.println("------------");
		Integer x = 100;
		Integer y = 100;
		System.out.println("x == y: " + (x == y)); // May be true (cached values)
		System.out.println("x.equals(y): " + x.equals(y)); // Always true

		System.out.println("\nKey Features:");
		System.out.println("- Automatic conversion between primitives and wrappers");
		System.out.println("- Autoboxing: Primitive -> Wrapper");
		System.out.println("- Unboxing: Wrapper -> Primitive");
		System.out.println("- Works seamlessly with collections");

		System.out.println("\nBenefits:");
		System.out.println("- Convenient");
		System.out.println("- Less boilerplate code");
		System.out.println("- Works with collections");
		System.out.println("- More readable code");

		System.out.println("\nNote:");
		System.out.println("- Autoboxing/unboxing has performance cost");
		System.out.println("- Use primitives when performance is critical");
		System.out.println("- Use wrappers when null is needed or with collections");
	}
}

