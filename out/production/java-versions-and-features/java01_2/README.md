# Java 1.2

A comprehensive guide to all Java 1.2 concepts with practical examples for interview preparation.

## Table of Contents

1. [Collections Framework](#1-collections-framework)
2. [Swing GUI Framework](#2-swing-gui-framework)
3. [Strictfp Keyword](#3-strictfp-keyword)
4. [Java Plug-in](#4-java-plug-in)
5. [Common Interview Questions](#5-common-interview-questions)

---

## 1. Collections Framework

Java 1.2 introduced the Collections Framework, providing a unified architecture for representing and manipulating collections.

### Overview

The Collections Framework provides implementations of data structures like lists, sets, and maps.

### Core Interfaces

```java
import java.util.*;

// List - Ordered collection
List<String> list = new ArrayList<>();
list.add("Java");
list.add("Python");
list.add("C++");

// Set - No duplicates
Set<Integer> set = new HashSet<>();
set.add(1);
set.add(2);
set.add(1); // Duplicate, ignored

// Map - Key-value pairs
Map<String, Integer> map = new HashMap<>();
map.put("Java", 1995);
map.put("Python", 1991);
```

### Key Classes

- **ArrayList**: Dynamic array implementation
- **LinkedList**: Doubly-linked list
- **HashSet**: Hash table-based set
- **TreeSet**: Sorted set
- **HashMap**: Hash table-based map
- **TreeMap**: Sorted map

### Benefits

- Unified API
- Reusable data structures
- Type-safe collections
- High performance
- Standard implementations

**See [CollectionsFrameworkExample.java](collections/CollectionsFrameworkExample.java) for complete example.**

---

## 2. Swing GUI Framework

Swing provides a rich set of GUI components, replacing AWT for most GUI applications.

### Overview

Swing is built on AWT but provides lightweight, platform-independent components.

### Basic Swing Application

```java
import javax.swing.*;

public class SwingExample extends JFrame {
    public SwingExample() {
        setTitle("Swing Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton button = new JButton("Click Me");
        JLabel label = new JLabel("Hello Swing");
        
        setLayout(new FlowLayout());
        add(label);
        add(button);
        
        setVisible(true);
    }
}
```

### Key Features

- Lightweight components
- Pluggable look and feel
- Rich component set
- Better than AWT
- MVC architecture

**See [SwingExample.java](swing/SwingExample.java) for complete example.**

---

## 3. Strictfp Keyword

The `strictfp` keyword ensures consistent floating-point calculations across platforms.

### Overview

`strictfp` guarantees that floating-point calculations produce the same results on all platforms.

### Usage

```java
strictfp class Calculator {
    strictfp double calculate(double a, double b) {
        return a * b / 3.14;
    }
}
```

### Key Features

- Platform-independent floating-point
- Consistent results
- IEEE 754 compliance
- Can be applied to classes, interfaces, and methods

**See [StrictfpExample.java](strictfpdemo/StrictfpExample.java) for complete example.**

---

## 4. Java Plug-in

Java Plug-in allows applets to run in web browsers with better control.

### Overview

Java Plug-in provides a way to run Java applets in browsers with improved security and performance.

### Benefits

- Better applet execution
- Improved security
- Better performance
- Cross-browser support

**Note:** Java Plug-in and applets are deprecated in modern Java.

---

## 5. Common Interview Questions

### Q1: What is the Collections Framework?
**A:** Unified architecture for collections:
- Core interfaces: List, Set, Map
- Implementations: ArrayList, HashSet, HashMap
- Algorithms: Sorting, searching
- Type-safe collections

### Q2: What is the difference between ArrayList and LinkedList?
**A:**
- **ArrayList**: Array-based, fast random access, slower insertions
- **LinkedList**: Node-based, slow random access, fast insertions
- Use ArrayList for frequent access
- Use LinkedList for frequent insertions/deletions

### Q3: What is Swing?
**A:** GUI framework:
- Lightweight components
- Platform-independent
- Rich component set
- Better than AWT
- Pluggable look and feel

### Q4: What is strictfp?
**A:** Keyword for consistent floating-point:
- Ensures same results on all platforms
- IEEE 754 compliance
- Can be applied to classes, interfaces, methods

### Q5: What is the difference between Set and List?
**A:**
- **Set**: No duplicates, unordered (HashSet) or sorted (TreeSet)
- **List**: Allows duplicates, maintains insertion order
- Set for unique elements
- List for ordered collections with duplicates

---

**Last Updated:** 2025  
**Version:** 1.0

*This guide covers the features of Java 1.2, released on December 8, 1998.*

