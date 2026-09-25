# Java 5

A comprehensive guide to all Java 5 concepts with practical examples for interview preparation.

## Table of Contents

1. [Generics](#1-generics)
2. [Enhanced For-Loop (For-Each)](#2-enhanced-for-loop-for-each)
3. [Autoboxing and Unboxing](#3-autoboxing-and-unboxing)
4. [Enumerations (Enum)](#4-enumerations-enum)
5. [Varargs (Variable-Length Arguments)](#5-varargs-variable-length-arguments)
6. [Static Imports](#6-static-imports)
7. [Annotations](#7-annotations)
8. [Common Interview Questions](#8-common-interview-questions)

---

## 1. Generics

Generics provide type safety by allowing types to be parameters when defining classes, interfaces, and methods.

### Overview

Generics enable you to write code that works with different types while maintaining type safety at compile time.

### Basic Usage

```java
// Generic class
class Box<T> {
    private T item;
    
    public void setItem(T item) {
        this.item = item;
    }
    
    public T getItem() {
        return item;
    }
}

// Usage
Box<String> stringBox = new Box<>();
stringBox.setItem("Hello");
String value = stringBox.getItem();
```

### Generic Methods

```java
public static <T> void printArray(T[] array) {
    for (T element : array) {
        System.out.println(element);
    }
}
```

### Wildcards

```java
// Upper bounded wildcard
List<? extends Number> numbers;

// Lower bounded wildcard
List<? super Integer> integers;

// Unbounded wildcard
List<?> list;
```

### Benefits

- Type safety
- Eliminates casting
- Better code reusability
- Compile-time type checking

**See [GenericsExample.java](generics/GenericsExample.java) for complete example.**

---

## 2. Enhanced For-Loop (For-Each)

The enhanced for-loop provides a simpler way to iterate over collections and arrays.

### Overview

The for-each loop eliminates the need for explicit iterator or index management.

### Syntax

```java
// For arrays
int[] numbers = {1, 2, 3, 4, 5};
for (int num : numbers) {
    System.out.println(num);
}

// For collections
List<String> list = new ArrayList<>();
for (String item : list) {
    System.out.println(item);
}
```

### Benefits

- Simpler syntax
- Less error-prone
- More readable
- Automatic iteration

**See [EnhancedForLoopExample.java](loops/EnhancedForLoopExample.java) for complete example.**

---

## 3. Autoboxing and Unboxing

Automatic conversion between primitive types and their wrapper classes.

### Overview

Autoboxing converts primitives to wrapper objects, and unboxing converts wrapper objects to primitives.

### Examples

```java
// Autoboxing: int -> Integer
Integer i = 10; // Automatically boxes int to Integer

// Unboxing: Integer -> int
int value = i; // Automatically unboxes Integer to int

// In collections
List<Integer> numbers = new ArrayList<>();
numbers.add(1); // Autoboxing
int num = numbers.get(0); // Unboxing
```

### Benefits

- Convenient
- Works seamlessly with collections
- Less boilerplate code

**See [AutoboxingExample.java](autoboxing/AutoboxingExample.java) for complete example.**

---

## 4. Enumerations (Enum)

Enums provide a type-safe way to define a fixed set of constants.

### Overview

Enums are classes that represent a group of constants.

### Basic Enum

```java
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

// Usage
Day today = Day.MONDAY;
```

### Enum with Methods

```java
enum Planet {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6);
    
    private final double mass;
    private final double radius;
    
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }
    
    public double getMass() {
        return mass;
    }
}
```

### Benefits

- Type safety
- Can have methods and fields
- Switch statement support
- Better than constants

**See [EnumExample.java](enums/EnumExample.java) for complete example.**

---

## 5. Varargs (Variable-Length Arguments)

Varargs allow methods to accept a variable number of arguments.

### Overview

Varargs simplify method calls by allowing zero or more arguments of the same type.

### Syntax

```java
public static void printNumbers(int... numbers) {
    for (int num : numbers) {
        System.out.println(num);
    }
}

// Usage
printNumbers(1, 2, 3);
printNumbers(1, 2, 3, 4, 5);
printNumbers(); // Zero arguments
```

### Key Features

- Variable number of arguments
- Treated as array
- Must be last parameter
- Can have other parameters before it

**See [VarargsExample.java](varargs/VarargsExample.java) for complete example.**

---

## 6. Static Imports

Static imports allow you to import static members of a class.

### Overview

Static imports eliminate the need to qualify static members with their class name.

### Usage

```java
import static java.lang.Math.*;

// Now can use without Math prefix
double result = sqrt(16); // Instead of Math.sqrt(16)
double pi = PI; // Instead of Math.PI
```

### Benefits

- Less verbose code
- More readable
- Convenient for constants and utility methods

**See [StaticImportsExample.java](staticimports/StaticImportsExample.java) for complete example.**

---

## 7. Annotations

Annotations provide metadata about program elements.

### Overview

Annotations can be used by tools and frameworks to generate code, perform validation, etc.

### Built-in Annotations

```java
@Override
public String toString() {
    return "Overridden method";
}

@Deprecated
public void oldMethod() {
    // Deprecated method
}

@SuppressWarnings("unchecked")
List list = new ArrayList();
```

### Custom Annotations

```java
@interface MyAnnotation {
    String value();
    int count() default 1;
}

@MyAnnotation(value = "test", count = 5)
public class MyClass {
}
```

### Key Features

- Metadata for code
- Used by tools and frameworks
- Compile-time and runtime processing
- Can have parameters

**See [AnnotationsExample.java](annotations/AnnotationsExample.java) for complete example.**

---

## 8. Common Interview Questions

### Q1: What are Generics?
**A:** Type parameters for classes and methods:
- Type safety at compile time
- Eliminates casting
- Better code reusability
- Wildcards for flexibility

### Q2: What is the difference between ArrayList and ArrayList<String>?
**A:**
- **ArrayList**: Raw type, no type safety, requires casting
- **ArrayList<String>**: Generic type, type-safe, no casting needed
- Always use generics for type safety

### Q3: What is Autoboxing?
**A:** Automatic conversion:
- Primitive to wrapper (autoboxing)
- Wrapper to primitive (unboxing)
- Happens automatically
- Used extensively with collections

### Q4: What are Enums?
**A:** Type-safe constants:
- Fixed set of values
- Can have methods and fields
- Better than public static final constants
- Switch statement support

### Q5: What are Varargs?
**A:** Variable-length arguments:
- Method accepts variable number of arguments
- Treated as array
- Must be last parameter
- Simplifies method calls

---

**Last Updated:** 2025  
**Version:** 1.0

*This guide covers the features of Java 5, released on September 30, 2004.*

