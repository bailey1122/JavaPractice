package innerc.anonymous;

// anonymous inner class
interface Age {
    int x = 21;
    void getAge();
}

public class AnonymInnerC {
    public static void main(String[] args) {
        Age obj1 = new Age() { // anonymous inner
            @Override
            public void getAge() {
                System.out.println("Age: " + x);
            }
        };
        obj1.getAge();

//        MyClass obj = new MyClass(); // the standard way
//        obj.getAge();
    }
}

// implements Age
//class MyClass implements Age { // the standard way
//    public void getAge() {
//        System.out.println("Age: " + x);
//    }
//}
