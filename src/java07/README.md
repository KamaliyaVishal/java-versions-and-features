# Java 7

A comprehensive guide to all Java 7 concepts with practical examples for interview preparation.

## Table of Contents

1. [Try-with-Resources](#1-try-with-resources)
2. [Diamond Operator](#2-diamond-operator)
3. [Strings in Switch](#3-strings-in-switch)
4. [Binary Literals and Underscores in Numeric Literals](#4-binary-literals-and-underscores-in-numeric-literals)
5. [Multi-Catch](#5-multi-catch)
6. [Fork/Join Framework](#6-forkjoin-framework)
7. [NIO.2](#7-nio2)
8. [Common Interview Questions](#8-common-interview-questions)

---

## 1. Try-with-Resources

Try-with-resources automatically closes resources that implement `AutoCloseable`.

### Overview

Try-with-resources eliminates the need for explicit `finally` blocks to close resources.

### Syntax

```java
// Automatic resource management
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    e.printStackTrace();
}
// Resource automatically closed
```

### Multiple Resources

```java
try (FileInputStream fis = new FileInputStream("input.txt");
     FileOutputStream fos = new FileOutputStream("output.txt")) {
    // Use resources
}
```

### Benefits

- Automatic resource cleanup
- Less boilerplate code
- Prevents resource leaks
- Cleaner code

**See [TryWithResourcesExample.java](trywithresources/TryWithResourcesExample.java) for complete example.**

---

## 2. Diamond Operator

The diamond operator (`<>`) simplifies generic type declarations.

### Overview

The diamond operator allows the compiler to infer generic types from the context.

### Usage

```java
// Before Java 7
List<String> list = new ArrayList<String>();

// Java 7+
List<String> list = new ArrayList<>(); // Type inferred
```

### Benefits

- Less verbose
- More readable
- Type inference
- Less repetition

**See [DiamondOperatorExample.java](diamond/DiamondOperatorExample.java) for complete example.**

---

## 3. Strings in Switch

Switch statements can now use String values.

### Overview

String-based switch statements provide cleaner code than multiple if-else statements.

### Usage

```java
String day = "MONDAY";

switch (day) {
    case "MONDAY":
        System.out.println("Start of week");
        break;
    case "FRIDAY":
        System.out.println("End of week");
        break;
    default:
        System.out.println("Other day");
}
```

### Benefits

- Cleaner than if-else chains
- More readable
- Better performance than if-else
- Type-safe

**See [StringSwitchExample.java](switchdemo/StringSwitchExample.java) for complete example.**

---

## 4. Binary Literals and Underscores in Numeric Literals

Java 7 allows binary literals and underscores in numeric literals for better readability.

### Overview

Binary literals and underscores make numeric constants more readable.

### Usage

```java
// Binary literals
int binary = 0b1010; // 10 in decimal
long binaryLong = 0b1010L;

// Underscores in numeric literals
int million = 1_000_000;
long creditCard = 1234_5678_9012_3456L;
double pi = 3.14159_26535;
```

### Benefits

- Better readability
- Binary literal support
- Easier to read large numbers
- No impact on value

**See [NumericLiteralsExample.java](literals/NumericLiteralsExample.java) for complete example.**

---

## 5. Multi-Catch

Multi-catch allows catching multiple exception types in a single catch block.

### Overview

Multi-catch reduces code duplication when handling multiple exceptions the same way.

### Usage

```java
try {
    // Code that may throw multiple exceptions
} catch (IOException | SQLException e) {
    // Handle both exceptions
    e.printStackTrace();
}
```

### Benefits

- Less code duplication
- Cleaner exception handling
- More readable
- Same handling for multiple exceptions

**See [MultiCatchExample.java](exceptions/MultiCatchExample.java) for complete example.**

---

## 6. Fork/Join Framework

Fork/Join provides a framework for parallel processing of tasks.

### Overview

Fork/Join is designed for divide-and-conquer algorithms that can be parallelized.

### Basic Usage

```java
import java.util.concurrent.*;

class SumTask extends RecursiveTask<Long> {
    private int[] array;
    private int start, end;
    
    SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected Long compute() {
        if (end - start < 1000) {
            // Direct computation
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // Fork
            int mid = (start + end) / 2;
            SumTask left = new SumTask(array, start, mid);
            SumTask right = new SumTask(array, mid, end);
            left.fork();
            long rightResult = right.compute();
            long leftResult = left.join();
            return leftResult + rightResult;
        }
    }
}

// Usage
ForkJoinPool pool = new ForkJoinPool();
SumTask task = new SumTask(array, 0, array.length);
long result = pool.invoke(task);
```

### Key Features

- Divide-and-conquer parallelism
- Work-stealing algorithm
- Efficient for recursive tasks
- Automatic load balancing

**See [ForkJoinExample.java](forkjoin/ForkJoinExample.java) for complete example.**

---

## 7. NIO.2

NIO.2 provides improved file I/O operations and file system access.

### Overview

NIO.2 (New I/O 2) enhances file operations with better APIs and performance.

### Key Features

```java
import java.nio.file.*;

// Path operations
Path path = Paths.get("file.txt");
Files.exists(path);
Files.createFile(path);
Files.delete(path);

// Reading files
List<String> lines = Files.readAllLines(path);
byte[] bytes = Files.readAllBytes(path);

// Writing files
Files.write(path, "content".getBytes());
```

### Key Features

- Path API
- Files utility class
- Directory watching
- Symbolic links support
- Better file operations

**See [NIO2Example.java](nio2/NIO2Example.java) for complete example.**

---

## 8. Common Interview Questions

### Q1: What is Try-with-Resources?
**A:** Automatic resource management:
- Resources automatically closed
- Implements AutoCloseable
- Prevents resource leaks
- Cleaner than finally blocks

### Q2: What is the Diamond Operator?
**A:** Type inference for generics:
- `new ArrayList<>()` instead of `new ArrayList<String>()`
- Compiler infers type
- Less verbose code
- Java 7+ feature

### Q3: Can Switch use Strings?
**A:** Yes, since Java 7:
- String-based switch statements
- Cleaner than if-else chains
- Better performance
- Type-safe

### Q4: What is Multi-Catch?
**A:** Catching multiple exceptions:
- `catch (IOException | SQLException e)`
- Same handling for multiple exceptions
- Less code duplication
- More readable

### Q5: What is Fork/Join Framework?
**A:** Parallel processing framework:
- Divide-and-conquer algorithms
- Work-stealing algorithm
- Efficient for recursive tasks
- Automatic load balancing

---

**Last Updated:** 2025  
**Version:** 1.0

*This guide covers the features of Java 7, released on July 28, 2011.*

