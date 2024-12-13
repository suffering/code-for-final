// Class extending Thread
class Printer extends Thread {
    private String text; // Field for the string

    // Constructor to initialize the string
    public Printer(String text) {
        this.text = text;
    }

    // Overriding the run() method
    @Override
    public void run() {
        // Print each character of the string on a separate line
        for (char c : text.toCharArray()) {
            System.out.println(c);
        }
    }

// Main class to run the thread
    public static void main(String[] args) {
        // Create a Printer thread with a string
        Printer printer = new Printer("Send Help!");
        
        // Start the thread
        printer.start();
    }
}
