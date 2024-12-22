package server;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.ini4j.Ini;

public class ServerConfig {
    private int port;
    private String webRoot;
    private int backlog;
    private InetAddress hostname;
    private static final String DEFAULT_CONFIG_PATH = "../conf/server_config.ini";

    public ServerConfig() throws UnknownHostException {
        this(DEFAULT_CONFIG_PATH);
    }

    public ServerConfig(String configPath) throws UnknownHostException {
        loadConfig(configPath);
    }

    private void loadConfig(String configPath) throws UnknownHostException {
        try {
            File configFile = new File(configPath);
            Ini ini = new Ini(configFile);

            this.port = Integer.parseInt(ini.get("server", "port"));
            this.backlog = Integer.parseInt(ini.get("server", "backlog"));
            this.hostname = InetAddress.getByName(ini.get("server", "hostname"));
            this.webRoot = ini.get("server", "web_root");
        } catch (IOException e) {
            // Fallback to default values if config file can't be read
            port = 9009;
            webRoot = "../www";
            backlog = 50;
            hostname = InetAddress.getByName("0.0.0.0");
            System.err.println("Could not read config file: " + e.getMessage());
        }
    }

    public void saveConfig() {
        try {
            Ini ini = new Ini(new File(DEFAULT_CONFIG_PATH));
            ini.put("server", "port", String.valueOf(port));
            ini.put("server", "backlog", String.valueOf(backlog));
            ini.put("server", "hostname", hostname.getHostAddress());
            ini.put("server", "web_root", webRoot);

            ini.store();
        } catch (IOException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
        }
    }

    // Getters and Setters
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebRoot() {
        return webRoot;
    }

    public void setWebRoot(String webRoot) {
        this.webRoot = webRoot;
    }

    public int getBacklog() {
        return backlog;
    }

    public void setBacklog(int backlog) {
        this.backlog = backlog;
    }

    public InetAddress getHostname() {
        return hostname;
    }

    public void setHostname(InetAddress hostname) {
        this.hostname = hostname;
    }
}
