package java7.nio2;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import java.util.List;

/**
 * Java 7 NIO.2 Example Demonstrates improved file I/O operations
 */
public class NIO2Example
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 7 NIO.2 ===\n");

		String fileName = "java7_nio2_example.txt";

		System.out.println("1. Path Operations");
		System.out.println("-----------------");
		Path path = Paths.get(fileName);
		System.out.println("Path: " + path);
		System.out.println("Absolute path: " + path.toAbsolutePath());
		System.out.println("File name: " + path.getFileName());
		System.out.println("Parent: " + path.getParent());

		System.out.println("\n2. File Operations");
		System.out.println("----------------");
		try
		{
			// Create file
			if (!Files.exists(path))
			{
				Files.createFile(path);
				System.out.println("File created: " + path);
			}

			// Write to file
			String content = "Hello, NIO.2!\nThis is a test file.";
			Files.write(path, content.getBytes());
			System.out.println("Content written to file");

			// Read from file
			byte[] bytes = Files.readAllBytes(path);
			System.out.println("Read bytes: " + bytes.length);

			// Read all lines
			List<String> lines = Files.readAllLines(path);
			System.out.println("Lines read:");
			for (String line : lines)
			{
				System.out.println("  " + line);
			}

			// File attributes
			System.out.println("\n3. File Attributes");
			System.out.println("-----------------");
			BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
			System.out.println("Size: " + attrs.size() + " bytes");
			System.out.println("Created: " + attrs.creationTime());
			System.out.println("Modified: " + attrs.lastModifiedTime());
			System.out.println("Is directory: " + attrs.isDirectory());
			System.out.println("Is regular file: " + attrs.isRegularFile());

			// Delete file
			Files.delete(path);
			System.out.println("\nFile deleted");

		}
		catch (IOException e)
		{
			System.err.println("Error: " + e.getMessage());
		}

		System.out.println("\nKey Features:");
		System.out.println("- Path API for file paths");
		System.out.println("- Files utility class");
		System.out.println("- Directory watching");
		System.out.println("- Symbolic links support");
		System.out.println("- Better file operations");

		System.out.println("\nNIO.2 Components:");
		System.out.println("- Path: Represents file system path");
		System.out.println("- Files: Utility class for file operations");
		System.out.println("- Paths: Factory for creating Path objects");
		System.out.println("- FileSystem: File system abstraction");

		System.out.println("\nBenefits:");
		System.out.println("- Better file operations");
		System.out.println("- More intuitive API");
		System.out.println("- Symbolic link support");
		System.out.println("- Directory watching");
		System.out.println("- Better performance");
	}
}

