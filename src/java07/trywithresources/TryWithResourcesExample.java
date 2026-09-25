package java7.trywithresources;

import java.io.*;

/**
 * Java 7 Try-with-Resources Example Demonstrates automatic resource management
 */
public class TryWithResourcesExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 7 Try-with-Resources ===\n");

		System.out.println("1. Single Resource");
		System.out.println("---------------");
		String fileName = "java7_example.txt";

		// Create a test file
		try (FileWriter writer = new FileWriter(fileName))
		{
			writer.write("Hello, Java 7 Try-with-Resources!");
		}
		catch (IOException e)
		{
			System.err.println("Error writing file: " + e.getMessage());
		}

		// Read the file
		try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
		{
			String line;
			while ((line = br.readLine()) != null)
			{
				System.out.println("Read: " + line);
			}
		}
		catch (IOException e)
		{
			System.err.println("Error reading file: " + e.getMessage());
		}

		System.out.println("\n2. Multiple Resources");
		System.out.println("-------------------");
		try (FileInputStream fis = new FileInputStream(fileName);
				BufferedInputStream bis = new BufferedInputStream(fis);
				DataInputStream dis = new DataInputStream(bis))
		{
			byte[] buffer = new byte[1024];
			int bytesRead = bis.read(buffer);
			System.out.println("Bytes read: " + bytesRead);
		}
		catch (IOException e)
		{
			System.err.println("Error: " + e.getMessage());
		}

		System.out.println("\n3. Custom Resource");
		System.out.println("-----------------");
		try (MyResource resource = new MyResource())
		{
			resource.doSomething();
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}

		System.out.println("\nKey Features:");
		System.out.println("- Automatic resource cleanup");
		System.out.println("- Resources implement AutoCloseable");
		System.out.println("- Less boilerplate code");
		System.out.println("- Prevents resource leaks");

		System.out.println("\nBenefits:");
		System.out.println("- No need for explicit finally blocks");
		System.out.println("- Automatic cleanup");
		System.out.println("- Cleaner code");
		System.out.println("- Prevents resource leaks");

		// Clean up test file
		new File(fileName).delete();
	}
}

// Custom resource implementing AutoCloseable
class MyResource implements AutoCloseable
{
	public MyResource()
	{
		System.out.println("Resource opened");
	}

	public void doSomething()
	{
		System.out.println("Doing something with resource");
	}

	@Override
	public void close() throws Exception
	{
		System.out.println("Resource closed automatically");
	}
}

