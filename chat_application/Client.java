package chat_application;

import java.io.*;
import java.net.*;

public class Client {

	    private static final String SERVER_ADDRESS = "localhost";
	    private static final int SERVER_PORT = 11223;
	    
	    public static void main(String[] args) {
	        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
	            System.out.println("Connected to the chat server");
	            
	            // Start a new thread to listen for messages from the server
	            new ReadMessageThread(socket).start();
	            
	            // Main thread for sending messages to the server
	            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	            BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
	            
	            String message;
	            while ((message = keyboardInput.readLine()) != null) {
	                out.println(message);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    private static class ReadMessageThread extends Thread {
	        private Socket socket;
	        
	        public ReadMessageThread(Socket socket) {
	            this.socket = socket;
	        }
	        
	        @Override
	        public void run() {
	            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
	                String message;
	                while ((message = in.readLine()) != null) {
	                    System.out.println("Received: " + message);
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

