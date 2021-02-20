package multithreading;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

// queue is dangerously in multithreading
public class Blockqueue {
    public static void main(String[] args) throws InterruptedException {
//        Queue<String> queue = new PriorityQueue<>();
        BlockingQueue<String> queue = new PriorityBlockingQueue<>();

        new Thread() {
            @Override
            public void run() {
//                System.out.println(queue.remove());
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                queue.add("this is string");
            }
        };
    }
}
