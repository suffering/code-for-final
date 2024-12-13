package unit13;

import java.util.*;

public class Incrementor {
    private int count;

    public int getCount() {
        return count;
    }

    public synchronized void increment() {
        // synchronized(this) {
            count++;
        // };
    }

    public static void main(String[] args) {
        int numberOfIncrements = 100000;

        List<Thread> threads = new ArrayList<>();

        Incrementor counter = new Incrementor();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(
                // Implementing a new runnable as a lambda
                () -> {
                    for (int n = 0; n < numberOfIncrements; n++) {
                        counter.increment();
                    }
                }
            );
            thread.start();
            threads.add(thread);
        }

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println(counter.getCount());
    }

}
