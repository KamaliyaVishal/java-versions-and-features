package java9.programs;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Practical examples using takeWhile() and dropWhile()
 * Demonstrates real-world use cases for these Java 9 stream methods
 */
public class TakeWhileDropWhileExamples {
    public static void main(String[] args) {
        
        // Example 1: Process sorted list until condition
        System.out.println("=== Example 1: Process until threshold ===");
        List<Integer> temperatures = Arrays.asList(10, 15, 20, 25, 30, 35, 40, 45);
        
        List<Integer> acceptableTemps = temperatures.stream()
            .takeWhile(t -> t <= 30)
            .collect(Collectors.toList());
        
        System.out.println("Acceptable temperatures (<= 30): " + acceptableTemps);
        
        List<Integer> highTemps = temperatures.stream()
            .dropWhile(t -> t <= 30)
            .collect(Collectors.toList());
        
        System.out.println("High temperatures (> 30): " + highTemps);
        
        // Example 2: Process words by length
        System.out.println("\n=== Example 2: Process words by length ===");
        List<String> words = Arrays.asList("apple", "banana", "cat", "dog", "elephant", "fish");
        
        List<String> shortWords = words.stream()
            .takeWhile(w -> w.length() <= 4)
            .collect(Collectors.toList());
        
        System.out.println("Short words (length <= 4): " + shortWords);
        
        List<String> longWords = words.stream()
            .dropWhile(w -> w.length() <= 4)
            .collect(Collectors.toList());
        
        System.out.println("Long words (length > 4): " + longWords);
        
        // Example 3: Process numbers until negative
        System.out.println("\n=== Example 3: Process until negative number ===");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, -1, 5, 6, 7);
        
        List<Integer> positive = numbers.stream()
            .takeWhile(n -> n > 0)
            .collect(Collectors.toList());
        
        System.out.println("Positive numbers until first negative: " + positive);
        
        // Example 4: Process prefix of list
        System.out.println("\n=== Example 4: Process prefix ===");
        List<String> data = Arrays.asList("header1", "header2", "data1", "data2", "data3");
        
        List<String> headers = data.stream()
            .takeWhile(s -> s.startsWith("header"))
            .collect(Collectors.toList());
        
        System.out.println("Headers: " + headers);
        
        List<String> actualData = data.stream()
            .dropWhile(s -> s.startsWith("header"))
            .collect(Collectors.toList());
        
        System.out.println("Data: " + actualData);
    }
}

