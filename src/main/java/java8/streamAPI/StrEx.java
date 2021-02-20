package java8.streamAPI;

import innerc.local.LOuter;

import java.io.IOException;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StrEx {

    public static void main(String[] args) {

//        // creating
//        Stream<Stream> empty = Stream.empty(); // count = 0
//        Stream<Integer> singleElement = Stream.of(1); // count = 1
//        Stream<Integer> fromArray = Stream.of(1, 2, 3); // count = 2
//        List<String> list = Arrays.asList("a", "b", "c");
//        Stream<String> fromList = list.stream();
//        Stream<String> fromListParallel = list.parallelStream();
//        Stream<Double> randoms = Stream.generate(Math::random);
//        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);

//        // count()
//        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
//        System.out.println(s.count()); // 3


//        // min() and max()
//        Stream<String> s = Stream.of("monkey", "ape", "bonobo");
//        Optional<String> min = s.min((s1, s2) -> s1.length()-s2.length());
//        min.ifPresent(System.out::println); // ape


//        // findAny() and findFirst()
//        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
//        Stream<String> infinite = Stream.generate(() -> "chimp");
//        s.findAny().ifPresent(System.out::println); // monkey
//        infinite.findAny().ifPresent(System.out::println); // chimp


//        // allMatch(), anyMatch() and noneMatch()
//        List<String> list = Arrays.asList("monkey", "2", "chimp");
//        Stream<String> infinite = Stream.generate(() -> "chimp");
//        Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
//        System.out.println(list.stream().anyMatch(pred)); // true
//        System.out.println(list.stream().allMatch(pred)); // false
//        System.out.println(list.stream().noneMatch(pred)); // false
//        System.out.println(infinite.anyMatch(pred)); // true


//        // forEach()
//        Stream<String> s = Stream.of("Monkey", "Gorilla", "Bonobo");
//        s.forEach(System.out::print); // MonkeyGorillaBonobo


//        // reduce()
//        String[] array = new String[] {"w", "o", "l", "f"};
//        String result = "";
//        for (String s: array) result = result + s;
//        System.out.println(result);
//
//        Stream<String> stream = Stream.of("w", "o", "l", "f");
//        String word = stream.reduce("", String::concat);
//        System.out.println(word);
//
//        Stream<Integer> stream1 = Stream.of(3,5,6);
//        System.out.println(stream1.reduce(1, (a, b) -> a*b));
//
//
//        BinaryOperator<Integer> op = (a, b) -> a * b;
//        Stream<Integer> empty = Stream.empty();
//        Stream<Integer> oneElement = Stream.of(3);
//        Stream<Integer> threeElements = Stream.of(3,5,6);
//
//        empty.reduce(op).ifPresent(System.out::print); // no output
//        oneElement.reduce(op).ifPresent(System.out::print); // 3
//        threeElements.reduce(op).ifPresent(System.out::print); // 90


//        // collect()
//        Stream<String> stream = Stream.of("w", "o", "l", "f");
//        StringBuilder word = stream.collect(StringBuilder::new,
//                StringBuilder::append, StringBuilder::append);
//
//        Stream<String> stream1 = Stream.of("w", "o", "l", "f");
//        TreeSet<String> set = stream1.collect(Collectors.toCollection(TreeSet::new));
//        System.out.println(set); // [f, l, o, w]
//
//        Stream<String> stream2 = Stream.of("w", "o", "l", "f");
//        Set<String> set2 = stream2.collect(Collectors.toSet());
//        System.out.println(set2);


//        // filter()
//        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
//        s.filter(x -> x.startsWith("m")).forEach(System.out::print); // monkey


//        // distinct()
//        Stream<String> s = Stream.of("duck", "duck", "duck", "goose");
//        s.distinct().forEach(System.out::print); // duckgoose


//        // limit() and skip()
//        Stream<Integer> s = Stream.iterate(1, n -> n + 1);
//        s.skip(5).limit(2).forEach(System.out::print);


//        // flatMap()
//        List<String> zero = Arrays.asList();
//        List<String> one = Arrays.asList("Bonobo");
//        List<String> two = Arrays.asList("Mama Gorilla", "Baby Gorilla");
//        Stream<List<String>> animals = Stream.of(zero, one, two);
//
//        animals.flatMap(l -> l.stream()).forEach(System.out::println);


//        // sorted()
//        Stream<String> s = Stream.of("brown-", "bear-");
//        s.sorted().forEach(System.out::print); // bear-brown-
//
//        Stream<String> s2 = Stream.of("brown bear-", "grizzly-");
//        s2.sorted(Comparator.reverseOrder())
//                .forEach(System.out::print);


//        // peek()
//        Stream<String> stream = Stream.of("black bear", "brown bear", "grizzly");
//        long count = stream.filter(s -> s.startsWith("g"))
//                .peek(System.out::println).count(); // grizzly
//        System.out.println(count); // 1
//
//        List<Integer> numbers = new ArrayList<>();
//        List<Character> letters = new ArrayList<>();
//        numbers.add(1);
//        letters.add('a');
//        StringBuilder builder = new StringBuilder();
//        Stream<List<?>> good = Stream.of(numbers, letters);
//        good.peek(l -> builder.append(l)).map(List::size).forEach(System.out::print); // l1
//        System.out.println(builder); // [1][a]


//        List<String> list = Arrays.asList("Toby", "Anna", "Leroy", "Alex");
//        list.stream().filter(s -> s.length() == 4).sorted()
//                .limit(2).forEach(System.out::println);


//        Stream<Integer> infinite = Stream.iterate(1, x -> x + 1);
//        infinite.limit(5)
//                .filter(x -> x % 2 == 1)
//                .forEach(System.out::print); // 135
//
//        System.out.println();
//        Stream<Integer> infinite2 = Stream.iterate(1, x -> x + 1);
//        infinite2.limit(5)
//                .peek(System.out::print)
//                .filter(x -> x % 2 == 1)
//                .limit(5)
//                .forEach(System.out::print); // 11233455
//
//        System.out.println();
//        Stream<Integer> infinite3 = Stream.iterate(1, x -> x + 1);
//        infinite3.filter(x -> x % 2 == 1)
//                .limit(5)
//                .forEach(System.out::print); // 13579
//
//        System.out.println();
//        Stream<Integer> infinite4 = Stream.iterate(1, x -> x + 1);
//        infinite4.filter(x -> x % 2 == 1)
//                .peek(System.out::print)
//                .limit(5)
//                .forEach(System.out::print); // 1133557799

//        // primitives
//        Stream<Integer> stream = Stream.of(1,2,3);
//        System.out.println(stream.reduce(0, (s, n) -> s + n));
//
//        Stream<Integer> stream1 = Stream.of(1,2,3);
//        System.out.println(stream1.mapToInt(x -> x).sum());
//
//        IntStream intStream = IntStream.of(1,2,3);
//        OptionalDouble avg = intStream.average();
//        System.out.println(avg.getAsDouble());
//
//        DoubleStream oneValue = DoubleStream.of(3.14);
//        DoubleStream varargs = DoubleStream.of(1.0, 1.1, 1.2);
//        oneValue.forEach(System.out::println);
//        System.out.println();
//        varargs.forEach(System.out::println);
//
//        DoubleStream random = DoubleStream.generate(Math::random);
//        DoubleStream fractions = DoubleStream.iterate(.5, d -> d / 2);
//        random.limit(3).forEach(System.out::println);
//        System.out.println();
//        fractions.limit(3).forEach(System.out::println);
//
//        IntStream count = IntStream.iterate(1, n -> n + 1).limit(5);
//        count.forEach(System.out::println);
//
//        IntStream stream2 = IntStream.range(1, 6);
//        stream2.forEach(System.out::println);
//
//        IntStream rangeClosed = IntStream.rangeClosed(1, 5);
//        rangeClosed.forEach(System.out::println);
//
//        Stream<String> objStream = Stream.of("penguin", "fish");
//        IntStream intStream1 = objStream.mapToInt(s -> s.length());
//        intStream1.forEach(System.out::print);


//        // optional with Primitive streams
//        IntStream stream = IntStream.rangeClosed(1, 10);
//        OptionalDouble optional = stream.average();
//
//        optional.ifPresent(System.out::println);
//        System.out.println(optional.getAsDouble());
//        System.out.println(optional.orElseGet(() -> Double.NaN));
//
//        LongStream longs = LongStream.of(5, 10);
//        long sum = longs.sum();
//        System.out.println(sum);
//
//        DoubleStream doubles = DoubleStream.generate(() -> Math.PI);
//        OptionalDouble min = doubles.min();


//        // functional interfaces for boolean
//        BooleanSupplier b1 = () -> true;
//        BooleanSupplier b2 = () -> Math.random() > .5;
//        System.out.println(b1.getAsBoolean());
//        System.out.println(b2.getAsBoolean());


//        // functional interfaces for double, int and long
//        double d = 1.0;
//        DoubleToIntFunction f1 = x -> 1;
//        f1.applyAsInt(d);


//        // linking streams to the underlying data
//        List<String> cats = new ArrayList<>();
//        cats.add("Annie");
//        cats.add("Ripley");
//        Stream<String> stream = cats.stream();
//        cats.add("KC");
//        System.out.println(stream.count());


//        // collecting using basic collectors
//        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
//        String result = ohMy.collect(Collectors.joining(", "));
//        System.out.println(result); // lions, tigers, bears
//
//        Stream<String> ohmy2 = Stream.of("lions", "tigers", "bears");
//        Double result2 = ohmy2.collect(Collectors.averagingInt(String::length));
//        System.out.println(result2);
//
//        Stream<String> ohMy3 = Stream.of("lions", "tigers", "bears");
//        TreeSet<String> result3 = ohMy3.filter(s -> s.startsWith("t"))
//                .collect(Collectors.toCollection(TreeSet::new));
//        System.out.println(result3); // [tigers]


//        // collecting into Maps
//        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
//        Map<String, Integer> map = ohMy.collect(
//                Collectors.toMap(s -> s, String::length));
//        System.out.println(map);
//
//        Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears");
//        Map<Integer, String> map2 = ohMy2.collect(Collectors.toMap(
//                String::length, k -> k, (s1, s2) -> s1 + "," + s2));
//        System.out.println(map2); // {5=lions,bears, 6=tigers}
//        System.out.println(map2.getClass()); // class java.util.HashMap
//
//        Stream<String> ohMy3 = Stream.of("lions", "tigers", "bears");
//        Map<Integer, String> map3 = ohMy3.collect(Collectors.toMap(
//                String::length, k -> k, (s1, s2) -> s1 + ", " + s2, TreeMap::new));
//        System.out.println(map3);
//        System.out.println(map3.getClass());


//        // collecting using grouping
//        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
//        Map<Integer, List<String>> map = ohMy.collect(
//                Collectors.groupingBy(String::length));
//        System.out.println(map); // {5=[lions, bears], 6=[tigers]}
//
//        Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears");
//        Map<Integer, Set<String>> map2 = ohMy2.collect(
//                Collectors.groupingBy(String::length, Collectors.toSet()));
//        System.out.println(map2);
//
//        Stream<String> ohMy3 = Stream.of("lions", "tigers", "bears");
//        TreeMap<Integer, Set<String>> map3 = ohMy3.collect(
//                Collectors.groupingBy(String::length, TreeMap::new, Collectors.toSet()));
//        System.out.println(map3);
//
//        Stream<String> ohMy4 = Stream.of("lions", "tigers", "bears");
//        TreeMap<Integer, List<String>> map4 = ohMy4.collect(
//                Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList()));
//        System.out.println(map4);

//        // collecting using partitioning, and Mapping
//        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
//        Map<Boolean, List<String>> map = ohMy.collect(
//                Collectors.partitioningBy(s -> s.length() <= 5));
//        System.out.println(map); // {false=[tigers], true=[lions, bears]}
//
//        Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears");
//        Map<Boolean, List<String>> map2 = ohMy2.collect(
//                Collectors.partitioningBy(s -> s.length() <= 7));
//        System.out.println(map2); // {false=[], true=[lions, tigers, bears]}
//
//        Stream<String> ohMy3 = Stream.of("lions", "tigers", "bears");
//        Map<Boolean, Set<String>> map3 = ohMy3.collect(
//                Collectors.partitioningBy(s -> s.length() <= 7, Collectors.toSet()));
//        System.out.println(map3); // {false=[], true=[lions, bears, tigers]}
//
//        Stream<String> ohMy4 = Stream.of("lions", "tigers", "bears");
//        Map<Integer, Long> map4 = ohMy4.collect(Collectors.groupingBy(
//                String::length, Collectors.counting()));
//        System.out.println(map4); // {5=2, 6=1}
//
////        Stream<String> ohMy5 = Stream.of("lions", "tigers", "bears");
////        Map<Integer, Optional<Character>> map5 = ohMy5.collect(
////                Collectors.groupingBy(
////                    String::length,
////                        Collectors.mapping(s -> s.charAt(0),
////                        Collectors.minBy(Comparator.naturalOrder()))));
////        System.out.println(map5);
    }

//    // summarizing statistics
//    private static int max(IntStream ints) {
//        OptionalInt optional = ints.max();
//        return optional.orElseThrow(RuntimeException::new);
//    }
//
//    private static int range(IntStream ints) {
//        IntSummaryStatistics stats = ints.summaryStatistics();
//        if (stats.getCount() == 0) throw new RuntimeException();
//        return stats.getMax()-stats.getMin();
//    }


//    // chaining optionals
//    private static void threeDigit(Optional<Integer> optional) {
//        optional.map(n -> "" + n)
//                .filter(s -> s.length() == 3)
//                .ifPresent(System.out::println);
//    }
}

class ExceptionCaseStudy {
    private static List<String> create() throws IOException {
        throw new IOException();
    }

    private static List<String> createSafe() {
        try {
            return ExceptionCaseStudy.create();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
//        ExceptionCaseStudy.create().stream().count();
        Supplier<List<String>> s2 = ExceptionCaseStudy::createSafe;
    }
}

























