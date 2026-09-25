package java9.streamapi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java 9 Stream API Enhancements
 * Demonstrates new methods: takeWhile(), dropWhile(), ofNullable(), and iterate() with predicate
 */
public class StreamEnhancements {
    public static void main(String[] args) {
        
        // 1. takeWhile() - Takes elements while predicate is true
        System.out.println("=== takeWhile() Example ===");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        List<Integer> taken = numbers.stream()
            .takeWhile(n -> n < 5)
            .collect(Collectors.toList());
        
        System.out.println("Original: " + numbers);
        System.out.println("takeWhile(n < 5): " + taken); // [1, 2, 3, 4]
        
        // 2. dropWhile() - Drops elements while predicate is true
        System.out.println("\n=== dropWhile() Example ===");
        List<Integer> dropped = numbers.stream()
            .dropWhile(n -> n < 5)
            .collect(Collectors.toList());
        
        System.out.println("Original: " + numbers);
        System.out.println("dropWhile(n < 5): " + dropped); // [5, 6, 7, 8, 9, 10]
        
        // 3. ofNullable() - Creates stream from nullable element
        System.out.println("\n=== ofNullable() Example ===");
        String value = "Hello";
        Stream<String> stream1 = Stream.ofNullable(value);
        System.out.println("ofNullable('Hello'): " + stream1.collect(Collectors.toList()));
        
        String nullValue = null;
        Stream<String> stream2 = Stream.ofNullable(nullValue);
        System.out.println("ofNullable(null): " + stream2.collect(Collectors.toList())); // []
        
        // 4. iterate() with predicate - Enhanced iterate with condition
        System.out.println("\n=== iterate() with Predicate Example ===");
        List<Integer> iterated = Stream.iterate(0, n -> n < 10, n -> n + 2)
            .collect(Collectors.toList());
        
        System.out.println("iterate(0, n < 10, n + 2): " + iterated); // [0, 2, 4, 6, 8]
        
        // Practical example: Processing until condition
        System.out.println("\n=== Practical Example ===");
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        
        List<String> untilLength5 = words.stream()
            .takeWhile(s -> s.length() <= 5)
            .collect(Collectors.toList());
        
        System.out.println("Words with length <= 5: " + untilLength5);
        
        List<String> afterLength5 = words.stream()
            .dropWhile(s -> s.length() <= 5)
            .collect(Collectors.toList());
        
        System.out.println("Words with length > 5: " + afterLength5);
    }
}

