package unit13;

public class PrintStringThread implements Runnable{
    private final String theString;

    public PrintStringThread(String inString) {
        this.theString = inString;
    }

    @Override
    public void run() {
        for (char c : this.theString.toCharArray()) {
            System.out.println(c);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Main thread starting");
        Runnable pst = new PrintStringThread("Hello world");
        Thread thread = new Thread(pst);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        thread = new Thread(pst);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Main thread ending");

    }

}
