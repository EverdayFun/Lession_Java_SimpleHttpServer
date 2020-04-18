package socket_server.http;

import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    public String method = "";
    public String url = "";
    public String body = "";
    public Map<String, String> headers = new HashMap<String, String>();

    private InputStream is = null;

    public void close() throws Exception {
        if (this.is != null) {
            this.is.close();
        }
    }

    public HttpRequest(Socket socket) throws Exception {
        this(socket.getInputStream());
    }

    public HttpRequest(InputStream is) throws Exception {
        this.is = is;

        Thread.sleep(100);

        int size = is.available();
        byte[] buf = new byte[size];
        is.read(buf);
        String data = new String(buf, 0, size, "UTF-8");

        String[] lines = data.split("\r\n");

        boolean headerFinished = false;
        for (int i = 0; i < lines.length; i++) {

            if (i == 0 && "".equals(lines[i])) {
                i--;
                continue;
            }

            if (i == 0) {
                // 第一行是请求行
                String[] parts = lines[i].split(" ");
                this.method = parts[0];
                this.url = parts[1];
                continue;
            }

            if ("".equals(lines[i])) {
                headerFinished = true;
                continue;
            }

            if (!headerFinished) {
                String[] parts = lines[i].split(": ");
                if (parts.length == 2) {
                    this.headers.put(parts[0], parts[1]);
                }
                continue;
            }

            this.body += lines[i];
        }
    }
}