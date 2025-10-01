package org.gowtham.server;

import com.sun.net.httpserver.HttpServer;
import org.gowtham.handler.Studenthandler;
import org.gowtham.repository.StudentRepository;

import java.io.IOException;
import java.net.InetSocketAddress;

public class StudentServer {
    public  static void main(String[] args) throws IOException{
        HttpServer server = HttpServer.create(new InetSocketAddress(8080),0);

        StudentRepository repository = new StudentRepository();
        server.createContext("/student",new  Studenthandler(repository));

        server.setExecutor(null);
        server.start();
        System.out.println("Server started at http://localhost:8080");
    }


}
