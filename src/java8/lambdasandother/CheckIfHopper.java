package java8.lambdasandother;

public class CheckIfHopper implements CheckTrait { // whether the Animal can hop
    public boolean test(Animal a) {
        return a.canHop();
    }
}
