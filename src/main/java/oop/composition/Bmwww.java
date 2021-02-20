package oop.composition;

// IS-A Carrr. And Has-A Engineee.
// It inherits all methods from Carrr (except final and static).
// It's dynamic binding(run-time binding).
public class Bmwww extends Carrr {
    public void BMWWWStart() {
        Engineee en = new Engineee(); // access the method by composition
        en.start();
    }
}
