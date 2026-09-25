# Java 9

A comprehensive guide to all Java 9 concepts with practical examples for interview preparation.

## Table of Contents

1. [Module System (Project Jigsaw)](#1-module-system-project-jigsaw)
2. [JShell (REPL)](#2-jshell-repl)
3. [Stream API Enhancements](#3-stream-api-enhancements)
4. [Private Methods in Interfaces](#4-private-methods-in-interfaces)
5. [Process API Improvements](#5-process-api-improvements)
6. [HTTP/2 Client](#6-http2-client)
7. [Factory Methods for Collections](#7-factory-methods-for-collections)
8. [Try-With-Resources Enhancement](#8-try-with-resources-enhancement)
9. [Diamond Operator Enhancement](#9-diamond-operator-enhancement)
10. [Enhanced Deprecation](#10-enhanced-deprecation)
11. [Multi-Release JAR Files](#11-multi-release-jar-files)
12. [Improved Javadoc](#12-improved-javadoc)
13. [Variable Handles](#13-variable-handles)
14. [Stack-Walking API](#14-stack-walking-api)
15. [Reactive Streams](#15-reactive-streams)
16. [CompletableFuture Improvements](#16-completablefuture-improvements)
17. [Common Interview Questions](#17-common-interview-questions)

---

## 1. Module System (Project Jigsaw)

Java 9 introduced the Java Platform Module System (JPMS) to create modular applications.

### Module Descriptor (module-info.java)

```java
module com.example.myapp {
    requires java.base;           // Implicitly required
    requires java.sql;
    requires java.logging;
    
    exports com.example.myapp.api;
    exports com.example.myapp.model to com.example.consumer;
    
    opens com.example.myapp.internal;  // For reflection
    
    provides com.example.myapp.Service
        with com.example.myapp.ServiceImpl;
    
    uses com.example.myapp.Service;
}
```

### Key Concepts

- **requires**: Declares dependencies on other modules
- **exports**: Makes packages available to other modules
- **opens**: Allows reflection access (for frameworks)
- **provides/uses**: Service locator pattern

### Benefits

- Better encapsulation
- Explicit dependencies
- Smaller runtime (can include only needed modules)
- Improved security

---

## 2. JShell (REPL)

JShell is a Read-Eval-Print Loop tool for interactive Java programming.

### Basic Usage

```java
jshell> int x = 10
x ==> 10

jshell> int y = 20
y ==> 20

jshell> x + y
$3 ==> 30

jshell> String greeting = "Hello, JShell!"
greeting ==> "Hello, JShell!"

jshell> System.out.println(greeting)
Hello, JShell!

jshell> /vars  // List all variables
jshell> /methods  // List all methods
jshell> /list  // List all snippets
jshell> /exit  // Exit JShell
```

### Advanced Features

```java
// Define methods
jshell> int add(int a, int b) { return a + b; }
|  created method add(int,int)

jshell> add(5, 3)
$5 ==> 8

// Define classes
jshell> class Calculator {
   ...>     public int multiply(int a, int b) { return a * b; }
   ...> }
|  created class Calculator

jshell> Calculator calc = new Calculator()
calc ==> Calculator@2f92e0f4

jshell> calc.multiply(4, 5)
$7 ==> 20
```

---

## 3. Stream API Enhancements

### 3.1 takeWhile()

Takes elements while a predicate is true.

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
List<Integer> taken = numbers.stream()
    .takeWhile(n -> n < 5)
    .collect(Collectors.toList());
// Result: [1, 2, 3, 4]
```

### 3.2 dropWhile()

Drops elements while a predicate is true.

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
List<Integer> dropped = numbers.stream()
    .dropWhile(n -> n < 5)
    .collect(Collectors.toList());
// Result: [5, 6, 7, 8]
```

### 3.3 ofNullable()

Creates a stream from a nullable element.

```java
String value = getNullableString();
Stream<String> stream = Stream.ofNullable(value);
// If value is null, returns empty stream
// If value is not null, returns stream with one element

// Before Java 9
Stream<String> oldWay = value != null ? Stream.of(value) : Stream.empty();
```

### 3.4 iterate() Enhancement

Overloaded iterate with predicate support.

```java
// Java 9: iterate with condition (limit with predicate)
Stream.iterate(0, n -> n < 10, n -> n + 1)
    .forEach(System.out::println);
// Prints: 0, 1, 2, ..., 9

// Old way (Java 8)
Stream.iterate(0, n -> n + 1)
    .limit(10)
    .forEach(System.out::println);
```

---

## 4. Private Methods in Interfaces

Interfaces can now have private methods for code reuse.

```java
interface Calculator {
    default int add(int a, int b) {
        return performOperation(a, b, "+");
    }
    
    default int subtract(int a, int b) {
        return performOperation(a, b, "-");
    }
    
    private int performOperation(int a, int b, String op) {
        // Shared implementation
        System.out.println("Performing: " + a + " " + op + " " + b);
        return op.equals("+") ? a + b : a - b;
    }
    
    static String getOperationName() {
        return getDefaultName();
    }
    
    private static String getDefaultName() {
        return "Basic Calculator";
    }
}
```

### Benefits

- Code reuse between default and static methods
- Better organization
- Encapsulation within interfaces

---

## 5. Process API Improvements

Enhanced Process API for better process management.

```java
// Get current process
ProcessHandle currentProcess = ProcessHandle.current();
long pid = currentProcess.pid();
System.out.println("Current PID: " + pid);

// Get process info
ProcessHandle.Info info = currentProcess.info();
info.command().ifPresent(cmd -> System.out.println("Command: " + cmd));
info.commandLine().ifPresent(cl -> System.out.println("Command Line: " + cl));
info.startInstant().ifPresent(start -> System.out.println("Start Time: " + start));

// List all processes
ProcessHandle.allProcesses()
    .filter(ph -> ph.info().command().isPresent())
    .forEach(ph -> System.out.println("PID: " + ph.pid() + 
        ", Command: " + ph.info().command().get()));

// Process builder with redirect
ProcessBuilder pb = new ProcessBuilder("echo", "Hello from Process API")
    .redirectOutput(ProcessBuilder.Redirect.INHERIT);
Process process = pb.start();
int exitCode = process.waitFor();
```

---

## 6. HTTP/2 Client

New HTTP Client API supporting HTTP/2 (incubator in Java 9, finalized in Java 11).

```java
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

// Create HTTP client
HttpClient client = HttpClient.newHttpClient();

// Build request
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/data"))
    .GET()
    .header("Accept", "application/json")
    .build();

// Send request (blocking)
HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandlers.ofString());

System.out.println("Status: " + response.statusCode());
System.out.println("Body: " + response.body());

// Async request
client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    .thenApply(HttpResponse::body)
    .thenAccept(System.out::println)
    .join();
```

---

## 7. Factory Methods for Collections

Convenient factory methods for creating immutable collections.

### List.of()

```java
// Immutable list
List<String> list = List.of("Apple", "Banana", "Cherry");

// Empty list
List<String> emptyList = List.of();

// Single element
List<String> single = List.of("One");

// Throws exception if null
// List.of("A", null); // NullPointerException
```

### Set.of()

```java
Set<Integer> set = Set.of(1, 2, 3, 4, 5);

// Duplicates throw exception
// Set.of(1, 2, 2); // IllegalArgumentException
```

### Map.of() and Map.ofEntries()

```java
// Map.of() - up to 10 key-value pairs
Map<String, Integer> map = Map.of(
    "One", 1,
    "Two", 2,
    "Three", 3
);

// Map.ofEntries() - for more entries
Map<String, Integer> largeMap = Map.ofEntries(
    Map.entry("One", 1),
    Map.entry("Two", 2),
    Map.entry("Three", 3),
    Map.entry("Four", 4),
    Map.entry("Five", 5)
);
```

### Characteristics

- Immutable collections
- Null elements not allowed
- Space-efficient
- Serialization-friendly

---

## 8. Try-With-Resources Enhancement

Try-with-resources now supports effectively final variables.

```java
// Before Java 9
BufferedReader reader1 = new BufferedReader(new FileReader("file.txt"));
try (BufferedReader reader = reader1) {  // Extra variable needed
    // Read file
}

// Java 9 - effectively final variables
BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
try (reader) {  // Direct use
    // Read file
}

// Multiple resources
BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
PrintWriter writer = new PrintWriter(new FileWriter("output.txt"));
try (reader; writer) {
    // Use resources
}
```

---

## 9. Diamond Operator Enhancement

Diamond operator can now be used with anonymous classes.

```java
// Before Java 9 - explicit type needed
List<String> list1 = new ArrayList<String>() {
    @Override
    public boolean add(String e) {
        System.out.println("Adding: " + e);
        return super.add(e);
    }
};

// Java 9 - diamond operator
List<String> list2 = new ArrayList<>() {
    @Override
    public boolean add(String e) {
        System.out.println("Adding: " + e);
        return super.add(e);
    }
};
```

---

## 10. Enhanced Deprecation

Enhanced @Deprecated annotation with more information.

```java
@Deprecated(since = "9", forRemoval = true)
public class OldClass {
    // This class is deprecated since Java 9
    // and will be removed in a future version
}

@Deprecated(since = "9")
public void oldMethod() {
    // Deprecated but not marked for removal
}

// Check deprecation
if (OldClass.class.isAnnotationPresent(Deprecated.class)) {
    Deprecated deprecated = OldClass.class.getAnnotation(Deprecated.class);
    System.out.println("Since: " + deprecated.since());
    System.out.println("For removal: " + deprecated.forRemoval());
}
```

---

## 11. Multi-Release JAR Files

Support for different class files for different Java versions in a single JAR file.

### Structure

```
myapp.jar
├── META-INF/
│   └── MANIFEST.MF (with Multi-Release: true)
├── com/example/
│   └── MyClass.class (Java 8 version - base)
└── META-INF/versions/
    └── 9/
        └── com/example/
            └── MyClass.class (Java 9 version)
```

### Creating Multi-Release JAR

```bash
# Compile base version (Java 8)
javac -d build/java8 src/main/java/com/example/MyClass.java

# Compile Java 9 version
javac --release 9 -d build/java9 src/main/java9/com/example/MyClass.java

# Create JAR with Multi-Release manifest
jar --create --file myapp.jar \
    --manifest META-INF/MANIFEST.MF \
    -C build/java8 .

# Add Java 9 version
jar --update --file myapp.jar \
    --release 9 \
    -C build/java9 .
```

### MANIFEST.MF

```
Manifest-Version: 1.0
Multi-Release: true
Main-Class: com.example.Main
```

### Example: Version-Specific Code

**Java 8 version (base):**
```java
public List<String> createList() {
    List<String> list = new ArrayList<>();
    list.add("Item 1");
    return list;
}
```

**Java 9 version (META-INF/versions/9/):**
```java
public List<String> createList() {
    return List.of("Item 1", "Item 2", "Item 3");  // Java 9 factory method
}
```

### Benefits

- Backward compatibility: Works on Java 8+
- Version-specific optimizations: Use Java 9+ features when available
- Gradual migration: Migrate code incrementally
- Single JAR: One JAR file for multiple Java versions

### Runtime Behavior

- **Java 8**: Uses base classes from root
- **Java 9+**: Automatically uses versioned classes from `META-INF/versions/9/`
- **Class Loading**: JVM selects correct version at runtime

---

## 12. Improved Javadoc

Enhanced Javadoc with search and HTML5 support.

```java
/**
 * Calculator class for basic arithmetic operations.
 * 
 * @author John Doe
 * @since 9
 * @see java.lang.Math
 */
public class Calculator {
    /**
     * Adds two numbers.
     * 
     * @param a first number
     * @param b second number
     * @return sum of a and b
     * @apiNote This method was introduced in Java 9
     */
    public int add(int a, int b) {
        return a + b;
    }
}
```

---

## 13. Variable Handles

Standard way to perform atomic operations and more.

```java
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

class Counter {
    private volatile int count = 0;
    
    private static final VarHandle COUNT_HANDLE;
    
    static {
        try {
            COUNT_HANDLE = MethodHandles.lookup()
                .findVarHandle(Counter.class, "count", int.class);
        } catch (Exception e) {
            throw new Error(e);
        }
    }
    
    public void increment() {
        COUNT_HANDLE.getAndAdd(this, 1);
    }
    
    public int get() {
        return (int) COUNT_HANDLE.getVolatile(this);
    }
}
```

---

## 14. Stack-Walking API

Efficient stack trace analysis.

```java
import java.lang.StackWalker;

// Get caller class
StackWalker walker = StackWalker.getInstance(
    StackWalker.Option.RETAIN_CLASS_REFERENCE);

String caller = walker.walk(stream -> 
    stream.skip(1)
          .findFirst()
          .map(StackWalker.StackFrame::getClassName)
          .orElse("Unknown"));

// Filter frames
walker.walk(stream -> 
    stream.filter(frame -> frame.getClassName().contains("com.example"))
          .forEach(frame -> System.out.println(
              frame.getClassName() + "." + frame.getMethodName())));
```

---

## 15. Reactive Streams

API for asynchronous stream processing.

```java
import java.util.concurrent.Flow.*;

// Publisher
class SimplePublisher implements Publisher<Integer> {
    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscriber.onSubscribe(new Subscription() {
            @Override
            public void request(long n) {
                // Emit items
            }
            
            @Override
            public void cancel() {
                // Cancel subscription
            }
        });
    }
}
```

---

## 16. CompletableFuture Improvements

Java 9 added several new methods to CompletableFuture for better timeout handling and delayed execution.

### 16.1 completeOnTimeout()

Completes the CompletableFuture with a default value if it doesn't complete within the specified timeout.

```java
CompletableFuture<String> future = CompletableFuture
    .supplyAsync(() -> {
        try {
            Thread.sleep(2000);  // Simulate long operation
            return "Result";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    })
    .completeOnTimeout("Timeout Default", 1, TimeUnit.SECONDS);

String result = future.join();  // "Timeout Default" if timeout occurs
```

### 16.2 orTimeout()

Completes the CompletableFuture exceptionally with TimeoutException if it doesn't complete within the specified timeout.

```java
CompletableFuture<String> future = CompletableFuture
    .supplyAsync(() -> {
        try {
            Thread.sleep(2000);
            return "Result";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    })
    .orTimeout(1, TimeUnit.SECONDS);

try {
    String result = future.join();
} catch (CompletionException e) {
    if (e.getCause() instanceof TimeoutException) {
        System.out.println("Operation timed out");
    }
}
```

### 16.3 delayedExecutor()

Creates an Executor that runs tasks after a specified delay.

```java
Executor delayedExecutor = CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS);

CompletableFuture<String> future = CompletableFuture
    .supplyAsync(() -> "Delayed Result", delayedExecutor);

// Task will execute after 2 seconds
String result = future.get();
```

### 16.4 newIncompleteFuture()

Allows subclasses to override the behavior of CompletableFuture.

```java
class CustomCompletableFuture<T> extends CompletableFuture<T> {
    @Override
    public <U> CompletableFuture<U> newIncompleteFuture() {
        return new CustomCompletableFuture<>();
    }
}
```

---

## 17. Common Interview Questions

### Q1: What is JPMS and why was it introduced?
**A:** Java Platform Module System (Project Jigsaw) was introduced to:
- Improve application scalability and maintainability
- Better encapsulation (strong encapsulation)
- Explicit dependency management
- Smaller runtime footprints
- Improved security

### Q2: What is the difference between `requires` and `requires transitive`?
**A:**
- `requires`: Module depends on another module
- `requires transitive`: Dependencies are also available to modules that depend on this module

```java
module A {
    requires transitive B;  // A depends on B, and anyone depending on A gets B too
}
```

### Q3: Explain takeWhile() vs filter().
**A:**
- `filter()`: Processes all elements, returns matching ones
- `takeWhile()`: Stops as soon as predicate becomes false (short-circuit)

### Q4: What are the benefits of factory methods for collections?
**A:**
- Concise syntax
- Immutable collections
- Better performance (optimized implementations)
- Type inference support

### Q5: How do you handle multiple resources in try-with-resources?
**A:** Separate resources with semicolons:

```java
try (Resource1 r1 = new Resource1();
     Resource2 r2 = new Resource2()) {
    // Use resources
}
```

---

## Quick Reference Cheat Sheet

### Module Descriptor Keywords
- `module`: Declares a module
- `requires`: Declares dependency
- `exports`: Makes package public
- `opens`: Allows reflection
- `provides/uses`: Service locator

### Stream New Methods
- `takeWhile(Predicate)`: Take while predicate is true
- `dropWhile(Predicate)`: Drop while predicate is true
- `ofNullable(T)`: Stream from nullable value

### Collection Factory Methods
- `List.of(...)`: Immutable list
- `Set.of(...)`: Immutable set
- `Map.of(...)`: Immutable map
- `Map.ofEntries(...)`: Immutable map from entries

---

**Last Updated:** 2024  
**Version:** 1.0

*This guide is designed for quick reference during interview preparation. Practice the examples and understand the concepts thoroughly.*

