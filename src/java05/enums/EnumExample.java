package java5.enums;

/**
 * Java 5 Enumerations (Enum) Example Demonstrates type-safe constants
 */
public class EnumExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 5 Enumerations (Enum) ===\n");

		System.out.println("1. Basic Enum");
		System.out.println("------------");
		Day today = Day.MONDAY;
		System.out.println("Today: " + today);
		System.out.println("All days:");
		for (Day day : Day.values())
		{
			System.out.println("  - " + day);
		}

		System.out.println("\n2. Enum in Switch");
		System.out.println("---------------");
		switch (today)
		{
			case MONDAY:
			case TUESDAY:
			case WEDNESDAY:
			case THURSDAY:
			case FRIDAY:
				System.out.println("Weekday");
				break;
			case SATURDAY:
			case SUNDAY:
				System.out.println("Weekend");
				break;
		}

		System.out.println("\n3. Enum with Methods and Fields");
		System.out.println("-------------------------------");
		Planet earth = Planet.EARTH;
		System.out.println("Planet: " + earth);
		System.out.println("Mass: " + earth.getMass() + " kg");
		System.out.println("Radius: " + earth.getRadius() + " m");

		System.out.println("\n4. Enum with Constructor");
		System.out.println("----------------------");
		Status status = Status.ACTIVE;
		System.out.println("Status: " + status);
		System.out.println("Code: " + status.getCode());
		System.out.println("Description: " + status.getDescription());

		System.out.println("\nKey Features:");
		System.out.println("- Type-safe constants");
		System.out.println("- Can have methods and fields");
		System.out.println("- Switch statement support");
		System.out.println("- Better than public static final constants");

		System.out.println("\nBenefits:");
		System.out.println("- Type safety");
		System.out.println("- Can have behavior");
		System.out.println("- Compile-time checking");
		System.out.println("- More powerful than constants");
	}
}

// Basic enum
enum Day
{
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

// Enum with fields and methods
enum Planet
{
	MERCURY(3.303e+23, 2.4397e6),
	VENUS(4.869e+24, 6.0518e6),
	EARTH(5.976e+24, 6.37814e6),
	MARS(6.421e+23, 3.3972e6);

	private final double mass; // in kilograms
	private final double radius; // in meters

	Planet(double mass, double radius)
	{
		this.mass = mass;
		this.radius = radius;
	}

	public double getMass()
	{
		return mass;
	}

	public double getRadius()
	{
		return radius;
	}
}

// Enum with constructor and methods
enum Status
{
	ACTIVE(1, "Active status"),
	INACTIVE(0, "Inactive status"),
	PENDING(2, "Pending status");

	private final int code;
	private final String description;

	Status(int code, String description)
	{
		this.code = code;
		this.description = description;
	}

	public int getCode()
	{
		return code;
	}

	public String getDescription()
	{
		return description;
	}
}

