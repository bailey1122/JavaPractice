package oop.polymorphism;

// Java Runtime polymorphism,
// dynamic polymorphism or late binding
class Bike {
    void run() {
        System.out.println("running");
    }

    int speedlimit = 40; // can't be achieved by data members
}

class Splendor extends Bike {
    @Override
    void run() {
        System.out.println("running safely with 60km");
    }

    int speedlimit = 100; // can't be achieved by data members
}

class Member extends Splendor { // multilevel inheritance
    @Override
    void run() {
        System.out.println("Member is running");
    }

    public static void main(String[] args) {
        Bike b = new Splendor();
        b.run();
        System.out.println(b.speedlimit); // can't be achieved by data members

        Bike b2 = new Member();
        b2.run();
    }
}

