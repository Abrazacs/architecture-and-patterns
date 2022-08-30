package ru.sergeysemenov;

import ru.sergeysemenov.config.ServerConfig;
import ru.sergeysemenov.config.ServerConfigFactory;
import ru.sergeysemenov.handler.MethodHandlerFactory;
import ru.sergeysemenov.services.FileService;
import ru.sergeysemenov.services.SocketService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;


public class WebServer {

    public static void main(String[] args) throws ClassNotFoundException {
        ServerConfig config = ServerConfigFactory.create(args);
        try (ServerSocket serverSocket = new ServerSocket(config.getPort())){
            System.out.println("Server started");

            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                SocketService socketService = new SocketService(socket);

                new Thread(new ReuqestHandler(
                        socketService,
                        new RequestParser(),
                        MethodHandlerFactory.createAnnotated(socketService,
                                new ResponseSerializer(),
                                new FileService(config.getWww()))
                )).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
