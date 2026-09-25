package java9.trywithresources;

import java.io.*;

/**
 * Java 9 Try-With-Resources Enhancement Demonstrates effectively final variables in try-with-resources
 */
public class TryWithResourcesEnhancement
{
	public static void main(String[] args)
	{
		// Java 9 - effectively final variables can be used directly
		BufferedReader reader = new BufferedReader(new StringReader("Hello, World!"));
		try (reader)
		{  // Direct use - no need for extra variable
			String line = reader.readLine();
			System.out.println("Read: " + line);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		// Multiple resources with effectively final variables
		BufferedReader reader2 = new BufferedReader(new StringReader("Line 1"));
		PrintWriter writer = new PrintWriter(new StringWriter());
		try (reader2; writer)
		{  // Multiple resources separated by semicolon
			String line = reader2.readLine();
			writer.println("Processed: " + line);
			System.out.println("Processed successfully");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		// Before Java 9 - needed extra variable
		BufferedReader reader3 = new BufferedReader(new StringReader("Old way"));
		BufferedReader reader4 = reader3;  // Extra variable needed
		try (BufferedReader r = reader4)
		{
			String line = r.readLine();
			System.out.println("Old way: " + line);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

