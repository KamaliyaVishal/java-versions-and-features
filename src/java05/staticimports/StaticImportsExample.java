package java5.staticimports;

import static java.lang.Math.*;
import static java.lang.System.out;

/**
 * Java 5 Static Imports Example Demonstrates importing static members
 */
public class StaticImportsExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 5 Static Imports ===\n");

		System.out.println("1. Math Functions (without Math prefix)");
		System.out.println("--------------------------------------");
		double sqrtResult = sqrt(16); // Instead of Math.sqrt(16)
		double powResult = pow(2, 3); // Instead of Math.pow(2, 3)
		double sinResult = sin(PI / 2); // Instead of Math.sin(Math.PI / 2)
		out.println("sqrt(16) = " + sqrtResult);
		out.println("pow(2, 3) = " + powResult);
		out.println("sin(PI/2) = " + sinResult);

		System.out.println("\n2. Constants");
		System.out.println("-----------");
		out.println("PI = " + PI);
		out.println("E = " + E);

		System.out.println("\n3. Comparison");
		System.out.println("------------");
		System.out.println("Without static import:");
		System.out.println("  double result = Math.sqrt(16);");
		System.out.println("\nWith static import:");
		System.out.println("  import static java.lang.Math.*;");
		System.out.println("  double result = sqrt(16);");

		System.out.println("\nKey Features:");
		System.out.println("- Import static members");
		System.out.println("- Eliminates class name prefix");
		System.out.println("- More readable code");
		System.out.println("- Convenient for constants and utility methods");

		System.out.println("\nBenefits:");
		System.out.println("- Less verbose code");
		System.out.println("- More readable");
		System.out.println("- Convenient for frequently used static members");

		System.out.println("\nBest Practices:");
		System.out.println("- Use sparingly");
		System.out.println("- Avoid name conflicts");
		System.out.println("- Import only what you need");
		System.out.println("- Don't overuse");
	}
}

