package java8.lambdasandother;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class MethodReferenceExample2 {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Matthew", "Arnold", 39)
        );

        // create a method that prints all elements in the list
        performConditionally(people, p -> true, System.out::println); // parameter call a method with the same parameter
    }

    private static void performConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
        for (Person p : people) {
            if (predicate.test(p)) {
                consumer.accept(p);
            }
        }
    }
}
