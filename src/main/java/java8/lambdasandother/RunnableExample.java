package java8.lambdasandother;

public class RunnableExample {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Printed inside Runnable");
            }
        });

        Thread t2 = new Thread(() -> System.out.println("Printed inside Runnable"));
        t2.start();
    }
}
