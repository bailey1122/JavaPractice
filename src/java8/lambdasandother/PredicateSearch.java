package java8.lambdasandother;

import java.util.ArrayList;
import java.util.List;

// the better way
public class PredicateSearch {
    public static void main(String[] args) {
        PredicateSearch ps = new PredicateSearch();

        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("fish", false, true));
        animals.add(new Animal("kangaroo", true, false));
        animals.add(new Animal("cat", false, false));

        ps.print(animals, a -> a.canHop());

//        (String a, String b) -> { return a.equals(b); };
//        (a -> a.equals.(b));
    }

    public void print(List<Animal> animals, Predicate<Animal> check) {
        for (Animal animal: animals) {
            if (check.test(animal)) {
                System.out.println(animal + " ");
            }
        }
    }
}
