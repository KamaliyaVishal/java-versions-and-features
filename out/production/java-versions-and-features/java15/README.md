# Java 15

A comprehensive guide to all Java 15 concepts with practical examples for interview preparation.

## Table of Contents

1. [Sealed Classes (Preview)](#1-sealed-classes-preview)
2. [Hidden Classes](#2-hidden-classes)
3. [Text Blocks (Finalized)](#3-text-blocks-finalized)
4. [Records (Second Preview)](#4-records-second-preview)
5. [Pattern Matching for instanceof (Second Preview)](#5-pattern-matching-for-instanceof-second-preview)
6. [Foreign-Memory Access API (Second Incubator)](#6-foreign-memory-access-api-second-incubator)
7. [Edwards-Curve Digital Signature Algorithm](#7-edwards-curve-digital-signature-algorithm)
8. [ZGC Production Ready](#8-zgc-production-ready)
9. [Shenandoah GC Production Ready](#9-shenandoah-gc-production-ready)
10. [Removed and Deprecated Features](#10-removed-and-deprecated-features)
11. [Common Interview Questions](#11-common-interview-questions)

---

## 1. Sealed Classes (Preview)

Classes that restrict which classes can extend them.

### Basic Sealed Class

```java
// Sealed class - only permits specific classes to extend
public sealed class Shape 
    permits Circle, Rectangle, Triangle {
    // Class definition
}

// Permitted subclasses
public final class Circle extends Shape {
    private final double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
}

public final class Rectangle extends Shape {
    private final double width, height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
}

public final class Triangle extends Shape {
    private final double base, height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
}

// Other classes cannot extend Shape
// public class Polygon extends Shape {} // Compile error
```

### Sealed Interface

```java
public sealed interface Vehicle 
    permits Car, Truck, Motorcycle {
    void start();
}

public final class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car starting");
    }
}

public final class Truck implements Vehicle {
    @Override
    public void start() {
        System.out.println("Truck starting");
    }
}

public final class Motorcycle implements Vehicle {
    @Override
    public void start() {
        System.out.println("Motorcycle starting");
    }
}
```

### Permitted Classes Must Be

- `final`: Cannot be extended
- `sealed`: Can be further restricted
- `non-sealed`: Can be extended by anyone

```java
public sealed class Animal 
    permits Dog, Cat, Bird {
}

public final class Dog extends Animal {}  // Final - cannot extend

public sealed class Cat extends Animal 
    permits Persian, Siamese {  // Sealed - further restricted
}

public non-sealed class Bird extends Animal {}  // Non-sealed - can extend freely

public class Parrot extends Bird {}  // OK - Bird is non-sealed
```

### Benefits

- Exhaustive pattern matching (switch knows all possible types)
- Better API design (restricted inheritance)
- Improved type safety
- Document intended hierarchy

---

## 2. Hidden Classes

Classes that cannot be used directly by other classes' bytecode.

### Overview

Hidden classes are classes that cannot be used directly by the bytecode of other classes. They are intended for use by frameworks that generate classes at runtime and use them indirectly through reflection.

### Characteristics

- Cannot be linked by other classes
- Not discoverable by reflection (unless explicitly allowed)
- Can be unloaded independently
- Created at runtime
- Not part of the package hierarchy

### Basic Usage

```java
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;

// Hidden classes are typically created by frameworks
MethodHandles.Lookup lookup = MethodHandles.lookup();
Class<?> hiddenClass = lookup.defineHiddenClass(byteCode, true).lookupClass();

// Hidden class can be used via reflection or method handles
// But not directly referenced in bytecode
```

### Use Cases

- Frameworks loading classes dynamically
- Language runtimes (e.g., lambdas, proxies)
- Application servers
- Dynamic code generation
- Performance optimization (faster class loading)

### Benefits

- Better encapsulation
- Can be unloaded when not needed
- Faster class loading
- Framework-friendly API

**Note:** For detailed documentation and examples, see [Hidden Classes Guide](hiddenclasses/HiddenClassesGuide.md).

---

## 3. Text Blocks (Finalized)

Text blocks are now a standard feature (no longer preview).

### Overview

Text blocks provide multi-line string literals without the need for escape sequences, improving code readability.

### Basic Usage

```java
// Finalized - no longer preview, no --enable-preview needed
String html = """
              <html>
                  <body>
                      <p>Hello, World!</p>
                  </body>
              </html>
              """;

// JSON example
String json = """
              {
                  "name": "John",
                  "age": 30,
                  "city": "New York"
              }
              """;

// SQL example
String sql = """
              SELECT id, name, email
              FROM users
              WHERE status = 'active'
              ORDER BY name
              """;
```

### Escape Sequences

```java
// Quotes don't need escaping (except triple quotes)
String text = """
              She said "Hello"
              """;

// Escape newline (suppress line break)
String text2 = """
                Line 1 \
                Line 2
                """;  // Results in "Line 1 Line 2"

// Standard escape sequences work
String text3 = """
                Tab:\tTab
                Newline:\nNewline
                """;
```

### Benefits

- Better readability for multi-line strings
- Preserves formatting
- Easier to write HTML, JSON, SQL, etc.
- Reduces escape sequences needed
- Standard feature (no preview flag)

---

## 4. Records (Second Preview)

Refinements to records feature based on feedback.

### Overview

Records provide a compact syntax for declaring classes that are transparent holders for shallowly immutable data.

### Basic Record

```java
// Simple record
public record Point(int x, int y) {}

// Usage
Point point = new Point(3, 4);
System.out.println(point.x());  // 3
System.out.println(point.y());  // 4
System.out.println(point);      // Point[x=3, y=4]
```

### Record with Validation

```java
public record Person(String name, int age) {
    // Compact constructor - validates before assignment
    public Person {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
    }
    
    // Additional methods
    public boolean isAdult() {
        return age >= 18;
    }
    
    // Static methods
    public static Person of(String name, int age) {
        return new Person(name, age);
    }
}
```

### Record Characteristics

- Immutable by default
- All fields are final
- Automatic equals(), hashCode(), toString()
- Accessor methods (fieldName(), not getFieldName())
- Can have constructors, methods, static methods
- Cannot extend other classes (implicitly extends Record)
- Can implement interfaces

---

## 5. Pattern Matching for instanceof (Second Preview)

Refinements to pattern matching based on feedback.

### Overview

Pattern matching for `instanceof` simplifies the common pattern of checking an object's type and casting it.

### Basic Usage

```java
Object obj = "Hello";

// Pattern matching - automatic casting
if (obj instanceof String str) {
    // str is automatically cast and available
    System.out.println(str.toUpperCase());
    System.out.println(str.length());
}
```

### With Conditions

```java
Object value = "Hello World";

// Combine pattern matching with conditions
if (value instanceof String str && str.length() > 5) {
    System.out.println("Long string: " + str);
}
```

### With Negation

```java
Object obj = "Hello";

if (!(obj instanceof String str)) {
    System.out.println("Not a string");
} else {
    // str is available in else block
    System.out.println(str.length());
}
```

### Benefits

- Eliminates manual casting
- Reduces boilerplate
- Prevents cast errors
- Improves readability
- Type-safe operations

---

## 6. Foreign-Memory Access API (Second Incubator)

API for safely accessing memory outside the Java heap.

### Overview

The Foreign-Memory Access API (second incubator) provides a way to access native memory safely and efficiently, useful for interop with native libraries.

### Basic Usage

```java
import jdk.incubator.foreign.*;

// Allocate native memory
try (var scope = MemorySession.openConfined()) {
    MemorySegment segment = MemorySegment.allocateNative(1024, scope);
    
    // Use the memory segment
    MemoryAccess.setIntAtOffset(segment, 0, 42);
    int value = MemoryAccess.getIntAtOffset(segment, 0);
    System.out.println(value); // 42
}
// Memory automatically freed when scope is closed
```

### Features

- Safe memory access (bounds checking)
- Automatic resource management
- Type-safe operations
- Useful for native library interop

### Use Cases

- Interfacing with native libraries
- High-performance memory operations
- System-level programming
- Zero-copy operations

**Note:** This is an incubator feature and requires `--add-modules jdk.incubator.foreign` and `--enable-preview` flags.

---

## 7. Edwards-Curve Digital Signature Algorithm

New cryptographic algorithm support for EdDSA (Edwards-Curve Digital Signature Algorithm).

### Overview

Java 15 adds support for the Edwards-Curve Digital Signature Algorithm (EdDSA), providing better security and performance for digital signatures.

### Supported Algorithms

- **Ed25519**: 255-bit curve
- **Ed448**: 448-bit curve

### Basic Usage

```java
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

// Generate EdDSA key pair
KeyPairGenerator keyGen = KeyPairGenerator.getInstance("Ed25519");
KeyPair keyPair = keyGen.generateKeyPair();

// Sign data
Signature signature = Signature.getInstance("Ed25519");
signature.initSign(keyPair.getPrivate());
byte[] data = "Hello, Java 15!".getBytes();
signature.update(data);
byte[] signatureBytes = signature.sign();

// Verify signature
signature.initVerify(keyPair.getPublic());
signature.update(data);
boolean verified = signature.verify(signatureBytes);
System.out.println("Signature verified: " + verified);
```

### Benefits

- Better security
- Improved performance
- Smaller signature sizes
- Modern cryptographic algorithm

### Use Cases

- Digital signatures
- Authentication
- Secure communications
- Certificate generation

---

## 8. ZGC Production Ready

Z Garbage Collector is now production-ready (no longer experimental).

### Overview

ZGC (Z Garbage Collector) is a scalable, low-latency garbage collector designed for applications that require low pause times and large heap sizes.

### Usage

```bash
# Use Z GC (production ready - no experimental flag needed)
java -XX:+UseZGC MainClass

# With additional options
java -XX:+UseZGC \
     -XX:MaxGCPauseMillis=10 \
     -Xmx16g \
     MainClass
```

### Key Features

- **Low Pause Times**: Sub-millisecond pause times (< 10ms)
- **Large Heaps**: Scales to multi-terabyte heaps
- **Concurrent**: Most operations are concurrent
- **Production Ready**: No longer experimental

### Configuration Options

- `-XX:+UseZGC`: Enable Z Garbage Collector
- `-XX:MaxGCPauseMillis=<ms>`: Target maximum pause time
- `-XX:ZUncommitDelay=<seconds>`: Delay before uncommitting unused memory

### Benefits

- Very low pause times
- Handles large heaps efficiently
- Concurrent operations
- Production-ready stability
- Suitable for latency-sensitive applications

### Use Cases

- Low-latency applications
- Large heap applications
- Real-time systems
- High-throughput applications

---

## 9. Shenandoah GC Production Ready

Shenandoah Garbage Collector is now production-ready (no longer experimental).

### Overview

Shenandoah is a low-pause-time garbage collector designed for applications that require consistent pause times regardless of heap size.

### Usage

```bash
# Use Shenandoah GC (production ready - no experimental flag needed)
java -XX:+UseShenandoahGC MainClass

# With additional options
java -XX:+UseShenandoahGC \
     -XX:MaxGCPauseMillis=10 \
     -Xmx16g \
     MainClass
```

### Key Features

- **Low Pause Times**: Consistent pause times (< 10ms) independent of heap size
- **Concurrent Evacuation**: Moves objects concurrently with application threads
- **Large Heaps**: Efficiently handles multi-gigabyte heaps
- **Production Ready**: No longer experimental

### Configuration Options

- `-XX:+UseShenandoahGC`: Enable Shenandoah Garbage Collector
- `-XX:MaxGCPauseMillis=<ms>`: Target maximum pause time
- `-XX:ShenandoahGCHeuristics=<heuristic>`: Choose GC heuristic

### Benefits

- Consistent low pause times
- Independent of heap size
- Concurrent evacuation
- Production-ready stability
- Suitable for latency-sensitive applications

### Use Cases

- Low-latency applications
- Large heap applications
- Real-time systems
- Applications requiring consistent pause times

### Comparison: ZGC vs Shenandoah

| Feature | ZGC | Shenandoah |
|---------|-----|------------|
| Pause Times | < 10ms | < 10ms |
| Heap Size | Multi-terabyte | Multi-gigabyte |
| Concurrent | Yes | Yes |
| Production Ready | Java 15 | Java 15 |

---

## 10. Removed and Deprecated Features

### Removed Features

#### Nashorn JavaScript Engine

The Nashorn JavaScript Engine has been **removed** from Java 15.

**Impact:**
- `jdk.scripting.nashorn` module no longer available
- `jjs` tool removed
- JavaScript execution in Java no longer supported

**Migration:**
- Use GraalVM JavaScript engine
- Use external JavaScript engines (Rhino, etc.)
- Consider alternative scripting solutions

#### Solaris and SPARC Ports

Support for Solaris and SPARC platforms has been removed.

**Impact:**
- No JDK builds for Solaris/SPARC
- Applications must migrate to other platforms

### Deprecated Features

#### RMI Activation System

The Remote Method Invocation (RMI) Activation System has been **deprecated** for removal in a future release.

**Impact:**
- `java.rmi.activation` package deprecated
- RMI activation will be removed in a future Java version

**Migration:**
- Use RMI without activation
- Consider alternative distributed computing solutions
- Plan migration before removal

### Deprecation Warnings

When using deprecated features, you'll see warnings like:
```
warning: [deprecation] RMI Activation is deprecated and will be removed
```

**Recommendation:** Update code to remove deprecated features before they are removed.

---

## 11. Common Interview Questions

### Q1: What are Sealed Classes and why use them?
**A:** Sealed classes restrict which classes can extend them:
- Exhaustive pattern matching (switch knows all possible types)
- Better API design (restricted inheritance)
- Improved type safety
- Document intended class hierarchy

### Q2: What are the requirements for permitted classes?
**A:** Permitted classes must be:
- `final`: Cannot be extended
- `sealed`: Can be further restricted
- `non-sealed`: Can be extended by anyone

### Q3: How do Sealed Classes help with Pattern Matching?
**A:** The compiler knows all possible subtypes, enabling exhaustive pattern matching in switch expressions without default case (when all types are handled).

---

**Last Updated:** 2024  
**Version:** 1.0

