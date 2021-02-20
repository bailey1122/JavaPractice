package java8.lambdasandother;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

// we cannot use a method reference if there's a manipulation with the data and if there's a conflict between an instance
// method and a static one
public class CollectionIterationExample {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Matthew", "Arnold", 39)
        );

        List<Integer> numbers = Arrays.asList(1,2,3,4);

        System.out.println(numbers.stream()
                .filter(e -> e % 2 == 0)
                .reduce(20, (a, b) -> a + b));

        // given tha values, double the even numbers and total
        int result = 0;

        for (int e : numbers) {
            result += e * 2;
        }

        System.out.println(result);

        System.out.println(numbers
            .stream()
                .filter(e -> e % 2 == 0)
                    .mapToInt(e -> compute(e))
                        .reduce(1, (a, b) -> a * b));

        // less time. Be very careful. A lot of resources needed
        numbers
            .parallelStream()
                .filter(e -> e % 2 == 0)
                    .mapToInt(CollectionIterationExample::compute)
                        .sum();

        numbers
            .stream()
                .filter(e -> e % 2 == 0)
                     .mapToInt(CollectionIterationExample::compute)
                        .sum();

        numbers
            .stream()
                .filter(e -> e % 2 == 0)
                    .mapToInt(e -> e * 2)
                        .sum();

        numbers
            .stream()
                .filter(e -> e % 2 == 0) // just give me even numbers only and block everything else
                    .map(e -> e * 2) // double the values
                        .reduce(0, Integer::sum); // get the sum of the values

        // iteration is up to the runtime. Execute a lamda for each element. Lamdas have no particular order in which they are executed
        people.forEach(p -> System.out.println(p)); // using lamda for each loop. It's very easy for the processor to run in multiple threads
        System.out.println();
        people.forEach(System.out::println); // method reference to an instance method

        System.out.println();

        numbers
            .stream()
                .map(e -> e.toString()) // the parameter e has become a target to this function. Call the function on the object
                    .forEach(System.out::println);

        numbers
            .stream()
                .map(e -> String.valueOf(e))
                    .map(String::toString) // method reference to an instance method
                        .forEach(System.out::println);

        numbers
            .stream()
                .map(String::valueOf) // method reference to a static method
                    .forEach(System.out::println);

        numbers
            .stream()
                .map(e -> String.valueOf(e)) // the parameter became an argument
                    .forEach(System.out::println);

        // internal iterators
        numbers
            .forEach(new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) {
                    System.out.print(integer);
                }
            });
        System.out.println();

        numbers
            .forEach(System.out::println);

        // Java 8 has type inference for lamda expressions
        numbers
            .forEach((integer) -> System.out.println(integer));

        // parentheses are optional for one parameter
        numbers
            .forEach(integer -> System.out.println(integer));

//        numbers
//            .forEach((Integer integer) -> System.out.println(integer));

        System.out.println();
        // external iterators. Go in this order
        for (int i = 0; i < people.size(); i++) { // using for loop
            System.out.println(people.get(i));
        }

        System.out.println();
        // external iterators. Have a particular order for an iteration
        for (Person p : people) { // using for in loop
            System.out.println(p);
        }

        // get the total. Pass the total's value and the e's value in as the arguments. The order of the parameters is important
        numbers
            .stream()
                .reduce(0, (total, e) -> Integer.sum(total, e)); // the first parameter (identity) indicates an initial value
                // the second parameter is a lamda expression

        numbers
            .stream()
                .reduce(0, Integer::sum);

        // the order makes a difference. The first parameter is a target, whereas the second parameter is an argument
        System.out.println(numbers
            .stream()
                .map(String::valueOf)
                    .reduce("", (carry, str) -> carry.concat(str)));

        System.out.println(numbers
            .stream()
                .map(String::valueOf)
                    .reduce("", String::concat));
    }

    public static int compute(int number) {
        // assume this is time intensive
        return number * 2;
    }
}
