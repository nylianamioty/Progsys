package gui;

import server.ServerConfig;
import java.net.UnknownHostException;

public class ServerConfigManager {
    private ServerConfig serverConfig;
    private static final int MIN_PORT = 1;
    private static final int MAX_PORT = 65535;

    public ServerConfigManager() throws UnknownHostException {
        serverConfig = new ServerConfig();
    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    public void updateConfiguration(ServerConfig newConfig) throws ConfigurationException {
        validateConfiguration(newConfig);
        serverConfig = newConfig;
        serverConfig.saveConfig();
    }

    private void validateConfiguration(ServerConfig config) throws ConfigurationException {
        if (config.getPort() < MIN_PORT || config.getPort() > MAX_PORT) {
            throw new ConfigurationException("Port invalide. Doit etre entre " + MIN_PORT + " et " + MAX_PORT);
        }

        if (config.getWebRoot() == null || config.getWebRoot().trim().isEmpty()) {
            throw new ConfigurationException("Le repertoire racine web ne peut pas etre vide");
        }

        if (config.getBacklog() <= 0) {
            throw new ConfigurationException("Le backlog doit etre un nombre positif");
        }
    }
}
