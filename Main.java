class Incrementor {
    private int count = 0;

    // Increment the shared counter
    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Incrementor incrementor = new Incrementor();

        // Create 10 threads, each incrementing the counter 100,000 times
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    incrementor.increment();
                }
            });
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print the final count
        System.out.println("Final count: " + incrementor.getCount());
    }
}






  




