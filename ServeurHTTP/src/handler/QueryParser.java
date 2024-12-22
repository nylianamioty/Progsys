package handler;

import java.io.*;
import java.util.*;

public class QueryParser {
    public Map<String, String> parseQueryParams(String path) {
        // Logique pour parser les paramètres GET
        Map<String, String> queryParams = new HashMap<>();
        if (path.contains("?")) {
            String[] parts = path.split("\\?");
            if (parts.length > 1) {
                String[] params = parts[1].split("&");
                for (String param : params) {
                    String[] keyValue = param.split("=");
                    if (keyValue.length == 2) {
                        queryParams.put(keyValue[0], keyValue[1]);
                    }
                }
            }
        }
        return queryParams;
    }

    public Map<String, String> parsePostParams(BufferedReader in) throws IOException {
        // Logique pour parser les paramètres POST
        StringBuilder body = new StringBuilder();
        while (in.ready()) {
            body.append((char) in.read());
        }
        System.out.println(body.toString());
        Map<String, String> postParams = new HashMap<>();
        String boy = body.toString();
        String[] params = boy.split("&");
        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2) {
                postParams.put(keyValue[0], keyValue[1]);
            }
        }
        return postParams;
    }
}
