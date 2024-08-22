package chat_application;

import java.io.*;
import java.net.*;
import java.util.*;

public class Chat {

	 private static final int PORT = 11233;
	    private static Set<Socket> clientSockets = new HashSet<>();
	    
	    public static void main(String[] args) {
	        System.out.println("Chat server started...");
	        
	        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
	            while (true) {
	                Socket clientSocket = serverSocket.accept();
	                clientSockets.add(clientSocket);
	                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());
	                
	                // Start a new thread to handle client communication
	                new ClientHandler(clientSocket).start();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    private static class ClientHandler extends Thread {
	        private Socket socket;
	        private BufferedReader in;
	        private PrintWriter out;
	        
	        public ClientHandler(Socket socket) {
	            this.socket = socket;
	        }
	        
	        @Override
	        public void run() {
	            try {
	                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	                out = new PrintWriter(socket.getOutputStream(), true);
	                
	                String message;
	                while ((message = in.readLine()) != null) {
	                    System.out.println("Received: " + message);
	                    broadcastMessage(message);
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            } finally {
	                try {
	                    socket.close();
	                    clientSockets.remove(socket);
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        
	        private void broadcastMessage(String message) {
	            for (Socket clientSocket : clientSockets) {
	                if (clientSocket != socket) {
	                    try {
	                        PrintWriter clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
	                        clientOut.println(message);
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }
	    }
	}