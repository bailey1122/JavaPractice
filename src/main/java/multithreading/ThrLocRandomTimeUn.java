package multithreading;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ThrLocRandomTimeUn {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Math.random());
        System.out.println(ThreadLocalRandom.current().nextInt()); // use it instead of above

        Thread.sleep(TimeUnit.DAYS.toMillis(14));
    }
}
