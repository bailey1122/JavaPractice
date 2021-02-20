package multithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SyncCollections {
    public static void main(String[] args) throws Exception {
        NameList nameList = new NameList();
        nameList.add("first");
        class St extends Thread {
            @Override
            public void run() {
                System.out.println(nameList.removeFirst());
            }
        }
        St synchThr = new St();
        synchThr.setName("one");
        synchThr.start();
        new St().start();

    }

    static class NameList {
        private List list = Collections.synchronizedList(new ArrayList<>());

        public synchronized void add(String name) {
            list.add(name);
        }

        public synchronized String removeFirst() {
            if (list.size() > 0) {
                if (Thread.currentThread().getName().equals("one")) {
                    Thread.yield();
                }
                return (String) list.remove(0);
            }
            return null;
        }
    }
}
