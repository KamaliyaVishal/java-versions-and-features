package java1_4.regex;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Java 1.4 Regular Expressions Example Demonstrates pattern matching with java.util.regex
 */
public class RegularExpressionsExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 1.4 Regular Expressions ===\n");

		String text = "The quick brown fox jumps over the lazy dog.";

		System.out.println("1. Pattern Matching");
		System.out.println("------------------");
		Pattern pattern = Pattern.compile("\\b\\w{4}\\b"); // 4-letter words
		Matcher matcher = pattern.matcher(text);
		System.out.println("Finding 4-letter words:");
		while (matcher.find())
		{
			System.out.println("  Found: " + matcher.group() + " at position " + matcher.start());
		}

		System.out.println("\n2. String Methods with Regex");
		System.out.println("---------------------------");
		String result = text.replaceAll("\\s+", " "); // Replace multiple spaces
		System.out.println("Original: " + text);
		System.out.println("Replaced: " + result);

		boolean matches = text.matches(".*fox.*");
		System.out.println("Contains 'fox': " + matches);

		System.out.println("\n3. Group Capturing");
		System.out.println("-----------------");
		String email = "user@example.com";
		Pattern emailPattern = Pattern.compile("(\\w+)@(\\w+\\.\\w+)");
		Matcher emailMatcher = emailPattern.matcher(email);
		if (emailMatcher.matches())
		{
			System.out.println("Full match: " + emailMatcher.group(0));
			System.out.println("Username: " + emailMatcher.group(1));
			System.out.println("Domain: " + emailMatcher.group(2));
		}

		System.out.println("\n4. Find and Replace");
		System.out.println("------------------");
		String text2 = "Java 1.4 was released in 2002";
		String replaced = text2.replaceAll("\\d+", "XXXX");
		System.out.println("Original: " + text2);
		System.out.println("Replaced: " + replaced);

		System.out.println("\n5. Split with Regex");
		System.out.println("------------------");
		String data = "apple,banana,cherry,date";
		String[] fruits = data.split(",");
		System.out.println("Split result:");
		for (String fruit : fruits)
		{
			System.out.println("  - " + fruit);
		}

		System.out.println("\nKey Features:");
		System.out.println("- Pattern compilation for efficiency");
		System.out.println("- Matcher for finding patterns");
		System.out.println("- String methods support regex");
		System.out.println("- Group capturing");
		System.out.println("- Replacement operations");

		System.out.println("\nCommon Patterns:");
		System.out.println("- \\d : Digit");
		System.out.println("- \\w : Word character");
		System.out.println("- \\s : Whitespace");
		System.out.println("- . : Any character");
		System.out.println("- * : Zero or more");
		System.out.println("- + : One or more");
		System.out.println("- ? : Zero or one");
	}
}

