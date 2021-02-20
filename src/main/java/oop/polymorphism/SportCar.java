package oop.polymorphism;

public class SportCar extends Car2 {
    private String testDrive;

    public SportCar(int cadence, int speed, int gear, String testDrive) {
        super(cadence, speed, gear);
        this.setTestDrive(testDrive);
    }

    public String getTestDrive() {
        return this.testDrive;
    }

    public void setTestDrive(String testDrive) {
        this.testDrive = testDrive;
    }

    public void printDescription() {
        super.printDescription();
        System.out.println("The " + "SportCar has a" +
                getTestDrive() + " testDrive."); // additional data
    }
}
