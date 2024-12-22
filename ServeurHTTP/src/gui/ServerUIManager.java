package gui;

import javax.swing.*;
import java.awt.*;
import util.Theme;

public class ServerUIManager {
    private SimpleHTTPServerApp application;
    private JFrame frame;
    private JTabbedPane tabbedPane;

    private ServerControlPanel controlPanel;
    private ServerConfigPanel configPanel;
    private JTextArea logArea;



    public ServerUIManager(SimpleHTTPServerApp app) {
        this.application = app;
        Theme.CyberpunkTheme();
        initializeComponents();
    }

    private void initializeComponents() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createAndShowGUI() {
        frame = new JFrame("Serveur HTTP Simple");
        frame.setUndecorated(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        // frame.setIconImage(new ImageIcon(getClass().getResource("resources/icon.png")).getImage());

        // Initialisation des panneaux
        controlPanel = new ServerControlPanel(application);
        configPanel = new ServerConfigPanel(application);
        
        // Initialisation du log
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        logArea.setBackground(Theme.SECONDARY);
        logArea.setForeground(Theme.TEXT_COLOR);

        // Configuration des onglets
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Theme.BACKGROUND);
        tabbedPane.setForeground(Theme.TEXT_COLOR);

        JScrollPane scrollPane = new JScrollPane(logArea);

        tabbedPane.addTab("Controles", getScaledIcon("../resources/controls_icon.png"), controlPanel);
        tabbedPane.addTab("Logs", getScaledIcon("../resources/log_icon.png"), scrollPane);
        tabbedPane.addTab("Parametres", getScaledIcon("../resources/settings_icon.png"), configPanel);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private ImageIcon getScaledIcon(String path) {
        try {
            return new ImageIcon(
                new ImageIcon(path)
                    .getImage()
                    .getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            return null;
        }
    }

    public void updateServerStatus(boolean running) {
        controlPanel.updateServerStatus(running);
    }

    public void updateServerURL(String host, int port) {
        String url = String.format("http://%s:%d", host, port);
        controlPanel.updateServerURL(url);
    }

    public void showErrorMessage(String title, Exception e) {
        JOptionPane.showMessageDialog(frame,
            e.getMessage(), 
            title, 
            JOptionPane.ERROR_MESSAGE);
    }

    public void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(frame, 
            message, 
            "Information", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    public JTextArea getLogArea() {
        return logArea;
    }
}