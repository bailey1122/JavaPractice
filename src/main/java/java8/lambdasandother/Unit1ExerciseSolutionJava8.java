package java8.lambdasandother;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// skip the implementation and use lamdas. The first step to create an interface and define a method in the interface.
// Find the right interface from the java.util package
public class Unit1ExerciseSolutionJava8 {

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
        printConditionally(people, p -> true);

        System.out.println();
        // create a method that prints all people that have last name beginning with C. Pass in a lamda
        printConditionally(people, p -> p.getLastName().startsWith("C"));

        // with B
        printConditionally(people, p -> p.getLastName().startsWith("B"));
    }

    private static void printConditionally(List<Person> people, Predicate<Person> predicate) {
        for (Person p : people) {
            if (predicate.test(p)) {
                System.out.println(p);
            }
        }
    }

//    private static void printConditionally(List<Person> people, Condition condition) {
//        for (Person p : people) {
//            if (condition.test(p)) {
//                System.out.println(p);
//            }
//        }
//    }
}
