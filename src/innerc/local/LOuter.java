package innerc.local;

// local inner class within a method body
public class LOuter {
    private void getValue() { // method body
        int sum = 20; // this local variable must be final till JDK 7. This work only in JDK 8

        class LocalInn { // local inner Class inside method
            public int divisor;
            public int remainder;

            public LocalInn() { // local variables referenced from an inner class must be final or
                divisor = 4; // effectively final
                remainder = sum % divisor;
            }

            private int getDivisor() {
                return divisor;
            }

            private int getRemainder() {
                return sum & divisor;
            }

            private int getQuotient() {
                System.out.println("Inside inner class");
                return sum / divisor;
            }
        }

        LocalInn localInn = new LocalInn();
        System.out.println("Divisor = " + localInn.getDivisor());
        System.out.println("Remainder = " + localInn.getRemainder());
        System.out.println("Quotient = " + localInn.getQuotient());
    }

    public static void main(String[] args) {
        LOuter lOuter = new LOuter();
        lOuter.getValue();
    }
}