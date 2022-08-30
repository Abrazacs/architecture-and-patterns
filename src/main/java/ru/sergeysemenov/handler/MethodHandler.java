package ru.sergeysemenov.handler;

import ru.sergeysemenov.domain.HttpRequest;

public interface MethodHandler {

    void handle(HttpRequest request);
}
