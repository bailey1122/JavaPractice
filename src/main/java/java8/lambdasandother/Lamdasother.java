package java8.lambdasandother;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

import static java.util.Comparator.comparingInt;

public class Lamdasother {
    public static void main(String[] args) {
        List<String> words = new ArrayList<String>(); // don't use raw types like just a List with no type
        words.add("one");
        words.add("two");
        words.add("three");

        // Anonymous class instance as a function object - obsolete!
        Collections.sort(words, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });

        // Lambda expression as function object (replaces anonymous class)
        Collections.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));

        // a comparator construction method
        Collections.sort(words, comparingInt(String::length));

        // the sort method
        words.sort(comparingInt(String::length));
    }

    // Enum type with constant-specific class bodies & data
    public enum Operation {
        PLUS("+") {
            public double apply(double x, double y) {
                return x + y;
            }
        },
        MINUS("-") {
            public double apply(double x, double y) {
                return x - y;
            }
        },
        TIMES("*") {
            public double apply(double x, double y) {
                return x * y;
            }
        },
        DIVIDE("/") {
            public double apply(double x, double y) {
                return x / y;
            }
        };

        private final String symbol;

        Operation(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }

        public abstract double apply(double x, double y);
    }

    // Enum with function object fields & constant-specific behavior
    public enum Operation2 {
        PLUS ("+", (x, y) -> x + y),
        MINUS("-", (x, y) -> x - y),
        TIMES("*", (x, y) -> x * y),
        DIVIDE("/", (x, y) -> x / y);

        private final String symbol;
        private final DoubleBinaryOperator op;

        Operation2(String symbol, DoubleBinaryOperator
                op) {
            this.symbol = symbol;
            this.op = op;
        }

        @Override public String toString() {
            return symbol;
        }

        public double apply(double x, double y) {
            return op.applyAsDouble(x, y);
        }
    }
}


























