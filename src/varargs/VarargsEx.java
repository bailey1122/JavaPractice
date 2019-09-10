package varargs;

// varargs
public class VarargsEx {

    // takes variable number of integer arguments
    static void fun(int ...a) {
        System.out.println("Number or arguments: " + a.length);

        // display contents of a
        for (int i: a)
            System.out.print(i + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        // call the varargs method with different number of parameters
        fun(100); // one parameter
        fun(1,2,3,4); // four parameters
        fun(); // no parameter
    }
}
