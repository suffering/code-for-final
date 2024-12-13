import java.util.Random;

class Sleeper extends Thread {
    private int sleepTime;

    // Constructor to initialize with random sleep time
    public Sleeper() {
        Random random = new Random();
        this.sleepTime = random.nextInt(10) + 1; // Random time between 1 and 10 seconds
    }

    @Override
    public void run() {
        try {
            System.out.println("Sleeping for " + sleepTime + " seconds...");
            Thread.sleep(sleepTime * 1000); // Convert seconds to milliseconds
            System.out.println("I'm awake!");
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted!");
        }
    }

    public static void main(String[] args) {
        // Create an array to hold 5 Sleeper threads
        Sleeper[] sleepers = new Sleeper[5];
        
        // Start all 5 threads
        for (int i = 0; i < sleepers.length; i++) {
            sleepers[i] = new Sleeper();
            sleepers[i].start();
        }

        // Wait for all threads to finish
        for (Sleeper sleeper : sleepers) {
            try {
                sleeper.join(); // Wait for each thread to finish
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted!");
            }
        }

        // Print final message
        System.out.println("Everyone's awake!");
    }
}
