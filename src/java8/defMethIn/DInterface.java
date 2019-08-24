package java8.defMethIn;

interface Phone {
    void call();  // abstract by default
    default void message() {
        System.out.println("messaging");
    }
}

class IosPhone implements Phone {
    public void call() {
        System.out.println("Calling");
    }

}

public class DInterface {
    public static void main(String[] args) {
        Phone p = new IosPhone();
        p.call();
        p.message();
    }
}
