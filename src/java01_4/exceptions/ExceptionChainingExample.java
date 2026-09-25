package java1_4.exceptions;

import java.io.IOException;

/**
 * Java 1.4 Exception Chaining Example Demonstrates exception chaining
 */
public class ExceptionChainingExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 1.4 Exception Chaining ===\n");

		try
		{
			processFile("nonexistent.txt");
		}
		catch (ProcessingException e)
		{
			System.out.println("Caught: " + e.getMessage());
			System.out.println("Original cause: " + e.getCause().getClass().getSimpleName());
			System.out.println("Cause message: " + e.getCause().getMessage());
			System.out.println("\nFull stack trace:");
			e.printStackTrace();
		}

		System.out.println("\nKey Features:");
		System.out.println("- Preserve exception history");
		System.out.println("- Better error diagnostics");
		System.out.println("- Cause tracking");
		System.out.println("- Improved debugging");

		System.out.println("\nBenefits:");
		System.out.println("- Maintains full exception chain");
		System.out.println("- Better error reporting");
		System.out.println("- Easier debugging");
		System.out.println("- Preserves original exception context");
	}

	private static void processFile(String filename) throws ProcessingException
	{
		try
		{
			// Simulate file operation that throws IOException
			throw new IOException("File not found: " + filename);
		}
		catch (IOException e)
		{
			// Chain the exception
			throw new ProcessingException("Failed to process file", e);
		}
	}
}

// Custom exception with chaining
class ProcessingException extends Exception
{
	public ProcessingException(String message, Throwable cause)
	{
		super(message, cause); // Chain the exception
	}
}

