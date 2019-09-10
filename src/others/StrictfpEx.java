package others;

// strictfp modifier. With classes, interfaces abd methods only. All nested became
// implicitly strictfp.
// Cannot be used with abstract methods and with any method inside an interface,
// can with abstract classes and interfaces
public class StrictfpEx {
    public strictfp double sum() {
        double num1 = 10e+10;
        double num2 = 6e+08;
        return (num1 + num2);
    }

    public static void main(String[] args) {
        StrictfpEx se = new StrictfpEx();
        System.out.println(se.sum());
    }
}
