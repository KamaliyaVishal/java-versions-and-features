package java5.varargs;

/**
 * Java 5 Varargs (Variable-Length Arguments) Example Demonstrates methods with variable number of arguments
 */
public class VarargsExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 5 Varargs (Variable-Length Arguments) ===\n");

		System.out.println("1. Basic Varargs");
		System.out.println("--------------");
		printNumbers(1, 2, 3);
		printNumbers(1, 2, 3, 4, 5);
		printNumbers(); // Zero arguments

		System.out.println("\n2. Varargs with Other Parameters");
		System.out.println("-------------------------------");
		printMessage("Numbers", 1, 2, 3, 4);
		printMessage("Values", 10, 20);

		System.out.println("\n3. Varargs with Different Types");
		System.out.println("-----------------------------");
		printStrings("Java", "Python", "C++");
		printStrings("Hello", "World");

		System.out.println("\n4. Varargs as Array");
		System.out.println("------------------");
		int[] numbers = {5, 10, 15, 20};
		printNumbers(numbers); // Can pass array

		System.out.println("\nKey Features:");
		System.out.println("- Variable number of arguments");
		System.out.println("- Treated as array");
		System.out.println("- Must be last parameter");
		System.out.println("- Can have other parameters before it");

		System.out.println("\nBenefits:");
		System.out.println("- Simplifies method calls");
		System.out.println("- Flexible number of arguments");
		System.out.println("- Less method overloading needed");
		System.out.println("- More convenient API");
	}

	// Varargs method
	public static void printNumbers(int... numbers)
	{
		if (numbers.length == 0)
		{
			System.out.println("No numbers provided");
			return;
		}
		System.out.print("Numbers: ");
		for (int num : numbers)
		{
			System.out.print(num + " ");
		}
		System.out.println();
	}

	// Varargs with other parameters
	public static void printMessage(String message, int... numbers)
	{
		System.out.print(message + ": ");
		for (int num : numbers)
		{
			System.out.print(num + " ");
		}
		System.out.println();
	}

	// Varargs with strings
	public static void printStrings(String... strings)
	{
		System.out.print("Strings: ");
		for (String str : strings)
		{
			System.out.print(str + " ");
		}
		System.out.println();
	}
}

