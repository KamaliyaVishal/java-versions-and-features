package java8.comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Java 8 Comparator Enhancements
 * 
 * Demonstrates new static methods and default methods in Comparator:
 * - comparing(): Create comparator from function
 * - thenComparing(): Chain comparators
 * - reversed(): Reverse order
 * - nullsFirst() / nullsLast(): Handle null values
 * - naturalOrder() / reverseOrder(): Natural ordering
 */
public class ComparatorEnhancements {
    
    static class Person {
        private String name;
        private int age;
        private String city;
        
        public Person(String name, int age, String city) {
            this.name = name;
            this.age = age;
            this.city = city;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getCity() { return city; }
        
        @Override
        public String toString() {
            return name + " (" + age + ", " + city + ")";
        }
    }
    
    public static void main(String[] args) {
        
        List<Person> people = Arrays.asList(
            new Person("Alice", 30, "New York"),
            new Person("Bob", 25, "Boston"),
            new Person("Charlie", 30, "Boston"),
            new Person("David", 25, "New York"),
            new Person("Eve", 35, "Chicago")
        );
        
        System.out.println("=== Comparator.comparing() ===");
        
        // 1. Sort by name
        System.out.println("\n--- Sort by Name ---");
        people.sort(Comparator.comparing(Person::getName));
        people.forEach(System.out::println);
        
        // 2. Sort by age
        System.out.println("\n--- Sort by Age ---");
        people.sort(Comparator.comparing(Person::getAge));
        people.forEach(System.out::println);
        
        // 3. Sort by age (reverse)
        System.out.println("\n--- Sort by Age (Reverse) ---");
        people.sort(Comparator.comparing(Person::getAge).reversed());
        people.forEach(System.out::println);
        
        System.out.println("\n=== Comparator.thenComparing() ===");
        
        // 4. Sort by age, then by name
        System.out.println("\n--- Sort by Age, then Name ---");
        people.sort(Comparator
            .comparing(Person::getAge)
            .thenComparing(Person::getName));
        people.forEach(System.out::println);
        
        // 5. Sort by city, then age, then name
        System.out.println("\n--- Sort by City, then Age, then Name ---");
        people.sort(Comparator
            .comparing(Person::getCity)
            .thenComparing(Person::getAge)
            .thenComparing(Person::getName));
        people.forEach(System.out::println);
        
        System.out.println("\n=== Null Handling ===");
        
        List<String> namesWithNulls = Arrays.asList("Charlie", null, "Alice", null, "Bob");
        
        // 6. Nulls first
        System.out.println("\n--- Nulls First ---");
        namesWithNulls.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
        namesWithNulls.forEach(s -> System.out.println(s == null ? "null" : s));
        
        // 7. Nulls last
        System.out.println("\n--- Nulls Last ---");
        List<String> namesWithNulls2 = Arrays.asList("Charlie", null, "Alice", null, "Bob");
        namesWithNulls2.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        namesWithNulls2.forEach(s -> System.out.println(s == null ? "null" : s));
        
        // 8. Null handling with field comparison
        List<Person> peopleWithNulls = Arrays.asList(
            new Person("Alice", 30, "New York"),
            new Person(null, 25, "Boston"),
            new Person("Charlie", 30, null),
            new Person("David", 25, "New York")
        );
        
        System.out.println("\n--- Sort by Name (Nulls Last) ---");
        peopleWithNulls.sort(Comparator.comparing(
            Person::getName, 
            Comparator.nullsLast(Comparator.naturalOrder())
        ));
        peopleWithNulls.forEach(System.out::println);
        
        System.out.println("\n=== Natural Ordering ===");
        
        // 9. Natural order
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);
        System.out.println("\n--- Natural Order ---");
        numbers.sort(Comparator.naturalOrder());
        System.out.println(numbers);
        
        // 10. Reverse order
        System.out.println("\n--- Reverse Order ---");
        numbers.sort(Comparator.reverseOrder());
        System.out.println(numbers);
        
        System.out.println("\n=== Custom Comparators ===");
        
        // 11. Custom comparator with lambda
        System.out.println("\n--- Sort by Name Length ---");
        people.sort(Comparator.comparing(p -> p.getName().length()));
        people.forEach(System.out::println);
        
        // 12. Multiple custom comparisons
        System.out.println("\n--- Sort by City (reverse), then Age ---");
        people.sort(Comparator
            .comparing(Person::getCity, Comparator.reverseOrder())
            .thenComparing(Person::getAge));
        people.forEach(System.out::println);
        
        System.out.println("\n=== Benefits ===");
        System.out.println("1. comparing(): Easy to create comparators from functions");
        System.out.println("2. thenComparing(): Chain multiple comparisons");
        System.out.println("3. nullsFirst/Last(): Handle null values gracefully");
        System.out.println("4. Method references: Clean and readable");
        System.out.println("5. Reversed: Easy to reverse any comparator");
        
        System.out.println("\n=== Before Java 8 (Old Way) ===");
        System.out.println("// Old way - verbose");
        System.out.println("Collections.sort(people, new Comparator<Person>() {");
        System.out.println("    public int compare(Person p1, Person p2) {");
        System.out.println("        int result = Integer.compare(p1.getAge(), p2.getAge());");
        System.out.println("        if (result == 0) {");
        System.out.println("            result = p1.getName().compareTo(p2.getName());");
        System.out.println("        }");
        System.out.println("        return result;");
        System.out.println("    }");
        System.out.println("});");
        
        System.out.println("\n=== Java 8 Way ===");
        System.out.println("// New way - concise");
        System.out.println("people.sort(Comparator");
        System.out.println("    .comparing(Person::getAge)");
        System.out.println("    .thenComparing(Person::getName));");
    }
}

