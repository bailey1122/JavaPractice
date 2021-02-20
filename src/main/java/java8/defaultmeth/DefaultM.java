package java8.defaultmeth;

// A default method(defender method or virtual extension method).
// A static method.
interface TestInterface {
//    public static final int x = 3; example
    public void square(int a); // public abstract by default here as well

    default void show() { // an example of a default method
        System.out.println("Default Method Executed");
    }

    static void showS() { // an example of a static method
        System.out.println("Static Method Executed");
    }
}

public class DefaultM implements TestInterface { // a subclass of that abstract class
    public void square(int a) {
        System.out.println(a * a);
    }

//    public void show() { // we can change it as well
//        System.out.println("hi");
//    }

    public static void main(String[] args) {
        DefaultM defaultM = new DefaultM();
//        TestInterface testInterface = new DefaultM(); you can crate object references
//        testInterface.square(2);

        defaultM.square(4);
        defaultM.show(); // a default method

        TestInterface.showS(); // a static method
    }
}
