package java7.literals;

/**
 * Java 7 Binary Literals and Underscores in Numeric Literals Example
 */
public class NumericLiteralsExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 7 Binary Literals and Underscores ===\n");

		System.out.println("1. Binary Literals");
		System.out.println("----------------");
		int binary = 0b1010; // 10 in decimal
		int binary2 = 0b1111; // 15 in decimal
		long binaryLong = 0b1010L;
		System.out.println("0b1010 = " + binary);
		System.out.println("0b1111 = " + binary2);
		System.out.println("0b1010L = " + binaryLong);

		System.out.println("\n2. Underscores in Integer Literals");
		System.out.println("--------------------------------");
		int million = 1_000_000;
		int thousand = 1_000;
		System.out.println("1_000_000 = " + million);
		System.out.println("1_000 = " + thousand);

		System.out.println("\n3. Underscores in Long Literals");
		System.out.println("------------------------------");
		long creditCard = 1234_5678_9012_3456L;
		long phoneNumber = 555_123_4567L;
		System.out.println("Credit card: " + creditCard);
		System.out.println("Phone: " + phoneNumber);

		System.out.println("\n4. Underscores in Floating-Point Literals");
		System.out.println("-----------------------------------------");
		double pi = 3.14159_26535;
		float largeNumber = 1_234.56_78f;
		System.out.println("Pi: " + pi);
		System.out.println("Large number: " + largeNumber);

		System.out.println("\n5. Underscores in Binary Literals");
		System.out.println("--------------------------------");
		int binaryWithUnderscores = 0b1010_1111_0000_1111;
		System.out.println("0b1010_1111_0000_1111 = " + binaryWithUnderscores);

		System.out.println("\nKey Features:");
		System.out.println("- Binary literals with 0b prefix");
		System.out.println("- Underscores for readability");
		System.out.println("- No impact on value");
		System.out.println("- Works with all numeric types");

		System.out.println("\nBenefits:");
		System.out.println("- Better readability");
		System.out.println("- Easier to read large numbers");
		System.out.println("- Binary literal support");
		System.out.println("- More intuitive numeric constants");

		System.out.println("\nRules:");
		System.out.println("- Underscores can be placed anywhere in numeric literal");
		System.out.println("- Cannot be at start or end");
		System.out.println("- Cannot be adjacent to decimal point");
		System.out.println("- Cannot be adjacent to L or F suffix");
	}
}

