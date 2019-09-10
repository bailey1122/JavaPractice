package others;

// instanceOf
public class InstanceO {
    public static void main(String[] args) {
        Integer a = new Integer(5);
        if (a instanceof java.lang.Integer) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}
