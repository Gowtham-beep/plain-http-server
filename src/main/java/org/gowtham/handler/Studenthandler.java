package org.gowtham.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.gowtham.entity.Student;
import org.gowtham.repository.StudentRepository;
import org.gowtham.request.CreateStudentRequest;
import org.gowtham.response.StudentResponse;

import java.io.IOException;
import java.io.OutputStream;
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
    private void handlePost(HttpExchange exchange)throws IOException{
        CreateStudentRequest request = mapper.readValue(exchange.getRequestBody(),CreateStudentRequest.class);

        Student student = new Student();
        student.setName(request.getName());
        student.setRollNo(request.getRollNo());

        Student saved= repository.save(student);

        StudentResponse response = new StudentResponse();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setRollNo(saved.getRollNo());

        String jsonResponse = mapper.writeValueAsString(response);
        exchange.getResponseHeaders().set("Content-Type","application/json");
        exchange.sendResponseHeaders(201,jsonResponse.getBytes().length);
        try(OutputStream os = exchange.getResponseBody()){
            os.write(jsonResponse.getBytes());
        }
    }


}
