# Java 8

A comprehensive guide to all Java 8 concepts with practical examples for interview preparation.

## Table of Contents

1. [Lambda Expressions](#1-lambda-expressions)
2. [Functional Interfaces](#2-functional-interfaces)
3. [Method References](#3-method-references)
4. [Streams API](#4-streams-api)
5. [Optional Class](#5-optional-class)
6. [Default Methods in Interfaces](#6-default-methods-in-interfaces)
7. [Static Methods in Interfaces](#7-static-methods-in-interfaces)
8. [Date and Time API](#8-date-and-time-api)
9. [CompletableFuture](#9-completablefuture)
10. [Parallel Streams](#10-parallel-streams)
11. [Nashorn JavaScript Engine](#11-nashorn-javascript-engine)
12. [Base64 Encoding/Decoding](#12-base64-encodingdecoding)
13. [Type Annotations](#13-type-annotations)
14. [Repeating Annotations](#14-repeating-annotations)
15. [StringJoiner](#15-stringjoiner)
16. [Collections Enhancements](#16-collections-enhancements)
17. [Arrays.parallelSort()](#17-arraysparallelsort)
18. [StampedLock](#18-stampedlock)
19. [Comparator Enhancements](#19-comparator-enhancements)
20. [Metaspace (Replacing PermGen)](#20-metaspace-replacing-permgen)
21. [Parameter Names in Reflection](#21-parameter-names-in-reflection)
22. [Common Interview Questions](#22-common-interview-questions)

---

## 1. Lambda Expressions

Lambda expressions provide a concise way to represent anonymous functions, enabling functional programming in Java.

### Syntax
```
(parameters) -> expression
(parameters) -> { statements; }
```

### Examples

```java
// Zero parameter
Runnable r1 = () -> System.out.println("Hello, World!");
r1.run();

// Shows the difference between traditional and lambda
Runnable r2 = new Runnable() {
    @Override
    public void run() {
        System.out.println("Traditional approach");
    }
};

Runnable r3 = () -> System.out.println("Lambda approach");

// Single parameter (parentheses optional for single param)
Function<Integer, Integer> square = (x) -> x * x;
Function<Integer, Integer> square2 = x -> x * x; // Also valid

// Multiple parameters
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

// With block body
BiFunction<Integer, Integer, Integer> multiply = (a, b) -> {
    int result = a * b;
    return result;
};
```

### Key Points for Interview:
- **Syntax**: `(params) -> body`
- **Type inference**: Compiler can infer types from context
- **Variable capture**: Can access final or effectively final variables
- **Short-circuit evaluation**: Can be used with conditional operations

---

## 2. Functional Interfaces

A functional interface has exactly one abstract method. Java 8 provides several built-in functional interfaces in `java.util.function` package.

### Core Functional Interfaces

#### 2.1 Predicate
Tests a condition and returns boolean.

```java
Predicate<Integer> isEven = x -> x % 2 == 0;
System.out.println(isEven.test(4));  // true
System.out.println(isEven.test(5));  // false

// Chaining predicates
Predicate<Integer> isGreaterThanTen = x -> x > 10;
Predicate<Integer> isEvenAndGreater = isEven.and(isGreaterThanTen);
System.out.println(isEvenAndGreater.test(12));  // true

// Negate
Predicate<Integer> isOdd = isEven.negate();
System.out.println(isOdd.test(5));  // true
```

#### 2.2 Function
Transforms one type to another.

```java
Function<String, Integer> lengthFunction = s -> s.length();
System.out.println(lengthFunction.apply("Hello"));  // 5

// Chaining functions
Function<Integer, Integer> multiplyByTwo = x -> x * 2;
Function<Integer, Integer> addThree = x -> x + 3;

Function<Integer, Integer> combined = multiplyByTwo.andThen(addThree);
System.out.println(combined.apply(5));  // 13

Function<Integer, Integer> composed = addThree.compose(multiplyByTwo);
System.out.println(composed.apply(5));  // 13
```

#### 2.3 Consumer
Consumes a value without returning anything.

```java
Consumer<String> printer = s -> System.out.println(s);
printer.accept("Hello World");

// Chaining consumers
Consumer<String> upperCase = s -> System.out.println(s.toUpperCase());
Consumer<String> doublizer = s -> System.out.println(s + s);
Consumer<String> chained = upperCase.andThen(doublizer);
chained.accept("Hi");  // HI, HiHi
```

#### 2.4 Supplier
Provides a value without taking any input.

```java
Supplier<Double> randomValue = () -> Math.random();
System.out.println(randomValue.get());

Supplier<String> stringSupplier = () -> "Hello from Supplier";
System.out.println(stringSupplier.get());
```

#### 2.5 UnaryOperator and BinaryOperator
Specialized versions of Function for same type operations.

```java
// UnaryOperator: Takes one parameter of type T and returns T
UnaryOperator<Integer> square = x -> x * x;
System.out.println(square.apply(5));  // 25

// BinaryOperator: Takes two parameters of type T and returns T
BinaryOperator<Integer> add = (a, b) -> a + b;
System.out.println(add.apply(10, 20));  // 30
```

#### 2.6 BiPredicate, BiFunction, BiConsumer
Two-parameter versions of the above.

```java
BiPredicate<Integer, Integer> isGreater = (a, b) -> a > b;
System.out.println(isGreater.test(10, 5));  // true

BiFunction<Integer, Integer, String> sumAsString = (a, b) -> String.valueOf(a + b);
System.out.println(sumAsString.apply(10, 20));  // "30"

BiConsumer<String, Integer> printer = (name, age) -> 
    System.out.println(name + " is " + age + " years old");
printer.accept("Alice", 25);  // Alice is 25 years old
```

### Custom Functional Interface

```java
@FunctionalInterface
interface Calculator {
    int operate(int a, int b);
}

Calculator addition = (a, b) -> a + b;
Calculator subtraction = (a, b) -> a - b;
Calculator multiplication = (a, b) -> a * b;

System.out.println(addition.operate(10, 5));  // 15
```

---

## 3. Method References

Method references provide a shorthand notation for lambda expressions that call methods.

### Types of Method References

#### 3.1 Static Method Reference
```java
// Lambda
Function<String, Integer> parseInt = s -> Integer.parseInt(s);

// Method reference
Function<String, Integer> parseIntRef = Integer::parseInt;
```

#### 3.2 Instance Method Reference (of a particular object)
```java
String prefix = "Hello ";
// Lambda
Function<String, String> addPrefix = s -> prefix.concat(s);

// Method reference
Function<String, String> addPrefixRef = prefix::concat;
```

#### 3.3 Instance Method Reference (of an arbitrary object)
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// Lambda
names.forEach(s -> System.out.println(s));

// Method reference
names.forEach(System.out::println);

// String length example
Function<String, Integer> length = String::length;
```

#### 3.4 Constructor Reference
```java
// Lambda
Supplier<List<String>> listSupplier = () -> new ArrayList<>();

// Constructor reference
Supplier<List<String>> listSupplierRef = ArrayList::new;

// With parameters
Function<Integer, String[]> arrayCreator = size -> new String[size];
Function<Integer, String[]> arrayCreatorRef = String[]::new;
```

---

## 4. Streams API

Streams provide a functional approach to process collections of data declaratively.

### Stream Characteristics
- **Not a data structure**: Doesn't store data
- **Lazy evaluation**: Operations are performed only when terminal operation is called
- **Functional in nature**: Doesn't modify source
- **Potentially unbounded**: Can work with infinite streams
- **Consumable**: Elements are visited only once during the stream's lifetime

### Creating Streams

```java
// From Collection
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream1 = list.stream();

// From Array
String[] array = {"a", "b", "c"};
Stream<String> stream2 = Arrays.stream(array);

// Using Stream.of()
Stream<String> stream3 = Stream.of("a", "b", "c");

// Using Stream.builder()
Stream<String> stream4 = Stream.<String>builder()
    .add("a").add("b").add("c").build();

// Empty stream
Stream<String> emptyStream = Stream.empty();

// Infinite streams
Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 2);
Stream<Double> randomStream = Stream.generate(Math::random);
```

### Intermediate Operations

Operations that return a stream and are lazy.

#### 4.1 filter()
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
List<Integer> evenNumbers = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());
// Result: [2, 4, 6]
```

#### 4.2 map()
```java
List<String> names = Arrays.asList("alice", "bob", "charlie");
List<String> upperNames = names.stream()
    .map(String::toUpperCase)
    .collect(Collectors.toList());
// Result: [ALICE, BOB, CHARLIE]

List<Integer> lengths = names.stream()
    .map(String::length)
    .collect(Collectors.toList());
// Result: [5, 3, 7]
```

#### 4.3 flatMap()
```java
List<List<String>> listOfLists = Arrays.asList(
    Arrays.asList("a", "b"),
    Arrays.asList("c", "d")
);
List<String> flatList = listOfLists.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());
// Result: [a, b, c, d]
```

#### 4.4 distinct()
```java
List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4);
List<Integer> distinct = numbers.stream()
    .distinct()
    .collect(Collectors.toList());
// Result: [1, 2, 3, 4]
```

#### 4.5 sorted()
```java
List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
List<String> sorted = names.stream()
    .sorted()
    .collect(Collectors.toList());
// Result: [Alice, Bob, Charlie]

// Custom comparator
List<String> reverseSorted = names.stream()
    .sorted(Comparator.reverseOrder())
    .collect(Collectors.toList());
```

#### 4.6 peek()
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
List<Integer> result = numbers.stream()
    .peek(System.out::println)  // Debugging
    .map(n -> n * 2)
    .peek(n -> System.out.println("Doubled: " + n))
    .collect(Collectors.toList());
```

#### 4.7 limit() and skip()
```java
Stream<Integer> numbers = Stream.iterate(0, n -> n + 1);
List<Integer> first10 = numbers.limit(10).collect(Collectors.toList());
// Result: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

List<Integer> skipFirst5 = Stream.iterate(0, n -> n + 1)
    .skip(5)
    .limit(5)
    .collect(Collectors.toList());
// Result: [5, 6, 7, 8, 9]
```

### Terminal Operations

Operations that produce a result or side-effect and terminate the stream.

#### 4.8 collect()
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// To List
List<String> list = names.stream().collect(Collectors.toList());

// To Set
Set<String> set = names.stream().collect(Collectors.toSet());

// To Map
Map<String, Integer> map = names.stream()
    .collect(Collectors.toMap(Function.identity(), String::length));

// Joining
String joined = names.stream().collect(Collectors.joining(", "));
// Result: "Alice, Bob, Charlie"

// Grouping
Map<Integer, List<String>> grouped = names.stream()
    .collect(Collectors.groupingBy(String::length));
// Result: {3=[Bob], 5=[Alice], 7=[Charlie]}

// Partitioning
Map<Boolean, List<String>> partitioned = names.stream()
    .collect(Collectors.partitioningBy(s -> s.length() > 4));
// Result: {false=[Bob], true=[Alice, Charlie]}
```

#### 4.9 reduce()
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Sum
Optional<Integer> sum = numbers.stream().reduce((a, b) -> a + b);
// or
Integer sum2 = numbers.stream().reduce(0, (a, b) -> a + b);

// Max
Optional<Integer> max = numbers.stream().reduce(Integer::max);

// String concatenation
String concatenated = numbers.stream()
    .map(String::valueOf)
    .reduce("", (a, b) -> a + b);
```

#### 4.10 forEach()
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.stream().forEach(System.out::println);
```

#### 4.11 findFirst() and findAny()
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
Optional<Integer> first = numbers.stream().findFirst();
Optional<Integer> any = numbers.parallelStream().findAny();
```

#### 4.12 anyMatch(), allMatch(), noneMatch()
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

boolean anyEven = numbers.stream().anyMatch(n -> n % 2 == 0);  // true
boolean allEven = numbers.stream().allMatch(n -> n % 2 == 0);  // false
boolean noneNegative = numbers.stream().noneMatch(n -> n < 0); // true
```

#### 4.13 Worst Case Scenario Examples
```java
// Longest string
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
Optional<String> longest = names.stream()
    .reduce((a, b) -> a.length() > b.length() ? a : b);

// Count occurrences
List<String> words = Arrays.asList("apple", "banana", "apple", "cherry");
Map<String, Long> countMap = words.stream()
    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

// Average
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
Double average = numbers.stream()
    .collect(Collectors.averagingInt(Integer::intValue));
```

### Primitive Streams

Specialized streams for primitives to avoid boxing/unboxing overhead.

```java
// IntStream
IntStream.range(1, 10).forEach(System.out::println);
int sum = IntStream.of(1, 2, 3, 4, 5).sum();

// LongStream
long product = LongStream.range(1, 6).reduce(1, (a, b) -> a * b);

// DoubleStream
double average = DoubleStream.of(1.0, 2.0, 3.0, 4.0).average().orElse(0.0);
```

---

## 5. Optional Class

Optional is a container object that may or may not contain a non-null value, helping to avoid NullPointerException.

### Creating Optional

```java
// Empty Optional
Optional<String> empty = Optional.empty();

// Optional with value
Optional<String> nonEmpty = Optional.of("Hello");

// Optional that may be null
Optional<String> nullable = Optional.ofNullable(getString());  // Returns null or string

// Optional.of() throws NPE if value is null
// Optional.ofNullable() returns empty Optional if value is null
```

### Working with Optional

```java
Optional<String> optional = Optional.of("Hello");

// isPresent() and get()
if (optional.isPresent()) {
    System.out.println(optional.get());
}

// ifPresent() - better approach
optional.ifPresent(System.out::println);

// orElse() - default value if empty
String value = optional.orElse("Default");

// orElseGet() - lazy evaluation
String value2 = optional.orElseGet(() -> expensiveOperation());

// orElseThrow() - throw exception if empty
String value3 = optional.orElseThrow(() -> new IllegalArgumentException("Value not present"));

// map() - transform value
Optional<Integer> length = optional.map(String::length);

// flatMap() - avoid nested Optional
Optional<String> flatMapped = optional.flatMap(s -> Optional.of(s.toUpperCase()));

// filter()
Optional<String> filtered = optional.filter(s -> s.length() > 5);
```

### Best Practices

```java
// ❌ BAD
if (optional.isPresent()) {
    return optional.get();
} else {
    return null;
}

// ✅ GOOD
return optional.orElse(null);

// ❌ BAD
if (optional.isPresent()) {
    doSomething(optional.get());
}

// ✅ GOOD
optional.ifPresent(this::doSomething);
```

---

## 6. Default Methods in Interfaces

Default methods allow interfaces to provide method implementations, enabling backward compatibility.

### Example

```java
interface Vehicle {
    void start();
    
    default void stop() {
        System.out.println("Vehicle stopped");
    }
    
    default void honk() {
        System.out.println("Beep beep!");
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car started");
    }
    
    // Can override default method
    @Override
    public void honk() {
        System.out.println("Car honking!");
    }
}

// Usage
Car car = new Car();
car.start();  // Car started
car.stop();   // Vehicle stopped
car.honk();   // Car honking!
```

### Multiple Inheritance Resolution

```java
interface A {
    default void method() {
        System.out.println("A");
    }
}

interface B {
    default void method() {
        System.out.println("B");
    }
}

class C implements A, B {
    // Must override to resolve conflict
    @Override
    public void method() {
        A.super.method();  // Call A's default method
        // or
        B.super.method();  // Call B's default method
        // or provide own implementation
        System.out.println("C");
    }
}
```

---

## 7. Static Methods in Interfaces

Interfaces can have static methods, providing utility methods related to the interface.

```java
interface MathOperations {
    static int add(int a, int b) {
        return a + b;
    }
    
    static int multiply(int a, int b) {
        return a * b;
    }
}

// Usage - called on interface, not instance
int sum = MathOperations.add(5, 3);
int product = MathOperations.multiply(4, 6);
```

---

## 8. Date and Time API

Java 8 introduced a new Date/Time API (java.time package) to replace the old Date and Calendar classes.

### Core Classes

```java
import java.time.*;

// LocalDate - date without time
LocalDate date = LocalDate.now();
LocalDate specificDate = LocalDate.of(2024, 3, 15);
LocalDate parsedDate = LocalDate.parse("2024-03-15");

// LocalTime - time without date
LocalTime time = LocalTime.now();
LocalTime specificTime = LocalTime.of(14, 30, 45);

// LocalDateTime - date and time
LocalDateTime dateTime = LocalDateTime.now();
LocalDateTime specificDateTime = LocalDateTime.of(2024, 3, 15, 14, 30);

// ZonedDateTime - with timezone
ZonedDateTime zonedDateTime = ZonedDateTime.now();
ZonedDateTime specificZone = ZonedDateTime.now(ZoneId.of("America/New_York"));

// Duration - time-based amount
Duration duration = Duration.between(time1, time2);
long seconds = duration.getSeconds();

// Period - date-based amount
Period period = Period.between(date1, date2);
int days = period.getDays();
```

### Operations

```java
LocalDate date = LocalDate.now();

// Adding/subtracting
LocalDate tomorrow = date.plusDays(1);
LocalDate nextWeek = date.plusWeeks(1);
LocalDate nextMonth = date.plusMonths(1);
LocalDate nextYear = date.plusYears(1);

// Comparison
boolean isAfter = date.isAfter(LocalDate.of(2020, 1, 1));
boolean isBefore = date.isBefore(LocalDate.of(2025, 1, 1));

// Formatting
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
String formatted = date.format(formatter);
LocalDate parsed = LocalDate.parse("15-03-2024", formatter);
```

---

## 9. CompletableFuture

CompletableFuture provides a way to write asynchronous, non-blocking code.

### Basic Usage

```java
// Creating CompletableFuture
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    // Simulate long-running task
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    return "Result";
});

// Getting result (blocks until complete)
String result = future.get();

// Non-blocking
future.thenAccept(result -> System.out.println("Got: " + result));
```

### Chaining Operations

```java
CompletableFuture<String> future = CompletableFuture
    .supplyAsync(() -> "Hello")
    .thenApply(s -> s + " World")
    .thenApply(String::toUpperCase);

future.thenAccept(System.out::println);  // HELLO WORLD

// Combining futures
CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");

CompletableFuture<String> combined = future1.thenCombine(
    future2, 
    (s1, s2) -> s1 + " " + s2
);

// Handling errors
CompletableFuture<String> futureWithError = CompletableFuture
    .supplyAsync(() -> {
        if (true) throw new RuntimeException("Error!");
        return "Success";
    })
    .exceptionally(ex -> "Error occurred: " + ex.getMessage());
```

---

## 10. Parallel Streams

Parallel streams enable parallel processing of collections using multiple threads.

```java
List<Integer> numbers = IntStream.range(0, 1000000).boxed()
    .collect(Collectors.toList());

// Sequential stream
long start = System.currentTimeMillis();
int sum1 = numbers.stream().mapToInt(Integer::intValue).sum();
long sequentialTime = System.currentTimeMillis() - start;

// Parallel stream
start = System.currentTimeMillis();
int sum2 = numbers.parallelStream().mapToInt(Integer::intValue).sum();
long parallelTime = System.currentTimeMillis() - start;

System.out.println("Sequential: " + sequentialTime);
System.out.println("Parallel: " + parallelTime);
```

### When to Use Parallel Streams

**Use when:**
- Large datasets
- Independent operations
- CPU-intensive tasks
- Order doesn't matter

**Avoid when:**
- Small datasets (overhead)
- Stateful operations
- Sequential dependencies
- Order matters

---

## 11. Nashorn JavaScript Engine

Java 8 includes Nashorn, a JavaScript engine (Note: Removed in Java 11).

```java
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

ScriptEngineManager manager = new ScriptEngineManager();
ScriptEngine engine = manager.getEngineByName("nashorn");

try {
    engine.eval("print('Hello from JavaScript!');");
    
    // Calling Java from JavaScript
    engine.eval("var result = java.lang.Math.sqrt(16); print(result);");
} catch (Exception e) {
    e.printStackTrace();
}
```

---

## 12. Base64 Encoding/Decoding

Java 8 provides built-in Base64 support.

```java
import java.util.Base64;

String original = "Hello, World!";

// Encoding
String encoded = Base64.getEncoder().encodeToString(original.getBytes());
System.out.println("Encoded: " + encoded);

// Decoding
byte[] decodedBytes = Base64.getDecoder().decode(encoded);
String decoded = new String(decodedBytes);
System.out.println("Decoded: " + decoded);

// URL and MIME encoding variants also available
String urlEncoded = Base64.getUrlEncoder().encodeToString(original.getBytes());
String mimeEncoded = Base64.getMimeEncoder().encodeToString(original.getBytes());
```

---

## 13. Type Annotations

Annotations can be applied to type uses.

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE_USE)
@interface NonNull {}

public class TypeAnnotationExample {
    public static void main(String[] args) {
        @NonNull String str = "Hello";
        System.out.println(str);
    }
}
```

---

## 14. Repeating Annotations

Same annotation can be applied multiple times.

```java
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Repeatable(Schedules.class)
@interface Schedule {
    String day();
}

@Retention(RetentionPolicy.RUNTIME)
@interface Schedules {
    Schedule[] value();
}

@Schedule(day = "Monday")
@Schedule(day = "Wednesday")
@Schedule(day = "Friday")
class Meeting {}
```

---

## 15. StringJoiner

StringJoiner provides a convenient way to join strings with a delimiter, prefix, and suffix.

```java
import java.util.StringJoiner;

// Basic usage
StringJoiner joiner = new StringJoiner(", ");
joiner.add("Apple").add("Banana").add("Cherry");
System.out.println(joiner.toString());  // Apple, Banana, Cherry

// With prefix and suffix
StringJoiner joiner2 = new StringJoiner(", ", "[", "]");
joiner2.add("One").add("Two").add("Three");
System.out.println(joiner2.toString());  // [One, Two, Three]

// Using String.join() (static method)
String joined = String.join("-", "2024", "03", "15");
System.out.println(joined);  // 2024-03-15
```

---

## 16. Collections Enhancements

### 16.1 removeIf()

Removes all elements matching a predicate.

```java
List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
numbers.removeIf(n -> n % 2 == 0);  // Remove even numbers
System.out.println(numbers);  // [1, 3, 5]
```

### 16.2 replaceAll()

Replaces all elements with the result of applying a function.

```java
List<String> names = new ArrayList<>(Arrays.asList("alice", "bob", "charlie"));
names.replaceAll(String::toUpperCase);
System.out.println(names);  // [ALICE, BOB, CHARLIE]
```

---

## 17. Arrays.parallelSort()

Parallel sorting for arrays, providing better performance on large datasets.

```java
int[] numbers = {5, 2, 8, 1, 9, 3, 7, 4, 6};
Arrays.parallelSort(numbers);
System.out.println(Arrays.toString(numbers));  // [1, 2, 3, 4, 5, 6, 7, 8, 9]

// With comparator
String[] names = {"Charlie", "Alice", "Bob", "David"};
Arrays.parallelSort(names, Comparator.reverseOrder());
System.out.println(Arrays.toString(names));  // [David, Charlie, Bob, Alice]
```

---

## 18. StampedLock

A new lock mechanism that provides optimistic read locks, improving performance for read-heavy scenarios.

```java
import java.util.concurrent.locks.StampedLock;

class Counter {
    private long value = 0;
    private final StampedLock lock = new StampedLock();
    
    public void increment() {
        long stamp = lock.writeLock();
        try {
            value++;
        } finally {
            lock.unlockWrite(stamp);
        }
    }
    
    public long read() {
        long stamp = lock.tryOptimisticRead();
        long current = value;
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                current = value;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return current;
    }
}
```

---

## 19. Comparator Enhancements

New static methods in Comparator for easier sorting.

```java
import java.util.Comparator;

List<Person> people = Arrays.asList(
    new Person("Alice", 30),
    new Person("Bob", 25),
    new Person("Charlie", 30)
);

// Comparing by field
people.sort(Comparator.comparing(Person::getName));
people.sort(Comparator.comparing(Person::getAge));

// Reverse order
people.sort(Comparator.comparing(Person::getAge).reversed());

// Then comparing (multiple fields)
people.sort(Comparator
    .comparing(Person::getAge)
    .thenComparing(Person::getName));

// Null handling
people.sort(Comparator.comparing(Person::getName, 
    Comparator.nullsLast(Comparator.naturalOrder())));
```

---

## 20. Metaspace (Replacing PermGen)

Java 8 replaced PermGen with Metaspace, which stores class metadata in native memory.

**Key Differences:**
- **PermGen**: Fixed size, part of heap
- **Metaspace**: Dynamic size, native memory
- **Benefits**: No OutOfMemoryError for PermGen, automatic resizing

**JVM Options:**
```bash
# Old (Java 7 and earlier)
-XX:PermSize=256m -XX:MaxPermSize=512m

# New (Java 8+)
-XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m
```

---

## 21. Parameter Names in Reflection

Java 8 allows access to parameter names via reflection (requires `-parameters` compiler flag).

```java
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ParameterNames {
    public void method(String name, int age) {
        // Method body
    }
    
    public static void main(String[] args) {
        Method method = ParameterNames.class.getMethod("method", String.class, int.class);
        Parameter[] parameters = method.getParameters();
        for (Parameter param : parameters) {
            System.out.println(param.getName());  // name, age (if compiled with -parameters)
        }
    }
}
```

**Compile with:**
```bash
javac -parameters ParameterNames.java
```

---

## 22. Common Interview Questions

### Q1: What is a functional interface?
**A:** A functional interface has exactly one abstract method. It can have multiple default or static methods. Examples: Runnable, Comparator, Predicate, Function, Consumer, Supplier.

### Q2: Difference between map() and flatMap()?
**A:** 
- `map()`: Transforms each element (1-to-1 mapping)
- `flatMap()`: Transforms and flattens (1-to-many mapping, then flattens)

```java
// map() - returns Stream<String>
Stream.of("a", "b").map(String::toUpperCase);  // [A, B]

// flatMap() - flattens nested streams
Stream.of(Arrays.asList("a", "b"), Arrays.asList("c", "d"))
    .flatMap(List::stream);  // [a, b, c, d]
```

### Q3: What is the difference between findFirst() and findAny()?
**A:** 
- `findFirst()`: Returns first element in encounter order
- `findAny()`: Returns any element (useful in parallel streams for performance)

### Q4: Explain lazy evaluation in streams.
**A:** Intermediate operations are lazy - they don't execute until a terminal operation is called. This allows optimization and early termination.

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
Stream<String> stream = names.stream()
    .filter(s -> {
        System.out.println("Filtering: " + s);  // Won't print yet
        return s.length() > 3;
    });  // No terminal operation - nothing happens

stream.forEach(System.out::println);  // Now it executes
```

### Q5: How do you handle exceptions in streams?
**A:** Streams don't support checked exceptions directly. Options:

```java
// 1. Wrap in RuntimeException
list.stream()
    .map(s -> {
        try {
            return riskyOperation(s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    });

// 2. Use a utility method
public <T, R> Function<T, R> wrap(CheckedFunction<T, R> fn) {
    return t -> {
        try {
            return fn.apply(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };
}
```

### Q6: Difference between Stream and Collection?
**A:**
- **Collection**: Data structure that stores elements
- **Stream**: Abstraction for processing sequences of elements
- Streams don't store data, are lazy, can be infinite, consumable once

### Q7: What are primitive streams and why use them?
**A:** Specialized streams (IntStream, LongStream, DoubleStream) for primitives to avoid boxing/unboxing overhead, improving performance.

### Q8: Explain Optional best practices.
**A:**
- Use `orElse()` for simple defaults
- Use `orElseGet()` for expensive operations (lazy)
- Use `orElseThrow()` to throw exceptions
- Avoid `get()` without checking `isPresent()`
- Use `ifPresent()` instead of if-else checks
- Chain operations with `map()` and `flatMap()`

### Q9: When to use parallel streams?
**A:** Use for large datasets with independent, CPU-intensive operations. Avoid for small data, stateful operations, or when order matters.

### Q10: Explain CompletableFuture vs Future.
**A:** CompletableFuture provides thoughtful asynchronous programming with chaining, composition, and exception handling. Future is more basic and requires manual thread management.

---

## Quick Reference Cheat Sheet

### Lambda Syntax
```java
() -> expression                    // No parameters
x -> expression                    // Single parameter
(x, y) -> expression              // Multiple parameters
x -> { statements; return value; } // Block body
```

### Common Functional Interfaces
- `Predicate<T>`: `boolean test(T t)`
- `Function<T, R>`: `R apply(T t)`
- `Consumer<T>`: `void accept(T t)`
- `Supplier<T>`: `T get()`
- `UnaryOperator<T>`: `T apply(T t)`
- `BinaryOperator<T>`: `T apply(T a, T b)`

### Stream Pipeline Pattern
```
source → intermediate operations → terminal operation
```

### Common Stream Operations
**Intermediate:** filter, map, flatMap, distinct, sorted, peek, limit, skip
**Terminal:** forEach, collect, reduce, count, findFirst, findAny, anyMatch, allMatch, noneMatch

### Optional Methods
- `isPresent()`, `isEmpty()`: Check if value exists
- `get()`: Get value (throws if empty)
- `orElse(T)`: Default value
- `orElseGet(Supplier)`: Lazy default
- `orElseThrow()`: Throw exception
- `ifPresent(Consumer)`: Execute if present
- `map(Function)`, `flatMap(Function)`: Transform
- `filter(Predicate)`: Filter value

---

## Resources

- [Oracle Java 8 Documentation](https://docs.oracle.com/javase/8/docs/)
- [Java Stream API Javadoc](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)
- [java.util.function Package](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)

---

**Last Updated:** 2024  
**Version:** 1.0

*This guide is designed for quick reference during interview preparation. Practice the examples and understand the concepts thoroughly.*

