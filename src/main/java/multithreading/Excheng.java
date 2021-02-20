package multithreading;

import java.util.concurrent.Exchanger;

public class Excheng {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Michael(exchanger);
        new BookM(exchanger);
    }

    static class Michael extends Thread {
        Exchanger<String> exchanger;

        public Michael(Exchanger exchanger) {
            this.exchanger = exchanger;
            start();
        }

        @Override
        public void run() {
            try {
                exchanger.exchange("Hi, my name is Michael");
                sleep(5000);
                exchanger.exchange("I'm a software engineer");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class BookM extends Thread {
        Exchanger<String> exchanger;

        public BookM(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
            start();
        }

        @Override
        public void run() {
            try {
                System.out.println(exchanger.exchange(null)); // get data
                System.out.println(exchanger.exchange(null));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
