import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * Простой встроенный веб-сервер на базе стандартного JDK (com.sun.net.httpserver).
 * Не требует внешних библиотек (Spring, Maven и т.д.) и работает на любой Java.
 * Запускает локальный сайт магазина модов на http://localhost:8088
 */
public class WebServer {

    private static final int PORT = 8088;

    public static void main(String[] args) {
        startServer(true);
    }

    /**
     * Запуск локального сервера.
     * @param openBrowser открывать ли сайт в браузере автоматически
     */
    public static void startServer(boolean openBrowser) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

            // Обработчик отдачи главной страницы сайта
            server.createContext("/", new StaticFileHandler());
            server.setExecutor(null); // Использование стандартного однопоточного/пулового исполнителя
            server.start();

            String url = "http://localhost:" + PORT;
            System.out.println("===============================================================");
            System.out.println("    ЛОКАЛЬНЫЙ САЙТ ЗАПУЩЕН: MODCRAFT STUDIO WEB                ");
            System.out.println("===============================================================");
            System.out.println(" Адрес сайта: " + url);
            System.out.println(" Для остановки сервера нажмите Ctrl + C в консоли.");
            System.out.println("===============================================================");

            // Автоматическое открытие сайта в браузере
            if (openBrowser) {
                openWebpage(url);
            }

        } catch (IOException e) {
            System.out.println("[ОШИБКА]: Не удалось запустить веб-сервер на порту " + PORT + ": " + e.getMessage());
            System.out.println("Попробуйте открыть файл web/index.html напрямую в браузере.");
        }
    }

    /**
     * Обработчик отдачи файлов web/index.html и web/fifine-dashboard.html.
     */
    static class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            File file = resolveFile(path);

            if (file != null && file.exists()) {
                byte[] bytes = new byte[(int) file.length()];
                try (FileInputStream fis = new FileInputStream(file)) {
                    fis.read(bytes);
                }

                String contentType = "text/html; charset=UTF-8";
                if (file.getName().endsWith(".svg")) contentType = "image/svg+xml; charset=UTF-8";
                else if (file.getName().endsWith(".css")) contentType = "text/css; charset=UTF-8";
                else if (file.getName().endsWith(".js")) contentType = "application/javascript; charset=UTF-8";

                exchange.getResponseHeaders().set("Content-Type", contentType);
                exchange.sendResponseHeaders(200, bytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(bytes);
                }
            } else {
                String errorMsg = "<h1>404: Файл не найден!</h1><p><a href='/'>ModCraft Studio</a> | <a href='/fifine-dashboard.html'>FIFINE Dashboard</a></p>";
                byte[] errorBytes = errorMsg.getBytes(StandardCharsets.UTF_8);
                exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                exchange.sendResponseHeaders(404, errorBytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(errorBytes);
                }
            }
        }

        private File resolveFile(String requestPath) {
            String filename = "index.html";
            if (requestPath != null && (requestPath.contains("fifine") || requestPath.equals("/fifine"))) {
                filename = "fifine-dashboard.html";
            } else if (requestPath != null && requestPath.length() > 1) {
                filename = requestPath.startsWith("/") ? requestPath.substring(1) : requestPath;
            }

            String[] baseDirs = {
                "web",
                "../web",
                "C:/Users/nnome/Desktop/Abm-Prime/web",
                "C:/Users/nnome/IdeaProjects/Abm-Prime/web"
            };

            for (String base : baseDirs) {
                File f = new File(base, filename);
                if (f.exists()) {
                    return f;
                }
            }
            return new File("web", filename);
        }
    }

    /**
     * Открытие ссылки в браузере по умолчанию.
     */
    public static void openWebpage(String url) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                // Запасной запуск для Windows через команду explorer / start
                Runtime.getRuntime().exec(new String[]{"rundll32", "url.dll,FileProtocolHandler", url});
            }
        } catch (Exception e) {
            System.out.println("[ИНФО]: Откройте в браузере ссылку вручную: " + url);
        }
    }
}
