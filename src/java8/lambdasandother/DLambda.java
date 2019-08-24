package java8.lambdasandother;

interface A {
    void show(int i);
}

//class Xyz implements A {
//    public void show(int i) {
//        System.out.println("Hello " + i);
//    }
//}

public class DLambda {
    public static void main(String[] args) {
        A obj;
//        obj = new Xyz();

//        obj = new A() { // anonymous inner class
//            public void show(int i) {
//                System.out.println("Hello " + i);
//            }
//        };
        obj = i -> System.out.println("Hello " + i); // consumer interface

        obj.show(6);
    }
}
