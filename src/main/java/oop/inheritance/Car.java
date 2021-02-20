package oop.inheritance;

// super
public class Car {
    int cadence;
    int speed;
    int gear;

    public Car(int cadence, int speed, int gear) {
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
        this.speed -= decrement;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }

    public void printStates() {
        System.out.println("cadence:" + cadence +
                " speed:" + speed + " gear:" + gear);
    }
}
