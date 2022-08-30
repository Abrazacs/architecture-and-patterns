package ru.sergeysemenov.handler;

import ru.sergeysemenov.ResponseSerializer;
import ru.sergeysemenov.domain.HttpRequest;
import ru.sergeysemenov.domain.HttpResponse;
import ru.sergeysemenov.services.SocketService;

abstract class MethodHandlerImpl implements MethodHandler {

    private final String method;
    private final MethodHandlerImpl next;
    protected final SocketService socketService;
    protected final ResponseSerializer responseSerializer;
    // убрал отсюда ServerConfig, т.к. он нигде не нужен.


    public MethodHandlerImpl(String method, MethodHandlerImpl next, SocketService socketService, ResponseSerializer responseSerializer) {
        this.method = method;
        this.next = next;
        this.socketService = socketService;
        this.responseSerializer = responseSerializer;
    }

    @Override
    public void handle(HttpRequest request) {
        HttpResponse response;
        if (method.equals(request.getMethod())) {
            response = handleInternal(request);
        } else if (next != null) {
            next.handle(request);
            return;
        } else {
            response = HttpResponse.createBuilder()
                    .withStatusCode(405)
                    .withStatusCodeName("METHOD_NOT_ALLOWED")
                    .withHeader("Content-Type", "text/html; charset=utf-8")
                    .withBody("<h1>Метод не поддерживается!</h1>")
                    .build();
        }
        String rawResponse = responseSerializer.serialize(response);
        socketService.writeResponse(rawResponse);
    }

    protected abstract HttpResponse handleInternal(HttpRequest request);
}
