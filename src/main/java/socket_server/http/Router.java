package socket_server.http;

import java.util.HashMap;
import java.util.Map;

public class Router implements IHttpHandler {

    Map<String, IHttpHandler> handlers = new HashMap<>();

    @Override
    public void handle(HttpRequest req, HttpResponse resp) throws Exception {
        IHttpHandler h = this.handlers.get(req.method + req.url);
        if (h == null) {
            resp.responseNotFound();
            return;
        }

        h.handle(req, resp);
    }

    public void register(String method, String url, IHttpHandler handler) {
        this.handlers.put(method + url, handler);
    }
}