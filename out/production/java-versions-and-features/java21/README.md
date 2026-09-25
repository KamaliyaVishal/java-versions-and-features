# Java 21 (LTS)

A comprehensive guide to all Java 21 (LTS) concepts with practical examples for interview preparation.

## Table of Contents

1. [Virtual Threads (Finalized)](#1-virtual-threads-finalized)
2. [Sequenced Collections](#2-sequenced-collections)
3. [Record Patterns (Finalized)](#3-record-patterns-finalized)
4. [Pattern Matching for switch (Finalized)](#4-pattern-matching-for-switch-finalized)
5. [String Templates (Preview)](#5-string-templates-preview)
6. [Unnamed Classes and Instance Main Methods (Preview)](#6-unnamed-classes-and-instance-main-methods-preview)
7. [Unnamed Variables and Patterns (Preview)](#7-unnamed-variables-and-patterns-preview)
8. [Scoped Values (Finalized)](#8-scoped-values-finalized)
9. [Structured Concurrency (Finalized)](#9-structured-concurrency-finalized)
10. [Foreign Function & Memory API (Third Preview)](#10-foreign-function--memory-api-third-preview)
11. [Vector API (Sixth Incubator)](#11-vector-api-sixth-incubator)
12. [Generational ZGC](#12-generational-zgc)
13. [Common Interview Questions](#13-common-interview-questions)

---

## 1. Virtual Threads (Finalized)

Virtual threads are now a standard feature in Java 21, providing lightweight threads managed by the JVM for high-throughput concurrent applications.

### Overview

Virtual threads are lightweight threads that are managed by the Java Virtual Machine rather than the operating system. They enable you to write high-throughput concurrent applications with a simple, synchronous programming model.

### Using Virtual Threads

```java
import java.util.concurrent.Executors;

// Virtual thread executor
try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
    for (int i = 0; i < 1_000_000; i++) {
        executor.submit(() -> {
            // I/O operation
            Thread.sleep(1000);
            return "Result";
        });
    }
}

// Create virtual thread directly
Thread virtualThread = Thread.ofVirtual()
    .name("worker-", 0)
    .start(() -> {
        System.out.println("Running on virtual thread");
    });

// Builder pattern
Thread.Builder.OfVirtual builder = Thread.ofVirtual().name("task-", 0);
Thread vt1 = builder.start(() -> doTask1());
Thread vt2 = builder.start(() -> doTask2());
```

### Characteristics

- **Lightweight**: Millions of virtual threads can be created with minimal overhead
- **Managed by JVM**: Not tied to OS threads
- **Non-blocking**: Blocking operations don't block OS threads
- **Perfect for I/O-bound operations**: Ideal for network I/O, file I/O, database operations
- **High throughput**: Enable high concurrency with simple code

### Benefits

- Simplified concurrent programming
- High scalability
- Better resource utilization
- No need for complex thread pools
- Compatible with existing code

### Use Cases

- Web servers handling many concurrent requests
- Database connection pools
- File processing
- Network I/O operations
- Microservices with high concurrency

**See [VirtualThreadsFinalized.java](virtualthreads/VirtualThreadsFinalized.java) for complete example.**

---

## 2. Sequenced Collections

New interfaces for collections with a defined encounter order, providing standardized operations for accessing first and last elements.

### Overview

Sequenced Collections introduce three new interfaces: `SequencedCollection`, `SequencedSet`, and `SequencedMap`, which provide a unified API for collections that maintain insertion order.

### SequencedCollection

```java
import java.util.SequencedCollection;

// LinkedHashSet maintains insertion order
SequencedCollection<String> collection = new LinkedHashSet<>();

collection.add("First");
collection.add("Second");
collection.add("Third");

// Access first and last
String first = collection.getFirst();  // "First"
String last = collection.getLast();    // "Third"

// Add to beginning/end
collection.addFirst("Zero");
collection.addLast("Fourth");

// Remove first/last
String removed = collection.removeFirst();
String removed2 = collection.removeLast();

// Reversed view
SequencedCollection<String> reversed = collection.reversed();
```

### SequencedSet

```java
import java.util.SequencedSet;

SequencedSet<String> set = new LinkedHashSet<>();
set.add("A");
set.add("B");
set.add("C");

String first = set.getFirst();  // "A"
String last = set.getLast();    // "C"
```

### SequencedMap

```java
import java.util.SequencedMap;

SequencedMap<String, Integer> map = new LinkedHashMap<>();
map.put("One", 1);
map.put("Two", 2);
map.put("Three", 3);

// First entry
Map.Entry<String, Integer> firstEntry = map.firstEntry();
// Last entry
Map.Entry<String, Integer> lastEntry = map.lastEntry();

// Add first/last
map.putFirst("Zero", 0);
map.putLast("Four", 4);

// Remove first/last
Map.Entry<String, Integer> removed = map.pollFirstEntry();
```

### Benefits

- Standardized API for ordered collections
- Easy access to first and last elements
- Reversed view support
- Consistent behavior across collection types

### Use Cases

- Maintaining insertion order
- Queue-like operations on collections
- Processing elements in order
- Accessing boundaries of ordered data

**See [SequencedCollections.java](sequencedcollections/SequencedCollections.java) for complete example.**

---

## 3. Record Patterns (Finalized)

Record patterns allow deconstructing record values in pattern matching, making it easier to extract and work with record components.

### Overview

Record patterns enable you to match against records and automatically extract their components, eliminating the need for manual field access.

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
    int width = x2 - x1;
    int height = y2 - y1;
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

- Concise record deconstruction
- Type-safe pattern matching
- Nested pattern support
- Works with sealed classes

**See [RecordPatterns.java](recordpatterns/RecordPatterns.java) for complete example.**

---

## 4. Pattern Matching for switch (Finalized)

Pattern matching in switch expressions is now a standard feature, allowing type patterns and guarded patterns in switch statements.

### Overview

Pattern matching for switch enables you to use type patterns directly in switch expressions, eliminating the need for `instanceof` checks and manual casting.

```java
Object obj = "Hello World";

String result = switch (obj) {
    case String s when s.length() > 10 -> "Long: " + s;
    case String s -> "Short: " + s;
    case Integer i when i > 100 -> "Large: " + i;
    case Integer i -> "Small: " + i;
    case null -> "Null";
    default -> "Unknown";
};

// With sealed classes - exhaustive
sealed interface Shape permits Circle, Rectangle {}

switch (shape) {
    case Circle c -> processCircle(c);
    case Rectangle r -> processRectangle(r);
    // No default needed - exhaustive
}
```

### Key Features

- Type patterns in switch
- Guarded patterns with `when` clauses
- Null handling
- Exhaustive matching with sealed classes
- Automatic type casting

### Benefits

- More readable code
- Eliminates manual casting
- Exhaustive pattern matching
- Better type safety
- Reduced boilerplate

**See [PatternMatchingSwitch.java](patternmatching/PatternMatchingSwitch.java) for complete example.**

---

## 5. String Templates (Preview)

String templates provide a safer and more readable way to include expressions within string literals.

### Overview

String templates allow embedding expressions directly in string literals using template processors, providing a safer alternative to string concatenation.

### Basic Usage

```java
String name = "John";
int age = 30;

// String template (preview)
String message = STR."Hello, \{name}! You are \{age} years old.";

// FMT for formatted strings
String formatted = FMT."Value: %5d\{age}";
```

### Template Processors

```java
// Built-in processors
String result = STR."Name: \{name}, Age: \{age}";
String formatted = FMT."Age: %03d\{age}";  // Age: 030
```

### Benefits

- Improved readability
- Safer than string concatenation
- Template processors for validation
- Reduces injection vulnerabilities

**Note:** String Templates were withdrawn in Java 23+ due to design concerns. See [StringTemplates.java](stringtemplates/StringTemplates.java) for details.

---

## 6. Unnamed Classes and Instance Main Methods (Preview)

Simplified class structure for simple programs, allowing programs without explicit class declarations.

### Overview

Unnamed classes and instance main methods reduce boilerplate for simple programs, making Java more accessible for beginners and scripting.

### Unnamed Classes

```java
// Unnamed class - no explicit class declaration
void main() {
    System.out.println("Hello, World!");
}

// Compiler generates class automatically
// Useful for simple scripts and learning
```

### Instance Main Methods

```java
// Instance main method (non-static)
void main() {
    System.out.println("Hello from instance main!");
}

// Static main still works
public static void main(String[] args) {
    System.out.println("Hello from static main!");
}
```

### Benefits

- Reduced boilerplate
- Easier for beginners
- Good for simple scripts
- More intuitive syntax

**See [UnnamedClassesAndInstanceMain.java](unnamedclasses/UnnamedClassesAndInstanceMain.java) for complete example.**

---

## 7. Unnamed Variables and Patterns (Preview)

Placeholder syntax for variables and pattern components that are intentionally unused.

### Overview

Unnamed variables and patterns allow you to explicitly mark variables and pattern components as unused, eliminating compiler warnings and making intent clear.

### Unnamed Variables

```java
// Unnamed variable (preview)
for (int i = 0; i < 10; i++) {
    String result = process(i);
    // Don't use 'result'
}

// Better with unnamed variable
for (int i = 0; i < 10; i++) {
    var _ = process(i);  // Unnamed variable
}

// Catch block
try {
    riskyOperation();
} catch (Exception _) {
    // Don't use exception
    System.out.println("Error occurred");
}
```

### Unnamed Patterns

```java
record Point(int x, int y) {}

// Unnamed pattern components
if (obj instanceof Point(int x, _)) {
    // Only use x, not y
    System.out.println("X: " + x);
}
```

### Benefits

- Eliminates unused variable warnings
- Makes intent clear
- Cleaner catch blocks
- Better pattern matching with partial deconstruction

**See [UnnamedVariablesAndPatterns.java](unnamedvariables/UnnamedVariablesAndPatterns.java) for complete example.**

---

## 8. Scoped Values (Finalized)

Scoped values provide a way to share immutable data within and across threads, replacing ThreadLocal with a better alternative.

### Overview

Scoped values are immutable, inheritable values that are automatically cleaned up when the scope ends, providing a safer alternative to ThreadLocal.

```java
import java.util.concurrent.ScopedValue;

final ScopedValue<String> USER = ScopedValue.newInstance();

ScopedValue.runWhere(USER, "Alice", () -> {
    String user = USER.get();
    System.out.println("User: " + user);
});
```

### Key Features

- **Immutable**: Cannot be modified after binding
- **Inherited**: Automatically inherited by child threads (including virtual threads)
- **No memory leaks**: Automatically cleaned up when scope ends
- **Structured**: Scoped to specific code blocks
- **Better performance**: More efficient than ThreadLocal

### Benefits

- Safer than ThreadLocal
- No memory leaks
- Inherited by child threads
- Better performance
- Immutable by design

**See [ScopedValues.java](scopedvalues/ScopedValues.java) for complete example.**

---

## 9. Structured Concurrency (Finalized)

Structured concurrency treats groups of related tasks as a unit, ensuring proper lifecycle management and error handling.

### Overview

Structured concurrency provides a way to manage the lifecycle of concurrent tasks, ensuring that all tasks complete before the scope closes and errors are properly propagated.

```java
import java.util.concurrent.StructuredTaskScope;

try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    Future<String> user = scope.fork(() -> fetchUser());
    Future<String> order = scope.fork(() -> fetchOrder());
    
    scope.join();
    scope.throwIfFailed();
    
    String userResult = user.resultNow();
    String orderResult = order.resultNow();
}
```

### Key Features

- **Structured lifecycle**: Tasks are managed as a unit
- **Error propagation**: Failures are properly handled
- **Shutdown strategies**: `ShutdownOnFailure` and `ShutdownOnSuccess`
- **Automatic cleanup**: Resources are cleaned up automatically

### Benefits

- Better error handling
- Prevents thread leaks
- Clearer code structure
- Easier debugging

**See [StructuredConcurrency.java](structuredconcurrency/StructuredConcurrency.java) for complete example.**

---

## 10. Foreign Function & Memory API (Third Preview)

API for calling native code and managing native memory, providing a safer and more efficient alternative to JNI.

### Overview

The Foreign Function & Memory API enables Java programs to interoperate with native code and manage off-heap memory in a type-safe manner.

```java
import java.lang.foreign.*;

// Enhanced foreign function interface
```

### Key Features

- Type-safe native interop
- Memory safety with arenas
- No JNI boilerplate
- Better performance than JNI
- Structured memory management

### Use Cases

- Calling C/C++ libraries
- System-level programming
- High-performance native code integration
- Memory-mapped files
- Direct memory access

**See [ForeignFunctionMemoryDemo.java](foreignapi/ForeignFunctionMemoryDemo.java) for complete example.**

---

## 11. Vector API (Sixth Incubator)

API for expressing vector computations that compile to optimal vector instructions on supported CPU architectures.

### Overview

The Vector API provides SIMD-style operations for parallel processing of arrays, with hardware-optimized computations that can significantly improve performance.

```java
import jdk.incubator.vector.*;

// Enhanced SIMD operations
```

### Key Features

- Hardware-agnostic: Works on different platforms
- Automatic optimization: Compiles to optimal instructions
- Type-safe: Supports int, long, float, double
- SIMD operations: Parallel processing of multiple elements
- Platform-specific optimizations

### Use Cases

- Scientific computing
- Machine learning
- Image processing
- Signal processing
- Numerical simulations
- Cryptography

**See [VectorAPIDemo.java](vector/VectorAPIDemo.java) for complete example.**

---

## 12. Generational ZGC

Generational Z Garbage Collector improves performance by collecting young objects more frequently.

### Overview

Generational ZGC enhances the Z Garbage Collector by introducing generational capabilities, separating young and old objects for more efficient collection.

### Key Features

- **Generational collection**: Young objects collected more frequently
- **Reduced pause times**: Better application responsiveness
- **Low latency**: Maintains ZGC's low-latency characteristics
- **Better throughput**: Improved overall performance
- **Automatic tuning**: Self-tuning based on workload

### How It Works

- Objects are divided into young and old generations
- Young generation is collected more frequently
- Old generation is collected less frequently
- Reduces the amount of work per collection cycle

### Benefits

- Lower pause times
- Better throughput
- Improved application responsiveness
- Automatic optimization
- Maintains low latency

### Configuration

```bash
# Enable Generational ZGC (default in Java 21+)
-XX:+UseZGC

# Disable generational mode (if needed)
-XX:+UseZGC -XX:-ZGenerational

# Tune young generation size
-XX:ZYoungGenerationSizeLimit=2G
```

### Use Cases

- Low-latency applications
- Large heap sizes
- Applications requiring predictable pause times
- Real-time systems
- High-throughput applications

**Note:** Generational ZGC is the default in Java 21+. No code changes are required.

---

## 13. Common Interview Questions

### Q1: Why is Java 21 an LTS version?
**A:** LTS (Long-Term Support):
- Extended support period (Oracle: until September 2028)
- Enterprise stability
- Long-term security updates
- Critical for production

### Q2: What are Virtual Threads and their benefits?
**A:**
- Lightweight threads managed by JVM
- Millions can be created
- Perfect for I/O-bound operations
- High throughput
- Blocking doesn't block OS thread

### Q3: What are Sequenced Collections?
**A:** Collections with defined encounter order:
- `getFirst()`, `getLast()`
- `addFirst()`, `addLast()`
- `removeFirst()`, `removeLast()`
- `reversed()` view

### Q4: What are Scoped Values and how do they differ from ThreadLocal?
**A:** Scoped Values are:
- Immutable (cannot be modified after binding)
- Inherited by child threads (including virtual threads)
- Automatically cleaned up (no memory leaks)
- More efficient than ThreadLocal
- Structured scoping

### Q5: What is Structured Concurrency?
**A:** Structured Concurrency:
- Treats groups of tasks as a unit
- Ensures proper lifecycle management
- Prevents thread leaks
- Better error handling
- Automatic resource cleanup

### Q6: What is Generational ZGC?
**A:** Generational ZGC:
- Separates young and old objects
- Collects young generation more frequently
- Reduces pause times
- Maintains low latency
- Better throughput

---

**Last Updated:** 2024  
**Version:** 1.0

