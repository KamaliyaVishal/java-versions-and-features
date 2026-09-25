package java8.foundation;

/**
 * Functional interface for multiplication operation.
 * <p>
 * This class provides examples of:
 * <ul>
 *   <li>Using {@code FunctionalInterface} to create a functional interface.</li>
 *   <li>Using {@code @FunctionalInterface} to annotate a functional interface.</li>
 *   <li>Using {@code FuncInterface} to implement a functional interface.</li>
 *   <li>Using {@code FuncInterfaceImpl} to implement a functional interface.</li>
 * </ul>
 */
@FunctionalInterface
interface FuncInterface
{
	int multiplication(int num);
}

/**
 * Named class implementation of FuncInterface.
 */
class FuncInterfaceImpl implements FuncInterface
{
	@Override
	public int multiplication(int num)
	{
		return num * num;
	}
}

/**
 * Demonstrates various implementations of FuncInterface.
 */
public class FuncInterfaceDemo
{
	public static void main(String[] args)
	{
		// Implementation using a Named Class
		FuncInterface namedImpl = new FuncInterfaceImpl();
		System.out.println("Named Class Implementation: " + namedImpl.multiplication(8));

		// Implementation using Anonymous Inner Class
		FuncInterface anonymousImpl = new FuncInterface()
		{
			@Override
			public int multiplication(int num)
			{
				return num * num;
			}
		};
		System.out.println("Anonymous Inner Class Implementation: " + anonymousImpl.multiplication(5));

		// Implementation using Lambda Expression
		// Interface reference can be used to hold lambda expression
		FuncInterface lambdaImpl = num -> num * num;
		System.out.println("Lambda Expression Implementation: " + lambdaImpl.multiplication(6));
	}
}

