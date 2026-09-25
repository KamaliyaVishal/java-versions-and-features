package java8.programs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Grouping and Partitioning Examples using Java 8 Streams
 * 
 * Common interview questions:
 * - Group employees by department
 * - Partition numbers into even/odd
 * - Group words by length
 * - Count occurrences and group
 */
public class _05_GroupByAndPartition
{
	public static void main(String[] args)
	{
		System.out.println("=== GROUPING AND PARTITIONING EXAMPLES ===\n");

		demonstrateGroupingBy();
		demonstratePartitioningBy();
		demonstrateGroupingByWithDownstream();
		demonstrateRealWorldExamples();
	}

	/**
	 * Demonstrates basic groupingBy() operation
	 */
	private static void demonstrateGroupingBy()
	{
		System.out.println("1. Basic Grouping By:");

		// Group strings by length
		List<String> words = Arrays.asList("apple", "banana", "cat", "dog", "elephant");
		Map<Integer, List<String>> groupedByLength = words.stream()
			.collect(Collectors.groupingBy(String::length));

		System.out.println("  Words grouped by length:");
		groupedByLength.forEach((length, wordList) -> 
			System.out.println("    Length " + length + ": " + wordList));

		// Group numbers by whether they are even or odd
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Map<String, List<Integer>> evenOddGroup = numbers.stream()
			.collect(Collectors.groupingBy(n -> n % 2 == 0 ? "Even" : "Odd"));

		System.out.println("\n  Numbers grouped by Even/Odd:");
		evenOddGroup.forEach((type, numList) -> 
			System.out.println("    " + type + ": " + numList));
		System.out.println();
	}

	/**
	 * Demonstrates partitioningBy() - special case of grouping with boolean key
	 */
	private static void demonstratePartitioningBy()
	{
		System.out.println("2. Partitioning By:");

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		// Partition into even and odd
		Map<Boolean, List<Integer>> partitioned = numbers.stream()
			.collect(Collectors.partitioningBy(n -> n % 2 == 0));

		System.out.println("  Partitioned numbers (even/odd):");
		System.out.println("    Even: " + partitioned.get(true));
		System.out.println("    Odd: " + partitioned.get(false));

		// Partition by value greater than 5
		Map<Boolean, List<Integer>> partitionedByValue = numbers.stream()
			.collect(Collectors.partitioningBy(n -> n > 5));

		System.out.println("\n  Partitioned by > 5:");
		System.out.println("    Greater than 5: " + partitionedByValue.get(true));
		System.out.println("    Less than or equal to 5: " + partitionedByValue.get(false));
		System.out.println();
	}

	/**
	 * Demonstrates groupingBy with downstream collectors
	 */
	private static void demonstrateGroupingByWithDownstream()
	{
		System.out.println("3. Grouping By with Downstream Collectors:");

		List<String> words = Arrays.asList("apple", "banana", "apricot", "cherry", "date");

		// Group by first letter and count occurrences
		Map<Character, Long> groupedByFirstLetterCount = words.stream()
			.collect(Collectors.groupingBy(
				s -> s.charAt(0),
				Collectors.counting()
			));

		System.out.println("  Words grouped by first letter with count:");
		groupedByFirstLetterCount.forEach((letter, count) -> 
			System.out.println("    " + letter + ": " + count));

		// Group by length and get the longest word in each group
		Map<Integer, Optional<String>> longestInGroup = words.stream()
			.collect(Collectors.groupingBy(
				String::length,
				Collectors.maxBy(Comparator.comparing(String::length))
			));

		System.out.println("\n  Longest word in each length group:");
		longestInGroup.forEach((length, word) -> 
			System.out.println("    Length " + length + ": " + word.orElse("")));

		// Group by length and join words with comma
		Map<Integer, String> joinedByLength = words.stream()
			.collect(Collectors.groupingBy(
				String::length,
				Collectors.joining(", ")
			));

		System.out.println("\n  Words joined by length:");
		joinedByLength.forEach((length, joined) -> 
			System.out.println("    Length " + length + ": " + joined));
		System.out.println();
	}

	/**
	 * Real-world examples using grouping and partitioning
	 */
	private static void demonstrateRealWorldExamples()
	{
		System.out.println("4. Real-World Examples:");

		// Example: Group employees by department
		class Employee {
			String name;
			String department;
			double salary;

			Employee(String name, String department, double salary) {
				this.name = name;
				this.department = department;
				this.salary = salary;
			}

			@Override
			public String toString() {
				return name + " ($" + salary + ")";
			}
		}

		List<Employee> employees = Arrays.asList(
			new Employee("Alice", "IT", 75000),
			new Employee("Bob", "HR", 60000),
			new Employee("Charlie", "IT", 80000),
			new Employee("David", "Finance", 70000),
			new Employee("Eve", "IT", 72000),
			new Employee("Frank", "HR", 65000)
		);

		// Group employees by department
		Map<String, List<Employee>> employeesByDept = employees.stream()
			.collect(Collectors.groupingBy(e -> e.department));

		System.out.println("  Employees grouped by department:");
		employeesByDept.forEach((dept, empList) -> {
			System.out.println("    " + dept + ": " + empList);
		});

		// Calculate average salary by department
		Map<String, Double> avgSalaryByDept = employees.stream()
			.collect(Collectors.groupingBy(
				e -> e.department,
				Collectors.averagingDouble(e -> e.salary)
			));

		System.out.println("\n  Average salary by department:");
		avgSalaryByDept.forEach((dept, avgSalary) -> 
			System.out.println("    " + dept + ": $" + String.format("%.2f", avgSalary)));

		// Partition employees by salary >= 70000
		Map<Boolean, List<Employee>> partitionedBySalary = employees.stream()
			.collect(Collectors.partitioningBy(e -> e.salary >= 70000));

		System.out.println("\n  Employees partitioned by salary >= $70000:");
		System.out.println("    High Salary: " + partitionedBySalary.get(true));
		System.out.println("    Lower Salary: " + partitionedBySalary.get(false));
	}
}

