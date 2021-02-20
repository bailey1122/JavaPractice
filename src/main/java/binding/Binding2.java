package binding;

// static binding(early binding). It happens during compilation.
// Happens before a program actually runs
class A2 {
    void method() {
        System.out.println("From Class A2");
    }
}

class B2 extends A2 {
    @Override
    void method() {
        System.out.println("From Class B2");
    }
}

public class Binding2 {
    public static void main(String[] args) {
     A2 a2 = new A2();
     a2.method();
     A2 a22 = new B2(); // doesn't check to which object a2 and a22 are pointing
     a22.method();
    }
}
