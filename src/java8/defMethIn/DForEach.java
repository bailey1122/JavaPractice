package java8.defMethIn;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


//class ConsImpl implements Consumer<Integer> {
//    public void accept(Integer i) {
//        System.out.println(i);
//    }
//}

public class DForEach {
    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(4,5,6,7,8);

//        for (int i = 0; i < values.size(); i++) {
//            System.out.println(values.get(i));
//        }

//        for (int i : values) {
//            System.out.println(i);
//        }

//        Consumer<Integer> c = new Consumer<Integer>() {
//            @Override
//            public void accept(Integer i) {
//                System.out.println(i);
//            }
//        };

//        Consumer<Integer> c = i -> System.out.println(i);

//        values.forEach(i -> System.out.println(i));
//        Consumer<Integer> c = new ConsImpl();
        values.forEach(i -> System.out.println(i));

    }
}
