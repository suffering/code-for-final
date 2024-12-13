import java.io.*;
import java.net.*;

public class ClientImplementation {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Server's IP address (localhost in this case)
        int serverPort = 12345;            // Server's port number

        try (Socket socket = new Socket(serverAddress, serverPort);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to the server at " + serverAddress + ":" + serverPort);
            System.out.println("Type a message to send to the server (type 'exit' to quit):");

            String userMessage;
            while (true) {
                System.out.print("Your message: ");
                userMessage = userInput.readLine(); // Read input from the user
                
                if ("exit".equalsIgnoreCase(userMessage)) {
                    System.out.println("Closing connection...");
                    break;
                }

                // Send the message to the server
                out.println(userMessage);

                // Receive and display the server's response
                String serverResponse = in.readLine();
                System.out.println("Server response: " + serverResponse);
            }

        } catch (IOException e) {
            System.err.println("Error communicating with the server: " + e.getMessage());
        }
    }
}

