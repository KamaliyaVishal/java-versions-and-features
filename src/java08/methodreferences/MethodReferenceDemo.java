package java8.methodreferences;

import java.util.*;
import java.util.function.*;

/**
 * Method References Demo
 * 
 * Method references provide a way to refer to methods by their name.
 * They are a concise way to pass methods as arguments.
 * 
 * Common use cases:
 * - Sorting collections
 * - Mapping collections
 * - Filtering collections
 */
class Person
{
	private String name;
	private Integer age;

	// Constructor with parameters
	public Person(String name, int age)
	{
		this.name = name;
		this.age = age;
	}

	// No-argument constructor (for constructor reference)
	public Person()
	{
		this.name = generateRandomName();
		this.age = new Random().nextInt(100);
	}

	public String getName()
	{
		return name;
	}

	public Integer getAge()
	{
		return age;
	}

	// Helper to generate random name
	private String generateRandomName()
	{
		Random ran = new Random();
		return ran.ints(97, 122 + 1)
				.limit(7)
				.collect(StringBuilder::new,
						StringBuilder::appendCodePoint,
						StringBuilder::append)
				.toString();
	}

	// Static method for comparison by name
	public static int compareByName(Person a, Person b)
	{
		return a.getName().compareTo(b.getName());
	}
}

// ComparisonProvider for instance method reference
class ComparisonProvider
{
	public int compareByAge(Person a, Person b)
	{
		return a.getAge().compareTo(b.getAge());
	}
}

public class MethodReferenceDemo
{
	// Utility to create a list of objects using a Supplier (constructor reference)
	public static <T> List<T> getObjectList(int length, Supplier<T> supplier)
	{
		List<T> list = new ArrayList<>();
		for (int i = 0; i < length; i++)
		{
			list.add(supplier.get());
		}
		return list;
	}

	public static void main(String[] args)
	{
		// Prepare list of Persons
		List<Person> people = Arrays.asList(
				new Person("Alice", 30),
				new Person("Bob", 25),
				new Person("Charlie", 35)
		);

		// 1. Static Method Reference: sort by name
		Collections.sort(people, Person::compareByName);
		System.out.println("Sorted by name (static method reference):");
		people.forEach(p -> System.out.println(p.getName() + " (" + p.getAge() + ")"));

		// 2. Instance Method Reference of a Particular Object: sort by age
		ComparisonProvider provider = new ComparisonProvider();
		Collections.sort(people, provider::compareByAge);
		System.out.println("\nSorted by age (instance method reference of particular object):");
		people.forEach(p -> System.out.println(p.getName() + " (" + p.getAge() + ")"));

		// 3. Instance Method Reference of an Arbitrary Object of a Particular Type
		List<String> names = Arrays.asList("Bob", "alice", "Charlie");
		Collections.sort(names, String::compareToIgnoreCase);
		System.out.println("\nSorted names (instance method reference of arbitrary object):");
		names.forEach(System.out::println);

		// 4. Constructor Reference: create random Person objects
		List<Person> randomPeople = getObjectList(3, Person::new);
		System.out.println("\nRandomly generated people (constructor reference):");
		randomPeople.forEach(p -> System.out.println(p.getName() + " (" + p.getAge() + ")"));
	}
}

