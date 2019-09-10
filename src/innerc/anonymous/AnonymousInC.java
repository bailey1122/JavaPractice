package innerc.anonymous;

// anonymous inner class that extend a class
public class AnonymousInC {
    public static void main(String[] args) {
        Thread t = new Thread() {
            public void run() {
                System.out.println("Child Thread");
            }
        };
        t.start();
        System.out.println("Main Thread");
    }
}
