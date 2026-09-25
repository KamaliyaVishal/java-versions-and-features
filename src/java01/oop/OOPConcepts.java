package java1.oop;

/**
 * Java 1.0 Object-Oriented Programming Concepts Demonstrates classes, objects, inheritance, and polymorphism
 */
public class OOPConcepts
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 1.0 OOP Concepts ===\n");

		System.out.println("1. Classes and Objects");
		System.out.println("---------------------");
		Person person = new Person("John", 30);
		System.out.println("Name: " + person.getName());
		System.out.println("Age: " + person.getAge());

		System.out.println("\n2. Inheritance");
		System.out.println("-------------");
		Dog dog = new Dog("Buddy");
		dog.makeSound();
		dog.displayInfo();

		System.out.println("\n3. Polymorphism");
		System.out.println("--------------");
		Animal animal1 = new Dog("Max");
		Animal animal2 = new Cat("Whiskers");
		animal1.makeSound(); // Polymorphic call
		animal2.makeSound(); // Polymorphic call

		System.out.println("\n4. Encapsulation");
		System.out.println("---------------");
		BankAccount account = new BankAccount(1000);
		System.out.println("Initial balance: " + account.getBalance());
		account.deposit(500);
		System.out.println("After deposit: " + account.getBalance());
		account.withdraw(200);
		System.out.println("After withdrawal: " + account.getBalance());

		System.out.println("\nKey OOP Principles:");
		System.out.println("- Encapsulation: Data hiding with private fields");
		System.out.println("- Inheritance: Extending classes");
		System.out.println("- Polymorphism: Method overriding");
		System.out.println("- Abstraction: Abstract classes and interfaces");
	}
}

// Example: Encapsulation
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

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}
}

// Example: Inheritance
class Animal
{
	protected String name;

	public Animal(String name)
	{
		this.name = name;
	}

	public void makeSound()
	{
		System.out.println("Animal makes sound");
	}

	public void displayInfo()
	{
		System.out.println("Animal: " + name);
	}
}

class Dog extends Animal
{
	public Dog(String name)
	{
		super(name);
	}

	@Override
	public void makeSound()
	{
		System.out.println(name + " barks: Woof! Woof!");
	}
}

class Cat extends Animal
{
	public Cat(String name)
	{
		super(name);
	}

	@Override
	public void makeSound()
	{
		System.out.println(name + " meows: Meow! Meow!");
	}
}

// Example: Encapsulation
class BankAccount
{
	private double balance;

	public BankAccount(double initialBalance)
	{
		this.balance = initialBalance;
	}

	public double getBalance()
	{
		return balance;
	}

	public void deposit(double amount)
	{
		if (amount > 0)
		{
			balance += amount;
		}
	}

	public void withdraw(double amount)
	{
		if (amount > 0 && amount <= balance)
		{
			balance -= amount;
		}
	}
}

