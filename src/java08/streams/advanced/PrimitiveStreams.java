package java8.streams.advanced;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class PrimitiveStreams
{
	public static void main(String[] args)
	{
		int[] numbers = {1,32,4,8,55,12,76};
		IntStream stream = Arrays.stream(numbers);

		System.out.println(IntStream.range(1,5).boxed().collect(Collectors.toList()));
		System.out.println(IntStream.rangeClosed(1,5).boxed().collect(Collectors.toList()));

		IntStream.of(1,12,55);

		DoubleStream doubleStream  = new Random().doubles(5);
		System.out.println(doubleStream.boxed().toList());

		IntStream intStream  = new Random().ints(5);
		System.out.println(intStream.boxed().toList());

	}
}

