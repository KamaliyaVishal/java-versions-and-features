package java8.string;

import java.util.Arrays;
import java.util.List;

/**
 * Java 8 String.join() Demo
 * 
 * String.join() is a static convenience method that uses StringJoiner internally.
 * It provides a simple way to join strings with a delimiter.
 */
public class StringJoinDemo {
    public static void main(String[] args) {
        
        System.out.println("=== String.join() Basic Usage ===");
        
        // 1. Join array of strings
        String[] fruits = {"Apple", "Banana", "Cherry"};
        String joined1 = String.join(", ", fruits);
        System.out.println("Joined array: " + joined1);
        // Output: Apple, Banana, Cherry
        
        // 2. Join list of strings
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        String joined2 = String.join(" | ", names);
        System.out.println("Joined list: " + joined2);
        // Output: Alice | Bob | Charlie
        
        // 3. Join with different delimiters
        String joined3 = String.join("-", "2024", "03", "15");
        System.out.println("Date format: " + joined3);
        // Output: 2024-03-15
        
        String joined4 = String.join("", "Hello", "World");
        System.out.println("No delimiter: " + joined4);
        // Output: HelloWorld
        
        System.out.println("\n=== Practical Examples ===");
        
        // 4. CSV format
        List<String> csvData = Arrays.asList("John", "30", "Engineer", "New York");
        String csv = String.join(",", csvData);
        System.out.println("CSV: " + csv);
        // Output: John,30,Engineer,New York
        
        // 5. Path joining
        List<String> pathParts = Arrays.asList("home", "user", "documents", "file.txt");
        String path = "/" + String.join("/", pathParts);
        System.out.println("Path: " + path);
        // Output: /home/user/documents/file.txt
        
        // 6. SQL IN clause
        List<String> values = Arrays.asList("Alice", "Bob", "Charlie");
        String sqlIn = "SELECT * FROM users WHERE name IN ('" + 
                      String.join("', '", values) + "')";
        System.out.println("SQL: " + sqlIn);
        
        // 7. HTML list
        List<String> items = Arrays.asList("Item 1", "Item 2", "Item 3");
        String htmlList = "<ul><li>" + String.join("</li><li>", items) + "</li></ul>";
        System.out.println("HTML: " + htmlList);
        
        // 8. URL query parameters
        List<String> params = Arrays.asList("name=John", "age=30", "city=NYC");
        String queryString = String.join("&", params);
        System.out.println("Query string: " + queryString);
        // Output: name=John&age=30&city=NYC
        
        // 9. Multi-line string (with newline)
        List<String> lines = Arrays.asList("Line 1", "Line 2", "Line 3");
        String multiLine = String.join("\n", lines);
        System.out.println("Multi-line:\n" + multiLine);
        
        // 10. Empty list
        List<String> empty = Arrays.asList();
        String emptyResult = String.join(", ", empty);
        System.out.println("Empty list result: '" + emptyResult + "'");
        // Output: '' (empty string)
        
        System.out.println("\n=== Comparison with StringBuilder ===");
        
        // Old way with StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fruits.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(fruits[i]);
        }
        String oldWay = sb.toString();
        System.out.println("StringBuilder way: " + oldWay);
        
        // New way with String.join()
        String newWay = String.join(", ", fruits);
        System.out.println("String.join() way: " + newWay);
        
        System.out.println("\n=== Benefits ===");
        System.out.println("1. Concise and readable");
        System.out.println("2. No need for loops or StringBuilder");
        System.out.println("3. Works with both arrays and collections");
        System.out.println("4. Handles empty collections gracefully");
        System.out.println("5. Uses StringJoiner internally (efficient)");
        
        System.out.println("\n=== When to Use ===");
        System.out.println("✓ Simple string joining with delimiter");
        System.out.println("✓ CSV, paths, query strings");
        System.out.println("✓ When you need prefix/suffix, use StringJoiner directly");
    }
}

