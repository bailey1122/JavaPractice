package java8.lambdasandother;

import java.util.ArrayList;
import java.util.List;

public class PredicateSearch {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("fish", false, true));

//        print(animals, a -> a.canHop());
        print(animals, a -> a.canSwim());
    }

    private static void print(List<Animal> animals, Predicate<Animal> checker) {
        for (Animal animal: animals) {
            if (checker.test(animal))
                System.out.println(animal + " ");
        }
        System.out.println();
    }
}
