package java9.diamondoperator;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 9 Diamond Operator Enhancement
 * Demonstrates using diamond operator with anonymous classes
 * 
 * In Java 9, the diamond operator (<>) can now be used with anonymous classes,
 * eliminating the need to explicitly specify type parameters.
 */
public class DiamondOperatorEnhancement {
    public static void main(String[] args) {
        
        System.out.println("=== Diamond Operator Enhancement ===");
        
        // Before Java 9 - explicit type needed
        System.out.println("\n--- Before Java 9 (explicit type) ---");
        List<String> list1 = new ArrayList<String>() {
            @Override
            public boolean add(String e) {
                System.out.println("Adding (old way): " + e);
                return super.add(e);
            }
        };
        list1.add("Item 1");
        list1.add("Item 2");
        System.out.println("List 1: " + list1);
        
        // Java 9 - diamond operator with anonymous classes
        System.out.println("\n--- Java 9 (diamond operator) ---");
        List<String> list2 = new ArrayList<>() {
            @Override
            public boolean add(String e) {
                System.out.println("Adding (new way): " + e);
                return super.add(e);
            }
            
            @Override
            public String toString() {
                return "Custom List: " + super.toString();
            }
        };
        list2.add("Item A");
        list2.add("Item B");
        System.out.println("List 2: " + list2);
        
        // Practical example: Custom list with logging
        System.out.println("\n=== Practical Example: Logging List ===");
        List<Integer> loggingList = new ArrayList<>() {
            @Override
            public boolean add(Integer e) {
                boolean result = super.add(e);
                System.out.println("Added element: " + e + ", Size: " + size());
                return result;
            }
            
            @Override
            public Integer remove(int index) {
                Integer removed = super.remove(index);
                System.out.println("Removed element: " + removed + ", Size: " + size());
                return removed;
            }
        };
        
        loggingList.add(10);
        loggingList.add(20);
        loggingList.add(30);
        loggingList.remove(0);
        
        // Example with Set
        System.out.println("\n=== Example with Custom Set Behavior ===");
        List<String> caseInsensitiveList = new ArrayList<>() {
            @Override
            public boolean add(String e) {
                // Convert to lowercase for storage
                return super.add(e != null ? e.toLowerCase() : null);
            }
            
            @Override
            public boolean contains(Object o) {
                // Case-insensitive contains
                if (o instanceof String) {
                    String str = (String) o;
                    return super.contains(str.toLowerCase());
                }
                return super.contains(o);
            }
        };
        
        caseInsensitiveList.add("Hello");
        caseInsensitiveList.add("WORLD");
        System.out.println("List: " + caseInsensitiveList);
        System.out.println("Contains 'HELLO': " + caseInsensitiveList.contains("HELLO"));
        System.out.println("Contains 'world': " + caseInsensitiveList.contains("world"));
        
        System.out.println("\n=== Benefits ===");
        System.out.println("1. Cleaner code - no redundant type specification");
        System.out.println("2. Better type inference");
        System.out.println("3. Consistent with regular diamond operator usage");
    }
}

