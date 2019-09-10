package concrete;

interface X {
    int product(int a, int b);
    int sum(int a, int b);
}

abstract class Product implements X {
    public int product(int a, int b) {
        return a * b;
    }
}

// a concrete class that implements the unimplemented method sum
public class Concrete2 extends Product {
    public int sum(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        Concrete2 c2 = new Concrete2();
        int p = c2.product(5, 10);
        int s = c2.sum(5, 10);

        System.out.println("Product: " + p);
        System.out.println("Sum: " + s);
    }
}
