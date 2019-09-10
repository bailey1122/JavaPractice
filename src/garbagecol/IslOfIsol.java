package garbagecol;

// island of isolation. Object1 references Object2 and Object 2 references Object1. Neither Object1 nor
// Object2 is referenced by any other object
public class IslOfIsol {
    IslOfIsol i;

    public static void main(String[] args) {
        IslOfIsol t1 = new IslOfIsol();
        IslOfIsol t2 = new IslOfIsol();

        // t1 gets a copy of t2
        t1.i = t2;

        // t2 gets a copy of t1
        t2.i = t1;

        // till now no object eligible for garbage collection
        t1 = null; // there's no way to call them
        t2 = null;

        // call garbage collector
        System.gc();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalize method called");
    }
}
