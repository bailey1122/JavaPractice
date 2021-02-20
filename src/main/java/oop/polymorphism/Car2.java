package oop.polymorphism;

// super
public class Car2 {
    int cadence;
    int speed;
    int gear;

    public Car2(int cadence, int speed, int gear) {
        this.cadence = cadence;
        this.speed = speed;
        this.gear = gear;
    }

    public void setCadence(int cadence) {
        this.cadence = cadence;
    }

    public void speedUp(int speed) {
        this.speed += speed;
    }

    public void applyBrakes(int decrement) {
        this.speed -= speed;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }

    public void printStates() {
        System.out.println("cadence:" + cadence +
                " speed:" + speed + " gear:" + gear);
    }

    public void printDescription() {
        System.out.println("\nCar is " + "in gear " + this.gear +
                " with a cadence of " + this.cadence +
                " and travelling at a speed of " + this.speed + ". ");
    }
}
