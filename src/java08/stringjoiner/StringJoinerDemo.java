package java8.stringjoiner;

import java.util.StringJoiner;

/**
 * Java 8 StringJoiner Demo
 * 
 * StringJoiner provides a convenient way to join strings with a delimiter,
 * prefix, and suffix. It's the foundation for String.join() method.
 */
public class StringJoinerDemo {
    public static void main(String[] args) {
        
        System.out.println("=== StringJoiner Basic Usage ===");
        
        // 1. Basic StringJoiner with delimiter
        StringJoiner joiner1 = new StringJoiner(", ");
        joiner1.add("Apple");
        joiner1.add("Banana");
        joiner1.add("Cherry");
        System.out.println("Basic: " + joiner1.toString());
        // Output: Apple, Banana, Cherry
        
        // 2. StringJoiner with prefix and suffix
        StringJoiner joiner2 = new StringJoiner(", ", "[", "]");
        joiner2.add("One");
        joiner2.add("Two");
        joiner2.add("Three");
        System.out.println("With prefix/suffix: " + joiner2.toString());
        // Output: [One, Two, Three]
        
        // 3. Empty StringJoiner
        StringJoiner emptyJoiner = new StringJoiner("-");
        System.out.println("Empty: '" + emptyJoiner.toString() + "'");
        // Output: '' (empty string)
        
        // 4. Empty with prefix/suffix
        StringJoiner emptyWithPrefix = new StringJoiner(", ", "[", "]");
        System.out.println("Empty with prefix/suffix: " + emptyWithPrefix.toString());
        // Output: []
        
        // 5. setEmptyValue() - custom value when empty
        StringJoiner joiner3 = new StringJoiner(", ");
        joiner3.setEmptyValue("No items");
        System.out.println("Empty with custom value: " + joiner3.toString());
        // Output: No items
        
        joiner3.add("Item1");
        System.out.println("After adding item: " + joiner3.toString());
        // Output: Item1
        
        System.out.println("\n=== String.join() Static Method ===");
        
        // String.join() is a static convenience method that uses StringJoiner internally
        String joined1 = String.join(", ", "Apple", "Banana", "Cherry");
        System.out.println("String.join(): " + joined1);
        // Output: Apple, Banana, Cherry
        
        // With Collection
        java.util.List<String> names = java.util.Arrays.asList("Alice", "Bob", "Charlie");
        String joined2 = String.join(" | ", names);
        System.out.println("String.join() with List: " + joined2);
        // Output: Alice | Bob | Charlie
        
        // Practical examples
        System.out.println("\n=== Practical Examples ===");
        
        // CSV format
        StringJoiner csv = new StringJoiner(",");
        csv.add("John").add("30").add("Engineer");
        System.out.println("CSV: " + csv.toString());
        
        // SQL IN clause
        StringJoiner sqlIn = new StringJoiner(", ", "(", ")");
        sqlIn.add("'Alice'").add("'Bob'").add("'Charlie'");
        System.out.println("SQL IN clause: SELECT * FROM users WHERE name IN " + sqlIn.toString());
        
        // Path joining
        StringJoiner path = new StringJoiner("/");
        path.add("home").add("user").add("documents");
        System.out.println("Path: /" + path.toString());
        
        // JSON array
        StringJoiner jsonArray = new StringJoiner(", ", "[", "]");
        jsonArray.add("\"item1\"").add("\"item2\"").add("\"item3\"");
        System.out.println("JSON array: " + jsonArray.toString());
        
        System.out.println("\n=== Benefits ===");
        System.out.println("1. Cleaner than StringBuilder for joining");
        System.out.println("2. Built-in prefix/suffix support");
        System.out.println("3. Thread-safe (immutable result)");
        System.out.println("4. Easy to use with Streams");
    }
}

