package java8.lambdasandother;

// a functional interface with one method
public interface Predicate<T> {
    boolean test(T t);
}
