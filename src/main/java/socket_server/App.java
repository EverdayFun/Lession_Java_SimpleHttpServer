/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package socket_server;

import java.net.ServerSocket;
import java.net.Socket;

import socket_server.controllers.CreateUser;
import socket_server.controllers.GetUsers;
import socket_server.http.*;

public class App {

    public static void threadServe(Socket socket) {
        new Thread() {
            @Override
            public void run() {

                try {
                    HttpRequest req = new HttpRequest(socket);
                    HttpResponse resp = new HttpResponse(socket);

                    System.out.println("[" + req.method + "] " + req.url + " start");
                    App.handleRequst(req, resp);
                    System.out.println("[" + req.method + "] " + req.url + " end");

                    req.close();
                    resp.close();
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    public static void handleRequst(HttpRequest req, HttpResponse resp) throws Exception {
        Router r = new Router();
        r.register("GET", "/users", new GetUsers());
        r.register("POST", "/users", new CreateUser());
        r.handle(req, resp);
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;

        ServerSocket server = new ServerSocket(port);
        System.out.println("---------\n服务启动: @" + port);

        try {
            while (true) {
                final Socket socket = server.accept();
                threadServe(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.close();
        }

    }
}
