package com.mycompany.clientservercw;

import com.mycompany.clientservercw.config.ApiApplication;
import java.io.IOException;
import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

//Eduardo Lamasanu w2078922


public class ClientServerCW {

    private static final String BASE_URI = "http://localhost:8080/";

    public static HttpServer startServer() {
        return GrizzlyHttpServerFactory.createHttpServer(
                URI.create(BASE_URI),
                new ApiApplication()
        );
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = startServer();
        System.out.println("Smart Campus API running at: " + BASE_URI + "api/v1/");
        System.out.println("Press Enter to stop the server...");
        System.in.read();
        server.shutdownNow();
    }
}