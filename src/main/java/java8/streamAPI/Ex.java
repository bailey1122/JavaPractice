package java8.streamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex {
    public static void main(String[] args) {
        List<Integer> values = new ArrayList<Integer>();
        for (int i = 1; i <= 100; i++) {
            values.add(i);
        }

        List<List<String>> lof = Arrays.asList(
                Arrays.asList("a"),
                Arrays.asList("b")
        );
        System.out.println(lof);
        System.out.println(lof.stream()
                .flatMap(Collection::stream) // flattens the input Stream of Streams to a Stream of Strings
                .collect(Collectors.toList()));

        List<String> list = Stream.of("a", "b")
                .map(String::toUpperCase) // wraps the underlying sequence in a Stream instance
                .collect(Collectors.toList());
        System.out.println(list);

        System.out.println(values.stream().filter(i -> {
            System.out.println("hi");
            return true;
        }).findFirst().orElse(0));


        List<Integer> values1 = Arrays.asList(1,2,3,42,5,25,6);

        Stream<Integer> s = values.stream();
        s.forEach(System.out::println); // will work
        s.forEach(System.out::println); // an exception. You cannot reuse it


//        values.stream().filter(i -> { // an intermediate method.
//            System.out.println("hi"); // Lazy evaluation. Just filtering values
//            return true;
//        });


//        values.parallelStream().forEach(System.out::println); // create multiple threads automatically
//        values.stream().forEach(System.out::println);
//        values.forEach(System.out::println);
    }
}
