package java8.streamAPI;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.*;

public class FuncIn {
    public static void main(String[] args) {

        // Supplier
        Supplier<LocalDate> s1 = LocalDate::now;
        Supplier<LocalDate> s2 = () -> LocalDate.now();

        LocalDate d1 = s1.get();
        LocalDate d2 = s2.get();

        System.out.println(d1);
        System.out.println(d2);


        // Consumer
        Consumer<String> c1 = System.out::println;
        Consumer<String> c2 = x -> System.out.println(x);

        c1.accept("Annie");
        c2.accept("Annie");


        // BiConsumer
        Map<String, Integer> map = new HashMap<>();
        BiConsumer<String, Integer> b1 = map::put;
        BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);

        b1.accept("chicken", 7);
        b2.accept("chick", 1);
        System.out.println(map);


        // Predicate
        Predicate<String> p1 = String::isEmpty;
        Predicate<String> p2 = x -> x.isEmpty();

        System.out.println(p1.test(""));
        System.out.println(p2.test(""));

        Predicate<String> egg = s -> s.contains("egg");
        Predicate<String> brown = s -> s.contains("brown");
        Predicate<String> brownEggs = s -> s.contains("egg") && s.contains("brown");
        Predicate<String> otherEggs = s -> s.contains("egg") && ! s.contains("brown");

        Predicate<String> brownEggs1 = egg.and(brown);
        Predicate<String> otherEggs1 = egg.and(brown.negate());


        // BiPredicate
        BiPredicate<String, String> bi1 = String::startsWith;
        BiPredicate<String, String> bi2 = (string, prefix) -> string.startsWith((prefix));

        System.out.println(bi1.test("chicken", "chick"));
        System.out.println(bi2.test("chicken", "chick"));


        // Function
        Function<String, Integer> f1 = String::length;
        Function<String, Integer> f2 = x -> x.length();

        System.out.println(f1.apply("cluck")); // 5
        System.out.println(f2.apply("cluck")); // 5


        // BiFunction
        BiFunction<String, String, String> fi1 = String::concat;
        BiFunction<String, String, String> fi2 = (string, toAdd) -> string.concat(toAdd);

        System.out.println(fi1.apply("baby ", "chick")); // baby chick
        System.out.println(fi2.apply("baby ", "chick")); // baby chick


        // UnaryOperator
        UnaryOperator<String> u1 = String::toUpperCase;
        UnaryOperator<String> u2 = x -> x.toUpperCase();

        System.out.println(u1.apply("chirp"));
        System.out.println(u2.apply("chirp"));


        // BinaryOperator
        BinaryOperator<String> bin1 = String::concat;
        BinaryOperator<String> bin2 = (string, toAdd) -> string.concat(toAdd);

        System.out.println(bin1.apply("baby ", "chick")); // baby chick
        System.out.println(bin2.apply("baby ", "chick")); // baby chick


        System.out.println(average(90, 100));
        System.out.println(average());

        Optional<Double> opt = average(90, 100);
        if (opt.isPresent())
            System.out.println(opt.get());

        Optional<Double> op2 = average(90, 100);
        op2.ifPresent(System.out::println);

        Optional<Double> op3 = average(90, 100);
        System.out.println(op3.orElse(Double.NaN));
        System.out.println(op3.orElseGet(() -> Math.random()));
        System.out.println(op3.orElseThrow(() -> new IllegalArgumentException()));
    }

    // returning an Optional
    public static Optional<Double> average(int... scores) {
        if (scores.length == 0) return Optional.empty();
        int sum = 0;
        for (int score: scores) sum += score;
        return Optional.of((double) sum / scores.length);
    }
}









