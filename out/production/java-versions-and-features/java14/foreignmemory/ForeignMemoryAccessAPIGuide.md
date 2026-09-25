# Java 14 Foreign-Memory Access API Guide

## Overview

The Foreign-Memory Access API provides a way to access native memory safely and efficiently, useful for interop with native libraries. This is an **incubator feature** introduced in Java 14.

**NOTE:** This is an incubator feature and requires:
- `--add-modules jdk.incubator.foreign`
- `--enable-preview`

**WARNING:** This API may change in future versions as it's still incubating.

## Compilation and Execution

```bash
# Compile
javac --enable-preview \
      --add-modules jdk.incubator.foreign \
      --release 14 \
      ForeignMemoryAccessAPI.java

# Run
java --enable-preview \
     --add-modules jdk.incubator.foreign \
     ForeignMemoryAccessAPI
```

## Key Classes

### 1. MemorySegment

Represents a contiguous region of memory. Can be allocated from native memory or mapped from a file.

### 2. MemoryAccess

Provides type-safe access to memory segments. Includes methods for reading and writing different data types.

### 3. MemoryLayout

Describes memory layouts for structured data. Useful for working with C structs or other structured memory formats.

### 4. MemoryAddress

Represents a memory address. Used for pointer-like operations.

## Basic Usage Example

```java
import jdk.incubator.foreign.*;

// Allocate native memory
try (MemorySegment segment = MemorySegment.allocateNative(100)) {
    // Write to memory
    MemoryAccess.setIntAtOffset(segment, 0, 42);
    MemoryAccess.setIntAtOffset(segment, 4, 100);
    
    // Read from memory
    int value1 = MemoryAccess.getIntAtOffset(segment, 0);
    int value2 = MemoryAccess.getIntAtOffset(segment, 4);
    
    System.out.println("Value 1: " + value1); // 42
    System.out.println("Value 2: " + value2); // 100
}
// Memory is automatically freed when segment is closed
```

## Features

1. **Safe Memory Access**: Bounds checking prevents memory errors
2. **Automatic Resource Management**: Memory is automatically freed using try-with-resources
3. **Type-Safe Operations**: Prevents type errors at compile time
4. **Better than JNI**: Easier to use than Java Native Interface for many use cases
5. **Useful for Native Library Interop**: Simplifies interaction with native libraries

## Use Cases

1. **Interfacing with Native Libraries**
   - Call native functions that require memory buffers
   - Pass data structures to native code
   - Receive data from native code

2. **High-Performance Memory Operations**
   - Direct memory access for performance-critical code
   - Zero-copy operations
   - Efficient data processing

3. **System-Level Programming**
   - Direct hardware access
   - Operating system APIs
   - Device drivers

4. **Zero-Copy Operations**
   - Avoid copying data between Java and native code
   - Efficient data transfer
   - Reduced memory overhead

5. **Direct Memory Access for Performance**
   - Bypass Java heap for performance
   - Lower latency operations
   - Better cache locality

## Example: Allocating and Using Native Memory

```java
import jdk.incubator.foreign.*;

public class MemoryExample {
    public static void main(String[] args) {
        // Allocate 100 bytes of native memory
        try (MemorySegment segment = MemorySegment.allocateNative(100)) {
            
            // Write different types to memory
            MemoryAccess.setIntAtOffset(segment, 0, 42);
            MemoryAccess.setLongAtOffset(segment, 4, 123456789L);
            MemoryAccess.setDoubleAtOffset(segment, 12, 3.14159);
            
            // Read back
            int intValue = MemoryAccess.getIntAtOffset(segment, 0);
            long longValue = MemoryAccess.getIntAtOffset(segment, 4);
            double doubleValue = MemoryAccess.getDoubleAtOffset(segment, 12);
            
            System.out.println("Int: " + intValue);
            System.out.println("Long: " + longValue);
            System.out.println("Double: " + doubleValue);
        }
        // Memory automatically freed here
    }
}
```

## Important Notes

- **Incubator Feature**: This API is still incubating and may change in future Java versions
- **Module Requirements**: Requires `--add-modules jdk.incubator.foreign`
- **Preview Feature**: Requires `--enable-preview` flag
- **Automatic Memory Management**: Memory is automatically managed using try-with-resources
- **Bounds Checking**: Prevents memory errors through bounds checking
- **Type Safety**: Type-safe operations prevent type errors

## Future Development

This API is being refined as part of **Project Panama**, which aims to improve interop with native code. The API may become a standard feature in future Java versions.

### Project Panama Goals

- Simplify native code interop
- Improve performance
- Better safety guarantees
- Easier to use than JNI

## Comparison with JNI

### Java Native Interface (JNI) - Traditional Approach

```java
// Requires native code
// Complex setup
// Manual memory management
// Error-prone
```

### Foreign-Memory Access API - Modern Approach

```java
// Pure Java (with incubator modules)
// Simpler setup
// Automatic memory management
// Type-safe and bounds-checked
```

## Benefits Over JNI

1. **Easier to Use**: No need to write native code
2. **Type Safe**: Compile-time type checking
3. **Memory Safe**: Automatic bounds checking
4. **Resource Management**: Automatic cleanup with try-with-resources
5. **Better Performance**: Optimized for modern JVMs

## Limitations

- Still incubating (API may change)
- Requires preview and module flags
- Not all use cases are covered yet
- Some features may be missing compared to JNI

## Additional Resources

- [JEP 370: Foreign-Memory Access API (Incubator)](https://openjdk.org/jeps/370)
- [Project Panama](https://openjdk.org/projects/panama/)
- Java API Documentation for `jdk.incubator.foreign` package

## Summary

The Foreign-Memory Access API provides a modern, safe, and efficient way to access native memory from Java. While still incubating, it represents a significant improvement over traditional JNI for many use cases. It's particularly useful for applications that need to interface with native libraries or require high-performance memory operations.

