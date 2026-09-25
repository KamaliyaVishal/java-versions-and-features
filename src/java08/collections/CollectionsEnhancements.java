package java8.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Java 8 Collections Enhancements
 * 
 * Demonstrates new methods added to Collection interface:
 * - removeIf(): Remove elements matching a predicate
 * - replaceAll(): Replace all elements with function result
 */
public class CollectionsEnhancements {
    public static void main(String[] args) {
        
        System.out.println("=== Collections.removeIf() ===");
        
        // 1. Remove even numbers
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Original: " + numbers1);
        numbers1.removeIf(n -> n % 2 == 0);
        System.out.println("After removeIf(even): " + numbers1);
        // Output: [1, 3, 5, 7, 9]
        
        // 2. Remove strings with length < 5
        List<String> words = new ArrayList<>(Arrays.asList("apple", "cat", "banana", "dog", "elephant"));
        System.out.println("\nOriginal words: " + words);
        words.removeIf(s -> s.length() < 5);
        System.out.println("After removeIf(length < 5): " + words);
        // Output: [apple, banana, elephant]
        
        // 3. Remove null values
        List<String> withNulls = new ArrayList<>(Arrays.asList("a", null, "b", null, "c"));
        System.out.println("\nOriginal with nulls: " + withNulls);
        withNulls.removeIf(s -> s == null);
        System.out.println("After removeIf(null): " + withNulls);
        // Output: [a, b, c]
        
        // 4. Remove based on complex condition
        List<Integer> numbers2 = new ArrayList<>(Arrays.asList(10, 15, 20, 25, 30, 35, 40));
        System.out.println("\nOriginal: " + numbers2);
        numbers2.removeIf(n -> n > 20 && n < 35);
        System.out.println("After removeIf(20 < n < 35): " + numbers2);
        // Output: [10, 15, 20, 35, 40]
        
        System.out.println("\n=== Collections.replaceAll() ===");
        
        // 1. Convert to uppercase
        List<String> names1 = new ArrayList<>(Arrays.asList("alice", "bob", "charlie"));
        System.out.println("Original: " + names1);
        names1.replaceAll(String::toUpperCase);
        System.out.println("After replaceAll(toUpperCase): " + names1);
        // Output: [ALICE, BOB, CHARLIE]
        
        // 2. Multiply numbers by 2
        List<Integer> numbers3 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("\nOriginal: " + numbers3);
        numbers3.replaceAll(n -> n * 2);
        System.out.println("After replaceAll(n * 2): " + numbers3);
        // Output: [2, 4, 6, 8, 10]
        
        // 3. Add prefix to strings
        List<String> items = new ArrayList<>(Arrays.asList("apple", "banana", "cherry"));
        System.out.println("\nOriginal: " + items);
        items.replaceAll(s -> "Item: " + s);
        System.out.println("After replaceAll(add prefix): " + items);
        // Output: [Item: apple, Item: banana, Item: cherry]
        
        // 4. Replace null with default value
        List<String> withNulls2 = new ArrayList<>(Arrays.asList("a", null, "b", null, "c"));
        System.out.println("\nOriginal with nulls: " + withNulls2);
        withNulls2.replaceAll(s -> s == null ? "DEFAULT" : s);
        System.out.println("After replaceAll(null -> DEFAULT): " + withNulls2);
        // Output: [a, DEFAULT, b, DEFAULT, c]
        
        // 5. Complex transformation
        List<String> numbers4 = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
        System.out.println("\nOriginal strings: " + numbers4);
        numbers4.replaceAll(s -> {
            int num = Integer.parseInt(s);
            return String.valueOf(num * num);
        });
        System.out.println("After replaceAll(square): " + numbers4);
        // Output: [1, 4, 9, 16, 25]
        
        System.out.println("\n=== Combined Usage ===");
        
        // Remove and replace in sequence
        List<Integer> numbers5 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Original: " + numbers5);
        numbers5.removeIf(n -> n % 2 == 0);  // Remove evens
        System.out.println("After removeIf(even): " + numbers5);
        numbers5.replaceAll(n -> n * n);  // Square remaining
        System.out.println("After replaceAll(square): " + numbers5);
        // Output: [1, 9, 25, 49, 81]
        
        System.out.println("\n=== Benefits ===");
        System.out.println("1. removeIf(): More efficient than iterator-based removal");
        System.out.println("2. replaceAll(): In-place transformation without creating new list");
        System.out.println("3. Functional style: Works with lambdas and method references");
        System.out.println("4. Thread-safe when used on synchronized collections");
    }
}

