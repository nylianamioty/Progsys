package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import handler.RequestHandler;
import util.*;;

public class HttpServer {
    private ServerConfig config;
    private LogManager logger;
    private boolean isRunning;
    private ServerSocket serverSocket;

    public HttpServer(ServerConfig config, LogManager logger) {
        this.config = config;
        this.logger = logger;
    }

    public void start() {
        if (isRunning)
            return;

        try {
            serverSocket = new ServerSocket(config.getPort(), config.getBacklog(), config.getHostname());
            isRunning = true;
            logger.log("Demarrage du serveur sur le port " + config.getPort() + "...");

            new Thread(() -> {
                while (isRunning) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        new Thread(() -> new RequestHandler(config, logger).handleRequest(clientSocket)).start();
                    } catch (IOException e) {
                        if (isRunning) {
                            logger.log("Erreur du serveur: " + e.getMessage());
                        }
                    }
                }
            }).start();

        } catch (IOException e) {
            logger.log("Impossible de démarrer le serveur : " + e.getMessage());
        }
    }

    public void stop() {
        isRunning = false;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            logger.log("Arret du serveur...");
        } catch (IOException e) {
            logger.log("Erreur lors de l'arret du serveur: " + e.getMessage());
        }
    }

    public boolean isRunning() {
        return isRunning;
    }   
}