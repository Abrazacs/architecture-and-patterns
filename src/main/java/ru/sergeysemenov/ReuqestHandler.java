package ru.sergeysemenov;

import ru.sergeysemenov.domain.HttpRequest;
import ru.sergeysemenov.domain.HttpResponse;
import ru.sergeysemenov.services.FileService;
import ru.sergeysemenov.services.SocketService;

import java.io.IOException;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;


public class ReuqestHandler implements Runnable {

    private final SocketService socketService;
    private final FileService fileService;
    private final RequestParser requestParser;
    private final ResponseSerializer responseSerializer;


    public ReuqestHandler(SocketService socketService,
                          FileService fileService,
                          RequestParser requestParser,
                          ResponseSerializer responseSerializer) {
        this.socketService = socketService;
        this.fileService = fileService;
        this.requestParser = requestParser;
        this.responseSerializer = responseSerializer;
    }

    @Override
    public void run() {
        Deque<String> rawRequest = socketService.readRequest();
        HttpRequest req = requestParser.parse(rawRequest);

        Map<String, String> headersForResponse = new HashMap<>();
        headersForResponse.put("Content-Type", "text/html; charset=utf-8");

        if (!fileService.exists(req.getUrl())) {
            socketService.writeResponse(responseSerializer.serialize(
                    HttpResponse.createBuilder()
                    .withStatusCode(404)
                    .withStatusCodeName("NOT_FOUND")
                    .withHeaders(headersForResponse)
                    .build()));
            return;
        }

        socketService.writeResponse(responseSerializer.serialize(
                HttpResponse.createBuilder()
                        .withStatusCode(200)
                        .withStatusCodeName("OK")
                        .withHeaders(headersForResponse)
                        .withBody(fileService.readFile(req.getUrl()))
                        .build()));

        try {
            socketService.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        System.out.println("Client disconnected!");
    }

}
