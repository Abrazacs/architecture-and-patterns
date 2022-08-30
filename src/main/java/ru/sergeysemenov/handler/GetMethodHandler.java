package ru.sergeysemenov.handler;

import ru.sergeysemenov.ResponseSerializer;
import ru.sergeysemenov.domain.HttpRequest;
import ru.sergeysemenov.domain.HttpResponse;
import ru.sergeysemenov.services.FileService;
import ru.sergeysemenov.services.SocketService;

@Handler(method = "GET", order = 0)
public class GetMethodHandler extends MethodHandlerImpl{

    private final FileService fileService;

    public GetMethodHandler(MethodHandlerImpl next, SocketService socketService, ResponseSerializer responseSerializer, FileService fileService) {
        super("GET", next, socketService, responseSerializer);
        this.fileService = fileService;
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        if (!fileService.exists(request.getUrl())) {
            return HttpResponse.createBuilder()
                    .withStatusCode(404)
                    .withStatusCodeName("NOT_FOUND")
                    .withHeader("Content-Type", "text/html; charset=utf-8")
                    .build();
        }

        return HttpResponse.createBuilder()
                .withStatusCode(200)
                .withStatusCodeName("OK")
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody(fileService.readFile(request.getUrl()))
                .build();
    }
}
