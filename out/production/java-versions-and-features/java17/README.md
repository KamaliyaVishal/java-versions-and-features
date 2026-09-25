# Java 17 (LTS)

A comprehensive guide to all Java 17 (LTS) concepts with practical examples for interview preparation.

## Table of Contents

1. [Sealed Classes (Finalized)](#1-sealed-classes-finalized)
2. [Pattern Matching for switch (Preview)](#2-pattern-matching-for-switch-preview)
3. [Enhanced Pseudo-Random Number Generators](#3-enhanced-pseudo-random-number-generators)
4. [Foreign Function & Memory API (Incubator)](#4-foreign-function--memory-api-incubator)
5. [Vector API (Second Incubator)](#5-vector-api-second-incubator)
6. [Context-Specific Deserialization Filters](#6-context-specific-deserialization-filters)
7. [New macOS Rendering Pipeline](#7-new-macos-rendering-pipeline)
8. [macOS/AArch64 Port](#8-macos-aarch64-port)
9. [Remove RMI Activation](#9-remove-rmi-activation)
10. [Deprecate Applet API](#10-deprecate-applet-api)
11. [Deprecation for Removal of Finalization](#11-deprecation-for-removal-of-finalization)
12. [Strongly Encapsulate JDK Internals](#12-strongly-encapsulate-jdk-internals)
13. [Performance Improvements](#13-performance-improvements)
14. [Common Interview Questions](#14-common-interview-questions)

---

## 1. Sealed Classes (Finalized)

Sealed classes are now a standard feature.

### Finalized Sealed Classes

```java
// Sealed class - restricts inheritance
public sealed class Shape 
    permits Circle, Rectangle, Triangle {
    
    public abstract double area();
}

// Permitted subclasses
public final class Circle extends Shape {
    private final double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

public final class Rectangle extends Shape {
    private final double width, height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double area() {
        return width * height;
    }
}

public final class Triangle extends Shape {
    private final double base, height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    @Override
    public double area() {
        return 0.5 * base * height;
    }
}

// Cannot extend Shape with unpermitted classes
// public class Polygon extends Shape {}  // Compile error
```

### Sealed Interfaces

```java
public sealed interface Expression 
    permits Constant, Variable, Add, Multiply {
    double evaluate();
}

public record Constant(double value) implements Expression {
    @Override
    public double evaluate() {
        return value;
    }
}

public record Variable(String name) implements Expression {
    @Override
    public double evaluate() {
        return lookup(name);
    }
}

public record Add(Expression left, Expression right) implements Expression {
    @Override
    public double evaluate() {
        return left.evaluate() + right.evaluate();
    }
}

public record Multiply(Expression left, Expression right) implements Expression {
    @Override
    public double evaluate() {
        return left.evaluate() * right.evaluate();
    }
}
```

**Note:** Sealed classes are now a standard feature (no preview flag needed). See [SealedClassesDemo.java](sealedclasses/SealedClassesDemo.java) for complete example.

---

## 2. Pattern Matching for switch (Preview)

Pattern matching with switch expressions.

### Basic Pattern Matching

```java
Object obj = "Hello";

// Pattern matching with switch (preview)
String result = switch (obj) {
    case String s -> "String: " + s;
    case Integer i -> "Integer: " + i;
    case null -> "Null";
    default -> "Unknown";
};
```

### With Sealed Classes

```java
Shape shape = new Circle(5.0);

double area = switch (shape) {
    case Circle c -> Math.PI * c.radius() * c.radius();
    case Rectangle r -> r.width() * r.height();
    case Triangle t -> 0.5 * t.base() * t.height();
    // No default needed - exhaustive (all cases covered)
};
```

### Guarded Patterns

```java
Object obj = "Hello World";

String result = switch (obj) {
    case String s when s.length() > 10 -> "Long string: " + s;
    case String s -> "Short string: " + s;
    case Integer i when i > 100 -> "Large number: " + i;
    case Integer i -> "Small number: " + i;
    default -> "Unknown";
};
```

---

## 3. Enhanced Pseudo-Random Number Generators

New interfaces and implementations for random number generation.

### Overview

Java 17 introduces enhanced pseudo-random number generators with a new API that provides better performance, reliability, and flexibility.

### Basic Usage

```java
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

// Get default random generator
RandomGenerator rng = RandomGeneratorFactory.getDefault().create();
int randomNumber = rng.nextInt(100);
System.out.println("Random number: " + randomNumber);

// Get specific algorithm
RandomGenerator lcg = RandomGeneratorFactory.of("L32X64MixRandom").create();
long randomLong = lcg.nextLong();

// Get all available algorithms
RandomGeneratorFactory.all()
    .map(f -> f.name() + " - " + f.group())
    .sorted()
    .forEach(System.out::println);
```

### Available Algorithms

- **L32X64MixRandom**: Fast, good quality
- **L64X128MixRandom**: Very fast, excellent quality
- **Xoshiro256PlusPlus**: Fast, good quality
- **Xoroshiro128PlusPlus**: Fast, good quality
- **SplittableRandom**: Splittable for parallel streams

### Features

- **Multiple algorithms**: Choose based on performance needs
- **Better performance**: Optimized implementations
- **Stream support**: Works with parallel streams
- **Splittable**: For parallel processing

### Use Cases

- Monte Carlo simulations
- Game development
- Cryptography (with secure random)
- Statistical sampling
- Testing and simulation

### Benefits

- Better performance than legacy Random
- Multiple algorithm choices
- Better quality random numbers
- Splittable for parallel processing

**Note:** See [RandomGeneratorDemo.java](random/RandomGeneratorDemo.java) for complete examples.

---

## 4. Foreign Function & Memory API (Incubator)

API for calling native code and accessing off-heap memory.

### Foreign Function Interface

```java
import jdk.incubator.foreign.*;
import java.lang.invoke.MethodHandle;

// Link with native libraries
Linker linker = Linker.nativeLinker();
SymbolLookup stdlib = linker.defaultLookup();

MethodHandle strlen = linker.downcallHandle(
    stdlib.find("strlen").orElseThrow(),
    FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
);

try (MemorySegment str = Arena.ofAuto().allocateUtf8String("Hello")) {
    long len = (long) strlen.invoke(str);
    System.out.println("Length: " + len);
}
```

### Foreign Memory Access

```java
import jdk.incubator.foreign.*;

// Allocate off-heap memory
try (Arena arena = Arena.ofConfined()) {
    MemorySegment segment = arena.allocate(100);
    
    // Write to memory
    segment.set(ValueLayout.JAVA_INT, 0, 42);
    
    // Read from memory
    int value = segment.get(ValueLayout.JAVA_INT, 0);
    System.out.println("Value: " + value);
}
```

---

**Note:** Requires `--add-modules jdk.incubator.foreign` and `--enable-preview` flags. See [ForeignFunctionMemoryDemo.java](foreignapi/ForeignFunctionMemoryDemo.java) for complete examples.

---

## 5. Vector API (Second Incubator)

Refinements to Vector API.

```java
import jdk.incubator.vector.*;

// SIMD operations
var species = IntVector.SPECIES_256;

int[] a = new int[256];
int[] b = new int[256];
int[] c = new int[256];

// Vectorized addition
for (int i = 0; i < a.length; i += species.length()) {
    var av = IntVector.fromArray(species, a, i);
    var bv = IntVector.fromArray(species, b, i);
    var cv = av.add(bv);
    cv.intoArray(c, i);
}
```

---

## 6. Context-Specific Deserialization Filters

Enhanced security for object deserialization.

### Overview

Context-specific deserialization filters allow you to control which classes can be deserialized, providing protection against deserialization attacks.

### Basic Usage

```java
import java.io.ObjectInputFilter;

// Create a filter
ObjectInputFilter filter = ObjectInputFilter.Config.createFilter(
    "java.base.*;!*");

ObjectInputStream ois = new ObjectInputStream(inputStream);
ois.setObjectInputFilter(filter);
```

### Filter Patterns

```java
// Allow all
"*"

// Reject all
"!*"

// Allow specific package
"java.base.*"

// Allow package, reject others
"java.base.*;!*"

// Multiple packages
"java.base.*;java.util.*;!*"

// Array size limit
"maxarray=1000"

// Depth limit
"maxdepth=10"

// Combined
"java.base.*;maxarray=1000;maxdepth=10;!*"
```

### Global Filter

```java
// Set global filter for all ObjectInputStream instances
ObjectInputFilter.Config.setSerialFilter(
    ObjectInputFilter.Config.createFilter("java.base.*;!*"));
```

### Custom Filter

```java
ObjectInputFilter customFilter = info -> {
    String className = info.serialClass() != null 
        ? info.serialClass().getName() 
        : null;
    
    if (className != null && className.startsWith("java.")) {
        return ObjectInputFilter.Status.ALLOWED;
    }
    
    return ObjectInputFilter.Status.REJECTED;
};
```

### Benefits

- **Security**: Prevents deserialization attacks
- **Control**: Fine-grained control over deserialization
- **Flexibility**: Multiple filter patterns
- **Performance**: Limits array size and depth

### Use Cases

- Secure deserialization
- Preventing deserialization attacks
- Controlling object graph complexity
- Limiting resource usage

**Note:** See [DeserializationFiltersDemo.java](deserialization/DeserializationFiltersDemo.java) for complete examples.

---

## 7. New macOS Rendering Pipeline

Java 17 introduces a new rendering pipeline based on Apple's Metal API.

### Overview

The new macOS rendering pipeline replaces the older OpenGL-based pipeline with Apple's Metal API, providing better performance and compatibility.

### Key Features

- **Metal-based**: Uses Apple's Metal API instead of OpenGL
- **Better performance**: Improved rendering performance on macOS
- **Modern API**: Aligned with Apple's direction
- **Future-proof**: Better compatibility with modern macOS versions

### Benefits

- Improved performance on macOS
- Enhanced compatibility with modern macOS versions
- Future-proof rendering pipeline
- Better integration with macOS graphics stack

### Impact

- Automatic for Java applications on macOS
- No code changes required
- Better performance out of the box

**Note:** This is an internal JVM improvement and doesn't require code changes.

---

## 8. macOS/AArch64 Port

Java 17 supports macOS running on Apple Silicon (M1) chips.

### Overview

The macOS/AArch64 port provides native support for Apple Silicon (M1, M2, etc.) processors, enabling Java applications to run natively on Apple Silicon Macs.

### Key Features

- **Native support**: Runs natively on Apple Silicon
- **Better performance**: Leverages full power of M1/M2 architecture
- **Optimized**: Optimized for ARM64 architecture
- **Full compatibility**: All Java features work on Apple Silicon

### Benefits

- Native support for Apple Silicon
- Better performance than Rosetta emulation
- Full utilization of M1/M2 architecture
- Streamlined development experience

### Usage

```bash
# Java 17 automatically detects and uses native ARM64 build
java -version
# Should show: ... aarch64 ... (on Apple Silicon)

# No special flags needed
java MyApplication
```

**Note:** This is a platform port and doesn't require code changes.

---

## 9. Remove RMI Activation

RMI Activation removed (deprecated since Java 14).

---

## 10. Deprecate Applet API

Applet API deprecated for removal (obsolete).

---

## 11. Deprecation for Removal of Finalization

Finalization mechanism is deprecated for future removal.

### Overview

Java 17 deprecates the `finalize()` method and finalization mechanism, signaling its future removal. This encourages developers to use better resource management alternatives.

### What is Finalization?

Finalization was a mechanism to clean up resources before an object is garbage-collected:

```java
// Deprecated in Java 17
public class Resource {
    @Deprecated(since = "17", forRemoval = true)
    protected void finalize() throws Throwable {
        // Cleanup code
        super.finalize();
    }
}
```

### Why Deprecate?

- **Unpredictable timing**: Finalization runs at unpredictable times
- **Performance overhead**: Adds overhead to garbage collection
- **Resource leaks**: Can cause resource leaks if not handled properly
- **Better alternatives**: Modern alternatives are available

### Alternatives

#### 1. Try-With-Resources (Recommended)

```java
// Use try-with-resources for automatic cleanup
try (FileInputStream fis = new FileInputStream("file.txt")) {
    // Use resource
} // Automatically closed
```

#### 2. Cleaner API (Java 9+)

```java
import java.lang.ref.Cleaner;

public class Resource {
    private static final Cleaner cleaner = Cleaner.create();
    
    private final Cleaner.Cleanable cleanable;
    
    public Resource() {
        this.cleanable = cleaner.register(this, () -> {
            // Cleanup code
        });
    }
}
```

#### 3. Phantom References

```java
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

// Use PhantomReference for cleanup
```

### Migration Path

1. **Identify finalize() usage**: Find all classes using `finalize()`
2. **Replace with try-with-resources**: For closeable resources
3. **Use Cleaner API**: For non-closeable resources
4. **Test thoroughly**: Ensure cleanup works correctly

### Benefits

- Better resource management
- Predictable cleanup timing
- Improved garbage collection performance
- Modern, recommended practices

**Note:** Finalization will be removed in a future Java version. Migrate now to avoid issues.

---

## 12. Strongly Encapsulate JDK Internals

Strong encapsulation of internal APIs.

- Access to internal APIs restricted
- Use `--add-opens` if needed
- Better security and maintainability

---

---

## 13. Performance Improvements

Java 17 includes numerous performance optimizations.

### Overview

Java 17 brings significant performance improvements across various areas, including garbage collection, JVM startup time, and application throughput.

### Key Improvements

#### Garbage Collection

- **G1 GC**: Further refinements and optimizations
- **ZGC**: Improved concurrent processing
- **Shenandoah**: Better pause time management
- **Parallel GC**: Enhanced for better throughput

#### JVM Startup

- Faster class loading
- Improved initialization
- Better module system performance
- Reduced startup overhead

#### Application Throughput

- Better compiler optimizations
- Improved inlining
- Enhanced escape analysis
- Better code generation

### Configuration

```bash
# Use G1 GC (default in many cases)
java -XX:+UseG1GC MyApp

# Use ZGC for low latency
java -XX:+UseZGC MyApp

# Use Shenandoah for consistent pause times
java -XX:+UseShenandoahGC MyApp
```

### Benefits

- Faster application startup
- Lower latency for high-performance applications
- Higher throughput
- Better resource utilization

### Use Cases

- Microservices (faster startup)
- High-performance applications
- Low-latency systems
- Large-scale applications

**Note:** These are internal JVM improvements and don't require code changes.

---

## 14. Common Interview Questions

### Q1: Why is Java 17 an LTS version?
**A:** LTS (Long-Term Support) means:
- Extended support period (Oracle: until September 2029)
- Important for enterprise applications
- Stability and security updates
- Critical for production systems

### Q2: How do Sealed Classes enable Pattern Matching?
**A:** Sealed classes allow exhaustive pattern matching:
- Compiler knows all possible subtypes
- Switch expressions can cover all cases
- No default case needed (when all types handled)
- Compile-time safety

### Q3: What is Pattern Matching for switch?
**A:** Pattern matching extends switch to:
- Work with types (instanceof pattern)
- Work with sealed classes (exhaustive matching)
- Use guards (when clauses)
- Bind variables automatically

---

**Last Updated:** 2024  
**Version:** 1.0

