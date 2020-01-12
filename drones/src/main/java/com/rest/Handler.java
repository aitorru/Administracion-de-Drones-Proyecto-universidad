package com.rest;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Handler implements HttpHandler {
    /**
     * <h1>Handler</h1> Sample
     * @param t peticion de http
     */
    @Override
    public void handle(HttpExchange t) throws IOException {
        String response = "Server is running!";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}