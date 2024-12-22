package gui;

import javax.swing.*;
import java.awt.*;
import util.Theme;

public class ServerControlPanel extends JPanel {
    private SimpleHTTPServerApp application;
    private JButton startButton, stopButton, openBrowserButton, copyUrlButton;
    private JLabel serverStatusLabel, serverUrlLabel;

    public ServerControlPanel(SimpleHTTPServerApp app) {
        this.application = app;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        setBackground(Theme.BACKGROUND);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Statut et URL du serveur
        serverStatusLabel = createStatusLabel("Serveur : Arrete", Color.RED);
        serverUrlLabel = createStatusLabel("URL : Non disponible", Theme.ACCENT_COLOR);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(serverStatusLabel, gbc);
        add(serverUrlLabel, gbc);

        // Boutons de contrôle
        startButton = createStyledButton("Demarrer", "../resources/start_icon.png", 
            new Color(0, 200, 0), Color.WHITE, e -> application.startServer());
        
        stopButton = createStyledButton("Arreter", "../resources/stop_icon.jpg", 
            new Color(200, 0, 0), Color.WHITE, e -> application.stopServer());
        stopButton.setEnabled(false);

        openBrowserButton = createStyledButton("Ouvrir Navigateur", "../resources/browser_icon.png", 
            Theme.ACCENT_COLOR, Color.WHITE, e -> application.openBrowser());
        openBrowserButton.setEnabled(false);

        copyUrlButton = createStyledButton("Copier URL", "../resources/copy_icon.png", 
            new Color(100, 100, 200), Color.WHITE, e -> application.copyServerURL());
        copyUrlButton.setEnabled(false);

        // Disposition des boutons
        gbc.gridwidth = 2;
        add(startButton, gbc);
        add(stopButton, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(openBrowserButton, gbc);
        add(copyUrlButton, gbc);
    }

    private JButton createStyledButton(String text, String iconPath, Color bgColor, 
                                       Color fgColor, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setIcon(new ImageIcon(
            new ImageIcon(iconPath)
                .getImage()
                .getScaledInstance(25, 25, Image.SCALE_SMOOTH)
        ));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.addActionListener(action);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(250, 40));
        button.setFocusPainted(false);
        return button;
    }

    private JLabel createStatusLabel(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        return label;
    }

    public void updateServerStatus(boolean running) {
        startButton.setEnabled(!running);
        stopButton.setEnabled(running);
        openBrowserButton.setEnabled(running);
        copyUrlButton.setEnabled(running);

        serverStatusLabel.setText(running ? "Serveur : En fonctionnement" : "Serveur : Arrete");
        serverStatusLabel.setForeground(running ? Color.GREEN : Color.RED);
    }

    public void updateServerURL(String url) {
        serverUrlLabel.setText("URL : " + url);
        serverUrlLabel.setForeground(Color.GREEN);
    }
}