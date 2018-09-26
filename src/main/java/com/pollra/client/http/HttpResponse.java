package com.pollra.client.http;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private String httpProtocol;
    private HttpStatus status;
    private Map<String, String> responseHeader;
    private byte[] body;

    private HttpResponse() {  }

    public String getHttpProtocol() {
        return httpProtocol;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Map<String, String> getResponseHeader() {
        return responseHeader;
    }

    public byte[] getBody() {
        return body;
    }

    public static class ResponseBuilder{
        private String httpProtocol = "HTTP/1.1";
        private HttpStatus status;
        private Map<String, String> responseHeader = new HashMap<>();
        private byte[] body;

        public ResponseBuilder setProtocol(String httpProtocol){
            this.httpProtocol = httpProtocol;
            return this;
        }

        public ResponseBuilder setStatus(HttpStatus status){
            this.status = status;
            return this;
        }

        public ResponseBuilder addHeader(String key, String value){
            this.responseHeader.put(key, value);
            return this;
        }

        public ResponseBuilder setBody(byte[] body){
            this.body = body;
            return this;
        }

        public HttpResponse getInstanse(){
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.httpProtocol = httpProtocol;
            httpResponse.status = status;
            httpResponse.responseHeader = responseHeader;
            httpResponse.body = body;
            return httpResponse;
        }
    }
}
