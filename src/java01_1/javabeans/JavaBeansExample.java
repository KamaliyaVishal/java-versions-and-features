package java1_1.javabeans;

import java.io.Serializable;

/**
 * Java 1.1 JavaBeans Example Demonstrates JavaBean conventions
 */
public class JavaBeansExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 1.1 JavaBeans ===\n");

		// Create JavaBean
		PersonBean person = new PersonBean();
		person.setName("John Doe");
		person.setAge(30);
		person.setEmail("john@example.com");

		System.out.println("Person Information:");
		System.out.println("Name: " + person.getName());
		System.out.println("Age: " + person.getAge());
		System.out.println("Email: " + person.getEmail());

		System.out.println("\nJavaBean Conventions:");
		System.out.println("- No-argument constructor");
		System.out.println("- Getter methods (getPropertyName)");
		System.out.println("- Setter methods (setPropertyName)");
		System.out.println("- Serializable (optional)");
		System.out.println("- Property change support (optional)");

		System.out.println("\nBenefits:");
		System.out.println("- Reusable components");
		System.out.println("- Standard conventions");
		System.out.println("- Tool support");
		System.out.println("- Framework integration");
	}
}

/**
 * JavaBean following conventions
 */
class PersonBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	private String email;

	// No-argument constructor (required)
	public PersonBean()
	{
	}

	// Getter methods
	public String getName()
	{
		return name;
	}

	public int getAge()
	{
		return age;
	}

	public String getEmail()
	{
		return email;
	}

	// Setter methods
	public void setName(String name)
	{
		this.name = name;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
}

