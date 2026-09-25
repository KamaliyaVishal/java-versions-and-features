package java1_4.assertions;

/**
 * Java 1.4 Assertions Example Demonstrates assertion usage
 * NOTE: Assertions are disabled by default. Enable with -ea flag:
 * java -ea AssertionsExample
 */
public class AssertionsExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 1.4 Assertions ===\n");

		System.out.println("NOTE: Assertions are disabled by default.");
		System.out.println("Enable with: java -ea AssertionsExample\n");

		int x = 10;
		int y = 5;

		// Simple assertion
		assert x > 0 : "x must be positive";
		System.out.println("Assertion passed: x is positive");

		// Assertion with calculation
		int result = divide(x, y);
		System.out.println("Division result: " + result);

		// Assertion for validation
		int[] array = {1, 2, 3, 4, 5};
		int index = 2;
		assert index >= 0 && index < array.length : "Index out of bounds";
		System.out.println("Array element at index " + index + ": " + array[index]);

		System.out.println("\nKey Features:");
		System.out.println("- Development-time testing");
		System.out.println("- Can be enabled/disabled");
		System.out.println("- Helps catch bugs early");
		System.out.println("- Not for production error handling");

		System.out.println("\nEnabling Assertions:");
		System.out.println("- Enable: java -ea AssertionsExample");
		System.out.println("- Disable: java -da AssertionsExample (default)");
		System.out.println("- Enable for specific package: java -ea:com.example...");

		System.out.println("\nBest Practices:");
		System.out.println("- Use for internal invariants");
		System.out.println("- Don't use for public method validation");
		System.out.println("- Don't have side effects in assertions");
		System.out.println("- Use for debugging and development");
	}

	private static int divide(int a, int b)
	{
		assert b != 0 : "Division by zero";
		return a / b;
	}
}

