package unit13.practicum;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SumServer {
    private class ClientHandler implements Runnable {
        private Duplexer duplexer;

        public ClientHandler(Socket socket) throws IOException {
            duplexer = new Duplexer(socket);
        }

        @Override
        public void run() {
            try {
                String msg = "";
                while (!msg.equals("0")) {
                    msg = duplexer.read();
                    System.out.println("Receved: " + msg);
                    synchronized (serverSocket) {
                        sum += Integer.parseInt(msg);
                        duplexer.send(String.valueOf(sum));
                        System.out.println("Sent: " + sum);
                    }
                }
                duplexer.close();
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        }
    }

    private ServerSocket serverSocket;
    private int sum = 0;

    public SumServer(int port) throws IOException {
        sum = 0;
        serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            ClientHandler handler = new ClientHandler(socket);
            new Thread(handler).start();
        }
    }

    public static void main(String[] args) throws IOException {
        new SumServer(9999);
    }
}
