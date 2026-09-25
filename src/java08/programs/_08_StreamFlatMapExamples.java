package java8.programs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * FlatMap Examples - Common Interview Questions
 * 
 * Demonstrates:
 * - map() vs flatMap()
 * - Flattening nested collections
 * - Extracting nested objects
 * - Combining multiple streams
 */
public class _08_StreamFlatMapExamples
{
	public static void main(String[] args)
	{
		System.out.println("=== FLATMAP EXAMPLES ===\n");

		demonstrateMapVsFlatMap();
		demonstrateFlatteningLists();
		demonstrateExtractingNestedObjects();
		demonstrateCombiningMultipleStreams();
		demonstrateRealWorldExamples();
	}

	/**
	 * Demonstrates the difference between map() and flatMap()
	 */
	private static void demonstrateMapVsFlatMap()
	{
		System.out.println("1. map() vs flatMap():");

		List<String> words = Arrays.asList("Hello", "World");

		// Using map() - returns Stream<Stream<Character>>
		List<Stream<Character>> mapResult = words.stream()
			.map(s -> s.chars().mapToObj(c -> (char) c))
			.collect(Collectors.toList());

		System.out.println("  map() result (nested streams):");
		mapResult.forEach(stream -> {
			System.out.print("    ");
			stream.forEach(c -> System.out.print(c + " "));
			System.out.println();
		});

		// Using flatMap() - flattens to Stream<Character>
		List<Character> flatMapResult = words.stream()
			.flatMap(s -> s.chars().mapToObj(c -> (char) c))
			.collect(Collectors.toList());

		System.out.println("\n  flatMap() result (flattened): " + flatMapResult);
		System.out.println();
	}

	/**
	 * Demonstrates flattening nested lists
	 */
	private static void demonstrateFlatteningLists()
	{
		System.out.println("2. Flattening Nested Lists:");

		List<List<Integer>> nestedList = Arrays.asList(
			Arrays.asList(1, 2, 3),
			Arrays.asList(4, 5),
			Arrays.asList(6, 7, 8, 9)
		);

		// Flatten nested list into single list
		List<Integer> flattened = nestedList.stream()
			.flatMap(List::stream)
			.collect(Collectors.toList());

		System.out.println("  Original nested list: " + nestedList);
		System.out.println("  Flattened list: " + flattened);

		// Find distinct elements from nested lists
		List<Integer> distinct = nestedList.stream()
			.flatMap(List::stream)
			.distinct()
			.sorted()
			.collect(Collectors.toList());

		System.out.println("  Distinct sorted: " + distinct);
		System.out.println();
	}

	/**
	 * Demonstrates extracting nested objects using flatMap
	 */
	private static void demonstrateExtractingNestedObjects()
	{
		System.out.println("3. Extracting Nested Objects:");

		class Order {
			String orderId;
			List<String> items;

			Order(String orderId, List<String> items) {
				this.orderId = orderId;
				this.items = items;
			}
		}

		List<Order> orders = Arrays.asList(
			new Order("O1", Arrays.asList("Apple", "Banana", "Cherry")),
			new Order("O2", Arrays.asList("Orange", "Grape")),
			new Order("O3", Arrays.asList("Apple", "Mango"))
		);

		// Extract all items from all orders
		List<String> allItems = orders.stream()
			.flatMap(order -> order.items.stream())
			.collect(Collectors.toList());

		System.out.println("  All items from all orders: " + allItems);

		// Get distinct items
		List<String> distinctItems = orders.stream()
			.flatMap(order -> order.items.stream())
			.distinct()
			.collect(Collectors.toList());

		System.out.println("  Distinct items: " + distinctItems);

		// Count occurrences of each item
		Map<String, Long> itemCount = orders.stream()
			.flatMap(order -> order.items.stream())
			.collect(Collectors.groupingBy(
				item -> item,
				Collectors.counting()
			));

		System.out.println("  Item count: " + itemCount);
		System.out.println();
	}

	/**
	 * Demonstrates combining multiple streams using flatMap
	 */
	private static void demonstrateCombiningMultipleStreams()
	{
		System.out.println("4. Combining Multiple Streams:");

		// Combine multiple lists
		List<Integer> list1 = Arrays.asList(1, 2, 3);
		List<Integer> list2 = Arrays.asList(4, 5);
		List<Integer> list3 = Arrays.asList(6, 7, 8);

		List<Integer> combined = Stream.of(list1, list2, list3)
			.flatMap(List::stream)
			.collect(Collectors.toList());

		System.out.println("  Combined lists: " + combined);

		// Split strings into characters and combine
		List<String> words = Arrays.asList("Java", "Stream", "API");
		List<Character> allCharacters = words.stream()
			.flatMap(word -> word.chars().mapToObj(c -> (char) c))
			.collect(Collectors.toList());

		System.out.println("  All characters from words: " + allCharacters);
		System.out.println();
	}

	/**
	 * Real-world examples using flatMap
	 */
	private static void demonstrateRealWorldExamples()
	{
		System.out.println("5. Real-World Examples:");

		// Example 1: Extract tags from blog posts
		class BlogPost {
			String title;
			List<String> tags;

			BlogPost(String title, List<String> tags) {
				this.title = title;
				this.tags = tags;
			}
		}

		List<BlogPost> posts = Arrays.asList(
			new BlogPost("Java 8 Guide", Arrays.asList("Java", "Programming", "Tutorial")),
			new BlogPost("Stream API", Arrays.asList("Java", "Stream", "API")),
			new BlogPost("Lambda Expressions", Arrays.asList("Java", "Lambda", "Functional"))
		);

		// Get all unique tags
		Set<String> allTags = posts.stream()
			.flatMap(post -> post.tags.stream())
			.collect(Collectors.toSet());

		System.out.println("  All unique tags: " + allTags);

		// Find posts by tag
		String searchTag = "Java";
		List<String> postsWithTag = posts.stream()
			.filter(post -> post.tags.contains(searchTag))
			.map(post -> post.title)
			.collect(Collectors.toList());

		System.out.println("  Posts with tag '" + searchTag + "': " + postsWithTag);

		// Example 2: Split sentences into words
		List<String> sentences = Arrays.asList(
			"Java is great",
			"Stream API is powerful",
			"Lambda expressions are concise"
		);

		List<String> allWords = sentences.stream()
			.flatMap(sentence -> Arrays.stream(sentence.split(" ")))
			.map(String::toLowerCase)
			.distinct()
			.collect(Collectors.toList());

		System.out.println("  All unique words from sentences: " + allWords);

		// Example 3: Find all numbers in nested arrays
		int[][] matrix = {{1, 2, 3}, {4, 5}, {6, 7, 8, 9}};
		List<Integer> allNumbers = Arrays.stream(matrix)
			.flatMapToInt(Arrays::stream)
			.boxed()
			.collect(Collectors.toList());

		System.out.println("  All numbers from matrix: " + allNumbers);
	}
}

