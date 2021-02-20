package java8.lambdasandother;

import java.util.function.BiConsumer;

public class ExceptionHandlingExample {

    public static void main(String[] args) {
        int key = 0, someNumbers[] = {1, 2, 3, 4};

        process(someNumbers, key, wrapperLamda((v, k) -> System.out.println(v / k)));

        // lamda will handle the exception
//        process(someNumbers, key, (v,k) -> {
//            try {
//                System.out.println(v / k);
//            } catch (ArithmeticException e) {
//                System.out.println(" Arithmetic exception");
//            }
//        });
    }

    // the new created lamda from the wrapperLamda is passed in. process gets a wrapped lamda.
    private static void process(int[] someNumbers, int key, BiConsumer<Integer, Integer> biConsumer) {
        for (int n : someNumbers) {
            biConsumer.accept(n, key); // biConsumer is executed
        }
    }

    // crates a new lamda which executes what is passed in
    private static BiConsumer<Integer, Integer> wrapperLamda(BiConsumer<Integer, Integer> consumer) { // wrapper
        return (v, k) -> { // this lamda is called. This block is executed
            try {
                consumer.accept(v, k);
            } catch (ArithmeticException e){
                System.out.println("Exception caught in wrapper lamda");
            }
        };
    }

//    // the new created lamda from the wrapperLamda is passed in. consumer is a wrapper therefore it accepts which lamda is called
//    private static void process(int[] someNumbers, int key, BiConsumer<Integer, Integer> biConsumer) {
//        for (int n : someNumbers) {
//            biConsumer.accept(n, key);
//        }
//    }
//
//    // crates a new lamda which executes what is passed in
//    private static BiConsumer<Integer, Integer> wrapperLamda(BiConsumer<Integer, Integer> consumer) { // wrapper
//        return (v, k) -> { // this lamda is called
//            System.out.println("Executing from wrapper");
//            consumer.accept(v, k);
//        };
//    }

//    // replace the old lamda with a new one and return it
//    private static BiConsumer<Integer, Integer> wrapperLamda(BiConsumer<Integer, Integer> consumer) {
//        return (v, k) -> System.out.println(v + k);
//    }
}
