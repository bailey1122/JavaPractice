package oop.polymorphism;

public class Truck extends Car2 {
    private int tireWidth;

    public Truck(int cadence, int speed, int gear, int tireWidth) {
        super(cadence, speed, gear);
        this.setTireWidth(tireWidth);
    }

    public int getTireWidth() {
        return this.tireWidth;
    }

    public void setTireWidth(int tireWidth) {
        this.tireWidth = tireWidth;
    }

    public void printDescription() {
        super.printDescription();
        System.out.println("The truck" + " has " + getTireWidth() +
                " MM tires.");
    }
}
