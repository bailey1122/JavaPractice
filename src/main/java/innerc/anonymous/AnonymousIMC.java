package innerc.anonymous;

// anonymous inner class that defines inside method/constructor argument
public class AnonymousIMC {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                System.out.println("Child Thread");
            }
        });
        t.start();

        System.out.println("Main Thread");
    }
}
