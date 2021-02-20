package oop.inheritance;

// inheritance
public class Bmw extends Car {
    int discount;

    public Bmw(int discount, int cadence, int speed, int gear) {
        super(cadence, speed, gear);
        this.discount = discount;
    }

    public int getDiscount() {
        return this.discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public static void main(String[] args) {
        Bmw bmw = new Bmw(100, 10, 150, 6);
        bmw.applyBrakes(10);
        bmw.printStates();
    }
}
