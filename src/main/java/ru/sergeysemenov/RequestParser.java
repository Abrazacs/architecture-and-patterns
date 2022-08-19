package ru.sergeysemenov;

import ru.sergeysemenov.domain.HttpRequest;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {

    public HttpRequest parse (Deque<String> rawRequest){

        String[] firstLine = rawRequest.pollFirst().split(" "); // получаем метод и url

        Map<String, String> headers = new HashMap<>(); // получаем все заголовки
        while (!rawRequest.isEmpty()){
            String line = rawRequest.pollFirst();
            if(line.isBlank()){
                break;
            }
            String[] header = line.split(": ");
            headers.put(header[0],header[1]);
        }

        StringBuilder sb = new StringBuilder(); // получаем тело запроса
        while (!rawRequest.isEmpty()){
            sb.append(rawRequest.pollFirst());
        }

        return HttpRequest.createBuilder()
                .withMethod(firstLine[0])
                .withUrl(firstLine[1])
                .withHeaders(headers)
                .withBody(sb.toString())
                .build();
    }
}
