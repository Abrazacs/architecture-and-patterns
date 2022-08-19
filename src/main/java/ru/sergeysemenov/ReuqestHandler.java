package ru.sergeysemenov;

import ru.sergeysemenov.domain.HttpRequest;
import ru.sergeysemenov.domain.HttpResponse;
import ru.sergeysemenov.services.FileService;
import ru.sergeysemenov.services.SocketService;

import java.io.IOException;
import java.util.Deque;


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

        if (!fileService.exists(req.getUrl())) {
            HttpResponse resp = new HttpResponse();
            resp.setStatusCode(404);
            resp.setStatusCodeName("NOT_FOUND");
            resp.getHeaders().put("Content-Type", "text/html; charset=utf-8");
            socketService.writeResponse(responseSerializer.serialize(resp));
            return;
        }

        HttpResponse resp = new HttpResponse();
        resp.setStatusCode(200);
        resp.setStatusCodeName("OK");
        resp.getHeaders().put("Content-Type", "text/html; charset=utf-8");
        resp.setBody(fileService.readFile(req.getUrl()));
        socketService.writeResponse(responseSerializer.serialize(resp));

        try {
            socketService.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        System.out.println("Client disconnected!");
    }

}
