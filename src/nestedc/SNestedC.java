package nestedc;

// static nested class. Cam refer through an object reference
class OuterClassS { // outer class
    static int outer_x = 10; // static member
    int outer_y = 20; // instance(non-static) member

    private static int outer_private = 30; // private member

    static class MyNestedClass {
        void display() {
            // can access static member of outer class
            System.out.println("outer_x = " + outer_x);

            // private static member of outer class
            System.out.println("outer_private = " + outer_private);

//            System.out.println(outer_y); error. Cannot access non-static member
        }
    }
}

public class SNestedC {
    public static void main(String[] args) {
        OuterClassS.MyNestedClass nestedClass = new OuterClassS.MyNestedClass(); // object reference
        nestedClass.display();
    }
}
