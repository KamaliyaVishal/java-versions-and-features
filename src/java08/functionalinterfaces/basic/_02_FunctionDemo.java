package java8.functionalinterfaces.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Function Functional Interface Demo
 * 
 * Function<T, R> represents a function that accepts one argument and produces a result.
 * It has one abstract method: R apply(T t)
 * 
 * Common use cases:
 * - Mapping collections
 * - Transforming data
 */
public class _02_FunctionDemo
{
	public static void main(String[] args)
	{
		//Represents a function that accepts one argument and produces a result.
		Function<String, String> prefixFun = x -> x.substring(0, 2);
		Function<List<Student>, List<Student>> studentWithVIPrefix = li -> {
			List<Student> filteredStudent = new ArrayList<>();
			for (Student student : li)
			{
				if (prefixFun.apply(student.name).equalsIgnoreCase("VI"))
				{
					filteredStudent.add(student);
				}
			}
			return filteredStudent;
		};

		Student s1 = new Student(1, "Vishal");
		Student s2 = new Student(1, "Raj");
		Student s3 = new Student(1, "Vipul");

		System.out.println("Student list that start with VI");
		System.out.println(studentWithVIPrefix.apply(Arrays.asList(s1, s2, s3)));
	}
}

class Student
{
	int id;
	String name;

	public void setId(int id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Student(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}

