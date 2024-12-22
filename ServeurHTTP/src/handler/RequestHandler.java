package handler;

import java.io.*;
import java.net.Socket;
import java.util.*;

import server.ServerConfig;
import util.LogManager;

public class RequestHandler {
    private LogManager logger;
    private static String WEB_ROOT;
    private FileHandler fileHandler;
    private QueryParser queryParser;

    public RequestHandler(ServerConfig config, LogManager logger) {
        this.logger = logger;
        RequestHandler.WEB_ROOT = config.getWebRoot();
        this.fileHandler = new FileHandler(WEB_ROOT, logger);
        new PHPExecutor();
        new ResponseSender();
        this.queryParser = new QueryParser();
    }

    public void handleRequest(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStream out = clientSocket.getOutputStream()) {

            String requestLine = in.readLine();
            if (requestLine == null || requestLine.isEmpty())
                return;

            logger.log("Requete recue: " + requestLine);

            // Lire les en-tetes
            String header;
            while (!(header = in.readLine()).isEmpty()) {
                logger.log(header);
            }

            String[] tokens = requestLine.split(" ");
            String method = tokens[0];
            String requestedPath = tokens[1];
            String requestedFile = requestedPath.split("\\?")[0]; // Ignore les parametres GET dans le chemin

            Map<String, String> getVariables = queryParser.parseQueryParams(requestedPath);
            Map<String, String> postVariables = new HashMap<>();

            // Gerer GET et POST
            if (method.equals("GET")) {
                getVariables = queryParser.parseQueryParams(requestedPath); // Seules les parametres GET
                logger.log("Parametres GET: " + getVariables);
            } else if (method.equals("POST")) {
                postVariables = queryParser.parsePostParams(in); // Seules les parametres POST
                logger.log("Parametres POST: " + postVariables);
            } else {
                ResponseSender.sendResponse(out, 405, "Method Not Allowed",
                        "Only GET and POST methods are supported.".getBytes(),
                        "text/plain");
                return;
            }

            fileHandler.serveFile(out, requestedFile, getVariables, postVariables);
        } catch (IOException e) {
            logger.log("Erreur du client: " + e.getMessage());
        }
    }
}
