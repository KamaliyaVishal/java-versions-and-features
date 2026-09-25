package java8.foundation;

/**
 * Demonstrates the use of anonymous inner classes in Java 8.
 * <p>
 * This class provides examples of:
 * <ul>
 *   <li>Using anonymous inner classes to implement interfaces.</li>
 *   <li>Using anonymous inner classes to override methods.</li>
 * </ul>
 */
interface Employee{
	void getSalary();
	void getDesignation();
}

public class AnonymousInnerClass
{
	public static void main(String[] args)
	{
		//Anonymous Inner class implementation,
		Employee employee = new Employee()
		{
			@Override
			public void getSalary()
			{
				
			}

			@Override
			public void getDesignation()
			{

			}
		};

	}
}

