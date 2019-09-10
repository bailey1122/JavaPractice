package binding;

// dynamic binding. At run-time
public class DynamicBi {
    void eat() {
        System.out.println("smth is eating");
    }
}

class Dog2 extends DynamicBi {
    void eat() {
        System.out.println("dog is eating");
    }

    public static void main(String[] args) {
        DynamicBi db = new Dog2();
        db.eat();
    }
}