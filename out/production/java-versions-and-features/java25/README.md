# Java 25

A comprehensive guide to all Java 25 concepts with practical examples for interview preparation.

## Table of Contents

1. [Instance Main Methods (JEP 512)](#1-instance-main-methods-jep-512)
2. [Flexible Constructor Bodies (JEP 513)](#2-flexible-constructor-bodies-jep-513)
3. [Enhanced Pattern Matching](#3-enhanced-pattern-matching)
4. [Scoped Values (JEP 506)](#4-scoped-values-jep-506)
5. [Key Derivation Function API (JEP 510)](#5-key-derivation-function-api-jep-510)
6. [Compact Object Headers (JEP 519)](#6-compact-object-headers-jep-519)
7. [Generational Shenandoah GC (JEP 521)](#7-generational-shenandoah-gc-jep-521)
8. [Vector API (JEP 508 - Tenth Incubator)](#8-vector-api-jep-508---tenth-incubator)
9. [Stream Gatherers (JEP 505 - Fifth Preview)](#9-stream-gatherers-jep-505---fifth-preview)
10. [Enhanced JFR Capabilities (JEP 509)](#10-enhanced-jfr-capabilities-jep-509)
11. [AOT Compilation Enhancements](#11-aot-compilation-enhancements)
12. [Foreign Function and Memory API Enhancements](#12-foreign-function-and-memory-api-enhancements)
13. [Module Import Declarations (JEP 511)](#13-module-import-declarations-jep-511)
14. [Removal of 32-bit x86 Support (JEP 503)](#14-removal-of-32-bit-x86-support-jep-503)
15. [Common Interview Questions](#15-common-interview-questions)

---

## 1. Instance Main Methods (JEP 512)

Simplified main method declaration (builds on Java 24 unnamed classes). This feature is finalized in Java 25.

### Overview

Instance Main Methods allow you to declare main methods without the `static` keyword, enabling access to instance fields and methods directly from the entry point.

### Basic Usage

```java
// Instance main method (no static keyword)
void main() {
    System.out.println("Hello, World!");
}

// Can access instance members
String greeting = "Hello";

void main() {
    System.out.println(greeting);
    displayMessage();
}

void displayMessage() {
    System.out.println("Message from instance method");
}

// With command-line arguments
void main(String[] args) {
    for (String arg : args) {
        System.out.println("Arg: " + arg);
    }
}
```

### Benefits

- No need for `static` keyword
- Can access instance fields and methods
- Simpler entry point
- Better for learning Java
- Perfect for simple programs
- Finalized feature (no preview flag needed)

**See [InstanceMainMethods.java](instancemain/InstanceMainMethods.java) for complete example.**

---

## 2. Flexible Constructor Bodies (JEP 513)

Statements allowed before `super()` or `this()` call.

```java
class Parent {
    Parent(String name) {
        System.out.println("Parent: " + name);
    }
}

class Child extends Parent {
    private String processedName;
    
    Child(String name) {
        // Statements before super() - now allowed
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        
        this.processedName = name.trim().toUpperCase();
        String validated = validate(processedName);
        
        super(validated);  // Now allowed after statements
        
        // After super()
        initialize();
    }
    
    private String validate(String s) {
        return s.length() > 0 ? s : "Default";
    }
    
    private void initialize() {
        System.out.println("Initialized: " + processedName);
    }
}

// Also works with this()
class Point {
    private int x, y;
    
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    Point(int value) {
        // Statements before this()
        int validated = value > 0 ? value : 0;
        this(validated, validated);  // Now allowed
    }
}
```

### Benefits

- Validate/preprocess before super() call
- Better constructor organization
- More flexible initialization
- Can throw exceptions before super()
- Finalized feature (no preview flag needed)

**See [FlexibleConstructorBodies.java](constructors/FlexibleConstructorBodies.java) for complete example.**

---

## 3. Enhanced Pattern Matching

Further refinements to pattern matching capabilities with advanced features finalized in Java 25.

### Overview

Enhanced Pattern Matching provides more powerful pattern matching capabilities, including nested patterns, record patterns, and primitive type patterns.

### Advanced Patterns

```java
// Enhanced pattern matching
record Point(int x, int y) {}
record Rectangle(Point topLeft, Point bottomRight) {}

// Complex nested patterns
Rectangle rect = new Rectangle(new Point(0, 0), new Point(10, 10));

String result = switch (rect) {
    case Rectangle(Point(int x1, int y1), Point(int x2, int y2)) 
        when x2 > x1 && y2 > y1 ->
        "Valid rectangle: width=" + (x2 - x1) + ", height=" + (y2 - y1);
    case Rectangle(Point(_, _), Point(_, _)) ->
        "Rectangle with zero or negative dimensions";
    default -> "Not a rectangle";
};

// Primitive type patterns
Object obj = 42;

if (obj instanceof int i && i > 0) {
    System.out.println("Positive integer: " + i);
}

// Pattern matching in instanceof
String result = obj instanceof Integer i 
    ? "Integer: " + i 
    : "Not an integer";
```

### Benefits

- More concise code
- Better type safety
- Exhaustive pattern matching (with sealed classes)
- Nested patterns for complex data structures
- Finalized features (no preview flag needed)

**See [EnhancedPatternMatching.java](patternmatching/EnhancedPatternMatching.java) for complete example.**

---

## 4. Scoped Values (JEP 506)

Scoped Values are finalized in Java 25, providing a safer and more efficient alternative to ThreadLocal.

### Overview

Scoped Values offer a controlled way to pass context data within and across threads, with immutable values that are automatically inherited by child threads.

```java
import java.util.concurrent.ScopedValue;

final ScopedValue<String> USER = ScopedValue.newInstance();
final ScopedValue<Integer> REQUEST_ID = ScopedValue.newInstance();

// Multiple scoped values
ScopedValue.runWhere(
    USER, "Alice",
    REQUEST_ID, 12345,
    () -> {
        String user = USER.get();
        int id = REQUEST_ID.get();
        
        System.out.println("User: " + user + ", Request ID: " + id);
        
        // Nested scope
        ScopedValue.runWhere(USER, "Bob", () -> {
            System.out.println("User: " + USER.get() + ", Request ID: " + REQUEST_ID.get());
        });
    }
);
```

### Key Features

- Immutable values
- Inherited by child threads
- Automatic cleanup
- Better performance than ThreadLocal
- Structured scoping
- Finalized feature (no preview flag needed)

### Benefits

- Safer than ThreadLocal (immutable by design)
- No memory leaks (automatic cleanup)
- Inherited by child threads (including virtual threads)
- Better performance
- Structured scoping
- Production-ready (finalized)

**See [ScopedValues.java](scopedvalues/ScopedValues.java) for complete example.**

---

## 5. Key Derivation Function API (JEP 510)

A standardized API for cryptographic key derivation functions, such as PBKDF2, enhancing security practices.

### Overview

Java 25 introduces a standardized API for key derivation functions, allowing developers to implement password-based encryption without external libraries.

```java
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

// Derive key from password
KeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
byte[] key = factory.generateSecret(spec).getEncoded();
```

### Key Features

- Standardized API for key derivation
- Support for PBKDF2 and other algorithms
- Password-based key derivation
- Configurable iterations
- Salt-based security
- Finalized feature (no preview flag needed)

### Benefits

- No external libraries needed
- Standardized implementation
- Better security practices
- Improved interoperability
- Production-ready (finalized)

### Use Cases

- Password-based encryption
- Secure key storage
- Cryptographic key derivation
- Authentication systems

**See [KeyDerivationFunctionAPI.java](crypto/KeyDerivationFunctionAPI.java) for complete example.**

---

## 6. Compact Object Headers (JEP 519)

Reduces memory overhead per object by compacting object headers.

### Overview

Compact Object Headers reduce the memory footprint of objects by optimizing the object header structure, leading to better memory utilization and performance.

### Key Features

- Reduced memory overhead per object
- Better memory utilization
- Improved performance
- Especially beneficial for applications with many objects
- Automatic optimization

### Benefits

- Lower memory footprint
- Better performance
- More efficient memory usage
- Automatic (no code changes needed)

### Use Cases

- Applications with many small objects
- Memory-constrained environments
- High-performance applications
- Long-running applications

**Note:** This is a JVM-level optimization that doesn't require code changes.

---

## 7. Generational Shenandoah GC (JEP 521)

The Shenandoah GC introduces generational collection, improving throughput and pause times.

### Overview

Generational Shenandoah GC enhances the Shenandoah garbage collector by introducing generational capabilities, separating young and old objects for more efficient collection.

### Key Features

- Generational collection
- Improved throughput
- Better pause times
- Concurrent collection
- Efficient memory management

### Benefits

- Better throughput
- Lower pause times
- More efficient memory utilization
- Concurrent collection
- Better for large heaps

### Configuration

```bash
# Enable Generational Shenandoah
-XX:+UseShenandoahGC -XX:+ShenandoahGCMode=generational

# Tune generation sizes
-XX:ShenandoahYoungGenerationSize=2G
```

### Use Cases

- Large heap applications
- Low-latency requirements
- High-throughput applications
- Applications with mixed object lifetimes

**Note:** This is a JVM-level feature that doesn't require code changes.

---

## 8. Vector API (JEP 508 - Tenth Incubator)

Continued improvements to Vector API with enhanced SIMD operations.

### Overview

The Vector API provides SIMD-style operations for parallel processing of arrays, with hardware-optimized computations that can significantly improve performance for data-parallel operations.

```java
import jdk.incubator.vector.*;

VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;
FloatVector va = FloatVector.fromArray(SPECIES, a, 0);
FloatVector vb = FloatVector.fromArray(SPECIES, b, 0);
FloatVector vc = va.add(vb);
vc.intoArray(c, 0);
```

### Key Features

- Hardware-agnostic: Works on different platforms
- Automatic optimization: Compiles to optimal instructions
- Type-safe: Supports int, long, float, double
- SIMD operations: Parallel processing of multiple elements
- Tenth incubator iteration (continued refinement)

### Use Cases

- Scientific computing
- Machine learning
- Image processing
- Signal processing
- Numerical simulations
- Cryptography
- AI inference

**See [VectorAPIDemo.java](vector/VectorAPIDemo.java) for complete example.**

---

## 9. Stream Gatherers (JEP 505 - Fifth Preview)

Custom intermediate stream operations that extend the Stream API functionality.

### Overview

Stream Gatherers allow you to create custom intermediate operations for streams, providing more flexibility than existing operations.

```java
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;

// Custom gatherer
Gatherer<String, ?, String> filterAndUpper = Gatherer.ofSequential(...);

// Use gatherer
List<String> result = Stream.of("apple", "banana", "cherry")
    .gather(filterAndUpper)
    .toList();
```

### Built-in Gatherers

- `windowSliding(n)`: Sliding window
- `windowFixed(n)`: Fixed window
- `fold()`: Accumulate values
- `scan()`: Scan with intermediate results

### Benefits

- Extend Stream API
- Create reusable operations
- More expressive code
- Better performance for custom operations

**See [StreamGatherersDemo.java](streamgatherers/StreamGatherersDemo.java) for complete example.**

---

## 10. Enhanced JFR Capabilities (JEP 509)

Java Flight Recorder improvements with CPU-time profiling and enhanced diagnostics.

### Overview

Java Flight Recorder now offers CPU-time profiling, cooperative sampling improvements, and method timing and tracing, providing developers with detailed insights into application performance.

### New Features

- **CPU-time profiling on Linux**: More accurate CPU time measurements
- **Ahead-of-time method profiling**: Profile methods before they're called
- **Cooperative sampling improvements**: Better sampling accuracy
- **Method timing and tracing**: Detailed method-level performance data
- **Better diagnostics**: Enhanced runtime diagnostics

### Benefits

- CPU-time profiling on Linux
- Ahead-of-time method profiling
- Better runtime diagnostics
- Enhanced performance analysis
- More granular insights into application performance

### Use Cases

- Performance analysis
- Application profiling
- Performance debugging
- Production monitoring
- Performance optimization

**Note:** This is a JVM-level feature that doesn't require code changes.

---

## 11. AOT Compilation Enhancements

Ahead-of-Time (AOT) compilation improvements for faster startup times.

### Overview

Java 25 introduces AOT method profiling and improved command-line ergonomics, significantly reducing startup and warm-up times, which is particularly beneficial for cloud-native applications.

### Key Features

- AOT method profiling
- Improved command-line ergonomics
- Faster application startup
- Reduced warm-up times
- Better for short-lived applications

### Benefits

- Faster application startup
- Reduced JIT compilation overhead
- Better performance for short-lived applications
- Improved user experience
- Lower latency

### Use Cases

- Short-lived applications
- Serverless functions
- Command-line tools
- Microservices
- Applications requiring fast startup

### Configuration

```bash
# Enable AOT compilation
java -XX:+UseAOT -XX:AOTLibrary=app.aotlib MyApp

# Generate AOT library
jaotc --output app.aotlib --module java.base MyApp.class
```

**Note:** This is a JVM-level feature that doesn't require code changes.

---

## 12. Foreign Function and Memory API Enhancements

Improvements to FFI API.

```java
import java.lang.foreign.*;

// Enhanced foreign function interface
// Better performance
// More capabilities

// Link with native mathematical libraries
Linker linker = Linker.nativeLinker();

// Example: Call math library functions
SymbolLookup mathLib = linker.defaultLookup();

MethodHandle sqrt = linker.downcallHandle(
    mathLib.find("sqrt").orElseThrow(),
    FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
);

try (Arena arena = Arena.ofConfined()) {
    double result = (double) sqrt.invoke(16.0);
    System.out.println("Square root: " + result);  // 4.0
}
```

### Benefits

- Link to native mathematical libraries
- Better maintainability
- Improved performance
- Safe memory management

---

## 13. Module Import Declarations (JEP 511)

Module import declarations are finalized in Java 25, simplifying module dependencies.

### Overview

Module Import Declarations allow you to import all packages exported by a module with a single declaration, simplifying module dependencies.

```java
// Module imports (finalized)
import module java.base;
import module java.sql;
import module java.nio.file;

module com.example.app {
    // Imported modules are available
    exports com.example.app.api;
}
```

### Benefits

- Simpler module syntax
- Cleaner module descriptors
- Easier to read and maintain
- Reduces boilerplate
- Production-ready (finalized)

---

## 14. Removal of 32-bit x86 Support (JEP 503)

Java 25 discontinues support for 32-bit x86 architectures.

### Overview

Java 25 removes support for 32-bit x86 systems, focusing on modern, 64-bit architectures to optimize performance and maintainability.

### Impact

- 32-bit x86 support is removed
- Focus on 64-bit architectures
- Better performance on modern systems
- Simplified maintenance

### Migration

- Ensure applications run on 64-bit systems
- Update build and deployment processes
- Test on 64-bit platforms

### Benefits

- Focuses resources on modern architectures
- Aligns with industry standards
- Simplifies maintenance
- Better performance on 64-bit systems

**Note:** Developers should ensure their applications are compatible with 64-bit systems.

---

## 15. Common Interview Questions

### Q1: What are the key features of Java 25?
**A:** Key features:
- Instance main methods (no static)
- Flexible constructor bodies
- Enhanced pattern matching
- Scoped values enhancements
- FFI API improvements
- Enhanced JFR capabilities

### Q2: How do Flexible Constructor Bodies work?
**A:** Allow statements before `super()` or `this()`:
- Validate/preprocess before calling super()
- Can throw exceptions before super()
- Better constructor organization
- More flexible initialization

### Q3: What are Instance Main Methods?
**A:** Main methods without `static` keyword:
- Can access instance members
- Simpler entry point
- Better for learning Java
- Perfect for simple programs

### Q4: What are the benefits of Enhanced Pattern Matching?
**A:**
- More concise code
- Better type safety
- Exhaustive pattern matching (with sealed classes)
- Nested patterns for complex data structures

### Q5: How do Scoped Values improve over ThreadLocal?
**A:**
- **Scoped Values**: Immutable, inherited by child threads, more efficient
- **ThreadLocal**: Mutable, not inherited, traditional approach
- Scoped Values work better with virtual threads
- Structured scoping model

### Q6: What is the Key Derivation Function API?
**A:** Standardized API for key derivation:
- Support for PBKDF2 and other algorithms
- Password-based key derivation
- No external libraries needed
- Better security practices
- Finalized in Java 25 (JEP 510)

### Q7: What are Compact Object Headers?
**A:** JVM optimization:
- Reduces memory overhead per object
- Better memory utilization
- Improved performance
- Automatic (no code changes needed)
- Especially beneficial for applications with many objects

### Q8: What is Generational Shenandoah GC?
**A:** Enhanced Shenandoah GC:
- Introduces generational collection
- Better throughput
- Lower pause times
- Concurrent collection
- Better for large heaps

### Q9: What are Stream Gatherers?
**A:** Custom intermediate stream operations:
- Extend Stream API functionality
- Create reusable operations
- Built-in gatherers available (windowSliding, windowFixed, fold)
- More flexible than existing operations
- Fifth preview in Java 25

### Q10: What improvements were made to JFR?
**A:** Enhanced JFR capabilities:
- CPU-time profiling on Linux
- Ahead-of-time method profiling
- Cooperative sampling improvements
- Method timing and tracing
- Better runtime diagnostics

---

## Quick Reference Cheat Sheet

### Instance Main Methods
```java
void main() {
    // No static keyword needed
}
```

### Flexible Constructor Bodies
```java
Child(String name) {
    // Statements allowed
    String validated = validate(name);
    super(validated);  // Now allowed
}
```

### Enhanced Pattern Matching
```java
switch (obj) {
    case Point(int x, int y) when x > 0 && y > 0 -> ...;
    case Point(int x, int y) -> ...;
}
```

### Scoped Values
```java
ScopedValue.runWhere(USER, "Alice", () -> {
    String user = USER.get();
});
```

---

**Last Updated:** 2025  
**Version:** 1.0

*This guide is designed for quick reference during interview preparation. Practice the examples and understand the concepts thoroughly.*

