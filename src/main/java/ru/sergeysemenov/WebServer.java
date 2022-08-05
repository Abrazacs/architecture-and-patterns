package ru.sergeysemenov;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WebServer {
    private final static int PORT = 8091;
    private static String WWW = "C:\\Users\\serge\\IdeaProjects\\architecture-and-patterns\\WWW";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("Server started");
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                new Thread(new ReuqestHandler(socket, WWW)).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
