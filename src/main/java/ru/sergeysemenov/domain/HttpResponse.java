package ru.sergeysemenov.domain;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    private int statusCode;

    private String statusCodeName;

    private Map<String, String> headers = new HashMap<>();

    private String body;

    public HttpResponse() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
    public String getBody() {
        return body;
    }

    public String getStatusCodeName() {
        return statusCodeName;
    }

    public static Builder createBuilder() {
        return new Builder();
    }

    public static class Builder{
        private final HttpResponse httpResponse;

        public Builder(){
            this.httpResponse = new HttpResponse();
        }

        public Builder withStatusCode(int statusCode){
            this.httpResponse.statusCode = statusCode;
            return this;
        }

        public Builder withStatusCodeName(String statusCodeName){
            this.httpResponse.statusCodeName = statusCodeName;
            return this;
        }

        public Builder withHeader(String key, String value){
            this.httpResponse.getHeaders().put(key, value);
            return this;
        }

        public Builder withBody(String body){
            this.httpResponse.body = body;
            return this;
        }

        public HttpResponse build(){
            return httpResponse;
        }

    }
}
