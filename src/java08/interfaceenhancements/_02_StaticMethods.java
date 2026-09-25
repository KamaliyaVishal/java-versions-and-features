package java8.interfaceenhancements;

/**
 * Static Methods in Interfaces
 * 
 * Static methods belong to the interface, not to instances.
 * They can be called using the interface name.
 * 
 * Common use cases:
 * - Providing utility methods
 * - Providing constants
 */
interface StaticA
{
	static void sayHello()
	{
		System.out.println("StaticA says sayHello");
	}

	default void defaultSayHello()
	{
		System.out.println("StaticA says defaultSayHello");
	}
}

class MethodHiding implements StaticA
{
	//This is method hiding not method overriding
	static void sayHello()
	{
		System.out.println("MethodHiding says sayHello");
	}
}

public class _02_StaticMethods implements StaticA
{
	public static void main(String[] args)
	{
		StaticA.sayHello(); // only one way to call static method inside interface

		_02_StaticMethods staticMethods = new _02_StaticMethods();
		//staticMethods.sayHello(); // Can't invoke static method by class reference
		staticMethods.defaultSayHello(); // default method can invoke by class reference

		MethodHiding.sayHello();

	}
}

interface MianInterface
{
	public static void main(String[] args)
	{
		System.out.println("Main method allow inside interface from Java 8");
	}
}

