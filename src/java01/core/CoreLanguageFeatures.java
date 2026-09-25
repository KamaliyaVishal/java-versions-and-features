package java1.core;

/**
 * Java 1.0 Core Language Features Demonstrates fundamental language constructs introduced in Java 1.0
 */
public class CoreLanguageFeatures
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 1.0 Core Language Features ===\n");

		System.out.println("1. Primitive Data Types");
		System.out.println("----------------------");
		byte b = 127;
		short s = 32767;
		int i = 2147483647;
		long l = 9223372036854775807L;
		float f = 3.14f;
		double d = 3.14159;
		char c = 'A';
		boolean bool = true;

		System.out.println("byte: " + b);
		System.out.println("short: " + s);
		System.out.println("int: " + i);
		System.out.println("long: " + l);
		System.out.println("float: " + f);
		System.out.println("double: " + d);
		System.out.println("char: " + c);
		System.out.println("boolean: " + bool);

		System.out.println("\n2. Control Flow - If-Else");
		System.out.println("------------------------");
		int number = 10;
		if (number > 0)
		{
			System.out.println("Number is positive");
		}
		else if (number < 0)
		{
			System.out.println("Number is negative");
		}
		else
		{
			System.out.println("Number is zero");
		}

		System.out.println("\n3. Control Flow - Switch");
		System.out.println("----------------------");
		int day = 3;
		switch (day)
		{
			case 1:
				System.out.println("Monday");
				break;
			case 2:
				System.out.println("Tuesday");
				break;
			case 3:
				System.out.println("Wednesday");
				break;
			default:
				System.out.println("Other day");
		}

		System.out.println("\n4. Control Flow - For Loop");
		System.out.println("-------------------------");
		System.out.print("Numbers 1-5: ");
		for (int j = 1; j <= 5; j++)
		{
			System.out.print(j + " ");
		}
		System.out.println();

		System.out.println("\n5. Control Flow - While Loop");
		System.out.println("---------------------------");
		int count = 0;
		System.out.print("Count 0-4: ");
		while (count < 5)
		{
			System.out.print(count + " ");
			count++;
		}
		System.out.println();

		System.out.println("\n6. Control Flow - Do-While Loop");
		System.out.println("------------------------------");
		int num = 0;
		System.out.print("Do-while 0-2: ");
		do
		{
			System.out.print(num + " ");
			num++;
		}
		while (num < 3);
		System.out.println();

		System.out.println("\n7. Arrays");
		System.out.println("--------");
		int[] numbers = {1, 2, 3, 4, 5};
		System.out.print("Array elements: ");
		for (int k = 0; k < numbers.length; k++)
		{
			System.out.print(numbers[k] + " ");
		}
		System.out.println();

		System.out.println("\n8. Exception Handling");
		System.out.println("--------------------");
		try
		{
			int result = 10 / 2;
			System.out.println("Division result: " + result);
		}
		catch (ArithmeticException e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		finally
		{
			System.out.println("Finally block executed");
		}

		System.out.println("\nKey Features:");
		System.out.println("- Platform independence");
		System.out.println("- Strong typing");
		System.out.println("- Exception handling");
		System.out.println("- Automatic garbage collection");
		System.out.println("- Object-oriented programming");
	}
}

