package socket_server.http;

import java.io.OutputStream;
import java.net.Socket;

public class HttpResponse {
    private OutputStream os = null;

    public void close() throws Exception {
        if (this.os != null) {
            this.os.close();
        }
    }

    public HttpResponse(Socket socket) throws Exception {
        this(socket.getOutputStream());
    }

    public HttpResponse(OutputStream os) {
        this.os = os;
    }

    private void response(int statusCode, String statusText, String contentType, String content) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 " + statusCode + " " + statusText + "\n");
        sb.append("Content-Type: " + contentType + "\n");
        sb.append("\n");
        sb.append(content);
        this.os.write(sb.toString().getBytes());
        this.os.flush();
    }

    public void responseJson(String content) throws Exception {
        this.response(200, "OK", "application/json", content);
    }

    public void responseHTML(String content) throws Exception {
        this.response(200, "OK", "text/html", content);
    }

    public void responseInternalError() throws Exception {
        this.response(500, "InternalError", "text/html", "500 InternalError");
    }

    public void responseNotFound() throws Exception {
        this.response(404, "NotFound", "text/html", "你迷路了吗？");
    }
}