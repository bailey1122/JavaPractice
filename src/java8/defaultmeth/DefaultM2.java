package java8.defaultmeth;

interface TestInterface1 {
    default void show() {
        System.out.println("Default TestInterface1");
    }
}

interface TestInterface2 {
    default void show() {
        System.out.println("Default TestInterface2");
    }
}

public class DefaultM2 implements TestInterface1, TestInterface2 {
    public void show() { // overriding default show method
        TestInterface1.super.show(); // call the show method of TestInterface1
        TestInterface2.super.show(); // call the show method of TestInterface2
    }

    public static void main(String[] args) {
        DefaultM2 dm2 = new DefaultM2();
        dm2.show();
    }
}
