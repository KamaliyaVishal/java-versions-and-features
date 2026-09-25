package java9.collections;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Java 9 Factory Methods for Collections
 * Demonstrates List.of(), Set.of(), Map.of(), and Map.ofEntries()
 */
public class FactoryMethods {
    public static void main(String[] args) {
        
        // 1. List.of() - Creates immutable list
        System.out.println("=== List.of() Examples ===");
        List<String> list1 = List.of("Apple", "Banana", "Cherry");
        System.out.println("List.of(): " + list1);
        
        List<String> emptyList = List.of();
        System.out.println("Empty List.of(): " + emptyList);
        
        List<String> singleList = List.of("One");
        System.out.println("Single List.of(): " + singleList);
        
        // Uncomment to see immutability
        // list1.add("Date"); // UnsupportedOperationException
        // list1.set(0, "Apricot"); // UnsupportedOperationException
        
        // 2. Set.of() - Creates immutable set
        System.out.println("\n=== Set.of() Examples ===");
        Set<Integer> set1 = Set.of(1, 2, 3, 4, 5);
        System.out.println("Set.of(): " + set1);
        
        Set<String> emptySet = Set.of();
        System.out.println("Empty Set.of(): " + emptySet);
        
        // Duplicates throw exception
        // Set.of(1, 2, 2); // IllegalArgumentException
        
        // 3. Map.of() - Creates immutable map (up to 10 key-value pairs)
        System.out.println("\n=== Map.of() Examples ===");
        Map<String, Integer> map1 = Map.of(
            "One", 1,
            "Two", 2,
            "Three", 3
        );
        System.out.println("Map.of(): " + map1);
        
        Map<String, String> emptyMap = Map.of();
        System.out.println("Empty Map.of(): " + emptyMap);
        
        // 4. Map.ofEntries() - For more than 10 entries
        System.out.println("\n=== Map.ofEntries() Examples ===");
        Map<String, Integer> largeMap = Map.ofEntries(
            Map.entry("One", 1),
            Map.entry("Two", 2),
            Map.entry("Three", 3),
            Map.entry("Four", 4),
            Map.entry("Five", 5),
            Map.entry("Six", 6),
            Map.entry("Seven", 7),
            Map.entry("Eight", 8),
            Map.entry("Nine", 9),
            Map.entry("Ten", 10),
            Map.entry("Eleven", 11)
        );
        System.out.println("Map.ofEntries(): " + largeMap);
        
        // Practical example
        System.out.println("\n=== Practical Example ===");
        Map<String, String> userRoles = Map.of(
            "admin", "Administrator",
            "user", "Regular User",
            "guest", "Guest User"
        );
        
        System.out.println("User Roles: " + userRoles);
        System.out.println("Admin role: " + userRoles.get("admin"));
        
        // Characteristics
        System.out.println("\n=== Characteristics ===");
        System.out.println("Collections are immutable");
        System.out.println("Null values not allowed");
        System.out.println("Space-efficient");
        System.out.println("Serialization-friendly");
    }
}

