package handler;

import java.io.*;

public class ResponseSender {
    public static void sendResponse(OutputStream out, int statusCode, String statusMessage, byte[] content, String contentType) throws IOException {
        String headers = "HTTP/1.1 " + statusCode + " " + statusMessage + "\r\n" +
                "Content-Type: " + contentType + "\r\n" +
                "Content-Length: " + content.length + "\r\n" +
                "\r\n";
        out.write(headers.getBytes());
        out.write(content);
    }
}
