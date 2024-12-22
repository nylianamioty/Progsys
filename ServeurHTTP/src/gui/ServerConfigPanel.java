package gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.net.InetAddress;
import server.ServerConfig;
import util.Theme;

public class ServerConfigPanel extends JPanel {
    private SimpleHTTPServerApp application;
    private JTextField portField, webRootField, backlogField, hostnameField;
    private JButton applyConfigButton;

    public ServerConfigPanel(SimpleHTTPServerApp app) {
        this.application = app;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        setBackground(Theme.BACKGROUND);
        setBorder(createPanelBorder());

        GridBagConstraints gbc = setupGridBagConstraints();

        ServerConfig currentConfig = application.getConfigManager().getServerConfig();
        addConfigFields(gbc, currentConfig);
        addApplyButton(gbc);
    }

    private Border createPanelBorder() {
        return new CompoundBorder(
                BorderFactory.createTitledBorder(
                        null,
                        "Configuration du Serveur",
                        TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION,
                        new Font("Segoe UI", Font.BOLD, 14),
                        Theme.TEXT_COLOR),
                new EmptyBorder(10, 10, 10, 10));
    }

    private GridBagConstraints setupGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }

    private void addConfigFields(GridBagConstraints gbc, ServerConfig currentConfig) {
        String[] labels = { "Port:", "Racine Web:", "Backlog:", "Hostname:" };
        JTextField[] fields = {
                portField = createTextField(String.valueOf(currentConfig.getPort())),
                webRootField = createTextField(currentConfig.getWebRoot()),
                backlogField = createTextField(String.valueOf(currentConfig.getBacklog())),
                hostnameField = createTextField(currentConfig.getHostname().getHostAddress())
        };

        String[] tooltips = {
                "Numero de port du serveur (1-65535)",
                "Repertoire racine pour les fichiers serveur",
                "Nombre maximum de connexions en attente",
                "Adresse d'ecoute du serveur"
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;

            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            label.setForeground(Theme.TEXT_COLOR);
            add(label, gbc);

            gbc.gridx = 1;
            fields[i].setToolTipText(tooltips[i]);
            add(fields[i], gbc);
        }
    }

    private JTextField createTextField(String value) {
        JTextField textField = new JTextField(value, 10);
        textField.setFont(new Font("Consolas", Font.PLAIN, 13));
        textField.setBackground(Theme.SECONDARY);
        textField.setForeground(Theme.TEXT_COLOR);
        textField.setCaretColor(Color.WHITE);
        return textField;
    }

    // Continuation de ServerConfigPanel.java
    private void addApplyButton(GridBagConstraints gbc) {
        applyConfigButton = new JButton("Appliquer");
        applyConfigButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        applyConfigButton.setBackground(Theme.ACCENT_COLOR);
        applyConfigButton.setForeground(Color.WHITE);
        applyConfigButton.addActionListener(e -> applyConfigChanges());

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(applyConfigButton, gbc);
    }

    private void applyConfigChanges() {
        try {
            int port = Integer.parseInt(portField.getText());
            String webRoot = webRootField.getText();
            int backlog = Integer.parseInt(backlogField.getText());
            InetAddress hostname = InetAddress.getByName(hostnameField.getText());

            ServerConfig newConfig = new ServerConfig();
            newConfig.setPort(port);
            newConfig.setWebRoot(webRoot);
            newConfig.setBacklog(backlog);
            newConfig.setHostname(hostname);

            application.updateServerConfiguration(newConfig);
            JOptionPane.showMessageDialog(this,
                    "Configuration modifier avec succes : ",
                    "Succes",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erreur lors de l'application des modifications : " + e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}