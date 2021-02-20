package java8.covariant;

class AC {
    AC get() {
        return this;
    }
}

class BC {
    BC get() {
        return this;
    }

    void message() {
        System.out.println("welcome to covariant return type");
    }
}

public class CovariantReturnT {
    public static void main(String[] args) {
        BC bc = new BC();
        bc.get().message();
    }
}
