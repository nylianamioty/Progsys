package util;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogManager {
    private JTextArea logArea;
    private static final String LOG_DIRECTORY = "../log";
    private static final String LOG_FILE = "server_logs.txt";

    public LogManager(JTextArea logArea) {
        this.logArea = logArea;
    }

    public void log(String message) {
        // Log to GUI
        SwingUtilities.invokeLater(() -> {
            logArea.append(message + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });

        // Log to file
        logToFile(message);
    }

    private void logToFile(String message) {
        try {
            if (shouldLog(message)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timestamp = sdf.format(new Date());
                String logMessage = timestamp + " - " + message;

                File logDir = new File(LOG_DIRECTORY);
                if (!logDir.exists()) {
                    logDir.mkdir();
                }

                File logFile = new File(logDir, LOG_FILE);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
                    writer.write(logMessage);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    private boolean shouldLog(String message) {
        return message.contains("Demarrage") ||
               message.contains("Arret") ||
               message.contains("Host") ||
               message.contains("Requete") ||
               message.contains("Referer") ||
               message.contains("Parametres") ||
               message.contains("User-Agent");
    }
}