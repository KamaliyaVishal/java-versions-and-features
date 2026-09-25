# Java 1.0

A comprehensive guide to all Java 1.0 concepts with practical examples for interview preparation.

## Table of Contents

1. [Core Language Features](#1-core-language-features)
2. [Object-Oriented Programming](#2-object-oriented-programming)
3. [AWT (Abstract Window Toolkit)](#3-awt-abstract-window-toolkit)
4. [Applets](#4-applets)
5. [Basic I/O](#5-basic-io)
6. [Common Interview Questions](#6-common-interview-questions)

---

## 1. Core Language Features

Java 1.0 introduced the fundamental language constructs that form the basis of Java.

### Basic Syntax

```java
// Hello World
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

### Primitive Types

```java
// Primitive data types
byte b = 127;
short s = 32767;
int i = 2147483647;
long l = 9223372036854775807L;
float f = 3.14f;
double d = 3.14159;
char c = 'A';
boolean bool = true;
```

### Control Flow

```java
// If-else
if (condition) {
    // code
} else {
    // code
}

// Switch
switch (value) {
    case 1:
        // code
        break;
    default:
        // code
}

// Loops
for (int i = 0; i < 10; i++) {
    // code
}

while (condition) {
    // code
}

do {
    // code
} while (condition);
```

### Key Features

- **Platform Independence**: Write once, run anywhere
- **Object-Oriented**: Classes, objects, inheritance, polymorphism
- **Memory Management**: Automatic garbage collection
- **Type Safety**: Strong typing system
- **Exception Handling**: Try-catch-finally blocks

**See [CoreLanguageFeatures.java](core/CoreLanguageFeatures.java) for complete example.**

---

## 2. Object-Oriented Programming

Java 1.0 established the core OOP principles.

### Classes and Objects

```java
// Class definition
public class Person {
    private String name;
    private int age;
    
    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Methods
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
```

### Inheritance

```java
// Base class
class Animal {
    void makeSound() {
        System.out.println("Animal makes sound");
    }
}

// Derived class
class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barks");
    }
}
```

### Polymorphism

```java
Animal animal = new Dog();
animal.makeSound(); // Polymorphic call
```

### Key Features

- **Classes**: Blueprint for objects
- **Objects**: Instances of classes
- **Inheritance**: Extending classes
- **Polymorphism**: Method overriding
- **Encapsulation**: Data hiding with private fields

**See [OOPConcepts.java](oop/OOPConcepts.java) for complete example.**

---

## 3. AWT (Abstract Window Toolkit)

AWT provides the foundation for GUI development in Java.

### Basic Window

```java
import java.awt.*;

public class BasicWindow extends Frame {
    public BasicWindow() {
        setTitle("My Window");
        setSize(400, 300);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new BasicWindow();
    }
}
```

### Components

```java
import java.awt.*;

public class ComponentExample extends Frame {
    public ComponentExample() {
        setLayout(new FlowLayout());
        
        // Button
        Button btn = new Button("Click Me");
        add(btn);
        
        // Label
        Label label = new Label("Hello AWT");
        add(label);
        
        // TextField
        TextField textField = new TextField(20);
        add(textField);
        
        setSize(300, 200);
        setVisible(true);
    }
}
```

### Key Features

- **Components**: Buttons, labels, text fields, etc.
- **Layout Managers**: FlowLayout, BorderLayout, GridLayout
- **Event Handling**: ActionListener, WindowListener
- **Graphics**: Drawing capabilities

**See [AWTExample.java](awt/AWTExample.java) for complete example.**

---

## 4. Applets

Applets allow Java programs to run in web browsers.

### Basic Applet

```java
import java.applet.Applet;
import java.awt.Graphics;

public class HelloApplet extends Applet {
    public void paint(Graphics g) {
        g.drawString("Hello, World!", 20, 20);
    }
}
```

### Applet Lifecycle

```java
import java.applet.Applet;
import java.awt.Graphics;

public class LifecycleApplet extends Applet {
    public void init() {
        // Initialization
    }
    
    public void start() {
        // Applet starts
    }
    
    public void paint(Graphics g) {
        // Drawing
    }
    
    public void stop() {
        // Applet stops
    }
    
    public void destroy() {
        // Cleanup
    }
}
```

### Key Features

- **Browser Integration**: Run in web browsers
- **Lifecycle Methods**: init(), start(), stop(), destroy()
- **Graphics**: Drawing on applet surface
- **Security**: Sandboxed execution

**Note:** Applets are deprecated in modern Java. This is for historical reference.

**See [AppletExample.java](applets/AppletExample.java) for complete example.**

---

## 5. Basic I/O

Java 1.0 provides basic input/output capabilities.

### File I/O

```java
import java.io.*;

public class FileIOExample {
    public static void main(String[] args) {
        // Writing to file
        try {
            FileWriter writer = new FileWriter("output.txt");
            writer.write("Hello, Java 1.0!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Reading from file
        try {
            FileReader reader = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Console I/O

```java
// Console output
System.out.println("Hello, World!");
System.out.print("No newline");

// Console input (using BufferedReader)
BufferedReader reader = new BufferedReader(
    new InputStreamReader(System.in)
);
String input = reader.readLine();
```

### Key Features

- **File I/O**: FileReader, FileWriter, BufferedReader
- **Streams**: InputStream, OutputStream
- **Exception Handling**: IOException handling
- **Console I/O**: System.in, System.out, System.err

**See [FileIOExample.java](io/FileIOExample.java) for complete example.**

---

## 6. Common Interview Questions

### Q1: What are the key features of Java 1.0?
**A:** Key features:
- Platform independence (Write Once, Run Anywhere)
- Object-oriented programming
- Automatic garbage collection
- Strong type system
- Exception handling
- AWT for GUI development
- Applets for web integration

### Q2: What is the difference between Java and other languages?
**A:**
- **Platform Independence**: Java bytecode runs on any platform with JVM
- **Automatic Memory Management**: Garbage collection
- **Strong Typing**: Compile-time type checking
- **Object-Oriented**: Everything is an object (except primitives)

### Q3: What is an Applet?
**A:** An applet is a Java program that runs in a web browser:
- Embedded in HTML pages
- Has lifecycle methods (init, start, stop, destroy)
- Runs in a sandboxed environment
- **Note**: Deprecated in modern Java

### Q4: What is AWT?
**A:** Abstract Window Toolkit:
- Provides GUI components
- Platform-dependent native components
- Layout managers for component arrangement
- Event handling mechanism

### Q5: How does Java achieve platform independence?
**A:**
- Java source code is compiled to bytecode
- Bytecode runs on Java Virtual Machine (JVM)
- JVM is platform-specific but bytecode is platform-independent
- "Write Once, Run Anywhere" principle

---

**Last Updated:** 2025  
**Version:** 1.0

*This guide covers the foundational features of Java 1.0, released on January 23, 1996.*

