package java8.lambdasandother;

interface MyLamda {
    void foo();
}

interface MyAdd {
    int add(int a, int b);
}

interface Greeting {
    void perform();
}

interface StringLength {
    int getLength(String s);
}

public class Atest {

    public void perform(Greeting greeting) {
        greeting.perform();
    }

    public static int callLambda(StringLength s) {
        return s.getLength("sss");
    }

    public static void sayHi(Greeting g) {
        g.perform();
    }

    public static void main(String[] args) {
//        Atest at = new Atest() {
//            public void foo() {
//                System.out.println("abc");
//            }
//        };

        sayHi(() -> System.out.println());

        System.out.println(callLambda(s -> s.length()));

        StringLength sll = s -> s.length();
        System.out.println(sll.getLength("aa"));

        Atest ai = new Atest();
        ai.perform(() -> System.out.println("s"));
//
//        AtesImpl a = new AtesImpl();
//        a.perform();

        Greeting g = () -> System.out.println("hi g"); // lambda as interface type
        g.perform();

//        at.perform(g);
        Greeting gr = new Greeting() {
            @Override
            public void perform() {
                System.out.println("hi impl");
            }
        };
//        gr.perform();
//
//        StringLength sl = (String s) -> s.length();
//        System.out.println(sl.getLength("Hello"));

//        MyLamda m = new MyLamda(
//        MyLamda greetingFunc = () -> System.out.println("xwz");
//        greetingFunc.foo();

//        MyAdd addFunction = (int a, int b) -> a + b;
//        System.out.println(addFunction.add(3, 4));


    }


//     dnf = (int a) -> a * 2;
//
//    ff = (int a, int b) -> a + b;
//
//    ffb = (int a, int b) -> {
//      if( b == 0) return 0;
//      return a / b;
//    };
//
//    ss = (String s) -> s.length()
}

class AtesImpl implements Greeting {
    @Override
    public void perform() {
        System.out.println("atesimop");
    }
}
