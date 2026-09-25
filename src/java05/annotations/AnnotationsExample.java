package java5.annotations;

import java.lang.annotation.*;
import java.util.*;

/**
 * Java 5 Annotations Example Demonstrates built-in and custom annotations
 */
public class AnnotationsExample
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		System.out.println("=== Java 5 Annotations ===\n");

		System.out.println("1. Built-in Annotations");
		System.out.println("---------------------");
		MyClass obj = new MyClass();
		obj.overriddenMethod();
		obj.oldMethod(); // Deprecated method

		System.out.println("\n2. Custom Annotation");
		System.out.println("-------------------");
		Class<?> clazz = AnnotatedClass.class;
		if (clazz.isAnnotationPresent(MyAnnotation.class))
		{
			MyAnnotation annotation = clazz.getAnnotation(MyAnnotation.class);
			System.out.println("Value: " + annotation.value());
			System.out.println("Count: " + annotation.count());
		}

		System.out.println("\n3. Annotation Types");
		System.out.println("-----------------");
		System.out.println("@Override: Indicates method overrides superclass method");
		System.out.println("@Deprecated: Marks element as deprecated");
		System.out.println("@SuppressWarnings: Suppresses compiler warnings");
		System.out.println("@Retention: Specifies annotation retention policy");
		System.out.println("@Target: Specifies where annotation can be used");

		System.out.println("\nKey Features:");
		System.out.println("- Metadata for code");
		System.out.println("- Used by tools and frameworks");
		System.out.println("- Compile-time and runtime processing");
		System.out.println("- Can have parameters");

		System.out.println("\nUse Cases:");
		System.out.println("- Framework configuration (Spring, Hibernate)");
		System.out.println("- Code generation");
		System.out.println("- Validation");
		System.out.println("- Documentation");
	}
}

// Custom annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface MyAnnotation
{
	String value();

	int count() default 1;
}

// Class using custom annotation
@MyAnnotation(value = "test", count = 5)
class AnnotatedClass
{
}

// Class demonstrating built-in annotations
class MyClass
{
	@Override
	public String toString()
	{
		return "Overridden toString method";
	}

	public void overriddenMethod()
	{
		System.out.println("This method uses @Override annotation");
	}

	@Deprecated
	public void oldMethod()
	{
		System.out.println("This method is deprecated");
	}
}

