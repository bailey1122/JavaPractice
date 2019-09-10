package innerc;

// inner(non-static nested) class
class OuterIClass { // outer class
    // static member
    static int outer_x = 10;
    // instance(non-static) member
    int outer_y = 20;

    private int outer_private = 30; // private member

    //
    class InnerC { // inner class
        void display() {
            System.out.println("outer_x = " + outer_x); // can access static member of outer class

            System.out.println("outer_y = " + outer_y); // can access non-static member of outer class

            System.out.println("outer_private = " + outer_private); // cam access private member of outer class
        }
    }
}

public class InnerCEx {
    public static void main(String[] args) {
        OuterIClass outerObject = new OuterIClass();
        OuterIClass.InnerC innerObject = outerObject.new InnerC();

        innerObject.display();

    }
}
