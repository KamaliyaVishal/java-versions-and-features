package java08.interfaceenhancements;

/**
 * Default Methods in Interfaces
 * 
 * Default methods allow interfaces to provide method implementations, enabling backward compatibility.
 * 
 * Common use cases:
 * - Adding new methods to existing interfaces
 * - Providing default implementations for methods
 */
interface DefaultA{
	default void sayHello(){
		System.out.println("A says Hello");
	}
}

interface DefaultB{
	default void sayHello(){
		System.out.println("B says Hello");
	}
}

public class _01_DefaultMethods implements DefaultA, DefaultB
{
	public static void main(String[] args)
	{
		_01_DefaultMethods defaultMethods = new _01_DefaultMethods();
		defaultMethods.sayHello();
	}

	@Override
	public void sayHello()
	{
		DefaultA.super.sayHello();
		DefaultB.super.sayHello();
		System.out.println("DefaultMethods says Hello");
	}
}

