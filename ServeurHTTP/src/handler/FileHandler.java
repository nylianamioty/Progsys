package handler;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;

import util.LogManager;

public class FileHandler {
    private static String webRoot;
    private PHPExecutor phpExecutor;

    public FileHandler(String webRoot, LogManager logger) {
        FileHandler.webRoot = webRoot;
        this.phpExecutor = new PHPExecutor();
        new ResponseSender();
    }

    public void serveFile(OutputStream out, String requestedPath, Map<String, String> getVariables,
            Map<String, String> postVariables) throws IOException {
        String fullPath = webRoot + requestedPath;

        if (requestedPath.equals("/") || requestedPath.endsWith("/")) {
            // mijery raha misy index
            File indexHtml = new File(fullPath + "index.html");
            File indexPhp = new File(fullPath + "index.php");

            if (indexHtml.exists()) {
                sendFileResponse(out, indexHtml);
            } else if (indexPhp.exists()) {
                phpExecutor.executePHP(out, indexPhp, getVariables, postVariables);
            } else {
                // Liste les fichiers du repertoire
                serveDirectoryListing(out, fullPath);
            }
            return;
        }

        File file = new File(fullPath);
        if (!file.exists() || file.isDirectory()) {
            ResponseSender.sendResponse(out, 404, "Not Found",
                    ("<html><body><h1>404 Not Found.</h1><h4>The requested file does not exist.</h4></body></html>")
                            .getBytes(),
                    "text/html");
            return;
        }

        if (requestedPath.endsWith(".php")) {
            phpExecutor.executePHP(out, file, getVariables, postVariables);
        } else {
            sendFileResponse(out, file);
        }
    }

    // fonction lister
    private void serveDirectoryListing(OutputStream out, String directoryPath) throws IOException {
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            ResponseSender.sendResponse(out, 404, "Not Found",
                    ("<html><body><h1>404 Not Found.</h1><h4>The requested file does not exist.</h4></body></html>")
                            .getBytes(),
                    "text/html");
            return;
        }

        StringBuilder html = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<html lang=\"en\">")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<title>Directory Listing</title>")
                .append("<style>")
                .append("body { ")
                .append("    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; ")
                .append("    background-color: #f0f2f5; ")
                .append("    margin: 0; ")
                .append("    padding: 20px; ")
                .append("    color: #333; ")
                .append("}")
                .append(".directory-container { ")
                .append("    max-width: 900px; ")
                .append("    margin: 0 auto; ")
                .append("    background-color: white; ")
                .append("    border-radius: 8px; ")
                .append("    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); ")
                .append("    padding: 20px; ")
                .append("}")
                .append("h1 { ")
                .append("    color: #2c3e50; ")
                .append("    text-align: center; ")
                .append("    border-bottom: 2px solid #3498db; ")
                .append("    padding-bottom: 10px; ")
                .append("}")
                .append(".file-list { ")
                .append("    display: grid; ")
                .append("    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); ")
                .append("    gap: 15px; ")
                .append("    list-style-type: none; ")
                .append("    padding: 0; ")
                .append("}")
                .append(".file-item { ")
                .append("    display: flex; ")
                .append("    align-items: center; ")
                .append("    background-color: #f8f9fa; ")
                .append("    border-radius: 6px; ")
                .append("    padding: 10px; ")
                .append("    transition: all 0.3s ease; ")
                .append("}")
                .append(".file-item:hover { ")
                .append("    background-color: #e9ecef; ")
                .append("    transform: scale(1.03); ")
                .append("    box-shadow: 0 4px 8px rgba(0,0,0,0.1); ")
                .append("}")
                .append(".file-icon { ")
                .append("    margin-right: 10px; ")
                .append("    font-size: 24px; ")
                .append("}")
                .append(".file-details { ")
                .append("    flex-grow: 1; ")
                .append("}")
                .append(".file-name { ")
                .append("    font-weight: 600; ")
                .append("    color: #2980b9; ")
                .append("}")
                .append(".file-info { ")
                .append("    font-size: 0.8em; ")
                .append("    color: #7f8c8d; ")
                .append("}")
                .append("a { ")
                .append("    text-decoration: none; ")
                .append("    color: inherit; ")
                .append("    display: flex; ")
                .append("    align-items: center; ")
                .append("}")
                .append("</style>")
                .append("</head>")
                .append("<body>")
                .append("<div class=\"directory-container\">")
                .append("<h1>&#128193 Directory Listing: " + directory.getName() + "</h1>")
                .append("<ul class=\"file-list\">");

        File[] files = directory.listFiles();
        if (files != null) {
            // Sort files: directories first, then alphabetically
            Arrays.sort(files, (a, b) -> {
                if (a.isDirectory() && !b.isDirectory())
                    return -1;
                if (!a.isDirectory() && b.isDirectory())
                    return 1;
                return a.getName().compareToIgnoreCase(b.getName());
            });

            for (File file : files) {
                String name = file.getName();
                String href = (file.isDirectory() ? name + "/" : name);

                // Choose appropriate icon
                String icon = file.isDirectory()
                        ? "&#128193" // Folder icon
                        : getFileTypeIcon(name);

                String sizeInfo = file.isDirectory()
                        ? "Folder"
                        : humanReadableByteCount(file.length());

                String dateInfo = new SimpleDateFormat("MM/dd/yyyy HH:mm")
                        .format(new Date(file.lastModified()));

                html.append("<li class=\"file-item\">")
                        .append("<a href=\"").append(href).append("\">")
                        .append("<span class=\"file-icon\">").append(icon).append("</span>")
                        .append("<div class=\"file-details\">")
                        .append("<div class=\"file-name\">").append(name)
                        .append(file.isDirectory() ? "/" : "").append("</div>")
                        .append("<div class=\"file-info\">")
                        .append("<span>Size: ").append(sizeInfo).append("</span>")
                        .append("<span> | Last modified: ").append(dateInfo).append("</span>")
                        .append("</div>")
                        .append("</div>")
                        .append("</a>")
                        .append("</li>");
            }
        }

        html.append("</ul>")
                .append("</div>")
                .append("</body>")
                .append("</html>");

        ResponseSender.sendResponse(out, 200, "OK", html.toString().getBytes(), "text/html");
    }

    private static String getFileTypeIcon(String fileName) {
        fileName = fileName.toLowerCase();

        // Image files
        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") ||
                fileName.endsWith(".png") || fileName.endsWith(".gif"))
            return "&#128444;";

        // Document files
        if (fileName.endsWith(".pdf"))
            return "&#128195;";
        if (fileName.endsWith(".doc") || fileName.endsWith(".docx"))
            return "&#128221;";
        if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx"))
            return "&#128202;";

        // Code files
        if (fileName.endsWith(".java") || fileName.endsWith(".py") ||
                fileName.endsWith(".js") || fileName.endsWith(".cpp"))
            return "&#128187;";

        // Compressed files
        if (fileName.endsWith(".zip") || fileName.endsWith(".rar") ||
                fileName.endsWith(".7z"))
            return "&#128193;";

        // Music files
        if (fileName.endsWith(".mp3") || fileName.endsWith(".wav") ||
                fileName.endsWith(".flac"))
            return "&#127925;";

        // Video files
        if (fileName.endsWith(".mp4") || fileName.endsWith(".avi") ||
                fileName.endsWith(".mkv"))
            return "&#127916;";

        // Default file icon
        return "&#128441;";
    }

    private static String humanReadableByteCount(long bytes) {
        int unit = 1024;
        if (bytes < unit)
            return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = "KMGTPE".charAt(exp - 1) + "";
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    // envoyer la reponse au client
    private void sendFileResponse(OutputStream out, File file) throws IOException {
        String contentType = getContentType(file.getName());
        // String contentType = Files.probeContentType(file.toPath());

        byte[] fileContent = Files.readAllBytes(file.toPath());
        ResponseSender.sendResponse(out, 200, "OK", fileContent, contentType);
    }

    private static String getContentType(String filePath) {
        if (filePath.endsWith(".html"))
            return "text/html";
        if (filePath.endsWith(".css"))
            return "text/css";
        if (filePath.endsWith(".js"))
            return "application/javascript";
        if (filePath.endsWith(".png"))
            return "image/png";
        if (filePath.endsWith(".jpg") || filePath.endsWith(".jpeg"))
            return "image/jpeg";
        if (filePath.endsWith(".gif"))
            return "image/gif";
        if (filePath.endsWith(".mp4"))
            return "video/*";
        return "application/octet-stream";
    }
}
