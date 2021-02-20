package exceptions;

// user-defined exception
public class MyExcep extends Exception {
    // store account
    private static int[] acc = {1001,1002,1003,1004,1005};

    private static String name[] = {"AB", "CD", "EF", "OO", "PP"};
    private static double[] bal =
            {10000.00, 12000.00, 5600.0, 999.0, 1100.55};

    // default constructor
    MyExcep() {
    }

    MyExcep(String str) {
        super(str);
    }

    public static void main(String[] args) {
        try {
            System.out.println("ACC" + "\t" + "CUSTOMER" +
                                        "\t" + "BALANCE");

            for (int i = 0; i < 5; i++) {
                System.out.println(acc[i] + "\t" + name[i] +
                                                "\t" + bal[i]);
                //display own exception if balance < 1000
                if (bal[i] < 1000) {
                    MyExcep me = new MyExcep("Balance is less than 1000");
                    throw me;
                }
            }
        }
        catch (MyExcep e) {
            e.printStackTrace();
        }
    }
}
