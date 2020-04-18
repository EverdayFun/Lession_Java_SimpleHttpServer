package socket_server.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import socket_server.http.HttpRequest;
import socket_server.http.HttpResponse;
import socket_server.http.IHttpHandler;
import socket_server.services.UserService;

public class GetUsers implements IHttpHandler {

    @Override
    public void handle(HttpRequest req, HttpResponse resp) throws Exception {
        String time = new SimpleDateFormat().format(new Date());

        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Users</h1>");
        sb.append("<ul>");

        List<String> users = UserService.listUsers();
        for (String u : users) {
            sb.append("<li>" + u + "</li>");
        }

        sb.append("</ul>");

        sb.append("<p>" + time + "</p>");

        resp.responseHTML(sb.toString());
    }
}