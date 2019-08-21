package multithreading;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExServ {
    public static void main(String[] args) {
        ScheduledExecutorService schexserv = Executors.newSingleThreadScheduledExecutor();
        schexserv.schedule(new ScThread(), 3, TimeUnit.SECONDS);
        schexserv.shutdown();
    }

    static class ScThread extends Thread {
        @Override
        public void run() {
            System.out.println(1);
        }
    }
}
