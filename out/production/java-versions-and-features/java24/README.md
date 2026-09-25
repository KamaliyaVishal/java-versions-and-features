# Java 24

A comprehensive guide to all Java 24 concepts with practical examples for interview preparation.

## Table of Contents

1. [Module Import Statements (Finalized)](#1-module-import-statements-finalized)
2. [Primitive Types in Patterns, instanceof, and switch (Second Preview)](#2-primitive-types-in-patterns-instanceof-and-switch-second-preview)
3. [String Templates (Finalized)](#3-string-templates-finalized)
4. [Implicitly Declared Classes and Instance Main Methods (Finalized)](#4-implicitly-declared-classes-and-instance-main-methods-finalized)
5. [Structured Concurrency (Finalized)](#5-structured-concurrency-finalized)
6. [Scoped Values (Finalized)](#6-scoped-values-finalized)
7. [Stream Gatherers (Finalized)](#7-stream-gatherers-finalized)
8. [Ahead-of-Time Class Loading and Linking (JEP 483)](#8-ahead-of-time-class-loading-and-linking-jep-483)
9. [Improved Virtual Threads (JEP 491)](#9-improved-virtual-threads-jep-491)
10. [Quantum-Resistant Cryptography (JEPs 496 & 497)](#10-quantum-resistant-cryptography-jeps-496--497)
11. [Vector API (Ninth Incubator)](#11-vector-api-ninth-incubator)
12. [Removal of Security Manager (JEP 486)](#12-removal-of-security-manager-jep-486)
13. [Deprecation of 32-bit x86 Support (JEP 501)](#13-deprecation-of-32-bit-x86-support-jep-501)
14. [Common Interview Questions](#14-common-interview-questions)

---

## 1. Module Import Statements (Finalized)

Module import statements are now a standard feature in Java 24, simplifying module declarations.

### Overview

Module Import Statements allow you to import all packages exported by a module with a single statement, making module descriptors cleaner and easier to read.

```java
// Module imports (finalized)
import module java.base;
import module java.sql;
import module java.nio.file;

module com.example.app {
    // Imported modules are available
    exports com.example.app.api;
}

// Simpler and cleaner than requires statements
```

### Benefits

- Simpler module syntax
- Cleaner module descriptors
- Easier to read and maintain
- Reduces boilerplate
- Production-ready (finalized)

**See [ModuleImportStatements.java](modulestatements/ModuleImportStatements.java) for complete example.**

---

## 2. Primitive Types in Patterns, instanceof, and switch (Second Preview)

Refinements to primitive type patterns for more consistent and expressive code.

### Overview

This feature allows you to use primitive types directly in pattern matching expressions, making pattern matching work consistently for both reference and primitive types.

```java
// Primitive types in patterns (preview)
Object obj = 42;

if (obj instanceof Integer i) {
    System.out.println("Integer: " + i);
}

// In switch
String result = switch (obj) {
    case Integer i when i > 0 -> "Positive: " + i;
    case Integer i -> "Zero or negative: " + i;
    case Double d -> "Double: " + d;
    case Boolean b -> "Boolean: " + b;
    default -> "Other";
};
```

### Benefits

- More consistent pattern matching
- Better type safety
- Unified approach for all types
- Eliminates need for wrapper types in patterns
- More expressive code

**See [PrimitiveTypesInPatterns.java](primitivetypes/PrimitiveTypesInPatterns.java) for complete example.**

---

## 3. String Templates (Finalized)

String templates are now a standard feature, providing safe string interpolation in Java 24.

### Overview

String Templates provide a safe and expressive way to perform string interpolation, replacing the need for string concatenation or `String.format()`.

```java
String name = "John";
int age = 30;

// String template (finalized)
String message = STR."Hello, \{name}! You are \{age} years old.";

// Formatted template
String formatted = FMT."Age: %03d\{age}";  // Age: 030
```

### Template Processors

```java
// Built-in processors
String result = STR."Name: \{name}, Age: \{age}";
String formatted = FMT."Value: %5d\{value}";

// Custom processor
var MY_PROCESSOR = StringTemplate.Processor.of(
    (StringTemplate st) -> {
        // Custom processing
        return process(st);
    }
);

String custom = MY_PROCESSOR."Template: \{name}";
```

### Key Features

- String interpolation
- Safe (injection-resistant)
- Customizable (template processors)
- Built-in processors: STR, FMT
- Finalized feature (no preview flag needed)

### Benefits

- Cleaner than concatenation
- Safe from injection attacks
- More readable
- Supports formatting
- Production-ready (finalized)

**See [StringTemplates.java](stringtemplates/StringTemplates.java) for complete example.**

---

## 4. Implicitly Declared Classes and Instance Main Methods (Finalized)

Unnamed classes are now a standard feature, simplifying the creation of Java programs.

### Overview

This feature allows you to write simple programs without explicit class declarations, reducing boilerplate code and making Java more accessible for beginners and scripting.

```java
// Unnamed class with instance main (finalized)
void main() {
    System.out.println("Hello, World!");
}

// Compiler automatically generates class
// Simplified entry point for simple programs
// Perfect for learning and scripting

// Can have methods, fields, etc.
int x = 10;

void printX() {
    System.out.println("X: " + x);
}

void main() {
    printX();
}
```

### Benefits

- Simplified syntax for simple programs
- No explicit class declaration needed
- Great for learning Java
- Perfect for scripting
- Production-ready (finalized)

**See [UnnamedClassesAndInstanceMain.java](unnamedclasses/UnnamedClassesAndInstanceMain.java) for complete example.**

---

## 5. Structured Concurrency (Finalized)

Structured concurrency is now a standard feature, treating groups of related tasks as a single unit.

### Overview

Structured Concurrency ensures proper lifecycle management and error handling for concurrent tasks, making multithreaded code safer and easier to reason about.

```java
import java.util.concurrent.StructuredTaskScope;

// Structured concurrency (finalized)
try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    Future<String> user = scope.fork(() -> fetchUser());
    Future<String> order = scope.fork(() -> fetchOrder());
    
    scope.join();
    scope.throwIfFailed();
    
    String userResult = user.resultNow();
    String orderResult = order.resultNow();
    
    // Use results
    processOrder(userResult, orderResult);
}
// Automatic cleanup on failure
```

### Key Features

- Structured lifecycle management
- Error propagation
- Shutdown strategies
- Automatic resource cleanup
- Prevents thread leaks
- Finalized feature (no preview flag needed)

### Benefits

- Structured lifecycle management
- Automatic cancellation on failure
- Better error handling
- Easier concurrent programming
- Production-ready (finalized)

**See [StructuredConcurrency.java](structuredconcurrency/StructuredConcurrency.java) for complete example.**

---

## 6. Scoped Values (Finalized)

Scoped values are now a standard feature, providing immutable thread-local data sharing.

### Overview

Scoped Values provide a way to share immutable data within and across threads, offering a safer alternative to ThreadLocal with better performance and automatic cleanup.

```java
import java.util.concurrent.ScopedValue;

final ScopedValue<String> USER = ScopedValue.newInstance();

ScopedValue.runWhere(USER, "Alice", () -> {
    String user = USER.get();
    System.out.println("User: " + user);
    
    // Nested scope
    ScopedValue.runWhere(USER, "Bob", () -> {
        System.out.println("User: " + USER.get());  // Bob
    });
    
    System.out.println("User: " + USER.get());  // Alice
});

// With virtual threads
try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
    ScopedValue.runWhere(USER, "Charlie", () -> {
        executor.submit(() -> {
            System.out.println("User: " + USER.get());  // Inherited
        });
    });
}
```

### Key Features

- Immutable values
- Inherited by child threads
- Automatic cleanup
- Better performance than ThreadLocal
- Structured scoping
- Finalized feature (no preview flag needed)

### Benefits over ThreadLocal

- Immutable (better safety)
- Inherited by child threads (virtual threads)
- More efficient
- Structured scoping
- Production-ready (finalized)

**See [ScopedValues.java](scopedvalues/ScopedValues.java) for complete example.**

---

## 7. Stream Gatherers (Finalized)

Stream Gatherers are now a standard feature, allowing custom intermediate stream operations.

### Overview

Stream Gatherers enable you to create custom intermediate operations for streams, providing more flexibility than existing operations and extending the Stream API functionality.

```java
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;

// Custom gatherer
Gatherer<String, ?, String> filterAndUpper = Gatherer.ofSequential(
    () -> new Object[1],
    (state, element, downstream) -> {
        if (element.length() > 5) {
            return downstream.push(element.toUpperCase());
        }
        return true;
    },
    (state, downstream) -> {}
);

// Use gatherer
List<String> result = Stream.of("apple", "banana", "cherry", "kiwi")
    .gather(filterAndUpper)
    .toList();
```

### Built-in Gatherers

```java
// Sliding window
List<List<Integer>> windows = Stream.of(1, 2, 3, 4, 5)
    .gather(Gatherers.windowSliding(3))
    .toList();

// Fixed window
List<List<Integer>> fixed = Stream.of(1, 2, 3, 4, 5, 6)
    .gather(Gatherers.windowFixed(3))
    .toList();

// Fold operation
String result = Stream.of("a", "b", "c")
    .gather(Gatherers.fold(() -> "", (acc, elem) -> acc + elem))
    .findFirst()
    .orElse("");
```

### Benefits

- Extend Stream API
- Create reusable operations
- More expressive code
- Better performance for custom operations
- Production-ready (finalized)

**See [StreamGatherersDemo.java](streamgatherers/StreamGatherersDemo.java) for complete example.**

---

## 8. Ahead-of-Time Class Loading and Linking (JEP 483)

Reduces application startup times by preloading and linking classes before runtime.

### Overview

Ahead-of-Time (AOT) Class Loading and Linking preloads and links classes before runtime, minimizing the overhead associated with just-in-time compilation and improving application startup performance.

### Key Features

- Preloads classes before runtime
- Links classes ahead of time
- Reduces startup overhead
- Faster application startup
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

## 9. Improved Virtual Threads (JEP 491)

Enhancements to virtual threads allow for efficient synchronization without pinning.

### Overview

Java 24 improves virtual threads by allowing synchronization without pinning the underlying platform thread, enabling better scalability and performance for high-concurrency workloads.

```java
Thread.startVirtualThread(() -> {
    synchronized (lock) {
        // Synchronization without pinning
        System.out.println("Virtual thread synchronized");
    }
});
```

### Key Features

- Synchronization without pinning
- Better scalability
- Improved performance
- Efficient resource usage
- Millions of virtual threads possible

### Benefits

- Better performance for concurrent operations
- No platform thread pinning during synchronization
- More efficient resource utilization
- Improved scalability
- Better for I/O-bound operations

### Use Cases

- High-concurrency applications
- I/O-bound operations
- Microservices
- Web servers
- Asynchronous processing

**See [VirtualThreadsDemo.java](virtualthreads/VirtualThreadsDemo.java) for complete example.**

---

## 10. Quantum-Resistant Cryptography (JEPs 496 & 497)

Introduces post-quantum cryptographic algorithms to prepare for future quantum computing threats.

### Overview

Java 24 introduces post-quantum cryptographic algorithms, including ML-KEM (Module-Lattice-Based Key Encapsulation Mechanism) and ML-DSA (Module-Lattice-Based Digital Signature Algorithm), to prepare applications for the post-quantum era.

### Key Algorithms

- **ML-KEM**: Key encapsulation mechanism
- **ML-DSA**: Digital signature algorithm

### Example

```java
// Generate key pair using ML-DSA
KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ML-DSA");
KeyPair keyPair = keyGen.generateKeyPair();

// Sign data
Signature signature = Signature.getInstance("ML-DSA");
signature.initSign(keyPair.getPrivate());
byte[] data = "Important data".getBytes();
signature.update(data);
byte[] digitalSignature = signature.sign();

// Verify signature
signature.initVerify(keyPair.getPublic());
signature.update(data);
boolean verified = signature.verify(digitalSignature);
```

### Key Features

- ML-KEM: Key encapsulation mechanism
- ML-DSA: Digital signature algorithm
- Post-quantum security
- Future-proof cryptography
- NIST standardized algorithms

### Benefits

- Protection against quantum computing threats
- Future-proof security
- Standardized algorithms (NIST)
- Long-term security
- Migration path for existing systems

### Use Cases

- Long-term data protection
- Secure communications
- Digital signatures
- Key exchange
- Future-proof applications

**See [QuantumResistantCryptoDemo.java](crypto/QuantumResistantCryptoDemo.java) for complete example.**

---

## 11. Vector API (Ninth Incubator)

Continued improvements to Vector API with enhanced SIMD operations.

### Overview

The Vector API provides SIMD-style operations for parallel processing of arrays, with hardware-optimized computations that can significantly improve performance for data-parallel operations.

```java
import jdk.incubator.vector.*;

// Define vector species (size)
VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

// Arrays to process
float[] a = {1.0f, 2.0f, 3.0f, 4.0f};
float[] b = {5.0f, 6.0f, 7.0f, 8.0f};
float[] c = new float[4];

// Load vectors from arrays
FloatVector va = FloatVector.fromArray(SPECIES, a, 0);
FloatVector vb = FloatVector.fromArray(SPECIES, b, 0);

// Perform vector operation (add)
FloatVector vc = va.add(vb);

// Store result back to array
vc.intoArray(c, 0);
```

### Key Features

- Hardware-agnostic: Works on different platforms
- Automatic optimization: Compiles to optimal instructions
- Type-safe: Supports int, long, float, double
- SIMD operations: Parallel processing of multiple elements
- Platform-specific optimizations
- Ninth incubator iteration (continued refinement)

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

## 12. Removal of Security Manager (JEP 486)

The Security Manager has been permanently disabled, reflecting the shift away from Java Applets.

### Overview

The Security Manager, originally designed to restrict permissions for remotely loaded code, has been permanently disabled in Java 24, reflecting the shift away from Java Applets and browser-based execution.

### Impact

- Security Manager is no longer available
- Legacy code using Security Manager will need updates
- Modern applications don't need Security Manager
- Reflects shift to modern security models

### Migration

- Remove Security Manager dependencies
- Use modern security mechanisms (module system, permissions)
- Update legacy code
- Use containerization for isolation

### Benefits

- Simplified security model
- Removes deprecated API
- Encourages modern security practices
- Better alignment with current use cases

**Note:** This change may require architectural updates for legacy systems relying on the Security Manager.

---

## 13. Deprecation of 32-bit x86 Support (JEP 501)

Java 24 formally deprecates the 32-bit x86 Linux port, following a similar move for Windows in JDK 21.

### Overview

Java 24 deprecates the 32-bit x86 Linux port, reflecting the industry's shift towards modern, 64-bit architectures. This follows the deprecation of 32-bit Windows support in JDK 21.

### Impact

- 32-bit x86 Linux port is deprecated
- Future Java versions may remove support
- Developers should migrate to 64-bit systems
- Modern systems are 64-bit

### Migration

- Ensure applications run on 64-bit systems
- Update build and deployment processes
- Test on 64-bit platforms
- Plan for future removal

### Benefits

- Focuses resources on modern architectures
- Aligns with industry standards
- Simplifies maintenance
- Better performance on 64-bit systems

**Note:** Developers should ensure their applications are compatible with 64-bit systems to continue receiving support.

---

## 14. Common Interview Questions

### Q1: What are String Templates?
**A:** String interpolation feature:
- `STR."Hello, \{name}!"`
- Safe (injection-resistant)
- Customizable with template processors
- Cleaner than concatenation

### Q2: What are Implicitly Declared Classes?
**A:** Unnamed classes:
- No explicit class declaration
- Compiler generates class automatically
- Simplified entry point for simple programs
- Perfect for learning and scripting

### Q3: What are Scoped Values and why use them over ThreadLocal?
**A:**
- **Scoped Values**: Immutable, inherited by child threads, more efficient
- **ThreadLocal**: Mutable, not inherited, traditional approach
- Scoped Values are better for virtual threads

### Q4: What are Stream Gatherers?
**A:** Custom intermediate stream operations:
- Extend Stream API functionality
- Create reusable stream operations
- Built-in gatherers available (windowSliding, windowFixed, fold)
- More flexible than existing operations
- Finalized in Java 24

### Q5: What is Ahead-of-Time Class Loading?
**A:** Preloads and links classes before runtime:
- Reduces startup overhead
- Faster application startup
- Better for short-lived applications
- JVM-level optimization

### Q6: What improvements were made to Virtual Threads in Java 24?
**A:** Synchronization without pinning:
- Better scalability
- Improved performance
- No platform thread pinning during synchronization
- More efficient resource utilization

### Q7: What is Quantum-Resistant Cryptography?
**A:** Post-quantum cryptographic algorithms:
- ML-KEM: Key encapsulation mechanism
- ML-DSA: Digital signature algorithm
- Protection against quantum computing threats
- Future-proof security
- NIST standardized algorithms

### Q8: What happened to the Security Manager?
**A:** Removed in Java 24:
- Permanently disabled
- Reflects shift away from Java Applets
- Modern applications don't need it
- Use module system and containerization instead

---

**Last Updated:** 2025  
**Version:** 1.0

