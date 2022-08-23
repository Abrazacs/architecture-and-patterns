package ru.sergeysemenov;


import ru.sergeysemenov.config.ServerConfig;
import ru.sergeysemenov.config.ServerConfigFactory;
import ru.sergeysemenov.services.FileService;
import ru.sergeysemenov.services.SocketService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public static void main(String[] args) {
        ServerConfig config = ServerConfigFactory.create(args);
        try (ServerSocket serverSocket = new ServerSocket(config.getPort())){
            System.out.println("Server started");
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                new Thread(new ReuqestHandler(
                        new SocketService(socket),
                        new FileService(config.getWww()),
                        new RequestParser(),
                        new ResponseSerializer())).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
