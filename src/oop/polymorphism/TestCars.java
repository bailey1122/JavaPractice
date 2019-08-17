package oop.polymorphism;

public class TestCars {
    public static void main(String[] args) {
        Car2 car2 = new Car2(50, 160, 6);
        Car2 car21 = new SportCar(80, 190, 8, "free");
        Car2 car22 = new Truck(30, 120, 4, 200);

        car2.printDescription();
        car21.printDescription();
        car22.printDescription();
    }
}
