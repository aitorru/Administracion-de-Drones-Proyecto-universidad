package com.rest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

import com.logger.AdminLogger;
import com.sun.net.httpserver.HttpServer;


public class RestApplication {
    private static final Logger LOGGE = Logger.getLogger(RestApplication.class.getName());
    private Logger LOGGER = new AdminLogger(LOGGE, "servidor.log").getLOGGER();
    public void run(){
        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(8008), 0);
            server.createContext("/api", new Handler());
            server.createContext("/api/query/getDrones", new CommandHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
            LOGGER.info("Server running");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}