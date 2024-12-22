package gui;

import server.*;
import util.*;
import javax.swing.*;

public class SimpleHTTPServerApp {
    private ServerConfigManager configManager;
    private ServerUIManager uiManager;
    private HttpServer httpServer;
    private LogManager logManager;
    private BrowserLauncher browserLauncher;

    public SimpleHTTPServerApp() {
        try {
            configManager = new ServerConfigManager();
            uiManager = new ServerUIManager(this);
            uiManager.createAndShowGUI();
            logManager = new LogManager(uiManager.getLogArea());
            ServerConfig serverConfig = configManager.getServerConfig();
            httpServer = new HttpServer(serverConfig, logManager);
            browserLauncher = new BrowserLauncher(serverConfig.getPort(), logManager);
            
        } catch (Exception e) {
            handleInitializationError(e);
        }
    }

    public void startServer() {
        try {
            httpServer.start();
            uiManager.updateServerStatus(true);
            uiManager.updateServerURL(NetworkUtils.getLocalIPv4Address(), 
                                      configManager.getServerConfig().getPort());
        } catch (Exception e) {
            uiManager.showErrorMessage("Impossible de demarrer le serveur", e);
        }
    }

    public void stopServer() {
        httpServer.stop();
        uiManager.updateServerStatus(false);
    }

    public void openBrowser() {
        browserLauncher.openBrowser();
    }

    public void copyServerURL() {
        try {
            String localIPv4 = NetworkUtils.getLocalIPv4Address();
            int port = configManager.getServerConfig().getPort();
            String url = String.format("http://%s:%d", localIPv4, port);
            NetworkUtils.copyToClipboard(url);
            uiManager.showInfoMessage("URL copiee : " + url);
        } catch (Exception e) {
            uiManager.showErrorMessage("Erreur lors de la copie de l'URL", e);
        }
    }

    public void updateServerConfiguration(ServerConfig newConfig) {
        try {
            configManager.updateConfiguration(newConfig);
            if (httpServer.isRunning()) {
                stopServer();
                startServer();
            }
        } catch (Exception e) {
            uiManager.showErrorMessage("Erreur de configuration", e);
        }
    }

    private void handleInitializationError(Exception e) {
        System.err.println("Erreur d'initialisation : " + e.getMessage());
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, 
            "Erreur d'initialisation : " + e.getMessage(), 
            "Erreur Critique", 
            JOptionPane.ERROR_MESSAGE);
    }

    public ServerConfigManager getConfigManager() {
        return configManager;
    }


}
