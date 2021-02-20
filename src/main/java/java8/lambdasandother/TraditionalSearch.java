package java8.lambdasandother;

import java.util.ArrayList;
import java.util.List;

public class TraditionalSearch {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<Animal>(); // list of animals
        animals.add(new Animal("fish", false, true));
        animals.add(new Animal("kangaroo", true, false));
        animals.add(new Animal("rabbit", true, false));
        animals.add(new Animal("turtle", false, true));

        print(animals, new CheckIfHopper());
        print(animals, a -> a.canHop());
//        print(animals, a -> a.canSwim());
//        print(animals, (Animal a) -> { return a.canHop(); });
//        print(animals, a -> a.canSwim());
//        print(animals, a -> ! a.canSwim());
    }

    private static void print(List<Animal> animals, CheckTrait checker) {
        for (Animal animal : animals) {
            if (checker.test(animal)) // the general check
                System.out.println(animal + " ");
        }
        System.out.println();
    }
}
