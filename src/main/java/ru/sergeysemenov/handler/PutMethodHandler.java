package ru.sergeysemenov.handler;

import ru.sergeysemenov.ResponseSerializer;
import ru.sergeysemenov.domain.HttpRequest;
import ru.sergeysemenov.domain.HttpResponse;
import ru.sergeysemenov.services.SocketService;

@Handler(method = "PUT", order = 3)
public class PutMethodHandler extends MethodHandlerImpl{

    public PutMethodHandler(MethodHandlerImpl next, SocketService socketService, ResponseSerializer responseSerializer) {
        super("PUT", next, socketService, responseSerializer);
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        return HttpResponse.createBuilder()
                .withStatusCode(200)
                .withStatusCodeName("Ok")
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody("<h1>PUT method handled</h1>")
                .build();
    }

}
