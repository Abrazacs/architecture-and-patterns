package ru.sergeysemenov.handler;

import ru.sergeysemenov.ResponseSerializer;
import ru.sergeysemenov.domain.HttpRequest;
import ru.sergeysemenov.domain.HttpResponse;
import ru.sergeysemenov.services.SocketService;

@Handler(method = "POST", order = 1)
public class PostMethodHandler extends MethodHandlerImpl {

    public PostMethodHandler(MethodHandlerImpl next, SocketService socketService, ResponseSerializer responseSerializer) {
        super("POST", next, socketService, responseSerializer);
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        return HttpResponse.createBuilder()
                .withStatusCode(200)
                .withStatusCodeName("Ok")
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody("<h1>POST method handled</h1>")
                .build();
    }
}
