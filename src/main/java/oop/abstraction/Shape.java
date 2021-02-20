package oop.abstraction;

// Encapsulation is data hiding(information hiding)
// while Abstraction is detail hiding(implementation hiding)
abstract class Shape {
    String color;

    // abstract methods
    abstract double area();
    public abstract String toString();

    public Shape(String color) {
        System.out.println("Shape constructor called");
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}

class Circle extends Shape {
    double radius;

    public Circle(String color, double radius) {
        // call Shape constructor
        super(color);
        System.out.println("Circle constructor called");
        this.radius = radius;
    }

    double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    public String toString() {
        return "Circle color is " + super.color +
                "and area is : " + area();
    }
}

class Rectangle extends Shape {
    double length;
    double width;

    public Rectangle(String color, double length, double width) {
        // call Shape constructor
        super(color);
        System.out.println("Rectangle constructor called");
        this.length = length;
        this.width = width;
    }

    double area() {
        return length * width;
    }

    public String toString() {
        return "Rectangle color is " + super.color +
                "and area is : " + area();
    }
}




























