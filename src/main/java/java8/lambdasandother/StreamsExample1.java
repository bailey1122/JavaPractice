package java8.lambdasandother;

import java.util.Arrays;
import java.util.List;

public class StreamsExample1 {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Matthew", "Arnold", 39)
        );

        long count = people.stream()
            .filter(p -> p.getLastName().startsWith("C"))
                .count();
        System.out.println(count);

        System.out.println();

        // split the collection into multiple streams and do the same
        long coun2 = people.parallelStream()
                .filter(p -> p.getLastName().startsWith("C"))
                .count();

        System.out.println();

        people.stream()
            .filter(p -> p.getLastName().startsWith("C")) // elements that start with "C" remains on the conveyor belt
                .forEach(p -> System.out.println(p.getFirstName()));

        System.out.println();
        // puts all elements to the conveyor belt and prints the first name for each element
        people.stream()
            .forEach(p -> System.out.println(p.getFirstName())); // terminal operation causes the stream to act

    }
}
