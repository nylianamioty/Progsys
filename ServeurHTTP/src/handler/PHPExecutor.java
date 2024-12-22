package handler;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class PHPExecutor {
    public void executePHP(OutputStream out, File file, Map<String, String> getVariables,
            Map<String, String> postVariables) throws IOException {
        StringBuilder getParameters = new StringBuilder();
        StringBuilder postParameters = new StringBuilder();

        // Construction des parametres GET et POST
        if (!getVariables.isEmpty()) {
            getVariables.forEach((key, value) -> {
                getParameters.append(URLEncoder.encode(key, StandardCharsets.UTF_8))
                        .append("=")
                        .append(URLEncoder.encode(value, StandardCharsets.UTF_8))
                        .append("&");
            });
            getParameters.setLength(getParameters.length() - 1); // Supprimer le dernier "&"
        }

        if (!postVariables.isEmpty()) {
            postVariables.forEach((key, value) -> {
                postParameters.append(URLEncoder.encode(key, StandardCharsets.UTF_8))
                        .append("=")
                        .append(URLEncoder.encode(value, StandardCharsets.UTF_8))
                        .append("&");
            });
            postParameters.setLength(postParameters.length() - 1); // Supprimer le dernier "&"
        }

        // Préparer la commande PHP avec parse_str() pour injecter les variables dans
        // $_GET et $_POST
        String phpCommand = "parse_str('" + getParameters.toString() + "', $_GET); " +
                "parse_str('" + postParameters.toString() + "', $_POST); " +
                "include('" + file.getAbsolutePath() + "');";

        // Lancer la commande PHP
        List<String> cmd = new ArrayList<>();
        cmd.add("php");
        cmd.add("-r");
        cmd.add(phpCommand); // Passer le code PHP directement à la commande PHP

        // Lancer le processus PHP
        ProcessBuilder pb = new ProcessBuilder(cmd);
        Process process = pb.start();

        // Lire la sortie du processus PHP
        try (InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isr)) {

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Envoyer la réponse au client
            ResponseSender.sendResponse(out, 200, "OK", output.toString().getBytes(), "text/html");
        }
    }
}
