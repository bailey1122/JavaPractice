package java8.lambdasandother;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class StandardFunctionalInterfaces {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Matthew", "Arnold", 39)
        );

        // sort list by last name in ascending order. Pass in a lamda
        Collections.sort(people, (p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));

        // create a method that prints all elements in the list
        performConditionally(people, p -> true, p -> System.out.println(p));

        System.out.println();
        // create a method that prints all people that have last name beginning with C. Pass in a lamda
        performConditionally(people, p -> p.getLastName().startsWith("C"), p -> System.out.println(p));

        // with B
        performConditionally(people, p -> p.getLastName().startsWith("B"), p -> System.out.println(p.getFirstName()));
    }

    private static void performConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
        for (Person p : people) {
            if (predicate.test(p)) {
                consumer.accept(p);
            }
        }
    }
}
