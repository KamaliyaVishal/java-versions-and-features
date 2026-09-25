package java1_2.strictfpdemo;

/**
 * Java 1.2 Strictfp Keyword Example Demonstrates consistent floating-point calculations
 */
public class StrictfpExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 1.2 Strictfp Keyword ===\n");

		Calculator calculator = new Calculator();
		double result = calculator.calculate(10.5, 3.14);
		System.out.println("Calculation result: " + result);

		StrictfpCalculator strictCalculator = new StrictfpCalculator();
		double strictResult = strictCalculator.calculate(10.5, 3.14);
		System.out.println("Strictfp calculation result: " + strictResult);

		System.out.println("\nKey Features:");
		System.out.println("- Ensures consistent floating-point results");
		System.out.println("- Platform-independent calculations");
		System.out.println("- IEEE 754 compliance");
		System.out.println("- Can be applied to classes, interfaces, and methods");

		System.out.println("\nWhen to use:");
		System.out.println("- Scientific calculations");
		System.out.println("- Financial applications");
		System.out.println("- When consistency across platforms is critical");
	}
}

// Regular class (platform-dependent floating-point)
class Calculator
{
	double calculate(double a, double b)
	{
		return a * b / 3.14159;
	}
}

// Strictfp class (platform-independent floating-point)
strictfp class StrictfpCalculator
{
	strictfp double calculate(double a, double b)
	{
		return a * b / 3.14159;
	}
}

