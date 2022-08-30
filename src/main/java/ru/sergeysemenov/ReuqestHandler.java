package ru.sergeysemenov;

import ru.sergeysemenov.domain.HttpRequest;
import ru.sergeysemenov.handler.MethodHandler;
import ru.sergeysemenov.services.SocketService;

import java.io.IOException;
import java.util.Deque;


public class ReuqestHandler implements Runnable {

    private final SocketService socketService;
    private final RequestParser requestParser;
    private final MethodHandler methodHandler;


    public ReuqestHandler(SocketService socketService,
                          RequestParser requestParser,
                          MethodHandler methodHandler) {
        this.socketService = socketService;
        this.requestParser = requestParser;
        this.methodHandler = methodHandler;
    }

    @Override
    public void run() {
        Deque<String> rawRequest = socketService.readRequest();
        HttpRequest req = requestParser.parse(rawRequest);

        methodHandler.handle(req);

        try {
            socketService.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        System.out.println("Client disconnected!");
    }

}
