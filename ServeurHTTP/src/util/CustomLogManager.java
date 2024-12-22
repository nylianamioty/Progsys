package util;

import javax.swing.JTextArea;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomLogManager {
    private JTextArea logArea;
    private SimpleDateFormat dateFormat;

    public CustomLogManager(JTextArea logArea) {
        this.logArea = logArea;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public void log(String message) {
        String timestamp = dateFormat.format(new Date());
        logArea.append(String.format("[%s] %s\n", timestamp, message));
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    public void logError(String message, Throwable throwable) {
        String timestamp = dateFormat.format(new Date());
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        
        logArea.append(String.format("[%s] ERROR: %s\n%s\n", 
            timestamp, message, sw.toString()));
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }
}