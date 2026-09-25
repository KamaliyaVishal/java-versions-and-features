# Java 19

A comprehensive guide to all Java 19 concepts with practical examples for interview preparation.

## Table of Contents

1. [Virtual Threads (Preview)](#1-virtual-threads-preview)
2. [Structured Concurrency (Incubator)](#2-structured-concurrency-incubator)
3. [Record Patterns (Preview)](#3-record-patterns-preview)
4. [Pattern Matching for switch (Fourth Preview)](#4-pattern-matching-for-switch-fourth-preview)
5. [Foreign Function & Memory API (Fourth Incubator)](#5-foreign-function--memory-api-fourth-incubator)
6. [Vector API (Fourth Incubator)](#6-vector-api-fourth-incubator)
7. [Linux/RISC-V Port](#7-linuxrisc-v-port)
8. [Common Interview Questions](#8-common-interview-questions)

---

## 1. Virtual Threads (Preview)

Lightweight threads for high-throughput concurrent applications.

### Creating Virtual Threads

```java
import java.util.concurrent.Executors;

// Create virtual thread
Thread virtualThread = Thread.ofVirtual().start(() -> {
    System.out.println("Running on virtual thread");
});

// Using executor
try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
    executor.submit(() -> {
        System.out.println("Task 1");
    });
    
    executor.submit(() -> {
        System.out.println("Task 2");
    });
}

// Builder pattern
Thread.Builder builder = Thread.ofVirtual().name("worker-", 0);
Thread vt1 = builder.start(() -> System.out.println("Task 1"));
Thread vt2 = builder.start(() -> System.out.println("Task 2"));
```

### Characteristics

- Lightweight (millions can be created)
- Managed by JVM (not OS threads)
- Blocking operations don't block OS thread
- Perfect for I/O-bound operations

### Benefits

```java
// Traditional threads - limited (thousands)
ExecutorService executor = Executors.newFixedThreadPool(100);
for (int i = 0; i < 10_000; i++) {
    executor.submit(() -> {
        // I/O operation
        Thread.sleep(1000);
    });
}
// Limited by thread pool size

// Virtual threads - millions possible
try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
    for (int i = 0; i < 1_000_000; i++) {
        executor.submit(() -> {
            // I/O operation
            Thread.sleep(1000);
        });
    }
}
// Can handle millions of concurrent operations
```

### Use Cases

- High-throughput servers
- I/O-bound applications
- Microservices
- Concurrent request handling
- Async operations

**Note:** Requires `--enable-preview` flag. See [VirtualThreadsDemo.java](virtualthreads/VirtualThreadsDemo.java) for complete examples.

---

## 2. Structured Concurrency (Incubator)

Simplifies multithreaded programming by treating groups of tasks as a unit.

### StructuredTaskScope

```java
import jdk.incubator.concurrent.StructuredTaskScope;

// Structured concurrency
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

## 3. Record Patterns (Preview)

Pattern matching with records.

### Basic Record Patterns

```java
record Point(int x, int y) {}

// Pattern matching with records
Object obj = new Point(5, 10);

if (obj instanceof Point p) {
    System.out.println("X: " + p.x() + ", Y: " + p.y());
}

// Destructuring pattern (preview)
if (obj instanceof Point(int x, int y)) {
    System.out.println("X: " + x + ", Y: " + y);
    // x and y extracted directly
}

// In switch
String result = switch (obj) {
    case Point(int x, int y) when x > 0 && y > 0 -> 
        "Positive quadrant: (" + x + ", " + y + ")";
    case Point(int x, int y) -> 
        "Other quadrant: (" + x + ", " + y + ")";
    default -> "Not a point";
};
```

### Nested Patterns

```java
record Rectangle(Point topLeft, Point bottomRight) {}

Rectangle rect = new Rectangle(new Point(0, 0), new Point(10, 10));

if (rect instanceof Rectangle(Point(int x1, int y1), Point(int x2, int y2))) {
    int width = x2 - x1;
    int height = y2 - y1;
    System.out.println("Width: " + width + ", Height: " + height);
}
```

### Benefits

- Destructuring records directly
- More concise code
- Type-safe pattern matching
- Nested pattern support

**Note:** Requires `--enable-preview` flag. See [RecordPatterns.java](recordpatterns/RecordPatterns.java) for complete examples.

---

## 4. Pattern Matching for switch (Fourth Preview)

Refinements to pattern matching in switch expressions.

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

## 5. Foreign Function & Memory API (Fourth Incubator)

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

## 6. Vector API (Fourth Incubator)

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

## 7. Linux/RISC-V Port

Official port to Linux/RISC-V architecture.

### Overview

Java 19 adds support for the Linux/RISC-V instruction set architecture, expanding Java's reach to more hardware platforms.

### Key Features

- **RISC-V support**: Native support for RISC-V architecture
- **Open-source**: RISC-V is an open-source instruction set
- **Hardware compatibility**: Better support for RISC-V hardware
- **Cross-platform**: Java runs on RISC-V systems

### Benefits

- Support for RISC-V architecture
- Better hardware compatibility
- Expanded platform support
- Open-source architecture support

### Use Cases

- RISC-V-based systems
- Embedded systems
- IoT devices
- Custom hardware platforms

**Note:** This is a platform port and doesn't require code changes. Java applications will run natively on RISC-V systems.

---

## 8. Common Interview Questions

### Q1: What are Virtual Threads and why use them?
**A:** Virtual threads are lightweight threads:
- Managed by JVM, not OS
- Millions can be created
- Perfect for I/O-bound operations
- Blocking doesn't block OS thread
- High throughput

### Q2: When should you use Virtual Threads vs Platform Threads?
**A:**
- **Virtual Threads**: I/O-bound, high concurrency, many short-lived tasks
- **Platform Threads**: CPU-bound, few long-running tasks

### Q3: What is Structured Concurrency?
**A:** Structured concurrency:
- Treats groups of tasks as a unit
- Automatic cancellation on failure
- Better error handling
- Structured lifecycle management

---

**Last Updated:** 2024  
**Version:** 1.0

