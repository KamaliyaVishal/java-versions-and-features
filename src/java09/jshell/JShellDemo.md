# Java 9 JShell (REPL) Demo

JShell is a Read-Eval-Print Loop (REPL) tool introduced in Java 9 for interactive Java programming.

## Starting JShell

```bash
jshell
```

## Basic Usage Examples

### Variables

```java
jshell> int x = 10
x ==> 10

jshell> int y = 20
y ==> 20

jshell> x + y
$3 ==> 30

jshell> String greeting = "Hello, JShell!"
greeting ==> "Hello, JShell!"
```

### Methods

```java
jshell> int add(int a, int b) {
   ...>     return a + b;
   ...> }
|  created method add(int,int)

jshell> add(5, 3)
$5 ==> 8
```

### Classes

```java
jshell> class Calculator {
   ...>     public int multiply(int a, int b) {
   ...>         return a * b;
   ...>     }
   ...> }
|  created class Calculator

jshell> Calculator calc = new Calculator()
calc ==> Calculator@2f92e0f4

jshell> calc.multiply(4, 5)
$7 ==> 20
```

## JShell Commands

### List Variables

```java
jshell> /vars
|    int x = 10
|    int y = 20
|    String greeting = "Hello, JShell!"
```

### List Methods

```java
jshell> /methods
|    int add(int,int)
```

### List Classes

```java
jshell> /types
|    class Calculator
```

### List All Snippets

```java
jshell> /list

   1 : int x = 10
   2 : int y = 20
   3 : x + y
   4 : String greeting = "Hello, JShell!"
   5 : int add(int a, int b) {
       return a + b;
   }
   6 : add(5, 3)
   7 : class Calculator {
       public int multiply(int a, int b) {
           return a * b;
       }
   }
   8 : Calculator calc = new Calculator()
   9 : calc.multiply(4, 5)
```

### Edit Snippet

```java
jshell> /edit add
```

### Save Snippets to File

```java
jshell> /save mysnippets.java
```

### Load Snippets from File

```java
jshell> /open mysnippets.java
```

### Reset JShell

```java
jshell> /reset
```

### Exit JShell

```java
jshell> /exit
```

## Advanced Features

### Imports

```java
jshell> import java.util.*
jshell> List<String> list = new ArrayList<>()
list ==> []
jshell> list.add("Hello")
$2 ==> true
```

### External Code

```java
jshell> /open /path/to/MyClass.java
```

### Feedback Mode

```java
jshell> /set feedback verbose
jshell> int x = 10
|  created variable x : int
x ==> 10

jshell> /set feedback normal
jshell> int y = 20
y ==> 20
```

## Use Cases

1. **Quick Testing**: Test Java code snippets without creating a full project
2. **Learning**: Experiment with Java features interactively
3. **Prototyping**: Quickly prototype ideas
4. **API Exploration**: Explore Java APIs interactively
5. **Debugging**: Test code snippets in isolation

## Benefits

- No need to create a class with main method
- Immediate feedback
- Easy experimentation
- Great for learning and teaching
- Can save and reload snippets

## Example Session

```java
$ jshell
|  Welcome to JShell -- Version 9
|  For an introduction type: /help intro

jshell> String name = "Java"
name ==> "Java"

jshell> System.out.println("Hello, " + name + "!")
Hello, Java!

jshell> int factorial(int n) {
   ...>     return n <= 1 ? 1 : n * factorial(n - 1);
   ...> }
|  created method factorial(int)

jshell> factorial(5)
$3 ==> 120

jshell> /vars
|    String name = "Java"

jshell> /methods
|    int factorial(int)

jshell> /exit
|  Goodbye
```

## Notes

- JShell is available from Java 9 onwards
- Variables, methods, and classes persist during the session
- Use `/help` to see all available commands
- Snippets can be saved and reused
- Supports tab completion for better productivity

