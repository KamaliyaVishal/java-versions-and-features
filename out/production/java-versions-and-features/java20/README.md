# Java 20

A comprehensive guide to all Java 20 concepts with practical examples for interview preparation.

## Table of Contents

1. [Scoped Values (Incubator)](#1-scoped-values-incubator)
2. [Record Patterns (Second Preview)](#2-record-patterns-second-preview)
3. [Pattern Matching for switch (Fifth Preview)](#3-pattern-matching-for-switch-fifth-preview)
4. [Foreign Function & Memory API (Fifth Incubator)](#4-foreign-function--memory-api-fifth-incubator)
5. [Vector API (Fifth Incubator)](#5-vector-api-fifth-incubator)
6. [Structured Concurrency (Second Incubator)](#6-structured-concurrency-second-incubator)
7. [Virtual Threads (Second Preview)](#7-virtual-threads-second-preview)
8. [Common Interview Questions](#8-common-interview-questions)

---

## 1. Scoped Values (Incubator)

Immutable thread-local data sharing mechanism.

### Basic Usage

```java
import jdk.incubator.concurrent.ScopedValue;

// Define scoped value
final ScopedValue<String> USER = ScopedValue.newInstance();

// Set value in scope
ScopedValue.runWhere(USER, "Alice", () -> {
    // Access value
    String user = USER.get();
    System.out.println("User: " + user);
    
    // Nested scope
    ScopedValue.runWhere(USER, "Bob", () -> {
        System.out.println("User: " + USER.get());  // Bob
    });
    
    System.out.println("User: " + USER.get());  // Alice (back to outer scope)
});

// With virtual threads
try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
    ScopedValue.runWhere(USER, "Charlie", () -> {
        executor.submit(() -> {
            System.out.println("User: " + USER.get());  // Charlie inherited
        });
    });
}
```

### Benefits over ThreadLocal

- **Immutable**: Better safety (cannot be modified)
- **Inherited**: Automatically inherited by child threads (virtual threads)
- **More efficient**: Better performance than ThreadLocal
- **Structured scoping**: Clear scope boundaries
- **No memory leaks**: Automatic cleanup when scope ends

### Use Cases

- Request context in web applications
- User authentication context
- Transaction context
- Request tracing
- Configuration per request

**Note:** Requires `--add-modules jdk.incubator.concurrent` flag. See [ScopedValues.java](scopedvalues/ScopedValues.java) for complete examples.

---

## 2. Record Patterns (Second Preview)

Refinements to record patterns with improved destructuring.

### Overview

Record patterns allow you to destructure record values directly in pattern matching, making code more concise and readable.

### Basic Usage

```java
record Point(int x, int y) {}
record Rectangle(Point topLeft, Point bottomRight) {}

// Pattern matching with records
Object obj = new Point(5, 10);

if (obj instanceof Point(int x, int y)) {
    System.out.println("X: " + x + ", Y: " + y);
}

// Nested patterns
Rectangle rect = new Rectangle(new Point(0, 0), new Point(10, 10));

if (rect instanceof Rectangle(Point(int x1, int y1), Point(int x2, int y2))) {
    System.out.println("Width: " + (x2 - x1) + ", Height: " + (y2 - y1));
}

// In switch
String result = switch (obj) {
    case Point(int x, int y) when x > 0 && y > 0 -> 
        "Positive: (" + x + ", " + y + ")";
    case Point(int x, int y) -> 
        "Other: (" + x + ", " + y + ")";
    default -> "Not a point";
};
```

### Benefits

- Destructuring records directly
- More concise code
- Type-safe pattern matching
- Nested pattern support
- Works with guarded patterns

**Note:** Requires `--enable-preview` flag. See [RecordPatterns.java](recordpatterns/RecordPatterns.java) for complete examples.

---

## 3. Pattern Matching for switch (Fifth Preview)

Continued refinements to pattern matching in switch expressions.

### Overview

Pattern matching for switch continues to be refined, providing more powerful and concise code for type-based switching.

### Basic Usage

```java
// Enhanced pattern matching
String result = switch (obj) {
    case String s when s.length() > 10 -> "Long: " + s;
    case String s -> "Short: " + s;
    case Integer i when i > 100 -> "Large: " + i;
    case Integer i -> "Small: " + i;
    case null -> "Null";
    default -> "Unknown";
};
```

### With Sealed Classes

```java
sealed interface Shape permits Circle, Rectangle, Triangle {}

Shape shape = new Circle(5.0);

double area = switch (shape) {
    case Circle c -> Math.PI * c.radius() * c.radius();
    case Rectangle r -> r.width() * r.height();
    case Triangle t -> 0.5 * t.base() * t.height();
    // No default needed - exhaustive matching
};
```

### Guarded Patterns

```java
Object value = "Hello World";

String result = switch (value) {
    case String s when s.length() > 10 -> "Long string: " + s;
    case String s -> "Short string: " + s;
    case Integer i when i > 100 -> "Large number: " + i;
    case Integer i -> "Small number: " + i;
    default -> "Unknown";
};
```

### Benefits

- More concise code
- Exhaustive pattern matching
- Type-safe operations
- Better readability

**Note:** Requires `--enable-preview` flag. See [PatternMatchingSwitch.java](patternmatching/PatternMatchingSwitch.java) for complete examples.

---

## 4. Foreign Function & Memory API (Fifth Incubator)

Continued improvements to Foreign Function & Memory API.

### Overview

The Foreign Function & Memory API provides a way to call native code and access off-heap memory safely and efficiently.

### Basic Usage

```java
import jdk.incubator.foreign.*;

// Allocate native memory
try (Arena arena = Arena.ofConfined()) {
    MemorySegment segment = arena.allocate(100);
    
    // Write to memory
    segment.set(ValueLayout.JAVA_INT, 0, 42);
    
    // Read from memory
    int value = segment.get(ValueLayout.JAVA_INT, 0);
    System.out.println("Value: " + value);
}
// Memory automatically freed when arena is closed
```

### Features

- Safe memory access (bounds checking)
- Automatic resource management
- Type-safe operations
- Native function calls

### Use Cases

- Interfacing with native libraries
- High-performance memory operations
- System-level programming
- Zero-copy operations

**Note:** Requires `--add-modules jdk.incubator.foreign` and `--enable-preview` flags.

---

## 5. Vector API (Fifth Incubator)

Continued refinements to Vector API for SIMD operations.

### Overview

The Vector API provides SIMD-style operations for parallel processing of arrays, with hardware-optimized computations.

### Basic Usage

```java
import jdk.incubator.vector.*;

VectorSpecies<Float> species = FloatVector.SPECIES_PREFERRED;

float[] a = {1.0f, 2.0f, 3.0f, 4.0f};
float[] b = {5.0f, 6.0f, 7.0f, 8.0f};
float[] c = new float[4];

FloatVector va = FloatVector.fromArray(species, a, 0);
FloatVector vb = FloatVector.fromArray(species, b, 0);
FloatVector vc = va.add(vb);
vc.intoArray(c, 0);
// Result: c = [6.0f, 8.0f, 10.0f, 12.0f]
```

### Features

- Hardware-agnostic API
- Automatic optimization
- Type-safe operations
- SIMD operations

### Use Cases

- Scientific computing
- Machine learning
- Image processing
- Signal processing
- Numerical simulations

**Note:** Requires `--add-modules jdk.incubator.vector` and `--enable-preview` flags.

---

## 6. Structured Concurrency (Second Incubator)

Refinements to structured concurrency with improved task coordination.

### Overview

Structured Concurrency simplifies multithreaded programming by treating groups of tasks as a single unit of work.

### Basic Usage

```java
import jdk.incubator.concurrent.StructuredTaskScope;

try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    Future<String> user = scope.fork(() -> fetchUser());
    Future<String> order = scope.fork(() -> fetchOrder());
    
    scope.join();  // Wait for all tasks
    scope.throwIfFailed();  // Throw if any failed
    
    // Use results
    String userResult = user.resultNow();
    String orderResult = order.resultNow();
}
// Automatic cleanup if any task fails
```

### Benefits

- Automatic cancellation of subtasks
- Exception propagation
- Better error handling
- Structured lifecycle
- Improved observability

### Use Cases

- Parallel API calls
- Concurrent data fetching
- Task coordination
- Error handling in concurrent code

**Note:** Requires `--add-modules jdk.incubator.concurrent` and `--enable-preview` flags. See [StructuredConcurrencyDemo.java](structuredconcurrency/StructuredConcurrencyDemo.java) for complete examples.

---

## 7. Virtual Threads (Second Preview)

Refinements to virtual threads with improved performance and features.

### Overview

Virtual threads are lightweight threads managed by the JVM, perfect for high-throughput concurrent applications.

### Basic Usage

```java
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

// Create virtual thread
Thread virtualThread = Thread.ofVirtual().start(() -> {
    System.out.println("Running on virtual thread");
});

// Using executor
try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
    for (int i = 0; i < 1000; i++) {
        executor.submit(() -> {
            // I/O operation
            Thread.sleep(100);
        });
    }
}
// Can handle millions of concurrent operations
```

### Characteristics

- **Lightweight**: Millions can be created
- **Managed by JVM**: Not OS threads
- **Blocking operations**: Don't block OS thread
- **Perfect for I/O**: Ideal for I/O-bound operations

### Use Cases

- High-throughput servers
- I/O-bound applications
- Microservices
- Concurrent request handling
- Async operations

**Note:** Requires `--enable-preview` flag. See [VirtualThreadsDemo.java](virtualthreads/VirtualThreadsDemo.java) for complete examples.

---

## 8. Common Interview Questions

### Q1: What are Scoped Values and how do they differ from ThreadLocal?
**A:**
- **Scoped Values**: Immutable, inherited by child threads, structured scoping
- **ThreadLocal**: Mutable, not inherited, less efficient
- Scoped Values are better for virtual threads

### Q2: What is the difference between Scoped Values and ThreadLocal?
**A:**
- Scoped Values: Immutable, inherited, more efficient
- ThreadLocal: Mutable, not inherited, traditional approach

---

**Last Updated:** 2024  
**Version:** 1.0

