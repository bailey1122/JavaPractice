package binding;

// binding refers to the link between method call and method definition
class A {
    void methodOne() {
        System.out.println("Method of Class A");
    }

    void methodTwo() {
        System.out.println("Method of Class A");
    }
}

public class Binding {
    public static void main(String[] args) {
        A a1 = new A();
        a1.methodTwo();
        a1.methodOne();
    }
}
