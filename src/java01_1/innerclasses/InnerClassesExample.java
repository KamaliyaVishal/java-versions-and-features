package java1_1.innerclasses;

/**
 * Java 1.1 Inner Classes Example Demonstrates different types of inner classes
 */
public class InnerClassesExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 1.1 Inner Classes ===\n");

		System.out.println("1. Member Inner Class");
		System.out.println("---------------------");
		Outer outer = new Outer();
		Outer.Inner inner = outer.new Inner();
		inner.display();

		System.out.println("\n2. Local Inner Class");
		System.out.println("-------------------");
		Outer outer2 = new Outer();
		outer2.methodWithLocalInner();

		System.out.println("\n3. Anonymous Inner Class");
		System.out.println("-----------------------");
		Greeting greeting = new Greeting()
		{
			@Override
			public void greet()
			{
				System.out.println("Hello from anonymous inner class");
			}
		};
		greeting.greet();

		System.out.println("\n4. Static Nested Class");
		System.out.println("---------------------");
		Outer.StaticNested nested = new Outer.StaticNested();
		nested.display();

		System.out.println("\nKey Features:");
		System.out.println("- Better encapsulation");
		System.out.println("- Access to outer class private members");
		System.out.println("- Logical grouping of classes");
		System.out.println("- More readable code");
	}
}

// Member Inner Class
class Outer
{
	private String message = "Hello from Outer";

	class Inner
	{
		void display()
		{
			System.out.println("Inner class accessing: " + message);
		}
	}

	// Local Inner Class
	void methodWithLocalInner()
	{
		class LocalInner
		{
			void display()
			{
				System.out.println("Local inner class");
			}
		}
		LocalInner local = new LocalInner();
		local.display();
	}

	// Static Nested Class
	static class StaticNested
	{
		void display()
		{
			System.out.println("Static nested class");
		}
	}
}

// Interface for anonymous inner class
interface Greeting
{
	void greet();
}

