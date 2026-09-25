package java8.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Java 8 Arrays.parallelSort() Demo
 * 
 * Demonstrates parallel sorting for arrays, which provides better performance
 * on large datasets by utilizing multiple threads.
 */
public class ParallelSortDemo {
    public static void main(String[] args) {
        
        System.out.println("=== Arrays.parallelSort() Basic Usage ===");
        
        // 1. Basic parallel sort
        int[] numbers1 = {5, 2, 8, 1, 9, 3, 7, 4, 6};
        System.out.println("Original: " + Arrays.toString(numbers1));
        Arrays.parallelSort(numbers1);
        System.out.println("After parallelSort(): " + Arrays.toString(numbers1));
        // Output: [1, 2, 3, 4, 5, 6, 7, 8, 9]
        
        // 2. Parallel sort with range
        int[] numbers2 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println("\nOriginal: " + Arrays.toString(numbers2));
        Arrays.parallelSort(numbers2, 2, 7);  // Sort from index 2 to 7
        System.out.println("After parallelSort(2, 7): " + Arrays.toString(numbers2));
        // Output: [9, 8, 2, 3, 4, 5, 6, 2, 1]
        
        // 3. Parallel sort with Comparator (for objects)
        String[] names = {"Charlie", "Alice", "Bob", "David", "Eve"};
        System.out.println("\nOriginal: " + Arrays.toString(names));
        Arrays.parallelSort(names);
        System.out.println("After parallelSort() (natural order): " + Arrays.toString(names));
        // Output: [Alice, Bob, Charlie, David, Eve]
        
        // 4. Parallel sort with reverse order
        String[] names2 = {"Charlie", "Alice", "Bob", "David", "Eve"};
        System.out.println("\nOriginal: " + Arrays.toString(names2));
        Arrays.parallelSort(names2, Comparator.reverseOrder());
        System.out.println("After parallelSort(reverse): " + Arrays.toString(names2));
        // Output: [Eve, David, Charlie, Bob, Alice]
        
        // 5. Parallel sort with custom Comparator
        String[] words = {"apple", "banana", "cherry", "date", "elderberry"};
        System.out.println("\nOriginal: " + Arrays.toString(words));
        Arrays.parallelSort(words, Comparator.comparing(String::length));
        System.out.println("After parallelSort(by length): " + Arrays.toString(words));
        // Output: [date, apple, banana, cherry, elderberry]
        
        System.out.println("\n=== Performance Comparison ===");
        
        // Generate large array for performance test
        int size = 1_000_000;
        int[] largeArray1 = generateRandomArray(size);
        int[] largeArray2 = Arrays.copyOf(largeArray1, largeArray1.length);
        
        // Sequential sort
        long start = System.currentTimeMillis();
        Arrays.sort(largeArray1);
        long sequentialTime = System.currentTimeMillis() - start;
        
        // Parallel sort
        start = System.currentTimeMillis();
        Arrays.parallelSort(largeArray2);
        long parallelTime = System.currentTimeMillis() - start;
        
        System.out.println("Array size: " + size);
        System.out.println("Sequential sort time: " + sequentialTime + " ms");
        System.out.println("Parallel sort time: " + parallelTime + " ms");
        System.out.println("Speedup: " + String.format("%.2f", (double) sequentialTime / parallelTime) + "x");
        
        System.out.println("\n=== When to Use parallelSort() ===");
        System.out.println("✓ Use when:");
        System.out.println("  - Large arrays (typically > 10,000 elements)");
        System.out.println("  - Multi-core systems");
        System.out.println("  - CPU-intensive sorting");
        System.out.println("✗ Avoid when:");
        System.out.println("  - Small arrays (overhead not worth it)");
        System.out.println("  - Single-core systems");
        System.out.println("  - Memory-constrained environments");
        
        System.out.println("\n=== Key Differences: sort() vs parallelSort() ===");
        System.out.println("sort():");
        System.out.println("  - Single-threaded");
        System.out.println("  - Stable sort");
        System.out.println("  - Better for small arrays");
        System.out.println("parallelSort():");
        System.out.println("  - Multi-threaded");
        System.out.println("  - Uses Fork/Join framework");
        System.out.println("  - Better for large arrays");
        System.out.println("  - May not be stable (but usually is)");
    }
    
    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1_000_000);
        }
        return array;
    }
}

