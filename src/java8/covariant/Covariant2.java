package java8.covariant;

// covariant return types
class AAC {}

class BBC extends AAC {}

class Base {
    AAC fun() {
        System.out.println("Base fun()");
        return new AAC();
    }
}

class Derived extends Base {
    BBC fun() {
        System.out.println("Derived fun()");
        return new BBC(); // if we swap return types of Base and Derived,
                          // then it wouldn't work
    }
}

public class Covariant2 {
    public static void main(String[] args) {
        Base base = new Base();
        base.fun();

        Derived derived = new Derived();
        derived.fun();
    }
}
