package binding;

// static binding. At compiled time. Not at run time
public class StaticBi {
    // if there's any private, final or static method in class,
    // there's static binding
    private void eat() {
        System.out.println("dog is eating");
    }

    public static void main(String[] args) {
        StaticBi sb = new StaticBi();
        sb.eat();
    }
}
