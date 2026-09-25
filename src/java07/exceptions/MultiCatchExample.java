package java7.exceptions;

import java.io.*;
import java.sql.SQLException;

/**
 * Java 7 Multi-Catch Example Demonstrates catching multiple exceptions in one catch block
 */
public class MultiCatchExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 7 Multi-Catch ===\n");

		System.out.println("1. Multi-Catch Example");
		System.out.println("---------------------");
		try
		{
			// Simulate operations that may throw different exceptions
			performIOOperation();
			performDatabaseOperation();
		}
		catch (IOException | SQLException e)
		{
			// Handle both exceptions the same way
			System.out.println("Caught exception: " + e.getClass().getSimpleName());
			System.out.println("Message: " + e.getMessage());
		}

		System.out.println("\n2. Multiple Exception Types");
		System.out.println("-------------------------");
		try
		{
			riskyOperation();
		}
		catch (IllegalArgumentException | NullPointerException | ArithmeticException e)
		{
			System.out.println("Caught: " + e.getClass().getSimpleName());
		}

		System.out.println("\n3. Comparison with Multiple Catch Blocks");
		System.out.println("---------------------------------------");
		System.out.println("Before Java 7:");
		System.out.println("  try { ... }");
		System.out.println("  catch (IOException e) { handle(e); }");
		System.out.println("  catch (SQLException e) { handle(e); }");
		System.out.println("\nJava 7+ (Multi-Catch):");
		System.out.println("  try { ... }");
		System.out.println("  catch (IOException | SQLException e) { handle(e); }");

		System.out.println("\nKey Features:");
		System.out.println("- Catch multiple exception types");
		System.out.println("- Same handling for multiple exceptions");
		System.out.println("- Less code duplication");
		System.out.println("- More readable");

		System.out.println("\nBenefits:");
		System.out.println("- Less boilerplate");
		System.out.println("- Cleaner code");
		System.out.println("- Easier maintenance");
		System.out.println("- More concise exception handling");
	}

	private static void performIOOperation() throws IOException
	{
		// Simulate IO operation
		throw new IOException("IO error occurred");
	}

	private static void performDatabaseOperation() throws SQLException
	{
		// Simulate database operation
		throw new SQLException("SQL error occurred");
	}

	private static void riskyOperation()
	{
		// This method may throw different exceptions
		throw new IllegalArgumentException("Invalid argument");
	}
}

