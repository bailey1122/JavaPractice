package java8.lambdasandother;

public class ThisReferenceExample {

    public void doProcess(int i, Process p) {
        p.process(i);
    }

    public void execute() {
        // this === ?  // it points to the instance of the object on which the execute method is being called
        doProcess(10, i -> {
            System.out.println("Value of i is " + i);
            System.out.println(this); // this is not in a static context. Therefore, it works. Lamda doesn't override a this reference.
        });
    }

    public static void main(String[] args) {
        ThisReferenceExample thisReferenceExample = new ThisReferenceExample();
        thisReferenceExample.execute();
        thisReferenceExample.doProcess(10, i -> {
            System.out.println("Value of i is " + i);
//            // it will not work because it's in a static context
//            System.out.println(this); // lamda doesn't override a this reference. Lamda's instance doesn't touch this reference
        });

        thisReferenceExample.doProcess(10, new Process() { // the instance was created. The anonymous inner class instance
            // overrides the this reference. This reference is changed inside
            @Override
            public void process(int i) {
                System.out.println("Value of i is " + i);
                System.out.println(this); // this points to the new instance of the anonymous inner class
            }

            public String toString() {
                return "This is the anonymous inner class";
            }
        });
    }

    public String toString() {
        return "hi";
    }
}
