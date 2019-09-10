package oop.composition;

public class Carrr {
    private String color;
    private int maxSpeed;

    public void info() {
        System.out.println("Car Color: " + color + " speed: " + maxSpeed);
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
