package oop.abstraction;

public class Test {
    public static void main(String[] args) {
        Shape shape = new Circle("White", 2.2); // object reference
        Shape shape2 = new Rectangle("Black", 2, 4);

        System.out.println(shape.toString());
        System.out.println(shape2.toString());
    }
}
