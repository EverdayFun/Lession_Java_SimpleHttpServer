package socket_server.http;

public interface IHttpHandler {
    void handle(HttpRequest req, HttpResponse resp) throws Exception;
}