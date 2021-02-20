package innerc.local;

// local inner class inside an if statement
public class IfOuter {
    public int data = 10;

    public int getData() {
        return data;
    }

    public static void main(String[] args) {
        IfOuter ifOuter = new IfOuter();

        if (ifOuter.getData() < 20) {

            class IfInner { // local inner class inside if clause
                public int getValue() {
                    System.out.println("Inside Inner class");
                    return ifOuter.data;
                }
            }

            IfInner ifInner = new IfInner();
            System.out.println(ifInner.getValue());
        }
        else {
            System.out.println("Inside Outer class");
        }
    }
}
