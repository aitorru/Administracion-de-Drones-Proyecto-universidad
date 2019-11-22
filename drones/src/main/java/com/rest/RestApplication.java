package com.rest;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;


public class RestApplication {
    public void run(){
        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(8008), 0);
            server.createContext("/api", new Handler());
            server.createContext("/api/query/getDrones", new CommandHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
            System.out.println("Server running");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}