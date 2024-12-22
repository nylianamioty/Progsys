
import javax.swing.SwingUtilities;

import gui.SimpleHTTPServerApp;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleHTTPServerApp::new);
    }
}
