package java1.io;

import java.io.*;

/**
 * Java 1.0 Basic I/O Example Demonstrates file reading and writing
 */
public class FileIOExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 1.0 Basic I/O ===\n");

		String fileName = "java1_example.txt";

		System.out.println("1. Writing to File");
		System.out.println("-----------------");
		try
		{
			FileWriter writer = new FileWriter(fileName);
			writer.write("Hello, Java 1.0!\n");
			writer.write("This is a file I/O example.\n");
			writer.write("Java 1.0 introduced basic file operations.");
			writer.close();
			System.out.println("File written successfully: " + fileName);
		}
		catch (IOException e)
		{
			System.err.println("Error writing file: " + e.getMessage());
		}

		System.out.println("\n2. Reading from File");
		System.out.println("-------------------");
		try
		{
			FileReader reader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(reader);
			String line;
			System.out.println("File contents:");
			while ((line = br.readLine()) != null)
			{
				System.out.println("  " + line);
			}
			br.close();
		}
		catch (IOException e)
		{
			System.err.println("Error reading file: " + e.getMessage());
		}

		System.out.println("\n3. Console Output");
		System.out.println("---------------");
		System.out.println("Using System.out.println()");
		System.out.print("Using System.out.print() - no newline");
		System.out.println(" (continued)");
		System.err.println("Using System.err.println() for errors");

		System.out.println("\nKey Features:");
		System.out.println("- FileReader/FileWriter for character I/O");
		System.out.println("- BufferedReader for efficient reading");
		System.out.println("- Exception handling with try-catch");
		System.out.println("- Console I/O with System.out/System.err");
	}
}

