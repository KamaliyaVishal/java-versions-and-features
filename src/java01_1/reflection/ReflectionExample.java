package java1_1.reflection;

import java.lang.reflect.*;

/**
 * Java 1.1 Reflection API Example Demonstrates runtime inspection and manipulation
 */
public class ReflectionExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 1.1 Reflection API ===\n");

		try
		{
			// Get class information
			Class<?> clazz = String.class;
			System.out.println("1. Class Information");
			System.out.println("-------------------");
			System.out.println("Class name: " + clazz.getName());
			System.out.println("Simple name: " + clazz.getSimpleName());
			System.out.println("Package: " + clazz.getPackage());

			// Get methods
			System.out.println("\n2. Methods");
			System.out.println("---------");
			Method[] methods = clazz.getMethods();
			System.out.println("Total methods: " + methods.length);
			System.out.println("First 5 methods:");
			for (int i = 0; i < Math.min(5, methods.length); i++)
			{
				System.out.println("  - " + methods[i].getName());
			}

			// Get specific method
			System.out.println("\n3. Invoke Method");
			System.out.println("---------------");
			Method lengthMethod = clazz.getMethod("length");
			String str = "Hello, Reflection!";
			int length = (Integer) lengthMethod.invoke(str);
			System.out.println("Length of \"" + str + "\": " + length);

			// Get fields
			System.out.println("\n4. Fields");
			System.out.println("-------");
			Field[] fields = clazz.getDeclaredFields();
			System.out.println("Declared fields: " + fields.length);
			for (Field field : fields)
			{
				System.out.println("  - " + field.getName() + " (" + field.getType() + ")");
			}

			// Get constructors
			System.out.println("\n5. Constructors");
			System.out.println("--------------");
			Constructor<?>[] constructors = clazz.getConstructors();
			System.out.println("Public constructors: " + constructors.length);
			for (Constructor<?> constructor : constructors)
			{
				System.out.println("  - " + constructor);
			}

			// Create instance using reflection
			System.out.println("\n6. Create Instance");
			System.out.println("-----------------");
			Class<?> personClass = Person.class;
			Constructor<?> constructor = personClass.getConstructor(String.class, int.class);
			Object person = constructor.newInstance("John", 30);
			Method getNameMethod = personClass.getMethod("getName");
			String name = (String) getNameMethod.invoke(person);
			System.out.println("Created person: " + name);

		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("\nKey Features:");
		System.out.println("- Class inspection");
		System.out.println("- Method invocation");
		System.out.println("- Field access");
		System.out.println("- Constructor access");
		System.out.println("- Dynamic class loading");

		System.out.println("\nUse Cases:");
		System.out.println("- Frameworks (Spring, Hibernate)");
		System.out.println("- Testing frameworks");
		System.out.println("- Serialization");
		System.out.println("- IDE tools");
	}
}

class Person
{
	private String name;
	private int age;

	public Person(String name, int age)
	{
		this.name = name;
		this.age = age;
	}

	public String getName()
	{
		return name;
	}

	public int getAge()
	{
		return age;
	}
}

