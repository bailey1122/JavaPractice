package java8.lambdasandother;

interface FuncInterface {
    void abstractFun(int x); // abstract function

    default void normalFun() {
        System.out.println("Hello");
    }
}

public class FunC{
    public static void main(String[] args) {
        FuncInterface fobj = (int x) -> System.out.println(2 * x);
        fobj.abstractFun(5); // calls lambda above
    }
}
