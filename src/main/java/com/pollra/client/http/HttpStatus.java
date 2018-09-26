package com.pollra.client.http;

public enum HttpStatus {
    // 1xx

    // 2xx
    OK("200 OK");
    // 3xx

    // 4xx

    private String statusMassage;
    // setter
    HttpStatus(String statusMassage) { this.statusMassage = statusMassage; }
    // getter
    public String getStatusMassage() { return statusMassage; }
}
