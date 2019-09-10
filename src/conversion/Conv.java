package conversion;

// conversion. Automatic conversion is done when two types are compatible and when assigned value of a
// smaller data type is bigger data type
public class Conv {
    public static void main(String[] args) {
        int i = 100;

        // automatic type conversion
        long l = i;

        // automatic type conversion
        float f = l;
        System.out.println("Int value " + i);
        System.out.println("Long value " + l);
        System.out.println("Float value " + f);

        double d = 100.04;

        // explicit type casting. A value of larger data to a smaller data type
        long lo = (long)d;

        // explicit type casting
        int i1 = (int)l;
        System.out.println("Double value d "+ d);

        // fractional part lost
        System.out.println("Long value lo "+ lo);

        // fractional part lost
        System.out.println("Int value i1 "+ i1);
    }
}
