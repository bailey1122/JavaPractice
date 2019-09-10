package concrete;

// concrete Class. It has an implementation for all of its methods
public class Concrete {

    static int product(int a, int b) {
        return a * b;
    }

    static int sum(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        int p = product(5, 10);
        int s = sum(5, 10);

        System.out.println("Product " + p);
        System.out.println("Sum " + s);
    }
}
