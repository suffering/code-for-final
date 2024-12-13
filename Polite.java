class Polite implements Runnable {
    private static final Object lock = new Object(); // Shared lock for synchronization
    private static boolean isFirstThreadTurn = true; // Flag to track turns
    private final String message; // Message to print
    private final boolean myTurn; // Indicates if this thread goes first

    // Constructor to initialize the message and thread turn
    public Polite(String message, boolean myTurn) {
        this.message = message;
        this.myTurn = myTurn;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                // Wait until it's this thread's turn
                while (isFirstThreadTurn != myTurn) {
                    try {
                        lock.wait(); // Wait for notification from the other thread
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Restore interrupt status
                        return;
                    }
                }
                // Print the message
                System.out.println(message);
                // Toggle the turn flag and notify the other thread
                isFirstThreadTurn = !isFirstThreadTurn;
                lock.notifyAll(); // Wake up the waiting thread
            }
        }
    }

    public static void main(String[] args) {
        // Create two threads with alternating turns
        Thread thread1 = new Thread(new Polite("Hello", true));
        Thread thread2 = new Thread(new Polite("World", false));

        // Start the threads
        thread1.start();
        thread2.start();
    }
}
