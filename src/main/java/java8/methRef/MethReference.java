package java8.methRef;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


interface Parser {
    String parse(String str);
}

class StringParser {
    public static String convert(String s) {
        if (s.length() <= 3)
            s = s.toUpperCase();
        else
            s = s.toLowerCase();
        return s;
    }
}

class MyPrinter {
    public void print(String str, Parser p) {
        str = p.parse(str);
        System.out.println(str);
    }
}

public class MethReference {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Michael", "Giannah", "Ian", "Conor");

//        Consumer<String> con = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };
//        names.forEach(con);

        names.forEach(s -> System.out.println(s));
//        names.forEach(System.out::println);


        String str = "Smth";
        MyPrinter mp = new MyPrinter();
        mp.print(str, (s -> StringParser.convert(s)));
//        mp.print(str, (StringParser::convert));

//        mp.print(str, new Parser() {
//            @Override
//            public String parse(String s) {
//                return StringParser.convert(s);
//            }
//        });
    }
}
