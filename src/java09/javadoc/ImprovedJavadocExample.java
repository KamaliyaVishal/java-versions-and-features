package java9.javadoc;

/**
 * Java 9 Improved Javadoc Example
 * 
 * Demonstrates enhanced Javadoc features introduced in Java 9:
 * - HTML5 output
 * - Search functionality
 * - Module system documentation
 * - Improved accessibility
 * 
 * @author Java Developer
 * @since 9
 * @see <a href="https://docs.oracle.com/javase/9/docs/api/">Java 9 API Documentation</a>
 * @see String
 * @see java.util.List
 */
public class ImprovedJavadocExample {
    
    /**
     * The default value for calculations.
     * 
     * @since 9
     */
    private static final int DEFAULT_VALUE = 10;
    
    /**
     * Adds two numbers together.
     * 
     * This method performs basic arithmetic addition.
     * 
     * @param a the first number (must be non-negative)
     * @param b the second number (must be non-negative)
     * @return the sum of a and b
     * @throws IllegalArgumentException if either parameter is negative
     * @apiNote This method was introduced in Java 9 as part of the improved documentation.
     *          For large numbers, consider using {@link java.math.BigInteger#add(java.math.BigInteger)}
     * @implSpec This implementation uses simple integer addition.
     *           Overflow behavior follows standard Java integer arithmetic.
     * @implNote The current implementation has O(1) time complexity.
     * 
     * @since 9
     */
    public int add(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Parameters must be non-negative");
        }
        return a + b;
    }
    
    /**
     * Calculates the product of two numbers.
     * 
     * @param a the first number
     * @param b the second number
     * @return the product of a and b
     * @apiNote This is a simple multiplication operation.
     * @since 9
     */
    public int multiply(int a, int b) {
        return a * b;
    }
    
    /**
     * Performs a calculation using the default value.
     * 
     * This method uses {@value #DEFAULT_VALUE} as the base value.
     * 
     * @param multiplier the value to multiply with the default
     * @return the result of multiplying the default value with the multiplier
     * @see #DEFAULT_VALUE
     * @since 9
     */
    public int calculateWithDefault(int multiplier) {
        return DEFAULT_VALUE * multiplier;
    }
    
    /**
     * Main method to demonstrate the class functionality.
     * 
     * @param args command-line arguments (not used)
     * @since 9
     */
    public static void main(String[] args) {
        System.out.println("=== Java 9 Improved Javadoc Example ===");
        
        ImprovedJavadocExample example = new ImprovedJavadocExample();
        
        System.out.println("\n--- Method Examples ---");
        int sum = example.add(5, 3);
        System.out.println("add(5, 3) = " + sum);
        
        int product = example.multiply(4, 7);
        System.out.println("multiply(4, 7) = " + product);
        
        int result = example.calculateWithDefault(5);
        System.out.println("calculateWithDefault(5) = " + result);
        
        System.out.println("\n=== Java 9 Javadoc Improvements ===");
        System.out.println("1. HTML5 Output: Modern, accessible HTML5 documentation");
        System.out.println("2. Search: Built-in search functionality in generated docs");
        System.out.println("3. Module Documentation: Support for module system");
        System.out.println("4. Enhanced Tags: @apiNote, @implSpec, @implNote");
        System.out.println("5. Better Accessibility: Improved for screen readers");
        System.out.println("6. Responsive Design: Works well on mobile devices");
        
        System.out.println("\n=== New Javadoc Tags ===");
        System.out.println("@apiNote: API notes for developers");
        System.out.println("@implSpec: Implementation specification");
        System.out.println("@implNote: Implementation notes");
        System.out.println("@since: Version when introduced");
        
        System.out.println("\n=== Generating Javadoc ===");
        System.out.println("Command: javadoc -d docs ImprovedJavadocExample.java");
        System.out.println("With HTML5: javadoc -html5 -d docs ImprovedJavadocExample.java");
        System.out.println("With search: javadoc -d docs --enable-search ImprovedJavadocExample.java");
    }
}

