package java8.optional;

import java.util.Optional;

/**
 * Optional Demo
 * 
 * Optional is a container object which may or may not contain a non-null value.
 * It is a replacement for null values.
 * 
 * Common use cases:
 * - Avoiding null checks
 * - Providing default values
 */
public class OptionalDemo
{
	public static void main(String[] args)
	{
		//Optional is a container object which may or may not contain a non-null value.
		Optional<String> optional = Optional.ofNullable(null);
		System.out.println("optional : " + optional);

		if (optional.isPresent())
		{
			System.out.println("optional is present");
		}
		else
		{
			System.out.println("optional is not present");
		}

		Optional<String> optional1 = Optional.of("Hello");
		System.out.println("optional1 : " + optional1);

		Optional<String> optional2 = Optional.ofNullable("Hello");
		System.out.println("optional2 : " + optional2);

		//orElse
		String value = optional1.orElse("World");
		System.out.println("value : " + value);

		//orElseGet
		String value2 = optional2.orElseGet(() -> "World");
		System.out.println("value2 : " + value2);

		//orElseThrow
		String value3 = optional1.orElseThrow(() -> new RuntimeException("Value is not present"));
		System.out.println("value3 : " + value3);

		//ifPresent
		optional1.ifPresent(System.out::println);
		optional2.ifPresent(System.out::println);

		//ifPresentOrElse
		optional1.ifPresentOrElse(System.out::println, () -> System.out.println("Value is not present"));
		optional2.ifPresentOrElse(System.out::println, () -> System.out.println("Value is not present"));

		//map
		Optional<String> value4 = optional1.map(String::toUpperCase);
		System.out.println("value4 : " + value4);
		Optional<String> value5 = optional2.map(String::toUpperCase);
		System.out.println("value5 : " + value5);

		//flatMap
		Optional<String> value6 = optional1.flatMap(s -> Optional.of(s.toUpperCase()));
		System.out.println("value6 : " + value6);
		Optional<String> value7 = optional2.flatMap(s -> Optional.of(s.toUpperCase()));
		System.out.println("value7 : " + value7);

	}
}

