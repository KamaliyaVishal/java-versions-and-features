package java7.switchdemo;

/**
 * Java 7 Strings in Switch Example Demonstrates string-based switch statements
 */
public class StringSwitchExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 7 Strings in Switch ===\n");

		System.out.println("1. Basic String Switch");
		System.out.println("--------------------");
		String day = "MONDAY";
		switch (day)
		{
			case "MONDAY":
				System.out.println("Start of work week");
				break;
			case "TUESDAY":
			case "WEDNESDAY":
			case "THURSDAY":
				System.out.println("Mid week");
				break;
			case "FRIDAY":
				System.out.println("End of work week");
				break;
			case "SATURDAY":
			case "SUNDAY":
				System.out.println("Weekend");
				break;
			default:
				System.out.println("Unknown day");
		}

		System.out.println("\n2. String Switch with Method");
		System.out.println("---------------------------");
		System.out.println(getDayType("MONDAY"));
		System.out.println(getDayType("FRIDAY"));
		System.out.println(getDayType("SATURDAY"));

		System.out.println("\n3. Comparison with If-Else");
		System.out.println("-------------------------");
		System.out.println("If-Else approach:");
		System.out.println("  if (day.equals(\"MONDAY\")) { ... }");
		System.out.println("  else if (day.equals(\"TUESDAY\")) { ... }");
		System.out.println("\nSwitch approach (Java 7+):");
		System.out.println("  switch (day) {");
		System.out.println("      case \"MONDAY\": ... break;");
		System.out.println("  }");

		System.out.println("\nKey Features:");
		System.out.println("- String-based switch statements");
		System.out.println("- Cleaner than if-else chains");
		System.out.println("- Better performance than if-else");
		System.out.println("- Type-safe");

		System.out.println("\nBenefits:");
		System.out.println("- More readable");
		System.out.println("- Better performance");
		System.out.println("- Less error-prone");
		System.out.println("- Cleaner code");
	}

	private static String getDayType(String day)
	{
		switch (day)
		{
			case "MONDAY":
			case "TUESDAY":
			case "WEDNESDAY":
			case "THURSDAY":
			case "FRIDAY":
				return "Weekday";
			case "SATURDAY":
			case "SUNDAY":
				return "Weekend";
			default:
				return "Unknown";
		}
	}
}

