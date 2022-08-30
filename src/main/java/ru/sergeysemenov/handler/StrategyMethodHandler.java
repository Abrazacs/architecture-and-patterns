package ru.sergeysemenov.handler;

import ru.sergeysemenov.ResponseSerializer;
import ru.sergeysemenov.domain.HttpRequest;
import ru.sergeysemenov.domain.HttpResponse;
import ru.sergeysemenov.services.SocketService;

import java.util.function.Function;

public class StrategyMethodHandler extends MethodHandlerImpl{

    private final Function<HttpRequest, HttpResponse> strategy;

    public StrategyMethodHandler(Function<HttpRequest, HttpResponse> strategy, String method, MethodHandlerImpl next, SocketService socketService, ResponseSerializer responseSerializer) {
        super(method, next, socketService, responseSerializer);
        this.strategy = strategy;
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        return strategy.apply(request);
    }
}
