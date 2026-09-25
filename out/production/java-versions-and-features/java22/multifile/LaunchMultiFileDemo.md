# Java 22 Launch Multi-File Source-Code Programs (JEP 458)

## Overview

Java 22 allows you to launch multi-file Java programs directly without prior compilation. This simplifies development and testing by eliminating the need to compile multiple files separately.

## Basic Usage

### Example: Two-File Program

**Main.java:**
```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Main class");
        Helper.printMessage();
        System.out.println(Helper.getMessage());
    }
}
```

**Helper.java:**
```java
public class Helper {
    public static void printMessage() {
        System.out.println("Hello from Helper!");
    }
    
    public static String getMessage() {
        return "Helper message";
    }
}
```

### Running the Program

```bash
# Run directly without compilation
java Main.java Helper.java

# Or specify all files
java *.java
```

## Key Features

- **No compilation step**: Java compiler runs automatically
- **Multiple source files**: Can specify multiple .java files
- **Automatic dependency resolution**: Compiler resolves dependencies
- **Simplified development**: Faster iteration during development

## Use Cases

- Quick prototyping
- Testing multi-file programs
- Learning Java
- Small scripts with multiple classes
- Development and debugging

## Benefits

- Faster development cycle
- No need for build tools for simple programs
- Easier to run examples
- Better for learning and experimentation

## Limitations

- Not suitable for large projects
- No separate compilation step
- All files must be in the same directory (or specify paths)
- Slower for repeated runs (compiles each time)

## Example Directory Structure

```
project/
├── Main.java
├── Helper.java
└── Utils.java
```

Run with:
```bash
java Main.java Helper.java Utils.java
```

## Notes

- This feature works with the `java` launcher
- Files are compiled in memory
- Dependencies are automatically resolved
- Works with standard Java source files

