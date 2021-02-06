package java8.lambdasandother;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.*;

public class StreamOperations {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9);

        List<Integer> duplicates = Arrays.asList(1,2,3,4,5,1,2,3,4,5);

        // filter and map stay withing their swimlanes

        // reduce cuts across swimlanes. It bring the values together. It transforms a collection into a single value or a non-stream
        // Take the input coming in, take the first element, perform the operation, put the result out step forward,
        // the result becomes an input, take the next result, perform the operation, put the result out, take the next result
        //
        //       ||||||||||||||||||||||||||||||||||||||
        //      |     one stage       the next stage |
        //     |                    T               |
        //    |                   1                |       2           3           4
        //   |                  /                 |     /            /           /
        //  | R (input) 1 -> * -> R (output) 1 ->|    * -> 2 ->    * -> 6 ->   * -> 24
        // ||||||||||||||||||||||||||||||||||||||
        // an output for one stage is an input for the next stage
        //
        // reduce on Stream<T> takes two parameters:
        // first parameter is of typeT
        // second parameter is of type BiFunction<R, T, R> to produce a result of R
        // R means an input type, T means an element's type, R means the output type
        //
        // '/' means the filter blocks the value. '->' means the filter moves forward the value. The values mind their own
        // business. '+' means an available element. They stay withing their swimlanes. '----------' means a swimlane
        //
        // filter        map         reduce
        //                             0.0 is an initial value which is coming in
        //     values in some collection \
        // x1   /                         \
        // ---------- ----------           \
        // x2   ->       x2'          ->    +
        // ---------- ----------             \
        // x3   /                             \
        // ---------- ----------               \
        // x4   ->       x4'          ->        +


        // filter lets some values go and blocks others
        // filter: 0 <= number of elements in the output <= number of the input
        // parameter: Stream<T> filter takes Predicate<T>
        System.out.println(numbers.stream()
                .filter(e -> e % 2 == 0)
                .reduce(20, (a, b) -> a + b));

        System.out.println(numbers.stream()
                .filter(e -> e % 2 == 0)
                .mapToDouble(e -> e * 2.0)
                .sum());

        // map transforms values. It receives a function as a parameter that will take a value from the collection coming in
        // and returns the value into the stream which is going out. Input stream -> output stream
        // number of the output == number of the input
        // no guarantee on the type of the output with respect to the type of the input
        // parameter: Stream<T> map takes Function<T, R> to return Stream<R>
        System.out.println(numbers.stream()
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2) // input type is Integer. The output type is Integer (stream)
//                .map(e -> e * 2.0) // input type is Integer. The output type is Double (stream)
                .reduce(20, (a, b) -> a + b));

        System.out.println(numbers.stream()
                .map(e -> e * 2.0)
                .reduce(0.0, (a, b) -> a + b));

        //double the even values and put them into a list

        // wrong way to do this
        List<Integer> doubleOfEven = new ArrayList<>();

        duplicates.stream()
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2)
                .forEach(e -> doubleOfEven.add(e)); // doubleOfEven is a shared variable
        // mutability is Ok, sharing is nice, shared mutability is wrong. Friends don't let friends do shared mutation
        System.out.println(doubleOfEven); // do not do this

        // collect is a reduce operation. It lets us get data out of streams and into another form

        // right way to do this
        List<Integer> doubleOfEven2 = duplicates.stream()
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2)
                .collect(toList());
        System.out.println(doubleOfEven2);


        List<Person> people = createPeople();

        // create a Map with name and age as key, and the person as value. toMap returns a Collector that accumulates elements
        // into a Map whose keys and values are the result of applying the provided mapping functions to the input elements.
        System.out.println(people.stream()
                .collect(toMap(
                        // pass a lamda that transforms objects as a key
                        person -> person.getFirstName() + "-" + person.getAge(),
                        // pass person as a value
                        person -> person
                )));

        // groupingBy Returns a Collector implementing a "group by" operation on input elements of type T, grouping elements
        // according to a classification function, and returning the results in a Map.

        // given a list of people, create a map where their name is a key and value is all the people with that name
        // first, create an empty map. Then, go take a list, take the first element from the list (it's a person), get a name
        // of the person. If a key(a name of a person) has not been added to the map before, create an empty list, add this object (person)
        // to the empty list, do a put on the map where put is name of a person and object is the list we've created, get the second
        // person, get the value for the person if such a key is already in the map, add the second person to the already existed list
        // according to the key (person's name) and so on. Otherwise, just add a key and a value

        people.stream()
                .collect(groupingBy(Person::getFirstName)); // group elements by the first name . A key is the name and a value is
        // a list containing the people that have the same name

        // given a list of people, create a map where their name is a key and value is all the ages of people with that name
        people.stream()
                .colect(groupingBy(Person::getFirstName),
                        mapping)
    }

    public static List<Person> createPeople() {
        return Arrays.asList(
                new Person("Sara", Gender.FEMALE, 20),
        new Person("Brenda", Gender.FEMALE, 20),
        new Person("Sara", Gender.FEMALE, 22),
        new Person("Bob", Gender.MALE, 20),
        new Person("Paula", Gender.FEMALE, 32),
        new Person("Paul", Gender.MALE, 32),
        new Person("Jack", Gender.MALE, 2),
        new Person("Jack", Gender.MALE, 72),
        new Person("Jill", Gender.FEMALE, 12)
        );
    }
}
