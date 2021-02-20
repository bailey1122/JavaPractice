package exceptions;

// exceptions are not checked at compile time. They are under
// Error and RuntimeException classes. Everything else under Throwable
// is checked
public class Unchecked {
    public static void main(String[] args) {
        int x = 0;
        int y = 10;
//        int z = y / x; // throws ArithmeticException, but the compile allows it to compile
//
//        System.out.println(z);

        try {
            int p = 3/0;
            System.out.println(p);
            throw new ArithmeticException();
        }
        catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }
}
