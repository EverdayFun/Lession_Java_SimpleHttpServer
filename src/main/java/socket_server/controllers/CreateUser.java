package socket_server.controllers;

import socket_server.http.HttpRequest;
import socket_server.http.HttpResponse;
import socket_server.http.IHttpHandler;
import socket_server.services.UserService;

import org.json.JSONObject;

public class CreateUser implements IHttpHandler {

    @Override
    public void handle(HttpRequest req, HttpResponse resp) throws Exception {

        JSONObject json = new JSONObject(req.body);
        String name = json.getString("name");

        UserService.addUser(name);

        resp.responseJson(req.body);
    }
}