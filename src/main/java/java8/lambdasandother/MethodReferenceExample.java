package java8.lambdasandother;

public class MethodReferenceExample {

    public static void main(String[] args) {
        // give the class name where you have method :: and the method. Smth with no parameter calls a method with no parameter
        Thread t = new Thread(MethodReferenceExample::printMessage); // () -> printMessage()
        t.start();

//        Thread t = new Thread(() -> printMessage()); // it gets treated as an instance of runnable
//        t.start();
    }

    public static void printMessage() {
        System.out.println("whatever");
    }
}
