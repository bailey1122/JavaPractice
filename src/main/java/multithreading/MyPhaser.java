package multithreading;

import java.util.concurrent.Phaser;

public class MyPhaser {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(2);
        new Washer(phaser);
        new Washer(phaser);

    }

    static class Washer extends Thread {
        Phaser phaser;

        public Washer(Phaser phaser) {
//            phaser.register();
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
//            phaser.arriveAndDeregister();
            for (int i = 0; i < 3; i++) {
                System.out.println(getName() + " wash a car");
                phaser.arriveAndAwaitAdvance();
            }
        }
    }
}
