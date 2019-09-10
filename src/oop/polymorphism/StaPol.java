package oop.polymorphism;

// Java Compile time polymorphism, static polymorphism or early
// binding
class Calc {
    int add(int a, int b) {
        return a + b;
    }
    int add(int a, int b, int c) {
        return a + b + c;
    }
}

public class StaPol {
    public static void main(String[] args) {
        Calc c = new Calc();
        System.out.println(c.add(1,2,3));
    }
}
