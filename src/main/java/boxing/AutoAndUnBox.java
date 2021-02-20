package boxing;

public class AutoAndUnBox {
    public static void main(String[] args) {
        Integer i = new Integer(10);

        int i1 = i; // unboxing the Object

        System.out.println("Value of i: " + i);
        System.out.println("Value of i1: " + i1);

        Character ch = 'a'; // autoboxing of char

        // auto-unboxing
        char chr = ch;
        System.out.println("Value of ch: " + ch);
        System.out.println("Value of chr: " + chr);
    }
}
