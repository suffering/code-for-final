public class ThreadPractices {

    // Shared resource for wait/notify example
    private static final Object lock = new Object();
    private static boolean taskCompleted = false; // Flag to indicate task completion

    public static void main(String[] args) {
        // Create the producer thread for wait/notify
        Thread producer = new Thread(() -> {
            synchronized (lock) { // Synchronize on the shared lock object
                try {
                    System.out.println("Producer: Producing task...");
                    Thread.sleep(1000); // Simulate task by sleeping for 1 second
                    taskCompleted = true; // Mark task as completed
                    lock.notify(); // Notify the waiting thread (consumer)
                    System.out.println("Producer: Task completed and notified.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupt status
                }
            }
        });

        // Create the consumer thread for wait/notify
        Thread consumer = new Thread(() -> {
            synchronized (lock) { // Synchronize on the shared lock object
                while (!taskCompleted) { // Loop to avoid spurious wake-ups
                    try {
                        System.out.println("Consumer: Waiting for task to complete...");
                        lock.wait(); // Wait for notification from producer
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Restore interrupt status
                    }
                }
                System.out.println("Consumer: Task consumed.");
            }
        });

        // Start both threads for wait/notify example
        consumer.start(); // Start consumer first so it waits
        producer.start(); // Start producer to complete the task

        // Create the first task thread for join example
        Thread task1 = new Thread(() -> {
            try {
                System.out.println("Task 1: Started");
                Thread.sleep(1500); // Simulate a task by sleeping for 1.5 seconds
                System.out.println("Task 1: Finished");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status
            }
        });

        // Create the second task thread for join example
        Thread task2 = new Thread(() -> {
            try {
                task1.join(); // Wait for task1 to complete before starting
                System.out.println("Task 2: Started after Task 1");
                Thread.sleep(1000); // Simulate a task by sleeping for 1 second
                System.out.println("Task 2: Finished");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status
            }
        });

        // Start threads for join example
        task1.start(); // Start the first task
        task2.start(); // Start the second task, but it will wait for task1 to complete

        // Main thread waits for all threads to complete
        try {
            consumer.join(); // Wait for consumer thread to finish
            producer.join(); // Wait for producer thread to finish
            task1.join();    // Wait for task1 thread to finish
            task2.join();    // Wait for task2 thread to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
        }

        // Main thread resumes after all threads are done
        System.out.println("Main thread: All tasks completed.");
    }
}
