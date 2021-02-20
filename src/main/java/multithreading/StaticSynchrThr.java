package multithreading;

public class StaticSynchrThr {
    public static void main(String[] args) throws Exception {
        ResourceSt.i = 5;

        StatThr statThr = new StatThr();
        statThr.setName("one");
        StatThr statThr2 = new StatThr();

        statThr.start();
        statThr2.start();
        statThr.join();
        statThr2.join();
        System.out.println(ResourceSt.i + " : i");
    }
}

class StatThr extends Thread {
    ResourceSt resourceST;

    @Override
    public void run() {
        ResourceSt.changeStaticI(); // static
    }
}

class ResourceSt {
    static int i;

    public static void changeStaticI() {
        synchronized (ResourceSt.class) {
            int i = ResourceSt.i;
            if (Thread.currentThread().getName().equals("one")) {
                Thread.yield();
            }
            i++;
            ResourceSt.i = i;
        }
    }
}