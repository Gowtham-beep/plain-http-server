package org.gowtham.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.gowtham.repository.StudentRepository;

import java.io.IOException;
import java.net.URI;

public class Studenthandler implements HttpHandler {
    private final StudentRepository repository;
    private final ObjectMapper mapper= new ObjectMapper();

    public Studenthandler(StudentRepository repository){
        this.repository=repository;
    }

    @Override
    public void handler(HttpExchange exchange)throws IOException{
        String method = exchange.getRequestMethod();
        URI uri = exchange.getRequestURI();
        if("POST".equalsIgnoreCase(method)){
            handlePost(exchange);
        }else if("GET".equalsIgnoreCase(method)){
            handleGet(exchange,uri.getPath());
        }else if("PUT".equalsIgnoreCase(method)){
            handlePut(exchange);
        }else if("DELETE".equalsIgnoreCase(method)){
            handleDelete(exchange,uri.getPath());
        }else{
            exchange.sendResponseHeaders(405,-1);
        }
    }

}
