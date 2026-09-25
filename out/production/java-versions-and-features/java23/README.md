# Java 23

A comprehensive guide to all Java 23 concepts with practical examples for interview preparation.

## Table of Contents

1. [Primitive Types in Patterns, instanceof, and switch (Preview)](#1-primitive-types-in-patterns-instanceof-and-switch-preview)
2. [Module Import Declarations (Preview)](#2-module-import-declarations-preview)
3. [Stream Gatherers (Second Preview)](#3-stream-gatherers-second-preview)
4. [Scoped Values (Third Preview)](#4-scoped-values-third-preview)
5. [Class-File API (Second Preview)](#5-class-file-api-second-preview)
6. [Markdown Documentation Comments](#6-markdown-documentation-comments)
7. [Flexible Constructor Bodies (Second Preview)](#7-flexible-constructor-bodies-second-preview)
8. [String Templates (Third Preview)](#8-string-templates-third-preview)
9. [Implicitly Declared Classes and Instance Main Methods (Third Preview)](#9-implicitly-declared-classes-and-instance-main-methods-third-preview)
10. [Structured Concurrency (Third Preview)](#10-structured-concurrency-third-preview)
11. [Foreign Function & Memory API (Finalized)](#11-foreign-function--memory-api-finalized)
12. [Vector API (Eighth Incubator)](#12-vector-api-eighth-incubator)
13. [ZGC: Generational Mode by Default](#13-zgc-generational-mode-by-default)
14. [Common Interview Questions](#14-common-interview-questions)

---

## 1. Primitive Types in Patterns, instanceof, and switch (Preview)

Pattern matching with primitive types for more consistent and expressive code.

### Overview

This feature allows you to use primitive types directly in pattern matching expressions, making pattern matching work consistently for both reference and primitive types.

### Primitive Patterns

```java
// Primitive types in patterns (preview)
Object obj = 42;

if (obj instanceof int i) {
    System.out.println("Integer: " + i);
}

// In switch
String result = switch (obj) {
    case int i when i > 0 -> "Positive: " + i;
    case int i -> "Zero or negative: " + i;
    case double d -> "Double: " + d;
    case boolean b -> "Boolean: " + b;
    default -> "Other";
};

// Pattern matching with primitives
Number num = 42;
if (num instanceof Integer i && i > 0) {
    System.out.println("Positive integer: " + i);
}
```

### Benefits

- More consistent pattern matching
- Better type safety
- Unified approach for all types
- Eliminates need for wrapper types in patterns
- More expressive code

**See [PrimitiveTypesInPatterns.java](primitivetypes/PrimitiveTypesInPatterns.java) for complete example.**

---

## 2. Module Import Declarations (Preview)

Simplified syntax for importing modules, making module descriptors cleaner and easier to read.

### Overview

Module Import Declarations allow you to import all packages exported by a module with a single statement, simplifying the reuse of modular libraries.

### Current Module Syntax

```java
// Current syntax
module com.example.app {
    requires java.base;
    requires java.sql;
    exports com.example.app.api;
}
```

### New Import Syntax (Preview)

```java
// Module import declarations (preview)
import module java.base;
import module java.sql;

module com.example.app {
    // Modules imported above are available
    exports com.example.app.api;
}
```

### Benefits

- Simpler module syntax
- Cleaner module descriptors
- Easier to read
- Reduces boilerplate
- Better module organization

**See [ModuleImportDeclarations.java](moduleimports/ModuleImportDeclarations.java) for complete example.**

---

## 3. Stream Gatherers (Second Preview)

Custom intermediate stream operations that extend the Stream API functionality.

### Overview

Stream Gatherers allow you to create custom intermediate operations for streams, providing more flexibility than existing operations.

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

**See [StreamGatherersDemo.java](streamgatherers/StreamGatherersDemo.java) for complete example.**

---

## 4. Scoped Values (Third Preview)

Refinements to scoped values with improved performance and safety.

### Overview

Scoped values provide a way to share immutable data within and across threads, offering a safer alternative to ThreadLocal.

```java
import java.util.concurrent.ScopedValue;

final ScopedValue<String> USER = ScopedValue.newInstance();

ScopedValue.runWhere(USER, "Alice", () -> {
    String user = USER.get();
    // Use value
});
```

### Key Features

- Immutable values
- Inherited by child threads
- Automatic cleanup
- Better performance than ThreadLocal
- Structured scoping

**See [ScopedValues.java](scopedvalues/ScopedValues.java) for complete example.**

---

## 5. Class-File API (Second Preview)

API for parsing, generating, and transforming Java class files.

### Overview

The Class-File API provides a standard way to work with Java class files, facilitating tools and frameworks that manipulate bytecode.

### Key Features

- Parse class files
- Generate class files
- Transform bytecode
- Standard API for class file manipulation
- Better than ASM for some use cases

### Use Cases

- Bytecode manipulation tools
- Code generation frameworks
- Static analysis tools
- Compiler plugins
- Runtime code generation

### Benefits

- Standard API
- Type-safe operations
- Better than third-party libraries
- Integrated with JDK

**Note:** This is an advanced API primarily for tool developers and frameworks.

---

## 6. Markdown Documentation Comments

Allows the use of Markdown syntax in Javadoc comments, making documentation more readable and easier to write.

### Overview

Java 23 allows you to use Markdown syntax directly in Javadoc comments, providing better formatting and readability.

### Example

```java
/**
 * # Example Method
 * 
 * This method demonstrates **Markdown** syntax in Javadoc.
 * 
 * ## Features
 * 
 * - **Bold text** using `**text**`
 * - *Italic text* using `*text*`
 * - Code blocks using backticks
 * 
 * ### Usage
 * 
 * ```java
 * example.exampleMethod();
 * ```
 */
public void exampleMethod() {
    // Implementation
}
```

### Supported Markdown Features

- Headers (#, ##, ###)
- Bold text (**text**)
- Italic text (*text*)
- Code blocks (```)
- Inline code (`code`)
- Lists (-, *, 1.)
- Links ([text](url))
- Tables

### Benefits

- More readable documentation
- Easier to write
- Better formatting
- Standard Markdown syntax
- Works with existing Javadoc tools

**See [MarkdownDocumentationComments.java](markdown/MarkdownDocumentationComments.java) for complete example.**

---

## 7. Flexible Constructor Bodies (Second Preview)

Allows statements before super() call in constructors, providing more flexibility in object initialization.

### Overview

This feature allows you to add logic before calling a superclass constructor, enabling validation and preprocessing.

```java
class Child extends Parent {
    Child(String name) {
        // Statements before super() (preview)
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        
        String processed = name.trim().toUpperCase();
        String validated = validate(processed);  // Static method call allowed
        
        super(validated);  // Now allowed after statements (preview)
    }
    
    private static String validate(String s) {
        return s.length() > 0 ? s : "Default";
    }
}
```

### Key Features

- Statements before super() call
- Validation and preprocessing
- Static method calls allowed
- Instance methods not allowed before super()

### Benefits

- More flexibility in constructors
- Can do validation/preprocessing before super()
- Better code organization
- Allows static method calls before super()

**See [FlexibleConstructorBodies.java](constructors/FlexibleConstructorBodies.java) for complete example.**

---

## 8. String Templates (Third Preview)

**Note:** String Templates were withdrawn in Java 23+ due to design concerns. See [StringTemplates.java](stringtemplates/StringTemplates.java) for details.

---

## 9. Implicitly Declared Classes and Instance Main Methods (Third Preview)

Refinements to unnamed classes with improved support for simple programs.

### Overview

This feature simplifies the creation of Java programs by allowing implicit class declarations and instance main methods, reducing boilerplate code.

```java
// Simplified class structure (preview)
void main() {
    System.out.println("Hello, World!");
}

// Compiler generates class automatically
```

### Benefits

- Reduced boilerplate
- Easier for beginners
- Good for simple scripts
- More intuitive syntax

**See [UnnamedClassesAndInstanceMain.java](unnamedclasses/UnnamedClassesAndInstanceMain.java) for complete example.**

---

## 10. Structured Concurrency (Third Preview)

Continued improvements to structured concurrency with enhanced error handling and lifecycle management.

### Overview

Structured concurrency treats groups of related tasks as a single unit, ensuring proper lifecycle management and error handling.

```java
import java.util.concurrent.StructuredTaskScope;

// Enhanced structured concurrency
try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    Future<String> user = scope.fork(() -> fetchUser());
    Future<String> order = scope.fork(() -> fetchOrder());
    
    scope.join();
    scope.throwIfFailed();
    
    // Use results
}
```

### Key Features

- Structured lifecycle management
- Error propagation
- Shutdown strategies
- Automatic resource cleanup
- Prevents thread leaks

**See [StructuredConcurrency.java](structuredconcurrency/StructuredConcurrency.java) for complete example.**

---

## 11. Foreign Function & Memory API (Finalized)

The Foreign Function & Memory API is now a standard feature, providing a safer and more efficient alternative to JNI.

### Overview

This API enables Java programs to interoperate with native code and manage off-heap memory in a type-safe manner, replacing the need for JNI boilerplate.

```java
import java.lang.foreign.*;

// Link with native libraries
Linker linker = Linker.nativeLinker();
SymbolLookup stdlib = linker.defaultLookup();

MethodHandle strlen = linker.downcallHandle(
    stdlib.find("strlen").orElseThrow(),
    FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
);

// Allocate and use memory
try (Arena arena = Arena.ofConfined()) {
    MemorySegment segment = arena.allocate(100);
    // Use memory
}
```

### Key Features

- Type-safe native interop
- Memory safety with arenas
- No JNI boilerplate
- Better performance than JNI
- Structured memory management
- Finalized API (stable)

### Benefits

- Call native code
- Access off-heap memory
- High performance interop
- Safe memory management
- Production-ready (finalized)

**See [ForeignFunctionMemoryDemo.java](foreignapi/ForeignFunctionMemoryDemo.java) for complete example.**

---

## 12. Vector API (Eighth Incubator)

Continued improvements to Vector API with enhanced SIMD operations.

### Overview

The Vector API provides SIMD-style operations for parallel processing of arrays, with hardware-optimized computations that can significantly improve performance.

```java
import jdk.incubator.vector.*;

// SIMD operations
// Enhanced performance for vectorized operations
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

## 13. ZGC: Generational Mode by Default

The Z Garbage Collector now operates in generational mode by default, improving application throughput and reducing memory footprint.

### Overview

Generational ZGC enhances the Z Garbage Collector by introducing generational capabilities, separating young and old objects for more efficient collection.

### Key Features

- **Generational collection**: Young objects collected more frequently
- **Reduced pause times**: Better application responsiveness
- **Low latency**: Maintains ZGC's low-latency characteristics
- **Better throughput**: Improved overall performance
- **Automatic tuning**: Self-tuning based on workload
- **Default mode**: Enabled by default in Java 23

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
- Default behavior (no configuration needed)

### Configuration

```bash
# ZGC is default in Java 23+
# No configuration needed

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

**Note:** Generational ZGC is the default in Java 23. No code changes are required.

---

## 14. Common Interview Questions

### Q1: What is the Foreign Function & Memory API?
**A:** API for:
- Calling native code from Java
- Accessing off-heap memory
- Safe memory management
- High-performance interop

### Q2: What are Module Import Declarations?
**A:** Simplified syntax for modules:
- `import module` statements
- Cleaner module descriptors
- Easier to read

### Q3: What are Primitive Types in Patterns?
**A:** Pattern matching with primitive types:
- `instanceof int i`
- Patterns work with primitives
- Consistent pattern matching
- Eliminates need for wrapper types

### Q4: What are Stream Gatherers?
**A:** Custom intermediate stream operations:
- Extend Stream API functionality
- Create reusable stream operations
- Built-in gatherers available (windowSliding, windowFixed, fold)
- More flexible than existing operations

### Q5: What are Scoped Values?
**A:** Immutable thread-local data sharing:
- Immutable values
- Inherited by child threads
- Automatic cleanup
- Better performance than ThreadLocal
- Structured scoping

### Q6: What is Markdown Documentation Comments?
**A:** Markdown syntax in Javadoc:
- Headers, bold, italic, code blocks
- Lists and tables
- More readable documentation
- Easier to write and maintain

### Q7: What is Flexible Constructor Bodies?
**A:** Statements before super() call:
- Allows validation/preprocessing
- Static method calls allowed
- More flexibility in constructors
- Better code organization

### Q8: What is ZGC Generational Mode?
**A:** ZGC with generational collection:
- Default in Java 23
- Separates young and old objects
- Better throughput
- Lower pause times
- Automatic optimization

---

**Last Updated:** 2024  
**Version:** 1.0

