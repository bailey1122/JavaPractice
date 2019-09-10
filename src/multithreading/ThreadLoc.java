package multithreading;

// ThreadLocal. Accesses one(via its get or set method) has its own, independently initialized copy of the
// variable
public class ThreadLoc {
    public static void main(String[] args) {
        ThreadLocal<Number> tl = new ThreadLocal<Number>();

        ThreadLocal<String> tls = new ThreadLocal<String>();

        // setting the value
        tl.set(100);

        // returns the current thread's value
        System.out.println("value = " + tl.get());

        tl.set(90);
        System.out.println("value = " + tl.get());

        tl.set(88.45);
        System.out.println("value = " + tl.get());

        tls.set("Hi");
        System.out.println("value = " + tls.get());


        tls.remove(); // remove
        System.out.println("value = " + tls.get());
        tl.remove();
        System.out.println("value = " + tl.get());



    }
}
