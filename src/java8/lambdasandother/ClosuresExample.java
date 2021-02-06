package java8.lambdasandother;

public class ClosuresExample {

    public static void main(String[] args) {
        int a = 10;
        int b = 20; // the value is frozen by Java Compiler. The value of this variable is get passed but not the variable itself

        // compiler expects b to be effectively final
        doProcess(a, i -> System.out.println(i + b));

//        doProcess(a, new Process() {
//
//            @Override
//            public void process(int i) { // this method is not executed inside main. It's passed in doProcess
//                System.out.println(i + b);
//            }
//        });
    }

    private static void doProcess(int i, Process p) {
        p.process(i); // executes the implementation of process
    }
}

interface Process {
    void process(int i);
}
