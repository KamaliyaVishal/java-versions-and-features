package java9.variablehandles;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * Java 9 Variable Handles
 * Demonstrates VarHandle for atomic operations and memory access
 * 
 * Variable Handles provide a standard way to perform atomic operations
 * and access object fields with various memory ordering semantics.
 */
public class VariableHandlesDemo {
    
    // Volatile field for demonstration
    private volatile int count = 0;
    private volatile String name = "Initial";
    
    // VarHandle for count field
    private static final VarHandle COUNT_HANDLE;
    private static final VarHandle NAME_HANDLE;
    
    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            COUNT_HANDLE = lookup.findVarHandle(
                VariableHandlesDemo.class, "count", int.class);
            NAME_HANDLE = lookup.findVarHandle(
                VariableHandlesDemo.class, "name", String.class);
        } catch (Exception e) {
            throw new Error(e);
        }
    }
    
    // Atomic operations on count
    public void increment() {
        COUNT_HANDLE.getAndAdd(this, 1);
    }
    
    public void decrement() {
        COUNT_HANDLE.getAndAdd(this, -1);
    }
    
    public int getCount() {
        return (int) COUNT_HANDLE.getVolatile(this);
    }
    
    public void setCount(int value) {
        COUNT_HANDLE.setVolatile(this, value);
    }
    
    public boolean compareAndSetCount(int expected, int update) {
        return COUNT_HANDLE.compareAndSet(this, expected, update);
    }
    
    // Operations on name
    public String getName() {
        return (String) NAME_HANDLE.getVolatile(this);
    }
    
    public void setName(String value) {
        NAME_HANDLE.setVolatile(this, value);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Variable Handles Demo ===");
        
        VariableHandlesDemo demo = new VariableHandlesDemo();
        
        // 1. Basic get/set operations
        System.out.println("\n--- Basic Operations ---");
        System.out.println("Initial count: " + demo.getCount());
        demo.setCount(10);
        System.out.println("After setCount(10): " + demo.getCount());
        
        // 2. Atomic increment/decrement
        System.out.println("\n--- Atomic Operations ---");
        demo.increment();
        System.out.println("After increment: " + demo.getCount());
        demo.increment();
        System.out.println("After increment: " + demo.getCount());
        demo.decrement();
        System.out.println("After decrement: " + demo.getCount());
        
        // 3. Compare and Set (CAS)
        System.out.println("\n--- Compare and Set (CAS) ---");
        int current = demo.getCount();
        System.out.println("Current count: " + current);
        boolean success = demo.compareAndSetCount(current, 100);
        System.out.println("CAS(" + current + ", 100): " + success);
        System.out.println("Count after CAS: " + demo.getCount());
        
        boolean fail = demo.compareAndSetCount(50, 200);
        System.out.println("CAS(50, 200): " + fail + " (expected false, current is 100)");
        System.out.println("Count after failed CAS: " + demo.getCount());
        
        // 4. String field operations
        System.out.println("\n--- String Field Operations ---");
        System.out.println("Initial name: " + demo.getName());
        demo.setName("Updated Name");
        System.out.println("After setName: " + demo.getName());
        
        // 5. Memory ordering semantics
        System.out.println("\n=== Memory Ordering Semantics ===");
        System.out.println("VarHandle supports different access modes:");
        System.out.println("- getVolatile/setVolatile: Volatile semantics");
        System.out.println("- getAcquire/setRelease: Acquire/release semantics");
        System.out.println("- getOpaque/setOpaque: Opaque semantics");
        System.out.println("- get/set: Plain access (no ordering guarantees)");
        
        // 6. Array element access (example)
        System.out.println("\n=== Array Element Access ===");
        int[] array = {1, 2, 3, 4, 5};
        VarHandle arrayHandle = MethodHandles.arrayElementVarHandle(int[].class);
        
        System.out.println("Array: " + java.util.Arrays.toString(array));
        int value = (int) arrayHandle.get(array, 2);
        System.out.println("Element at index 2: " + value);
        
        arrayHandle.set(array, 2, 99);
        System.out.println("After set(array, 2, 99): " + java.util.Arrays.toString(array));
        
        int oldValue = (int) arrayHandle.getAndSet(array, 3, 88);
        System.out.println("getAndSet(array, 3, 88): old value = " + oldValue);
        System.out.println("Array after getAndSet: " + java.util.Arrays.toString(array));
        
        System.out.println("\n=== Benefits of Variable Handles ===");
        System.out.println("1. Standard API for atomic operations");
        System.out.println("2. More flexible than AtomicInteger, AtomicReference, etc.");
        System.out.println("3. Support for different memory ordering semantics");
        System.out.println("4. Can work with any field type");
        System.out.println("5. Better performance than reflection");
    }
}

