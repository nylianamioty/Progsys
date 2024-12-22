package util;

import java.awt.*;
import java.net.URI;

public class BrowserLauncher {
    private int port;
    private LogManager logger;

    public BrowserLauncher(int port, LogManager logger) {
        this.port = port;
        this.logger = logger;
    }

    public void openBrowser() {
        try {
            Desktop.getDesktop().browse(new URI("http://localhost:" + port));
            logger.log("Ouverture du navigateur...");
        } catch (Exception e) {
            logger.log("Erreur lors de l'ouverture du navigateur: " + e.getMessage());
        }
    }
}