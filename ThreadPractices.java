public class ThreadPractices {

    // Shared resource for wait/notify example
    private static final Object lock = new Object();
    private static boolean taskCompleted = false;

    public static void main(String[] args) {
        // Wait/Notify Example
        Thread producer = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Producer: Producing task...");
                    Thread.sleep(1000); // Simulate task
                    taskCompleted = true;
                    lock.notify(); // Notify waiting thread
                    System.out.println("Producer: Task completed and notified.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            synchronized (lock) {
                while (!taskCompleted) {
                    try {
                        System.out.println("Consumer: Waiting for task to complete...");
                        lock.wait(); // Wait for notification
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Consumer: Task consumed.");
            }
        });

        // Start both threads for wait/notify
        consumer.start();
        producer.start();

        // Join Example
        Thread task1 = new Thread(() -> {
            try {
                System.out.println("Task 1: Started");
                Thread.sleep(1500); // Simulate task
                System.out.println("Task 1: Finished");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread task2 = new Thread(() -> {
            try {
                task1.join(); // Wait for task1 to complete
                System.out.println("Task 2: Started after Task 1");
                Thread.sleep(1000); // Simulate task
                System.out.println("Task 2: Finished");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Start threads for join example
        task1.start();
        task2.start();

        // Ensure main thread waits for all threads to complete
        try {
            consumer.join();
            producer.join();
            task1.join();
            task2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Main thread: All tasks completed.");
    }
}

