package unit13.assignment1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TinyWebServer implements Runnable {
    private final Socket client;
    private final InputStream input;
    private final Scanner scanner;
    private final OutputStream output;

    public TinyWebServer(Socket client) throws IOException {
        this.client = client;
        this.input = client.getInputStream();
        this.scanner = new Scanner(input);
        this.output = client.getOutputStream();
    }

    @Override
    public void run() {
        try {
            
            System.out.println(scanner.nextLine());
            System.out.println(scanner.nextLine());
            System.out.println(scanner.nextLine());

            System.out.println("Here...");
            String response = "HTTP/1.1 200 OK\r\n" 
                + "Content-Length: 12\r\n"
                + "Content-Type: text/plain; charset=utf-8\r\n\r\n"
                + "Hello World!\r\n";
            // String response = "HTTP/1.1 404\r\n"
            //     + "Content-Length: 0\r\n\r\n";

            output.write(response.getBytes());
            output.flush();
        } catch(IOException ioe) {
            System.out.println("An error occurred!");
            ioe.printStackTrace();
        }
        close();
    }

    public void close() {
        try {
            input.close();
            output.close();
            scanner.close();
            client.close();
        } catch(IOException ioe) {
            // squash
        }
    }

    public static void main(String[] args) throws IOException {
        try(ServerSocket server = new ServerSocket(8080)) {
            while(true) {
                System.out.println("Listening for connections on port " 
                    + server.getLocalPort() + "...");
                Socket client = server.accept();
                System.out.println("Client connected: " 
                    + client.getInetAddress());
                TinyWebServer webServer = new TinyWebServer(client);
                Thread thread = new Thread(webServer);
                thread.start();
            }
        }
    }
    
}
