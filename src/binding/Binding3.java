package binding;

// dynamic binding(late binding). It happens during run time.
// It happens when program actually is running.
class A3 {
    void method () {
        System.out.println("From Class A");
    }
}

class B3 extends A3{
    void method () {
        System.out.println("From Class B");
    }
}

public class Binding3 {
    public static void main(String[] args) {
        A3 a3 = new A3();
        a3.method();
        A3 a33 = new B3();
        a33.method();
    }
}
