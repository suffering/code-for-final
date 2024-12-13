import java.io.*; // For input and output streams
import java.net.*; // For networking classes such as Socket

public class ClientImplementation {
    public static void main(String[] args) {
        // The server's IP address and port number
        String serverAddress = "127.0.0.1"; // Localhost IP address
        int serverPort = 12345;            // Port number the server is listening on

        // Try-with-resources block to automatically close resources after use
        try (Socket socket = new Socket(serverAddress, serverPort); // Establish connection to the server
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // To send messages to the server
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // To receive messages from the server
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) { // To read input from the user

            // Notify the user that the connection to the server was successful
            System.out.println("Connected to the server at " + serverAddress + ":" + serverPort);

            // Prompt the user to type messages to send to the server
            System.out.println("Type a message to send to the server (type 'exit' to quit):");

            String userMessage; // Variable to store the user's input

            // Infinite loop for user-server communication
            while (true) {
                System.out.print("Your message: "); // Prompt the user to type a message
                userMessage = userInput.readLine(); // Read the user's input

                // Check if the user wants to exit the chat
                if ("exit".equalsIgnoreCase(userMessage)) { // If the user types "exit" (case-insensitive)
                    System.out.println("Closing connection..."); // Notify the user that the connection is being closed
                    break; // Exit the loop
                }

                // Send the user's message to the server
                out.println(userMessage);

                // Wait for and display the server's response
                String serverResponse = in.readLine(); // Read the server's response
                System.out.println("Server response: " + serverResponse); // Print the server's response
            }

        } catch (IOException e) { // Handle any IO-related exceptions
            // Print an error message if there's an issue with communication
            System.err.println("Error communicating with the server: " + e.getMessage());
        }
    }
}
