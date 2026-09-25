# Java 1.1

A comprehensive guide to all Java 1.1 concepts with practical examples for interview preparation.

## Table of Contents

1. [Inner Classes](#1-inner-classes)
2. [JavaBeans](#2-javabeans)
3. [JDBC (Java Database Connectivity)](#3-jdbc-java-database-connectivity)
4. [RMI (Remote Method Invocation)](#4-rmi-remote-method-invocation)
5. [Reflection API](#5-reflection-api)
6. [Common Interview Questions](#6-common-interview-questions)

---

## 1. Inner Classes

Java 1.1 introduced inner classes, allowing classes to be defined within other classes.

### Overview

Inner classes provide better encapsulation and can access private members of the outer class.

### Types of Inner Classes

#### Member Inner Class

```java
class Outer {
    private String message = "Hello from Outer";
    
    class Inner {
        void display() {
            System.out.println(message); // Can access outer's private members
        }
    }
}

// Usage
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();
inner.display();
```

#### Local Inner Class

```java
class Outer {
    void method() {
        class LocalInner {
            void display() {
                System.out.println("Local inner class");
            }
        }
        LocalInner local = new LocalInner();
        local.display();
    }
}
```

#### Anonymous Inner Class

```java
interface Greeting {
    void greet();
}

// Anonymous inner class
Greeting greeting = new Greeting() {
    public void greet() {
        System.out.println("Hello from anonymous class");
    }
};
greeting.greet();
```

### Benefits

- Better encapsulation
- Access to outer class private members
- Logical grouping of classes
- More readable code

**See [InnerClassesExample.java](innerclasses/InnerClassesExample.java) for complete example.**

---

## 2. JavaBeans

JavaBeans is a component architecture for reusable Java components.

### Overview

JavaBeans follow specific conventions for creating reusable components.

### JavaBean Conventions

```java
public class PersonBean {
    private String name;
    private int age;
    
    // No-arg constructor
    public PersonBean() {
    }
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    // Setter methods
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
}
```

### Key Features

- No-argument constructor
- Getter methods (getPropertyName)
- Setter methods (setPropertyName)
- Serializable (optional)
- Property change support (optional)

**See [JavaBeansExample.java](javabeans/JavaBeansExample.java) for complete example.**

---

## 3. JDBC (Java Database Connectivity)

JDBC provides a standard API for database connectivity.

### Overview

JDBC allows Java applications to connect to databases and execute SQL queries.

### Basic JDBC Operations

```java
import java.sql.*;

public class JDBCExample {
    public static void main(String[] args) {
        try {
            // Load driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Create connection
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb", "user", "password");
            
            // Create statement
            Statement stmt = conn.createStatement();
            
            // Execute query
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            
            // Process results
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
            
            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### Key Features

- Database connectivity
- SQL query execution
- Result set processing
- Transaction management
- Prepared statements

**See [JDBCExample.java](jdbc/JDBCExample.java) for complete example.**

---

## 4. RMI (Remote Method Invocation)

RMI enables distributed computing by allowing objects to invoke methods on remote objects.

### Overview

RMI allows Java objects to call methods on objects running in different JVMs.

### RMI Components

```java
// Remote interface
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    int add(int a, int b) throws RemoteException;
}

// Remote implementation
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {
    public CalculatorImpl() throws RemoteException {
        super();
    }
    
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}

// Server
Registry registry = LocateRegistry.createRegistry(1099);
Calculator calc = new CalculatorImpl();
registry.rebind("Calculator", calc);

// Client
Registry registry = LocateRegistry.getRegistry("localhost", 1099);
Calculator calc = (Calculator) registry.lookup("Calculator");
int result = calc.add(5, 3);
```

### Key Features

- Remote method invocation
- Distributed computing
- Object serialization
- Registry for service lookup

**See [RMIExample.java](rmi/RMIExample.java) for complete example.**

---

## 5. Reflection API

Reflection allows runtime inspection and manipulation of classes, methods, and fields.

### Overview

Reflection API provides the ability to examine and modify program structure at runtime.

### Basic Reflection

```java
import java.lang.reflect.*;

// Get class information
Class<?> clazz = String.class;
System.out.println("Class name: " + clazz.getName());

// Get methods
Method[] methods = clazz.getMethods();
for (Method method : methods) {
    System.out.println("Method: " + method.getName());
}

// Get fields
Field[] fields = clazz.getDeclaredFields();
for (Field field : fields) {
    System.out.println("Field: " + field.getName());
}

// Invoke method
Method method = clazz.getMethod("length");
String str = "Hello";
int length = (Integer) method.invoke(str);
```

### Key Features

- Class inspection
- Method invocation
- Field access
- Constructor access
- Dynamic class loading

**See [ReflectionExample.java](reflection/ReflectionExample.java) for complete example.**

---

## 6. Common Interview Questions

### Q1: What are Inner Classes?
**A:** Classes defined within other classes:
- **Member Inner Class**: Defined at class level
- **Local Inner Class**: Defined within a method
- **Anonymous Inner Class**: Class without a name
- Can access outer class private members

### Q2: What are JavaBeans?
**A:** Reusable Java components following conventions:
- No-argument constructor
- Getter/setter methods
- Serializable (optional)
- Property change support

### Q3: What is JDBC?
**A:** Java Database Connectivity:
- Standard API for database access
- Driver-based architecture
- SQL query execution
- Result set processing

### Q4: What is RMI?
**A:** Remote Method Invocation:
- Distributed computing
- Remote method calls
- Object serialization
- Registry for service lookup

### Q5: What is Reflection?
**A:** Runtime inspection and manipulation:
- Class information
- Method invocation
- Field access
- Dynamic class loading

---

**Last Updated:** 2025  
**Version:** 1.0

*This guide covers the features of Java 1.1, released on February 19, 1997.*

